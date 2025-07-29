import { Button, Input, Menu, Portal, Table } from "@chakra-ui/react";
import { useState } from "react";

interface UsersTableRowProps {
  userId: number;
  lastName: string;
  firstName: string;
  email: string;
  phoneNumber: string;
  onUpdate: (data: {
    lastName: string;
    firstName: string;
    email: string;
    phoneNumber: string;
  }) => void;
  onDelete: () => void;
}

function UsersTableRow({
  userId,
  lastName,
  firstName,
  email,
  phoneNumber,
  onUpdate,
  onDelete,
}: UsersTableRowProps) {
  const [isEditing, setIsEditing] = useState(false);
  const [editValues, setEditValues] = useState(() => ({
    lastName,
    firstName,
    email,
    phoneNumber,
  }));

  const handleChange = (field: string, value: string) => {
    setEditValues((prev) => ({ ...prev, [field]: value }));
  };

  const handleSave = () => {
    setIsEditing(false);
    onUpdate({
      lastName: editValues.lastName,
      firstName: editValues.firstName,
      email: editValues.email,
      phoneNumber: editValues.phoneNumber,
    });
  };

  return (
    <Table.Row>
      <Table.Cell>{userId}</Table.Cell>
      <Table.Cell>
        {isEditing ? (
          <Input
            value={editValues.lastName}
            onChange={(e) => handleChange("lastName", e.target.value)}
          />
        ) : (
          lastName
        )}
      </Table.Cell>

      <Table.Cell>
        {isEditing ? (
          <Input
            value={editValues.firstName}
            onChange={(e) => handleChange("firstName", e.target.value)}
          />
        ) : (
          firstName
        )}
      </Table.Cell>
      <Table.Cell>
        {isEditing ? (
          <Input
            value={editValues.email}
            onChange={(e) => handleChange("email", e.target.value)}
          />
        ) : (
          email
        )}
      </Table.Cell>
      <Table.Cell>
        {isEditing ? (
          <Input
            value={editValues.phoneNumber}
            onChange={(e) => handleChange("phoneNumber", e.target.value)}
          />
        ) : (
          phoneNumber
        )}
      </Table.Cell>
      <Table.Cell>
        {isEditing ? (
          <>
            <Button size="sm" colorScheme="green" onClick={handleSave}>
              Save
            </Button>
            <Button
              size="sm"
              ml={2}
              colorScheme="gray"
              onClick={() => setIsEditing(false)}
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
                  <Menu.Item value="delete" color="red.500" onSelect={onDelete}>
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

export default UsersTableRow;
