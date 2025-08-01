import { Card, Button } from "@chakra-ui/react";

type PaymentCardProps = {
  isVisible?: boolean;
  onPaid: (data: boolean) => void; 
};

function PaymentCard({ isVisible, onPaid }: PaymentCardProps) {
  return (
    <Card.Root>
      <Card.Header>
        <Card.Title>Platiti</Card.Title>
      </Card.Header>
      <Card.Body>
        <Card.Description>
          
        </Card.Description>
      </Card.Body>
      <Card.Footer justifyContent="flex-end">
        <Button variant="outline" hidden={isVisible} onClick={() => onPaid(true)}>
          Plateste
        </Button>
        <Button>Anuleaza</Button>
      </Card.Footer>
    </Card.Root>
  );
}

export default PaymentCard;
