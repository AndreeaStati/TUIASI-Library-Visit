import { Footer } from "@/components/Footer";
import { Header } from "@/components/Header";
import { Button, Card, CardBody, Flex, Text } from "@chakra-ui/react";
import { Link } from "react-router-dom";

function FinalizedPage() {
    return(
      <Flex direction="column" minH="100vh" justifyContent="space-between" alignItems="center" gap="20px">
        <Header title="Universiteatea Tehnica Gheorghe Asachi"/>
        <Card.Root maxW={["90%", "50%"]} padding="50px">
            <CardBody gap="20px">
                <Text fontSize="xl">
                    Inregistrare reusita.
                </Text>
                <Button><Link to="/">Spre pagina principala</Link></Button>
            </CardBody>
        </Card.Root>
        <Footer />
      </Flex>  
    );
}

export default FinalizedPage