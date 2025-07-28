import BlockedSlotsCard from "../components/BlockedSlots/BlockedSlotsCard";
import BookingsCard from "../components/Bookings/BookingsCard";
import { Flex } from "@chakra-ui/react";

function AdminPage() {
  return (
    <Flex
      direction={"column"}
      justify={"center"}
      align={"space-around"}
      gap={"60px"}
      m="auto"
      p={10}
      width="100%"
      maxW="1500px"
    >
      <BlockedSlotsCard />
      <BookingsCard />
    </Flex>
  );
}

export default AdminPage;
