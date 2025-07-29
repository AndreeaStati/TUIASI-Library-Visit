import { Box, Card, DataList, Table } from "@chakra-ui/react";

interface BookingDetailsProps {
  bookingId: number;
  categoryId: number;
  numberOfPersons: number;
}

function BookingDetails({
  bookingId,
  categoryId,
  numberOfPersons,
}: BookingDetailsProps) {
  return (
    <Card.Root>
      <Card.Header px={"10px"} m={"10px"}>
        <Card.Title>Booking Details</Card.Title>
      </Card.Header>
      <Card.Body>
        <DataList.Root orientation={"horizontal"} maxW="md">
          <DataList.Item>
            <DataList.ItemLabel>Booking ID</DataList.ItemLabel>
            <DataList.ItemValue>{bookingId}</DataList.ItemValue>
          </DataList.Item>
          <DataList.Item>
            <DataList.ItemLabel>Category ID</DataList.ItemLabel>
            <DataList.ItemValue>{categoryId}</DataList.ItemValue>
          </DataList.Item>
          <DataList.Item>
            <DataList.ItemLabel>Number Of Persons</DataList.ItemLabel>
            <DataList.ItemValue>{numberOfPersons}</DataList.ItemValue>
          </DataList.Item>
        </DataList.Root>
      </Card.Body>
    </Card.Root>
  );
}

export default BookingDetails;
