import { Button, Card, SimpleGrid, Text, Box, Flex, Input, Field, NativeSelect } from "@chakra-ui/react"
import { useState, useEffect } from "react";
import type { DateTime } from "../../types/calendar"
 
type CalendarProps = {
    month: number;
    year: number;
    highlightedDates?: DateTime[];
    blockedDates?: DateTime[];
    onDateTimeSelected: (data: DateTime) => void;
};

function Calendar({month, year, highlightedDates, blockedDates, onDateTimeSelected}:CalendarProps){
    function isDateHighlighted(date: number) {
        return highlightedDates?.some(
            (d) =>
            d.date.getDate() === date &&
            d.date.getMonth() === currentMonth &&
            d.date.getFullYear() === currentYear
        );
    }
    function handleSelectedDate(day: number){
        const dateStr1 = `${day}/${currentMonth + 1}/${currentYear}`;
        const dateStr = `${currentYear}-${String(currentMonth + 1).padStart(2, '0')}-${String(day).padStart(2, '0')}`
        setSelectedDate(dateStr);
        setSelectedDay(day);
    } 
    const hoursToCheck = [11,12,13,14];
    function isDateBlocked(date: number) {
        return blockedDates?.some(
            (d) =>
            d.date.getDate() === date &&
            d.date.getMonth() === currentMonth &&
            d.date.getFullYear() === currentYear &&
            (hoursToCheck.every(hour => d.hours.includes(hour)) ||
            d.hours.length === 0)
        ) || highlightedDates?.some(
            (d) =>
            d.date.getDate() === date &&
            d.date.getMonth() === currentMonth &&
            d.date.getFullYear() === currentYear &&
            hoursToCheck.every(hour => d.hours.includes(hour))
        );
    }
    function isHourBlocked(date: number, hour: number) {
        return blockedDates?.some(
            (d) =>
            d.date.getDate() === date &&
            d.date.getMonth() === currentMonth &&
            d.date.getFullYear() === currentYear &&
            d.hours.includes(hour)
        ) || highlightedDates?.some(
            (d) =>
            d.date.getDate() === date &&
            d.date.getMonth() === currentMonth &&
            d.date.getFullYear() === currentYear &&
            d.hours.includes(hour)
        );
    }
    function isDateEarlierThanToday(date: number){
        const today = new Date();
        if (currentYear < today.getFullYear()) return true;
        if (currentYear > today.getFullYear()) return false;

        if (currentYear === today.getFullYear()) {
            if (currentMonth < today.getMonth()) return true;
            if (currentMonth > today.getMonth()) return false;
            return date < today.getDate();
        }
        return false;
    }
    const [currentMonth, setCurrentMonth] = useState(month);
    const [currentYear, setCurrentYear] = useState(year);
    const [selectedDate, setSelectedDate] = useState("");
    const [selectedDay, setSelectedDay] = useState<number>(-1);
    const [selectedEndHour, setSelectedEndHour] = useState("");
    const [selectedStartHour, setSelectedStartHour] = useState("");
    const daysOfWeek = ["Lun", "Mar", "Mie", "Joi", "Vin", "Sâm", "Dum"];
    const monthNames = [
        "Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie",
        "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie"
    ];
    const getDaysInMonth = (year: number, month: number) => {
            return new Date(year, month + 1, 0).getDate(); 
        };
    const getStartDayOffset = (year: number, month: number) => {
    const day = new Date(year, month, 1).getDay();
                return (day + 6) % 7;
        };
    const daysInMonth = getDaysInMonth(currentYear, currentMonth);
    const startDayOffset = getStartDayOffset(currentYear, currentMonth);
    const dates = Array(startDayOffset).fill(null).concat(
    Array.from({ length: daysInMonth }, (_, i) => i + 1)
    );

    const thisMonth = new Date().getMonth();
    const thisYear  = new Date().getFullYear();


    useEffect(() => {
    if (
        selectedDay !== -1 &&
        selectedStartHour !== "" &&
        selectedEndHour !== "" &&
        selectedDate !== ""
    ) {
        const selectedDateObj = new Date(selectedDate);
        const startHour = 10 + parseInt(selectedStartHour);
        const endHour = 10 + parseInt(selectedEndHour);

        const selectedHours = [];
        for (let hour = startHour; hour < endHour; hour++) {
        selectedHours.push(hour);
        }

        onDateTimeSelected({
        date: selectedDateObj,
        hours: selectedHours,
        });
    }
    }, [selectedDay, selectedStartHour, selectedEndHour, selectedDate]);

    return (
        <Card.Root w="fit-content">
            <Card.Header>
                <Card.Title>
                    Selectați data la care doriți să faceți programarea.
                </Card.Title>
                <Card.Description>
                    <Text color="yellow.500">*Există deja rezervări în această zi, dar mai sunt locuri disponibile</Text>
                </Card.Description>
            </Card.Header>
            <Card.Body gap="10px">
                <Flex direction="row" justifyContent="space-between">
                    <Button onClick={() => {
                        if ((currentYear == thisYear) && (currentMonth - 1 < thisMonth))
                            return;
                        if (currentMonth === 0) {
                            setCurrentMonth(11)
                            setCurrentYear(currentYear - 1)
                        } else {
                            setCurrentMonth(currentMonth - 1)
                        }
                    }}
                    disabled={((currentYear == thisYear) && (currentMonth - 1 < thisMonth))}
                    >‹</Button>
                    <Text>{monthNames[currentMonth]} {currentYear}</Text>
                    <Button
                        onClick={() => {
                        if (currentMonth === 11) {
                            setCurrentMonth(0)
                            setCurrentYear(currentYear + 1)
                        } else {
                            setCurrentMonth(currentMonth + 1)
                        }
                        }}>›</Button>
                </Flex>
                <SimpleGrid columns={7} gap="10px">
                    {daysOfWeek.map((day) =>(
                        <Text key={day} textAlign="center" fontWeight="bold">
                            {day}
                        </Text>
                    ))}

                </SimpleGrid>
                <SimpleGrid columns={7} gap="10px">
                    {dates.map((date, index) =>
                        date ? (
                        <Button key={index} variant="outline" size="sm"
                        bgColor={ (isDateHighlighted(date) && !isDateBlocked) ? "yellow.500" : undefined}
                        disabled={(isDateBlocked(date) || isDateEarlierThanToday(date))}
                        onClick={() => handleSelectedDate(date)}>
                            {date}
                        </Button>
                        ) : (
                        <Box key={index} />
                        )
                    )}
                </SimpleGrid>
                <Field.Root>
                    <Field.Label>Data:</Field.Label>
                    <Input placeholder="DD/MM/YYYY" readOnly={true} value={selectedDate} textAlign="center" width="content-fit"/>
                </Field.Root>
                <Field.Root hidden={selectedDate===""}>
                    <Field.Label>
                        Selecteaza ora la care doresti sa incepi
                    </Field.Label>
                    <NativeSelect.Root>
                        <NativeSelect.Field onChange={val => {setSelectedStartHour(val.currentTarget.value);
                            setSelectedEndHour("");
                        }}
                        placeholder="Selecteaza ora">
                            <option value="1" disabled={isHourBlocked(selectedDay, 11)}>11:00</option>
                            <option value="2" disabled={isHourBlocked(selectedDay, 12)}>12:00</option>
                            <option value="3" disabled={isHourBlocked(selectedDay, 13)}>13:00</option>
                            <option value="4" disabled={isHourBlocked(selectedDay, 14)}>14:00</option>
                        </NativeSelect.Field>
                        <NativeSelect.Indicator/>
                    </NativeSelect.Root>
                </Field.Root>
                <Field.Root hidden={selectedDate===""}>
                    <Field.Label>
                        Selecteaza ora la care doresti sa termini.
                    </Field.Label>
                    <NativeSelect.Root>
                        <NativeSelect.Field value={selectedEndHour}
                            onChange={val => setSelectedEndHour(val.currentTarget.value)}
                            placeholder="Selecteaza ora">
                            <option value="1" disabled={parseInt(selectedStartHour) > 1 || isHourBlocked(selectedDay, 11)}>12:00</option>
                            <option value="2" disabled={parseInt(selectedStartHour) > 2 || isHourBlocked(selectedDay, 12)}>13:00</option>
                            <option value="3" disabled={parseInt(selectedStartHour) > 3 || isHourBlocked(selectedDay, 13)}>14:00</option>
                            <option value="4" disabled={parseInt(selectedStartHour) > 4 || isHourBlocked(selectedDay, 14)}>15:00</option>
                        </NativeSelect.Field>
                        <NativeSelect.Indicator/>
                    </NativeSelect.Root>
                </Field.Root>
            </Card.Body>
            <Card.Footer>
                <Button variant="outline">
                    
                </Button>
            </Card.Footer>
        </Card.Root>
    );
}

export default Calendar