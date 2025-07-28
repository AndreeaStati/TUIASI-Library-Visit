import { NativeSelect, Table } from "@chakra-ui/react";

interface CategoriesTableRowProps {
  categoryName: string;
  pricePerPerson: number;
  numberOfPersons: number;
  numberOfAvailablePlaces?: number;
  onChange: (value: number) => void;
}

function CategoriesTableRow({
  categoryName,
  pricePerPerson,
  numberOfPersons,
  numberOfAvailablePlaces = 41,
  onChange,
}: CategoriesTableRowProps) {
  return (
    <Table.Row px={20} py={8}>
      <Table.Cell>{categoryName}</Table.Cell>
      <Table.Cell>{pricePerPerson}</Table.Cell>
      <Table.Cell>
        <NativeSelect.Root>
          <NativeSelect.Field
            width="80px"
            value={numberOfPersons}
            onChange={(e) => onChange(Number(e.target.value))}
          >
            {Array.from({ length: numberOfAvailablePlaces }, (_, i) => (
              <option key={i} value={i}>
                {i}
              </option>
            ))}
          </NativeSelect.Field>
        </NativeSelect.Root>
      </Table.Cell>
    </Table.Row>
  );
}

export default CategoriesTableRow;
