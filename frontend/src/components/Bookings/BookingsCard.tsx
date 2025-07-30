import { Alert, Card, Table } from "@chakra-ui/react";
import BookingsTableRow from "../Bookings/BookingsTableRow";
import { useEffect, useState } from "react";
import axios from "axios";

function BookingsCard() {
  const [bookings, setBookings] = useState<any[]>([]);
  const [errorMessage, setErrorMessage] = useState<string | null>(null);

  useEffect(() => {
    axios.get("http://localhost:8080/bookings").then((res) => {
      const parsed = res.data.map((booking: any) => ({
        id: booking.id,
        bookingId: booking.id,
        totalPrice: booking.total_price,
        status: booking.status,
        details: booking.details,
        userId: booking.user?.id,
        bookingDate: new Date(booking.booking_date), // <-- convert string to Date here
        startTime: booking.start_time,
        endTime: booking.end_time,
      }));

      console.log("Parsed bookings:", parsed);
      setBookings(parsed);
    });
  }, []);

  const handleUpdate = (id: number, updatedData: any) => {
    console.log("PATCH booking with id:", id);
    console.log("Payload bookingId:", updatedData.bookingId);

    const updatedDto = {
      booking_id: id,
      user: {
        id: updatedData.userId, // Corect format pentru backend
      },
      booking_date: updatedData.bookingDate.toISOString().split("T")[0],
      start_time: updatedData.startTime,
      end_time: updatedData.endTime,
      total_price: updatedData.totalPrice,
      status: updatedData.status,
      details: updatedData.details,
    };
    console.log("Updating booking with id:", id, "data:", updatedDto);

    axios
      .patch(`http://localhost:8080/bookings/${id}`, updatedDto)
      .then((res) => {
        const updatedBooking = {
          id: res.data.booking_id,
          userId: res.data.user_id ?? updatedData.userId,
          bookingDate: new Date(res.data.booking_date),
          startTime: res.data.start_time,
          endTime: res.data.end_time,
          totalPrice: res.data.total_price,
          status: res.data.status,
          details: res.data.details,
        };
        setBookings((prev) =>
          prev.map((b) => (b.id === id ? updatedBooking : b))
        );
      });
  };

  const handleDelete = async (id: number): Promise<void> => {
    try {
      await axios.delete(`http://localhost:8080/bookings/${id}`);
      setBookings((prev) => prev.filter((b) => b.id !== id));
    } catch (err) {
      console.error("Eroare la delete:", err);
      throw err; // propagă pentru a ajunge în catch-ul din UsersTableRow
    }
  };

  return (
    <Card.Root colorPalette={"blue"} variant={"elevated"}>
      <Card.Header>
        <Card.Title>Bookings</Card.Title>
      </Card.Header>
      <Card.Body>
        <Table.Root
          colorScheme="gray"
          interactive
          p="6"
          m="auto"
          variant={"outline"}
        >
          <Table.Header fontSize="16px">
            <Table.Row>
              <Table.ColumnHeader>Booking ID</Table.ColumnHeader>
              <Table.ColumnHeader>User ID</Table.ColumnHeader>
              <Table.ColumnHeader>Date</Table.ColumnHeader>
              <Table.ColumnHeader>Start time</Table.ColumnHeader>
              <Table.ColumnHeader>End time</Table.ColumnHeader>
              <Table.ColumnHeader>Total price</Table.ColumnHeader>
              <Table.ColumnHeader>Status</Table.ColumnHeader>
              <Table.ColumnHeader>Details</Table.ColumnHeader>
              <Table.ColumnHeader>Actions</Table.ColumnHeader>
            </Table.Row>
          </Table.Header>

          <Table.Body>
            {bookings.map((booking) => (
              <BookingsTableRow
                key={`${booking.id}-${booking.userId}`}
                bookingId={booking.id}
                userId={booking.userId}
                bookingDate={booking.bookingDate}
                startTime={booking.startTime}
                endTime={booking.endTime}
                totalPrice={booking.totalPrice}
                status={booking.status}
                details={booking.details}
                onUpdate={(updated) => handleUpdate(booking.id, updated)}
                onDelete={() => handleDelete(booking.id)}
              />
            ))}
          </Table.Body>
        </Table.Root>
      </Card.Body>
    </Card.Root>
  );
}

export default BookingsCard;
