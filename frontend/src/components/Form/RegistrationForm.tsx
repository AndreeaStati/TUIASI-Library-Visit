import { Box, Text, Input, Flex } from "@chakra-ui/react";
import { FormControl, FormLabel } from "@chakra-ui/form-control";
import { Stack, HStack } from "@chakra-ui/layout";
import { Select } from "@chakra-ui/select";
import { useState, useRef } from "react";
import type { FormData } from "@/types/formData";

interface RegistrationFormProps {
  onDataChange: (data: any) => void;
  formRef: React.RefObject<HTMLFormElement | null>;
  showEmailError?: boolean;
}

function RegistrationForm({ onDataChange, formRef, showEmailError }: RegistrationFormProps) {
  const [info, setInfo] = useState<FormData>({
  lastName: "",
  firstName: "",
  email: "",
  phone: "",
  institution: "",
  observations: "",
  language: "ro",
});

  const handleChange = (key: string, value: string) => {
    const updated = { ...info, [key]: value };
    setInfo(updated);
    onDataChange(updated);
  };

  return (
    <Flex
      direction={"column"}
      justifyContent={"center"}
      alignSelf={"flex-start"}
      color="#2e2e2e"
      px={20}
      mt={4}
      height={"auto"}
    >
      <Stack spacing={10}>
        <form ref={formRef} 
          onSubmit={(e) => {
          e.preventDefault();
          console.log("Form valid and submitted!");
        }}>
          <HStack spacing={20} mb={45}>
            <FormControl id="lastName">
              <FormLabel>Last Name</FormLabel>
              <Input
                type="text"
                variant="flushed"
                value={info.lastName}
                onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                  handleChange("lastName", e.target.value)
                }
              />
            </FormControl>

            <FormControl id="firstName">
              <FormLabel>First Name</FormLabel>
              <Input
                type="text"
                variant="flushed"
                value={info.firstName}
                onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                  handleChange("firstName", e.target.value)
                }
              />
            </FormControl>
          </HStack>
          
          <Text hidden={!showEmailError} color="red">Introduceti un email corect</Text>
          <FormControl id="email" mb={45}>
            <FormLabel>Email</FormLabel>
            <Input
              type="email"
              variant="flushed"
              value={info.email}
              onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                handleChange("email", e.target.value)
              }
            />
          </FormControl>

          <FormControl mb={45}>
            <FormLabel>Phone</FormLabel>
            <Input
              type="tel"
              variant="flushed"
              value={info.phone}
              onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                handleChange("phone", e.target.value)
              }
            />
          </FormControl>

          <FormControl mb={45}>
            <FormLabel>Institution</FormLabel>
            <Input
              variant="flushed"
              value={info.institution}
              onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                handleChange("institution", e.target.value)
              }
            />
          </FormControl>

          <FormControl mb={45}>
            <FormLabel>Observations (Optional)</FormLabel>
            <Input
              type="text"
              variant="flushed"
              value={info.observations}
              onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
                handleChange("observations", e.target.value)
              }
            />
          </FormControl>

          <FormControl mb={45}>
            <FormLabel>Preferred Language for Presentation</FormLabel>
            <Select
              value={info.language}
              onChange={(e: React.ChangeEvent<HTMLSelectElement>) =>
                handleChange("language", e.target.value)
              }
            >
              <option value="ro">Romanian</option>
              <option value="en">English</option>
              <option value="fr">French</option>
            </Select>
          </FormControl>
        </form>
      </Stack>
    </Flex>
  );
}

export default RegistrationForm;
