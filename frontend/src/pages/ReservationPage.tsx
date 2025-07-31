import { Box, Flex, Text, Button, Steps, ButtonGroup } from "@chakra-ui/react";
import { Tooltip } from "@/components/ui/tooltip";
import CategoryPrice from "../components/Categories/CategoryPrice";
import RegistrationForm from "../components/Form/RegistrationForm";
import { useEffect, useRef, useState } from "react";
import { Header } from "@/components/Header";
import { StepsForm } from "@/components/StepsForm";
import { Calendar } from "@/components/Calendar";
import { Footer } from "@/components/Footer";
import { PaymentCard } from "@/components/PaymentCard";
import { submitReservation } from "../api/ReservationPageApi";
import type { BookingSummary, DateTime } from "@/types/calendar";
import type { FormData } from "@/types/formData";
import type { CategoryItem } from "@/types/categoryItem";
import axios from "axios";

function ReservationPage() {
  const [categoryData, setCategoryData] = useState<CategoryItem[]>([]);
  const [totalPeople, setTotalPeople] = useState<number>(0);
  const [formData, setFormData] = useState<FormData>({
    lastName: "",
    firstName: "",
    email: "",
    phone: "",
    institution: "",
    observations: "",
    language: "ro",
  });
  const [calendarData, setCalendarData] = useState<DateTime>();
  const [step, setStep] = useState<number>(0);
  const [blockedDates, setBlockedDates] = useState<DateTime[]>([]);

  function convertBlockedSlotsToDateTime(blockedSlots: any[]): DateTime[] {
    return blockedSlots.map((slot) => {
      const date = new Date(slot.slot_date);
      const startHour = parseInt(slot.start_time.split(":")[0]);
      const endHour = parseInt(slot.end_time.split(":")[0]);

      const hours = [];
      for (let h = startHour; h < endHour; h++) {
        hours.push(h);
      }

      const color = "gray"; // blocked-slots înseamnă indisponibil

      return { date, hours, color };
    });
  }

  function convertBookingSummariesToDateTime(
    summaries: BookingSummary[]
  ): DateTime[] {
    const slotsByDateHour = new Map<string, number>(); // cheie: date-hour, valoare: totalUsers

    for (const summary of summaries) {
      const { bookingDate, startTime, endTime, totalUsers } = summary;

      const startHour = parseInt(startTime.split(":")[0]);
      const endHour = parseInt(endTime.split(":")[0]);
      const [year, month, day] = bookingDate.split("-").map(Number);
      const date = new Date(year, month - 1, day);

      for (let hour = startHour; hour < endHour; hour++) {
        const key = `${bookingDate}-${hour}`;
        const existing = slotsByDateHour.get(key) || 0;
        slotsByDateHour.set(key, existing + totalUsers);
      }
    }

    const tempMap = new Map<
      string,
      { date: Date; hours: number[]; hasGray: boolean }
    >();

    for (const [key, total] of slotsByDateHour.entries()) {
      const [yearStr, monthStr, dayStr, hourStr] = key.split("-");
      const year = Number(yearStr);
      const month = Number(monthStr);
      const day = Number(dayStr);
      const hour = Number(hourStr);

      const dateKey = `${year}-${month}-${day}`;
      const date = new Date(year, month - 1, day);
      const isGray = total >= 40;

      if (!tempMap.has(dateKey)) {
        tempMap.set(dateKey, {
          date,
          hours: [hour],
          hasGray: isGray,
        });
      } else {
        const existing = tempMap.get(dateKey)!;
        if (!existing.hours.includes(hour)) existing.hours.push(hour);
        if (isGray) existing.hasGray = true;
      }
    }

    const dateTimeArray: DateTime[] = [];

    for (const { date, hours, hasGray } of tempMap.values()) {
      dateTimeArray.push({
        date,
        hours,
        color: hasGray ? "gray" : "yellow",
      });
    }

    return dateTimeArray;
  }

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res1 = await axios.get("http://localhost:8080/blocked-slots");
        const res2 = await axios.get(
          "http://localhost:8080/booking-details/summary/all"
        );

        const bookingSummaries = res2.data; // din backend
        const blockedSlots = res1.data; // din backend

        // Combină bookingSummaries și blockedSlots într-un singur array cu prioritate blocat
        const slotsByDateHour = new Map<string, number>();
        const blockedKeys = new Set<string>();

        // 1. Booking summaries
        for (const summary of bookingSummaries) {
          const { bookingDate, startTime, totalUsers } = summary;
          const startHour = parseInt(startTime.split(":")[0]);
          const key = `${bookingDate}-${startHour}`;

          const current = slotsByDateHour.get(key) || 0;
          slotsByDateHour.set(key, current + totalUsers);
        }

        // 2. Blocked slots
        for (const blocked of blockedSlots) {
          const { slot_date, start_time, end_time } = blocked;
          const startHour = parseInt(start_time.split(":")[0]);
          const endHour = parseInt(end_time.split(":")[0]);

          for (let hour = startHour; hour < endHour; hour++) {
            const key = `${slot_date}-${hour}`;
            blockedKeys.add(key);
          }
        }

        // 3. Construiește array-ul final
        const dateTimeArray: DateTime[] = [];

        const addToDateTimeArray = (
          key: string,
          color: "orange.400" | "yellow" | "gray"
        ) => {
          const [yearStr, monthStr, dayStr, hourStr] = key.split("-");
          const date = new Date(
            Number(yearStr),
            Number(monthStr) - 1,
            Number(dayStr)
          );
          const hour = Number(hourStr);

          const existing = dateTimeArray.find(
            (entry) =>
              entry.date.getFullYear() === date.getFullYear() &&
              entry.date.getMonth() === date.getMonth() &&
              entry.date.getDate() === date.getDate() &&
              entry.color === color
          );

          if (existing) {
            if (!existing.hours.includes(hour)) {
              existing.hours.push(hour);
            }
          } else {
            dateTimeArray.push({
              date,
              hours: [hour],
              color,
            });
          }
        };

        // 4. Adaugă sloturile blocate
        for (const key of blockedKeys) {
          addToDateTimeArray(key, "gray");
        }

        // 5. Adaugă booking-urile, doar dacă nu sunt deja blocate
        for (const [key, total] of slotsByDateHour.entries()) {
          if (blockedKeys.has(key)) continue;
          const color = total >= 40 ? "gray" : "yellow";
          addToDateTimeArray(key, color);
        }

        setBlockedDates(dateTimeArray);
      } catch (error) {
        console.error("Eroare la fetch:", error);
      }
    };

    fetchData();
  }, []);

  const handleSubmit = async () => {
    console.log({
      categories: categoryData,
      personal: formData,
      calendar: calendarData,
    });
    try {
      await submitReservation(categoryData, calendarData!, formData);
    } catch (error) {
      console.log(error);
    }
  };

  const requiredFields = ["lastName", "firstName", "email", "phone"] as const;
  function areRequiredFieldsFilled(data: FormData): boolean {
    return requiredFields.every((field) => data[field].trim() !== "");
  }

  const isFormValid = areRequiredFieldsFilled(formData);

  const formRef = useRef<HTMLFormElement>(null);

  const [showEmailError, setShowEmailError] = useState(false);

  const handleNext = () => {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    if (!emailRegex.test(formData.email)) {
      setStep(step);
      setShowEmailError(true);
    } else {
      setShowEmailError(false);
    }
  };

  const totalPrice = categoryData.reduce((acc, item) => {
    return acc + item.pricePerPerson * item.numberOfPersons;
  }, 0);

  return (
    <Flex direction="column" alignItems="center">
      <Header
        optionalButton={true}
        title="UNIVERSITATEA TEHNICĂ „GHEORGHE ASACHI” DIN IAȘI"
      />
      <Steps.Root
        step={step}
        defaultStep={0}
        count={3}
        width="fit"
        margin="10px"
      >
        <Steps.List>
          <Steps.Item index={0}>
            <Steps.Indicator />
            <Steps.Separator />
          </Steps.Item>
          <Steps.Item index={1}>
            <Steps.Indicator />
            <Steps.Separator />
          </Steps.Item>
          <Steps.Item index={2}>
            <Steps.Indicator />
            <Steps.Separator />
          </Steps.Item>
        </Steps.List>
        <Box display="flex" justifyContent="center" width="100%">
          <Steps.Content index={0} width="fit-content">
            <CategoryPrice
              onDataChange={setCategoryData}
              onTotalPeopleChange={setTotalPeople}
            />
          </Steps.Content>
          <Steps.Content index={1}>
            <Calendar
              onDateTimeSelected={setCalendarData}
              month={6}
              year={2025}
              blockedDates={blockedDates}
            />
          </Steps.Content>
          <Steps.Content index={2}>
            <RegistrationForm
              onDataChange={setFormData}
              formRef={formRef}
              showEmailError={showEmailError}
            />
          </Steps.Content>
          <Steps.CompletedContent>
            <PaymentCard isVisible={totalPrice === 0} />
            <Button onClick={handleSubmit}>Finalizeaza</Button>
          </Steps.CompletedContent>
        </Box>

        <ButtonGroup size="sm" variant="outline" justifyContent="flex-end">
          <Steps.PrevTrigger asChild>
            <Button
              onClick={() => {
                setStep(step - 1);
                if (step === 2) handleNext();
              }}
            >
              Prev
            </Button>
          </Steps.PrevTrigger>
          <Steps.NextTrigger asChild>
            <Button
              hidden={step === 3}
              onClick={() => {
                setStep(step + 1);
                if (step === 2) handleNext();
              }}
              disabled={
                (totalPeople === 0 && step === 0) ||
                (calendarData === undefined && step === 1) ||
                (!isFormValid && step === 2)
              }
            >
              Next
            </Button>
          </Steps.NextTrigger>
        </ButtonGroup>
      </Steps.Root>
      <Footer />
    </Flex>
  );
}

export default ReservationPage;
