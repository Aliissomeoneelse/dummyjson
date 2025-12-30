import React from "react";
import { Link } from "react-router-dom";

export default function Navbar() {
    const logout = () => {
        localStorage.removeItem("accessToken");
        window.location.href = "/login";
    };

    return (
        <nav>
            <Link to="/dashboard">Dashboard</Link> |{" "}
            <button onClick={logout}>Logout</button>
        </nav>
    );
}
