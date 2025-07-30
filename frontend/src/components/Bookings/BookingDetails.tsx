import { useEffect, useState } from "react";
import { Card, DataList, Spinner } from "@chakra-ui/react";
import axios from "axios";

interface BookingDetail {
  categoryId: number;
  categoryName: string;
  numberOfUsers: number;
}

interface BookingDetailsProps {
  bookingId: number;
}

function BookingDetails({ bookingId }: BookingDetailsProps) {
  const [details, setDetails] = useState<BookingDetail[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/booking-details/${bookingId}`)
      .then((res) => {
        setDetails(
          res.data.map((item: any) => ({
            categoryId: item.category.id,
            categoryName: item.category.category_name,
            numberOfUsers: item.number_of_users,
          }))
        );

        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching booking details", err);
        setLoading(false);
      });
  }, [bookingId]);

  if (loading) return <Spinner size="md" />;

  return (
    <Card.Root>
      <Card.Header px={"10px"} m={"10px"}>
        <Card.Title>Booking Details</Card.Title>
      </Card.Header>
      <Card.Body>
        <DataList.Root orientation="horizontal" maxW="md">
          {details.map((detail, index) => (
            <DataList.Item key={index}>
              <DataList.ItemLabel>
                {detail.categoryName} (ID: {detail.categoryId})
              </DataList.ItemLabel>
              <DataList.ItemValue>
                {detail.numberOfUsers} persons
              </DataList.ItemValue>
            </DataList.Item>
          ))}
        </DataList.Root>
      </Card.Body>
    </Card.Root>
  );
}

export default BookingDetails;
