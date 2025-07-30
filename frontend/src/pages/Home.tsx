import { Button, Flex } from "@chakra-ui/react"
import { ColorModeButton, useColorModeValue } from "../components/ui/color-mode"
import {Header} from "../components/Header"
import {Footer} from "../components/Footer"
import {Calendar} from "../components/Calendar"
import type { DateTime } from "../types/calendar"
import { Link } from "react-router-dom"

type HomeProps = {
    optionalButton?: boolean
}

function Home({optionalButton}: HomeProps) {
    const bg = useColorModeValue("black", "gray.800");
    let lunaSelectata = new Date().getMonth();
    const highlightedDates: DateTime[] = [ {date: new Date(2025,7,12), hours: [11,14]}, {date: new Date(2025,7,14), hours: [11,12,13,14]}];
    const blockedDates: DateTime[] = [{date: new Date(2025,7,11), hours: [11,12,13,14]}, {date: new Date(2025,7,13), hours: [11,13,14]}];
    return (
        <Flex direction="column" alignItems="center">
            <Header optionalButton={optionalButton} title="Vizitează Universitatea Tehnică „Gheorghe Asachi” din Iași "/>
            <ColorModeButton />
            <Link to="/reservation">Catre rezervare</Link>
            <Footer />
        </Flex>
    )    
}

export default Home;