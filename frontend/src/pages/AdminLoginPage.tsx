import { Footer } from "@/components/Footer";
import { Header } from "@/components/Header";
import { Input, Button, Flex, Card, CardHeader, Text } from "@chakra-ui/react";
import { useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";

function AdminLoginPage() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {
        const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({id: 0, username, password_hash: password, email: "asdf" }),
        });

        if (response.ok) {
            const data = await response.json();
            sessionStorage.setItem("token", data.token);
            navigate("/admin");
        } else {
            alert("Invalid credentials");
        }
    };

    return (
        <Flex alignItems={"center"} direction="column" gap="10px" minHeight="100vh" justifyContent="space-between">
            <Header title="Universiteatea Tehnica Gheorghe Asachi"/>
            <Card.Root alignSelf="center">
                <CardHeader>
                    <Card.Title>
                        Introduceti datele:
                    </Card.Title>
                </CardHeader>
                <Card.Body gap = "10px">
                    <Text>Username:</Text>
                    <Input value={username} onChange={e => setUsername(e.target.value)} />
                    <Text>Parola:</Text>
                    <Input type="password" value={password} onChange={e => setPassword(e.target.value)} />
                    <Button onClick={handleLogin}>Login</Button>
                </Card.Body>
            </Card.Root>
            <Footer />
        </Flex>
    );
}

export default AdminLoginPage