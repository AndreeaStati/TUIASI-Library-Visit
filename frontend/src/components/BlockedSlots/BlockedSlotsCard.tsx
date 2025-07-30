import { useEffect, useState } from "react";
import BlockedSlotsTableRow from "../BlockedSlots/BlockedSlotsTableRow";
import { Button, Card, Table } from "@chakra-ui/react";
import axios from "axios";

interface BlockedSlotProps {
  id: number;
  slotDate: Date;
  startTime: string; // era Date, acum string
  endTime: string;
  reason: string;
}

function BlockedSlotsCard() {
  const [slots, setSlots] = useState<BlockedSlotProps[]>([]);
  const [isAdding, setIsAdding] = useState(false);

  // fetch la backend
  useEffect(() => {
    axios
      .get("http://localhost:8080/blocked-slots")
      .then((response) => {
        console.log("Response data from backend:", response.data);
        const parsed = response.data.map((slot: any) => {
          const parsedDate = new Date(`${slot.slot_date}T00:00:00`);

          if (isNaN(parsedDate.getTime())) {
            console.warn("Invalid date from backend:", slot.slot_date);
          }

          return {
            ...slot,
            slotDate: parsedDate, // aici faci proprietatea camelCase pentru frontend
            startTime: slot.start_time ?? "", // atenție, folosește și start_time (underscore)
            endTime: slot.end_time ?? "",
          };
        });

        setSlots(parsed);
      })
      .catch((error) => console.error("Eroare la fetch:", error));
  }, []);

  const handleAdd = (newSlot: {
    slotDate: Date;
    startTime: string;
    endTime: string;
    reason: string;
  }) => {
    const payload = {
      slot_date: newSlot.slotDate.toISOString().substring(0, 10),
      start_time: newSlot.startTime,
      end_time: newSlot.endTime,
      reason: newSlot.reason,
    };

    axios
      .post("http://localhost:8080/blocked-slots", payload)
      .then((res) => {
        const addedSlot = {
          ...res.data,
          slotDate: new Date(res.data.slot_date),
          startTime: res.data.start_time,
          endTime: res.data.end_time,
        };
        setSlots((prev) => [...prev, addedSlot]);
        setIsAdding(false);
      })
      .catch((err) => console.error("Eroare la adăugare:", err));
  };

  const handleUpdate = (id: number, updated: any) => {
    const updatedDto = {
      id,
      slot_date: updated.slotDate.toISOString().substring(0, 10),
      start_time: updated.startTime,
      end_time: updated.endTime,
      reason: updated.reason,
    };

    console.log("Payload update:", updatedDto);

    axios
      .put(`http://localhost:8080/blocked-slots/${id}`, updatedDto)
      .then((res) => {
        setSlots((prev) =>
          prev.map((slot) =>
            slot.id === id
              ? {
                  ...res.data,
                  slotDate: new Date(res.data.slot_date),
                  startTime: res.data.start_time,
                  endTime: res.data.end_time,
                }
              : slot
          )
        );
      })
      .catch((err) => console.error("Eroare la update:", err));
  };

  const handleDelete = (id: number) => {
    axios
      .delete(`http://localhost:8080/blocked-slots/${id}`)
      .then(() => {
        setSlots((prev) => prev.filter((slot) => slot.id !== id));
      })
      .catch((err) => console.error("Eroare la delete:", err));
  };

  return (
    <Card.Root colorPalette={"blue"} variant={"elevated"}>
      <Card.Header>
        <Card.Title mt="2">Blocked Slots</Card.Title>
        <Button
          size="sm"
          ml="auto"
          colorScheme="blue"
          onClick={() => setIsAdding(true)}
        >
          Add Slot
        </Button>
      </Card.Header>

      <Card.Body gap="2">
        {/* înlocuiește Card.Description cu un container valid */}

        <Table.Root
          colorScheme="gray"
          interactive
          p="6"
          m="auto"
          variant={"outline"}
        >
          <Table.Header fontSize="16px">
            <Table.Row>
              <Table.ColumnHeader>Date</Table.ColumnHeader>
              <Table.ColumnHeader>Start time</Table.ColumnHeader>
              <Table.ColumnHeader>End time</Table.ColumnHeader>
              <Table.ColumnHeader>Reason</Table.ColumnHeader>
              <Table.ColumnHeader>Actions</Table.ColumnHeader>
            </Table.Row>
          </Table.Header>

          <Table.Body>
            {slots.map((slot) => (
              <BlockedSlotsTableRow
                key={slot.id}
                slotDate={slot.slotDate}
                startTime={slot.startTime}
                endTime={slot.endTime}
                reason={slot.reason}
                onUpdate={(updated) => handleUpdate(slot.id, updated)}
                onDelete={() => handleDelete(slot.id)}
              />
            ))}

            {isAdding && (
              <BlockedSlotsTableRow
                slotDate={new Date()}
                startTime=""
                endTime=""
                reason=""
                onUpdate={(newSlot) => handleAdd(newSlot)}
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

export default BlockedSlotsCard;
