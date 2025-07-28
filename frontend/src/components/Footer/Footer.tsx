import { Box, Image, Link, Text } from "@chakra-ui/react";
import maps_svg from "../../assets/maps-icon.svg"

function Footer(){
    const mapsLink = "https://www.google.com/maps/place/47.1747528,27.5692249,720m/data=!3m2!1e3!4b1!4m6!3m5!1s0x40cafb61ad8ff441:0xa6da011c7591e3ff!8m2!3d47.1747528!4d27.5717998!16s%2Fg%2F11csq_b_7c?hl=ro&entry=ttu&g_ep=EgoyMDI0MTExOC4wIKXMDSoASAFQAw%3D%3D";

    return(
        <Box borderWidth="2px" width="100%" display="flex" flexWrap="wrap" 
         justifyContent="space-around" padding="40px" gap="10px">
            <Box>
                <Text margin="10px">2025 Copyright &copy;</Text>
                <Text margin="10px">Biblioteca Universităţii Tehnice „Gheorghe Asachi” din Iaşi</Text>
            </Box>
            <Box>
                <Text margin="10px">Locatie</Text>
                <Text margin="10px">Adresa: Bulevardul Carol I, nr. 11, 700506 Iaşi</Text>
                <Box display="flex" margin="10px">
                    <Image src={maps_svg} alt="Imagine cu google maps pin" maxH="30px" margin="10px"/>
                    <Link href={mapsLink}>
                    Găseşte-ne pe hartă</Link>
                </Box>
            </Box>
        </Box>
    );
}


export default Footer