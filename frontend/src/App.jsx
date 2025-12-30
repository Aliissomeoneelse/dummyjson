import React, { useState, useEffect } from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import Register from "./pages/Register";
import Welcome from "./pages/Welcome";
import { getMe } from "./services/api";

function App() {
    const [user, setUser] = useState(null);
    const [loading, setLoading] = useState(true); // ждём проверки токена

    useEffect(() => {
        const token = localStorage.getItem("accessToken");

        if (token) {
            getMe()
                .then(res => setUser(res.data))
                .catch(() => localStorage.removeItem("accessToken"))
                .finally(() => setLoading(false));
        } else {
            setLoading(false);
        }
    }, []);

    if (loading) return <div style={{ textAlign: "center", marginTop: "100px" }}>Loading...</div>;

    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={user ? <Navigate to="/dashboard" /> : <Welcome />} />
                <Route path="/login" element={user ? <Navigate to="/dashboard" /> : <Login />} />
                <Route path="/register" element={user ? <Navigate to="/dashboard" /> : <Register />} />
                <Route path="/dashboard" element={user ? <Dashboard /> : <Navigate to="/login" />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;
