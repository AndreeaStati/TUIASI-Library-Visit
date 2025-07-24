import { Table } from "@chakra-ui/react";

interface BlockedSlotsTableRowProps {
  slotDate: Date;
  startTime: Date;
  endTime: Date;
  reason: string;
}

function BlockedSlotsTableRow({
  slotDate,
  startTime,
  endTime,
  reason,
}: BlockedSlotsTableRowProps) {
  return (
    <Table.Row px={20} py={8}>
      <Table.Cell>{slotDate.toLocaleDateString()}</Table.Cell>
      <Table.Cell>{startTime.toLocaleDateString()}</Table.Cell>
      <Table.Cell>{endTime.toLocaleDateString()}</Table.Cell>
      <Table.Cell>{reason}</Table.Cell>
      <Table.Cell>
        <></>
      </Table.Cell>
    </Table.Row>
  );
}
