import { Button, Card, Table } from "@chakra-ui/react";
import AdminCategoriesTableRow from "./AdminCategoriesTableRow";
import { useEffect, useState } from "react";
import axios from "axios";

interface CategoriesCardProps {
  id: number;
  categoryName: string;
  pricePerPerson: number;
}

function CategoriesCard() {
  const [categories, setCategories] = useState<CategoriesCardProps[]>([]);
  const [isAdding, setIsAdding] = useState(false);

  useEffect(() => {
    axios
      .get("http://localhost:8080/categories")
      .then((response) => {
        console.log("Response data from backend:", response.data);
        const parsed = response.data.map((category: any) => ({
          id: category.id,
          categoryName: category.category_name,
          pricePerPerson: category.price_per_person,
        }));
        setCategories(parsed);
      })
      .catch((error) => console.error("Eroare la fetch:", error));
  }, []);

  const handleAdd = (newCategory: {
    categoryName: string;
    pricePerPerson: number;
  }) => {
    const payload = {
      category_name: newCategory.categoryName,
      price_per_person: newCategory.pricePerPerson,
    };

    axios
      .post("http://localhost:8080/categories", payload)
      .then((res) => {
        const addedCategory = {
          ...res.data,
        };
        setCategories((prev) => [...prev, addedCategory]);
        setIsAdding(false);
      })
      .catch((err) => console.error("Eroare la adăugare:", err));
  };

  const handleUpdate = (id: number, updated: any) => {
    const updatedDto = {
      id,
      category_name: updated.categoryName,
      price_per_person: updated.pricePerPerson,
    };

    console.log("Payload update:", updatedDto);

    axios
      .put(`http://localhost:8080/categories/${id}`, updatedDto)
      .then((res) => {
        setCategories((prev) =>
          prev.map((category) =>
            category.id === id
              ? {
                  ...res.data,
                }
              : category
          )
        );
      })
      .catch((err) => console.error("Eroare la update:", err));
  };

  const handleDelete = (id: number) => {
    axios
      .delete(`http://localhost:8080/categories/${id}`)
      .then(() => {
        setCategories((prev) => prev.filter((category) => category.id !== id));
      })
      .catch((err) => console.error("Eroare la delete:", err));
  };

  return (
    <Card.Root colorPalette={"blue"} variant={"elevated"}>
      <Card.Header>
        <Card.Title mt="2">Categories</Card.Title>
        <Button
          size="sm"
          ml="auto"
          colorScheme="blue"
          onClick={() => setIsAdding(true)}
        >
          Add Category
        </Button>
      </Card.Header>

      <Card.Body gap="2">
        <Table.Root
          colorScheme="gray"
          interactive
          p="6"
          m="auto"
          variant={"outline"}
        >
          <Table.Header fontSize={"16px"}>
            <Table.Row>
              <Table.ColumnHeader>Category name</Table.ColumnHeader>
              <Table.ColumnHeader>Price/person</Table.ColumnHeader>
              <Table.ColumnHeader>Actions</Table.ColumnHeader>
            </Table.Row>
          </Table.Header>

          <Table.Body>
            {categories.map((category) => (
              <AdminCategoriesTableRow
                key={category.id}
                categoryName={category.categoryName}
                pricePerPerson={category.pricePerPerson}
                onUpdate={(updated) => handleUpdate(category.id, updated)}
                onDelete={() => handleDelete(category.id)}
              />
            ))}

            {isAdding && (
              <AdminCategoriesTableRow
                categoryName=""
                pricePerPerson={0}
                onUpdate={(newCategory) => handleAdd(newCategory)}
                onDelete={() => setIsAdding(false)}
                isNew
              />
            )}
          </Table.Body>
        </Table.Root>
      </Card.Body>
    </Card.Root>
  );
}

export default CategoriesCard;
