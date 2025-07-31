import { Box, Button, Flex } from "@chakra-ui/react"
import { ColorModeButton, useColorModeValue } from "../components/ui/color-mode"
import {Header} from "../components/Header"
import {Footer} from "../components/Footer"
import {Calendar} from "../components/Calendar"
import type { DateTime } from "../types/calendar"
import { Link } from "react-router-dom"
import { ContentBlock } from "@/components/ContentBlock"
import loadTranslations  from "../services/FetchLnguage"
import { useEffect, useState } from "react"

type HomeProps = {
    optionalButton?: boolean
}

function Home({optionalButton}: HomeProps) {
    const [translation, setTranslation] = useState<any | null>(null);

    useEffect(() => {
        loadTranslations().then(setTranslation);
    }, []);
    const bg = useColorModeValue("black", "gray.800");
    const title="Biblioteca Unversității Tehnice \"Gheorghe Asachi\" - centrul cunoașterii din Iași";
    const description='Biblioteca Universităţii Tehnice “Gheorghe Asachi” din Iaşi încă din anul înfiinţării sale, 1948, deținând inițial un număr de 12.000 volume, contribuie la sprijinirea procesului de învăţământ şi de cercetare, asigurând informarea şi documentarea utilizatorilor săi prin accesul la fondul de documente ajuns astăzi la aproximativ 1.000.000 volume, acoperind astfel un mare număr de specialităţi din domeniul ştiinţei, tehnologiei, economiei şi legislaţiei. Biblioteca TUIASI este într-o continuă schimbare, căutând noi soluţii pentru implementarea şi valorificarea sistemelor moderne de informare şi documentare, de comunicare, de selectare şi dirijare a informaţiei către utilizatori, prin cele 7 biblioteci filiale. În prezent, Biblioteca Universității Tehnice „Gheorghe Asachi” din Iași, Sala de lectură – Imobil „A ”din Palatul Universitar, votată în 2015 ca fiind cea mai frumoasă bibliotecă, funcțională, din lume, concurând cu alte biblioteci, precum cea de la Trinity College din Dublin, Biblioteca Regală Portugheză de la Rio de Janeiro sau Biblioteca Naţională din Praga, își deschide porțile pentru toți cei care doresc să viziteze un monument de arhitectură, intrat în patrimoniul național din anul 1955 și devenit un simbol al Iașului academic! Vă așteptăm la una din vizitele ghidate, organizate în fiecare zi, de luni până vineri, între orele 11:00 – 14:00, pe durata cărora se vor ”deschide file” din istoria Palatului Universitar și a Bibliotecii.';
    
    if (!translation) return <div>Loading...</div>;
    
    return (
        <Flex direction="column" alignItems="center">
            <Header optionalButton={optionalButton} title={translation.headerTitle1}/>
            <ColorModeButton />
            <Box maxW={["90%", "70%"]}>
                <ContentBlock title={title} description={description} buttonText="Vizitează TUIASI acum" />
            </Box>
            <Footer />
        </Flex>
    )    
}

export default Home;