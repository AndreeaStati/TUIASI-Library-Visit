import { Button, Input, Menu, Portal, Table } from "@chakra-ui/react";
import { useState } from "react";

interface AdminCategoriesTableRowProps {
  categoryName: string;
  pricePerPerson: number;
  isNew?: boolean;
  onUpdate: (data: { categoryName: string; pricePerPerson: number }) => void;
  onDelete: () => void;
}

function AdminCategoriesTableRow({
  categoryName,
  pricePerPerson,
  onUpdate,
  onDelete,
  isNew,
}: AdminCategoriesTableRowProps) {
  const [isEditing, setIsEditing] = useState(isNew ?? false);
  const [editValues, setEditValues] = useState(() => ({
    categoryName,
    pricePerPerson,
  }));

  const handleChange = (field: string, value: string | number) => {
    setEditValues((prev) => ({
      ...prev,
      [field]:
        field === "pricePerPerson" ? parseFloat(value as string) || 0 : value,
    }));
  };

  const handleSave = () => {
    setIsEditing(false);
    onUpdate({
      categoryName: editValues.categoryName,
      pricePerPerson: Number(editValues.pricePerPerson),
    });
  };

  return (
    <Table.Row>
      <Table.Cell>
        {isEditing ? (
          <Input
            value={editValues.categoryName ?? ""}
            onChange={(e) => handleChange("categoryName", e.target.value)}
            size="sm"
            aria-label="Category Name"
          />
        ) : (
          categoryName
        )}
      </Table.Cell>

      <Table.Cell>
        {isEditing ? (
          <Input
            type="number"
            value={editValues.pricePerPerson ?? ""}
            onChange={(e) => handleChange("pricePerPerson", e.target.value)}
            size="sm"
            aria-label="Price/person"
          />
        ) : (
          pricePerPerson
        )}
      </Table.Cell>

      <Table.Cell>
        {isEditing ? (
          <>
            <Button size="sm" colorScheme="green" onClick={handleSave}>
              {isNew ? "Add" : "Save"}
            </Button>
            <Button
              size="sm"
              ml={2}
              colorScheme="gray"
              onClick={() => {
                if (isNew) {
                  onDelete(); // anulare adăugare
                } else {
                  setIsEditing(false);
                }
              }}
            >
              Cancel
            </Button>
          </>
        ) : (
          <Menu.Root>
            <Menu.Trigger asChild>
              <Button variant="outline" size="sm">
                Options
              </Button>
            </Menu.Trigger>

            <Portal>
              <Menu.Positioner>
                <Menu.Content>
                  <Menu.Item value="update" onSelect={() => setIsEditing(true)}>
                    Update
                  </Menu.Item>
                  <Menu.Item value="delete" onSelect={onDelete} color="red.500">
                    Delete
                  </Menu.Item>
                </Menu.Content>
              </Menu.Positioner>
            </Portal>
          </Menu.Root>
        )}
      </Table.Cell>
    </Table.Row>
  );
}

export default AdminCategoriesTableRow;
