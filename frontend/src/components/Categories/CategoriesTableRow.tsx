import { NativeSelect, Table } from "@chakra-ui/react";

interface CategoriesTableRowProps {
  categoryName: string;
  pricePerPerson: number;
  numberOfPersons: number;
  onChange: (value: number) => void;
}

function CategoriesTableRow({
  categoryName,
  pricePerPerson,
  numberOfPersons,
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
            {Array.from({ length: 41 }, (_, i) => (
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
