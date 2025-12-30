import React, { useState } from "react";
import { login } from "../services/api";

export default function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const res = await login({ username, password });
            // Сохраняем токен именно из поля token, как возвращает backend
            localStorage.setItem("accessToken", res.data.token);
            window.location.href = "/dashboard";
        } catch (err) {
            console.error("Login error:", err.response || err);
            alert("Login failed. Проверьте username и пароль");
        }
    };


    return (
        <div style={{ padding: 40 }}>
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <input
                    placeholder="Username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                /><br/><br/>
                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                /><br/><br/>
                <button type="submit">Login</button>
            </form>
        </div>
    );
}
