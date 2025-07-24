import { Box, Flex, Text, Button } from "@chakra-ui/react";
import CategoryPrice from "../components/Categories/CategoryPrice";
import RegistrationForm from "../components/Form/RegistrationForm";
import { useState } from "react";

interface CategoryItem {
  id: number;
  categoryName: string;
  pricePerPerson: number;
  numberOfPersons: number;
}

function ReservationPage() {
  const [categoryData, setCategoryData] = useState<CategoryItem[]>([]);
  const [formData, setFormData] = useState({});
  const [calendarData, setCalendarData] = useState({ date: "", timeSlot: "" });

  const handleSubmit = () => {
    console.log({
      categories: categoryData,
      personal: formData,
      calendar: calendarData,
    });

    // TODO: Validare + trimitere POST
  };

  return (
    <Flex direction="column" alignItems="center" pt={10} px={4} gap={8}>
      <Text fontSize="2xl" fontWeight="bold" color="#B87333" textAlign="center">
        Complete the necessary details to complete the reservation
      </Text>

      <Flex direction="column" gap={6} width="100%" maxW="800px">
        <CategoryPrice onDataChange={setCategoryData} />

        <Box
          background="tomato"
          padding="20px"
          color="white"
          borderRadius="md"
          boxShadow="md"
        >
          calendar
        </Box>

        <RegistrationForm onDataChange={setFormData} />
      </Flex>

      <Flex direction="column" alignItems="center" mt={6}>
        <Button colorScheme="orange" onClick={handleSubmit} disabled mt={4}>
          Trimite Rezervare
        </Button>
      </Flex>
    </Flex>
  );
}

export default ReservationPage;
