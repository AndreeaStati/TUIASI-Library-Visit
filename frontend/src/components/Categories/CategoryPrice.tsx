import { useEffect, useState } from "react";
import { Box, Table, Text } from "@chakra-ui/react";
import CategoriesTableRow from "./CategoriesTableRow";

interface CategoryItem {
  id: number;
  categoryName: string;
  pricePerPerson: number;
  numberOfPersons: number;
}

const mockCategories: Omit<CategoryItem, "numberOfPersons">[] = [
  { id: 1, categoryName: "Adults", pricePerPerson: 10 },
  { id: 2, categoryName: "Tourist Group*", pricePerPerson: 15 },
  { id: 3, categoryName: "School children", pricePerPerson: 5 },
  { id: 4, categoryName: "Students", pricePerPerson: 5 },
  { id: 5, categoryName: "Pensioners", pricePerPerson: 5 },
  { id: 6, categoryName: "Free access**", pricePerPerson: 0 },
  { id: 7, categoryName: "Filming/photography**", pricePerPerson: 15 },
];

function CategoryPrice({
  onDataChange,
  onTotalPeopleChange,
  freePlaces = 0
}: {
  onDataChange: (data: CategoryItem[]) => void;
  onTotalPeopleChange: (data: number) => void;
  freePlaces?: number;
}) {
  const [categories, setCategories] = useState<CategoryItem[]>([]);

  useEffect(() => {
    const initialized = mockCategories.map((item) => ({
      ...item,
      numberOfPersons: 0,
    }));
    setCategories(initialized);
    onDataChange(initialized);
    onTotalPeopleChange(0);
  }, []);

  const handlePersonChange = (index: number, value: number) => {
    const updated = [...categories];
    updated[index].numberOfPersons = value;
    setCategories(updated);
    onDataChange(updated);
  };

  const totalPrice = categories.reduce(
    (sum, cat) => sum + cat.pricePerPerson * cat.numberOfPersons,
    0
  );

  let totalPeople = categories.reduce( 
    (sum, cat) => sum + cat.numberOfPersons,
    0
  );

  onTotalPeopleChange(totalPeople);
  return (
    <Box
      mt={4}
      borderRadius={"8px"}
      p={"10px"}
      alignSelf={"flex-start"}
      flexBasis={"25%"}
    >
      <Table.Root
        size="md"
        colorScheme="gray"
        width="100%"
        border="1px solid #ccc"
      >
        <Table.Header>
          <Table.Row>
            <Table.ColumnHeader>Category</Table.ColumnHeader>
            <Table.ColumnHeader>Price/pers</Table.ColumnHeader>
            <Table.ColumnHeader>Number of persons</Table.ColumnHeader>
          </Table.Row>
        </Table.Header>

        <Table.Body>
          {categories.map((cat, index) => {
            let available = 41
            if(cat.numberOfPersons == 0)
              available = 41 - totalPeople - freePlaces;
            else if(cat.numberOfPersons + 1 > 41 - totalPeople - freePlaces)
              available = cat.numberOfPersons + 1;
            else
              available = 41 - totalPeople - freePlaces + cat.numberOfPersons;
            return (<CategoriesTableRow
              key={cat.id}
              categoryName={cat.categoryName}
              pricePerPerson={cat.pricePerPerson}
              numberOfPersons={cat.numberOfPersons}
              onChange={(value) => handlePersonChange(index, value)}
              numberOfAvailablePlaces={available}
            />);
          })}
        </Table.Body>
      </Table.Root>

      <Text mt={4} fontSize="sm" fontStyle="italic" color="gray.500">
        *Travel agency group regardless of category visiting regulations
      </Text>
      <Text fontSize="sm" fontStyle="italic" color="gray.500">
        **Details in visiting regulations
      </Text>
      <Text mt={4} fontWeight="bold" fontSize="lg">
        Total Price: {totalPrice} LEI
      </Text>
    </Box>
  );
}

export default CategoryPrice;
