import { Card, Table } from "@chakra-ui/react";
import UsersTableRow from "./UsersTableRow";
import { useEffect, useState } from "react";
import axios from "axios";

interface UserProps {
  id: number;
  lastName: string;
  firstName: string;
  email: string;
  phoneNumber: string;
}

function UsersCard() {
  const [users, setUsers] = useState<UserProps[]>([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/users")
      .then((res) => {
        const parsedUsers = res.data.map((user: any) => ({
          id: user.id,
          lastName: user.last_name ?? "",
          firstName: user.first_name ?? "",
          email: user.email ?? "",
          phoneNumber: user.phone_number ?? "",
        }));
        setUsers(parsedUsers);
      })
      .catch((err) => {
        console.error("Eroare la încărcarea userilor:", err);
      });
  }, []);

  const handleUpdate = (id: number, updated: any) => {
    const updatedDto = {
      id,
      last_name: updated.lastName,
      first_name: updated.firstName,
      email: updated.email,
      phone_number: updated.phoneNumber,
    };

    console.log("Payload update:", updatedDto);

    axios
      .patch(`http://localhost:8080/users/${id}`, updatedDto)
      .then((res) => {
        const updatedUser = {
          id: res.data.id,
          lastName: res.data.last_name,
          firstName: res.data.first_name,
          email: res.data.email,
          phoneNumber: res.data.phone_number,
        };
        setUsers((prev) =>
          prev.map((user) => (user.id === id ? updatedUser : user))
        );
      })
      .catch((err) => console.error("Eroare la update:", err));
  };

  const handleDelete = async (id: number): Promise<void> => {
    try {
      await axios.delete(`http://localhost:8080/users/${id}`);
      setUsers((prev) => prev.filter((user) => user.id !== id));
    } catch (err) {
      console.error("Eroare la delete:", err);
      throw err; // propagă pentru a ajunge în catch-ul din UsersTableRow
    }
  };

  return (
    <Card.Root colorPalette={"blue"} variant={"elevated"}>
      <Card.Header direction={"column"}>
        <Card.Title mt="2">Users</Card.Title>
      </Card.Header>
      <Card.Body gap="2">
        <Table.Root
          colorScheme="gray"
          interactive
          p="6"
          m="auto"
          variant={"outline"}
        >
          <Table.Header fontSize="16px">
            <Table.Row>
              <Table.ColumnHeader>ID</Table.ColumnHeader>
              <Table.ColumnHeader>Last name</Table.ColumnHeader>
              <Table.ColumnHeader>First name</Table.ColumnHeader>
              <Table.ColumnHeader>Email</Table.ColumnHeader>
              <Table.ColumnHeader>Phone number</Table.ColumnHeader>
              <Table.ColumnHeader>Actions</Table.ColumnHeader>
            </Table.Row>
          </Table.Header>
          <Table.Body fontSize={"15px"}>
            {users.map((user) => (
              <UsersTableRow
                key={user.id}
                userId={user.id}
                lastName={user.lastName}
                firstName={user.firstName}
                email={user.email}
                phoneNumber={user.phoneNumber}
                onUpdate={(updatedData) => handleUpdate(user.id, updatedData)}
                onDelete={() => handleDelete(user.id)}
              />
            ))}
          </Table.Body>
        </Table.Root>
      </Card.Body>
    </Card.Root>
  );
}

export default UsersCard;
