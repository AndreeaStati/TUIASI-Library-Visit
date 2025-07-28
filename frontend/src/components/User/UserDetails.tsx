import { Card, DataList } from "@chakra-ui/react";

interface UserDetailsProps {
  lastName: string;
  firstName: string;
  email: string;
  phoneNumber: string;
}

function UserDetails({
  lastName,
  firstName,
  email,
  phoneNumber,
}: UserDetailsProps) {
  return (
    <Card.Root>
      <Card.Header px={"10px"} m={"10px"}>
        <Card.Title>User Details</Card.Title>
      </Card.Header>
      <Card.Body>
        <DataList.Root orientation={"horizontal"} maxW="md">
          <DataList.Item>
            <DataList.ItemLabel>Last Name</DataList.ItemLabel>
            <DataList.ItemValue>{lastName}</DataList.ItemValue>
          </DataList.Item>
          <DataList.Item>
            <DataList.ItemLabel>firstName</DataList.ItemLabel>
            <DataList.ItemValue>{firstName}</DataList.ItemValue>
          </DataList.Item>
          <DataList.Item>
            <DataList.ItemLabel>Email</DataList.ItemLabel>
            <DataList.ItemValue>{email}</DataList.ItemValue>
          </DataList.Item>
          <DataList.Item>
            <DataList.ItemLabel>Phone number</DataList.ItemLabel>
            <DataList.ItemValue>{phoneNumber}</DataList.ItemValue>
          </DataList.Item>
        </DataList.Root>
      </Card.Body>
    </Card.Root>
  );
}

export default UserDetails;
