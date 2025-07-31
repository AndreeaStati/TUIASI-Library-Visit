import { ButtonGroup, Button, Flex, Heading, Image, Box } from "@chakra-ui/react";
import stema from "../../assets/stema.png"
import background1 from "../../assets/background_header1.jpg"
import logo from "../../assets/logo1.png"
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";

type HeaderProps = {
    title?: string;
    optionalButton?: boolean;
}

function Header({ title, optionalButton }: HeaderProps) {
    const [lang, setLang] = useState(localStorage.getItem('lang') || 'ro');
    useEffect(() => {
        localStorage.setItem('lang', lang);
    }, [lang]);
    let backButton = null;
    if (optionalButton)
        backButton = <Button fontFamily="'Cinzel', serif" variant="outline" color="white" _hover={{ color: "black" }}
            alignSelf="flex-start"><Link to="/">Inapoi</Link></Button>;
    return (
        <Flex direction="column" backgroundImage={`url(${background1})`}
            backgroundSize="cover" w="100%" backgroundPosition="center" paddingTop="30px"
            paddingBottom="50px" gap="20px" fontFamily="'Cinzel', serif">
            <ButtonGroup alignSelf="flex-end" display="flex" flexDirection="row"
                justifyContent="flex-end" gap="10px" variant="outline" width="100%"
                padding="10px">
                {backButton && (
                    <Box marginRight="auto">
                        {backButton}
                    </Box>
                )}
                <Flex gap="10px" alignSelf="flex-end" >
                    <Button fontFamily="'Cinzel', serif" color="white" _hover={{ color: "black" }} 
                    onClick={() => {localStorage.setItem("lang", "ro"); window.location.reload();}}>Romana</Button>
                    <Button fontFamily="'Cinzel', serif" color="white" _hover={{ color: "black" }}
                    onClick={() => {localStorage.setItem("lang", "en"); window.location.reload();}}>English</Button>
                </Flex>
            </ButtonGroup>
            <Flex direction={{ base: "column", lg: "row" }} alignItems="center"
                justifyContent="center" gap="30px" maxW="100%" maxH="100%" h="100%">
                <Image src={stema} alt="WOW!" objectFit="contain" height={{ base: "60px", lg: "100px" }}
                    maxW="100%" />
                <Heading fontFamily="'Cinzel', serif" fontSize={['xl', '2xl', '3xl', '4xl']} color="white" textAlign="center">{title}</Heading>
                <Image src={logo} alt="Logo-ul facultatii" objectFit="contain" height={{ base: "60px", lg: "100px" }}
                    maxW="100%" />
            </Flex>
        </Flex>
    );
}

export default Header;