import { Card, Button } from "@chakra-ui/react";
import { Link } from "react-router-dom";

type PaymentCardProps = {
  isVisible?: boolean;
  onPaid: (data: boolean) => void; 
};

function PaymentCard({ isVisible, onPaid }: PaymentCardProps) {
  return (
    <Card.Root>
      <Card.Header>
        <Card.Title>Plată rezervare</Card.Title>
      </Card.Header>
      <Card.Body>
        <Card.Description>
          Pentru a confirma rezervarea, vă rugăm să efectuați plata.
        </Card.Description>
      </Card.Body>
      <Card.Footer justifyContent="flex-end">
        <Button variant="outline" hidden={isVisible} onClick={() => onPaid(true)}>
          Plateste
        </Button>
        <Button><Link to="/">Anuleaza</Link></Button>
      </Card.Footer>
    </Card.Root>
  );
}

export default PaymentCard;
