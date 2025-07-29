import {
  Button,
  Flex,
  Input,
  Menu,
  Portal,
  Table,
  Text,
} from "@chakra-ui/react";
import BookingDetails from "./BookingDetails";
import UserDetails from "../User/UserDetails";
import { useEffect, useState } from "react";
import axios from "axios";

// Cache local pentru useri, evită fetch duplicat
const userCache = new Map<
  number,
  {
    lastName: string;
    firstName: string;
    email: string;
    phoneNumber: string;
  }
>();

interface BookingsTableRowProps {
  userId: number;
  bookingId: number;

  bookingDate: Date;
  startTime: string;
  endTime: string;
  totalPrice: number;
  status: string;
  details: string;
  onUpdate: (data: {
    userId: number;
    bookingId: number;

    bookingDate: Date;
    startTime: string;
    endTime: string;
    totalPrice: number;
    status: string;
    details: string;
  }) => void;
  onDelete: () => void;
}

function BookingsTableRow({
  userId,
  bookingId,
  bookingDate,
  startTime,
  endTime,
  totalPrice,
  status,
  details,
  onUpdate,
  onDelete,
}: BookingsTableRowProps) {
  const [isEditing, setIsEditing] = useState(false);
  const [isExpanded, setIsExpanded] = useState(false);
  const [userData, setUserData] = useState<null | {
    lastName: string;
    firstName: string;
    email: string;
    phoneNumber: string;
  }>(null);

  // Fetch user data with mapping for camelCase keys
  useEffect(() => {
    if (!userId) return; // <- protecție împotriva undefined/null

    // Verificăm dacă userul e deja în cache
    if (userCache.has(userId)) {
      setUserData(userCache.get(userId)!);
      return;
    }

    // Dacă nu, facem request și salvăm în cache
    axios
      .get(`http://localhost:8080/users/${userId}`)
      .then((res) => {
        console.log("Response data from backend:", res.data);
        const user = res.data;
        const formattedUser = {
          firstName: user.first_name ?? user.firstName,
          lastName: user.last_name ?? user.lastName,
          email: user.email,
          phoneNumber: user.phone_number ?? user.phoneNumber,
        };
        userCache.set(userId, formattedUser);
        setUserData(formattedUser);
      })
      .catch((err) => {
        console.error("Failed to fetch user data", err);
      });
  }, [userId]);

  // Synchronize editValues state when props change
  const [editValues, setEditValues] = useState({
    userId,
    bookingId,
    bookingDate:
      bookingDate instanceof Date && !isNaN(bookingDate.getTime())
        ? bookingDate.toISOString().substring(0, 10)
        : "",
    startTime,
    endTime,
    totalPrice,
    status,
    details,
  });

  useEffect(() => {
    setEditValues({
      userId,
      bookingId,
      bookingDate:
        bookingDate instanceof Date && !isNaN(bookingDate.getTime())
          ? bookingDate.toISOString().substring(0, 10)
          : "",
      startTime: typeof startTime === "string" ? startTime : "",
      endTime: typeof endTime === "string" ? endTime : "",
      totalPrice,
      status: status ?? "",
      details: details ?? "",
    });
  }, [
    userId,
    bookingId,
    bookingDate,
    startTime,
    endTime,
    totalPrice,
    status,
    details,
  ]);

  const handleChange = (field: string, value: string) => {
    setEditValues((prev) => ({ ...prev, [field]: value }));
  };

  const handleSave = () => {
    setIsEditing(false);
    console.log("Saving with bookingId:", bookingId); // ← DEBUG
    onUpdate({
      userId: Number(editValues.userId),
      bookingId: Number(editValues.bookingId),
      bookingDate: new Date(editValues.bookingDate),
      startTime: String(editValues.startTime),
      endTime: String(editValues.endTime),
      totalPrice: Number(editValues.totalPrice),
      status: editValues.status,
      details: editValues.details,
    });
  };
  return (
    <>
      {/* Main row */}
      <Table.Row>
        <Table.Cell>{bookingId}</Table.Cell>
        <Table.Cell>
          {isEditing ? (
            <Input
              type="number"
              value={editValues.userId.toString()} // asigură string, altfel poate da warning
              onChange={(e) => handleChange("userId", e.target.value)}
              size="sm"
              aria-label="User ID"
            />
          ) : (
            userId
          )}
        </Table.Cell>

        <Table.Cell>
          {isEditing ? (
            <Input
              type="date"
              value={editValues.bookingDate ?? ""}
              onChange={(e) => handleChange("bookingDate", e.target.value)}
              size="sm"
              aria-label="Booking Date"
            />
          ) : (
            bookingDate.toLocaleDateString()
          )}
        </Table.Cell>

        <Table.Cell>
          {isEditing ? (
            <Input
              type="time"
              value={
                typeof editValues.startTime === "string"
                  ? editValues.startTime
                  : ""
              }
              onChange={(e) => handleChange("startTime", e.target.value)}
              size="sm"
              aria-label="Start Time"
            />
          ) : typeof startTime === "string" ? (
            startTime.slice(0, 5)
          ) : (
            "-"
          )}
        </Table.Cell>
        <Table.Cell>
          {isEditing ? (
            <Input
              type="time"
              value={
                typeof editValues.endTime === "string" ? editValues.endTime : ""
              }
              onChange={(e) => handleChange("endTime", e.target.value)}
              size="sm"
              aria-label="End Time"
            />
          ) : typeof endTime === "string" ? (
            endTime.slice(0, 5)
          ) : (
            "-"
          )}
        </Table.Cell>

        <Table.Cell>
          {isEditing ? (
            <Input
              type="number"
              value={editValues.totalPrice ?? ""}
              onChange={(e) => handleChange("totalPrice", e.target.value)}
              size="sm"
              aria-label="Total Price"
            />
          ) : (
            totalPrice
          )}
        </Table.Cell>

        <Table.Cell>
          {isEditing ? (
            <Input
              value={editValues.status ?? ""}
              onChange={(e) => handleChange("status", e.target.value)}
              size="sm"
              aria-label="Status"
            />
          ) : (
            status
          )}
        </Table.Cell>

        <Table.Cell>
          {isEditing ? (
            <Input
              value={editValues.details ?? ""}
              onChange={(e) => handleChange("details", e.target.value)}
              size="sm"
              aria-label="Details"
            />
          ) : (
            details
          )}
        </Table.Cell>

        <Table.Cell>
          {isEditing ? (
            <>
              <Button
                size="sm"
                colorScheme="green"
                onClick={handleSave}
                aria-label="Save Booking"
              >
                Save
              </Button>
              <Button
                size="sm"
                ml={2}
                colorScheme="gray"
                onClick={() => setIsEditing(false)}
                aria-label="Cancel Editing"
              >
                Cancel
              </Button>
            </>
          ) : (
            <Menu.Root>
              <Menu.Trigger asChild>
                <Button variant="outline" size="sm" aria-label="Options">
                  Options
                </Button>
              </Menu.Trigger>

              <Portal>
                <Menu.Positioner>
                  <Menu.Content>
                    <Menu.Item
                      value="update"
                      onSelect={() => setIsEditing(true)}
                    >
                      Update
                    </Menu.Item>
                    <Menu.Item
                      value="delete"
                      onSelect={onDelete}
                      style={{ color: "red" }}
                    >
                      Delete
                    </Menu.Item>
                    <Menu.Item
                      value="toggle-details"
                      onSelect={() => setIsExpanded(!isExpanded)}
                    >
                      {isExpanded ? "Hide" : "Show"} Details
                    </Menu.Item>
                  </Menu.Content>
                </Menu.Positioner>
              </Portal>
            </Menu.Root>
          )}
        </Table.Cell>
      </Table.Row>

      {/* Expanded row below the current one */}
      {isExpanded && (
        <Table.Row key={`expanded-${bookingId}`}>
          <Table.Cell colSpan={8}>
            <Flex
              direction="row"
              justifyContent="flex-start"
              gap="60px"
              width="100%"
              maxW="1200px"
              alignItems="flex-start"
              p="30px"
            >
              {/* TODO: Pass real bookingId, categoryId, numberOfPersons from props or context */}
              <BookingDetails
                bookingId={1}
                categoryId={2}
                numberOfPersons={3}
              />
              {userData ? (
                <UserDetails
                  lastName={userData.lastName}
                  firstName={userData.firstName}
                  email={userData.email}
                  phoneNumber={userData.phoneNumber}
                />
              ) : (
                <Text color="gray.500">Loading user info...</Text>
              )}
            </Flex>
          </Table.Cell>
        </Table.Row>
      )}
    </>
  );
}

export default BookingsTableRow;
