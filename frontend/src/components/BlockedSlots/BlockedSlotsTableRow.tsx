import { Button, Input, Table, Portal, Menu } from "@chakra-ui/react";
import { useState } from "react";

interface BlockedSlotsTableRowProps {
  slotDate: Date;
  startTime: string; // era Date, acum string
  endTime: string;
  reason: string;
  onUpdate: (data: {
    slotDate: Date;
    startTime: string; // era Date, acum string
    endTime: string;
    reason: string;
  }) => void;
  onDelete: () => void;
  isNew?: boolean;
}

function BlockedSlotsTableRow({
  slotDate,
  startTime,
  endTime,
  reason,
  onUpdate,
  onDelete,
  isNew,
}: BlockedSlotsTableRowProps) {
  const [isEditing, setIsEditing] = useState(isNew ?? false);
  const [editValues, setEditValues] = useState(() => ({
    slotDate:
      slotDate instanceof Date && !isNaN(slotDate.getTime())
        ? slotDate.toISOString().substring(0, 10)
        : "",
    startTime,
    endTime,
    reason,
  }));

  const handleChange = (field: string, value: string) => {
    setEditValues((prev) => ({ ...prev, [field]: value }));
  };

  const handleSave = () => {
    setIsEditing(false);
    onUpdate({
      slotDate: new Date(editValues.slotDate),
      startTime: editValues.startTime, // string "HH:mm"
      endTime: editValues.endTime,
      reason: editValues.reason,
    });
  };

  return (
    <Table.Row>
      <Table.Cell>
        {isEditing ? (
          <Input
            type="date"
            value={editValues.slotDate}
            onChange={(e) => handleChange("slotDate", e.target.value)}
            size="sm"
          />
        ) : (
          slotDate.toLocaleDateString()
        )}
      </Table.Cell>
      <Table.Cell>
        {isEditing ? (
          <Input
            type="time"
            value={editValues.startTime}
            onChange={(e) => handleChange("startTime", e.target.value)}
            size="sm"
          />
        ) : (
          startTime?.slice(0, 5) || "-"
        )}
      </Table.Cell>
      <Table.Cell>
        {isEditing ? (
          <Input
            type="time"
            value={editValues.endTime}
            onChange={(e) => handleChange("endTime", e.target.value)}
            size="sm"
          />
        ) : (
          endTime?.slice(0, 5) || "-"
        )}
      </Table.Cell>
      <Table.Cell>
        {isEditing ? (
          <Input
            value={editValues.reason}
            onChange={(e) => handleChange("reason", e.target.value)}
            size="sm"
          />
        ) : (
          reason
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

export default BlockedSlotsTableRow;
