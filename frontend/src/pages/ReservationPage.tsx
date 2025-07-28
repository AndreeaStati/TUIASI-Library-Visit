import { Box, Flex, Text, Button, Steps, ButtonGroup } from "@chakra-ui/react";
import { Tooltip } from "@/components/ui/tooltip";
import CategoryPrice from "../components/Categories/CategoryPrice";
import RegistrationForm from "../components/Form/RegistrationForm";
import { useRef, useState } from "react";
import { Header } from "@/components/Header";
import { StepsForm } from "@/components/StepsForm";
import { Calendar } from "@/components/Calendar";
import { Footer } from "@/components/Footer";
import { PaymentCard } from "@/components/PaymentCard";
import type { DateTime } from "@/types/calendar";
import type { FormData } from "@/types/formData";

interface CategoryItem {
  id: number;
  categoryName: string;
  pricePerPerson: number;
  numberOfPersons: number;
}

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

  const handleSubmit = () => {
    console.log({
      categories: categoryData,
      personal: formData,
      calendar: calendarData,
    });

    // TODO: Validare + trimitere POST
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
    if(!emailRegex.test(formData.email)){
      setStep(step);
      setShowEmailError(true);
    }
    else{
      setShowEmailError(false);
    }
    
  };

  const totalPrice = categoryData.reduce((acc, item) => {
    return acc + item.pricePerPerson * item.numberOfPersons;
  }, 0);

  return (
    <Flex direction="column" alignItems="center" >
        <Header optionalButton={true} title="UNIVERSITATEA TEHNICĂ „GHEORGHE ASACHI” DIN IAȘI"/>
        <Steps.Root step={step} defaultStep={0} count={3} width="fit" margin="10px" >
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
              <CategoryPrice onDataChange={setCategoryData} onTotalPeopleChange={setTotalPeople}/>
            </Steps.Content>
            <Steps.Content index={1}>
              <Calendar onDateTimeSelected={setCalendarData} month={6} year={2025}/>
            </Steps.Content>
            <Steps.Content index={2}>
              <RegistrationForm onDataChange={setFormData} formRef={formRef} showEmailError={showEmailError}/>
            </Steps.Content>
            <Steps.CompletedContent>
              <PaymentCard isVisible={totalPrice === 0}/>
            </Steps.CompletedContent>
          </Box>
          
      <ButtonGroup size="sm" variant="outline" justifyContent="flex-end">
        <Steps.PrevTrigger asChild>
          <Button onClick={()=>{
            setStep(step-1); 
            if(step === 2)
                handleNext();
              }}>Prev</Button>
        </Steps.PrevTrigger>
        <Steps.NextTrigger asChild>
            <Button hidden={step === 3}
             onClick={()=>{
              setStep(step+1);
              if(step === 2)
                handleNext();
            }} 
            disabled={(totalPeople === 0 && step === 0) || (calendarData === undefined && step === 1) || (!isFormValid && step === 2)}
            >Next</Button>
        </Steps.NextTrigger>
      </ButtonGroup>
        </Steps.Root>
      <Footer />
    </Flex>
  );
}

export default ReservationPage;
