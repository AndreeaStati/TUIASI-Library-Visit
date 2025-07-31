import { Input, Button } from "@chakra-ui/react";
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
        <>
            <Input value={username} onChange={e => setUsername(e.target.value)} />
            <Input type="password" value={password} onChange={e => setPassword(e.target.value)} />
            <Button onClick={handleLogin}>Login</Button>
        </>
    );
}

export default AdminLoginPage