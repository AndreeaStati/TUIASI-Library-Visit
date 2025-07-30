import { Button, Card, Flex, Image, Text } from "@chakra-ui/react";
import default_content_image from "@/assets/default_content_image.jpg"
import { Link } from "react-router-dom";

type ContentBlockProps = {
    title: string,
    image?: string,
    description: string,
    buttonText?: string,
    linkTo?: string
}

function ContentBlock({title, image = default_content_image, description, buttonText, linkTo = "/reservation"}:ContentBlockProps) {
    return(
        <Card.Root>
        <Card.Header textAlign="center">
            <Card.Title fontSize="2xl">
                {title}
            </Card.Title>
        </Card.Header>
        <Card.Body flexDir={["column", "row"]} gap="30px">
            <Flex direction="column" flex={2} gap="20px">
                <Image src={image} alt="imagine content" 
            maxW="100%"/>
                <Button border="0.5px solid #1456d1"
                color="white"
                bg="#1456d1" 
                alignSelf="center" width="fit-content" 
                hidden={buttonText===undefined}
                paddingLeft="20px" paddingRight="20px"
                paddingTop="10px" paddingBottom="10px"
                _hover={{
                    bg: "white",
                    color: "#1456d1"
                }}
                fontSize="xl"
                ><Link to={linkTo}>{buttonText}</Link></Button>
            </Flex>
            <Flex flex={3}>
                <Text>{description}</Text>
            </Flex>
        </Card.Body>
    </Card.Root>
    );
}

export default ContentBlock;