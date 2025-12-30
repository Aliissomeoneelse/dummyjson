import React, { useState } from "react";
import { register } from "../services/api";

export default function Register() {
    const [form, setForm] = useState({
        username: "",
        password: "",
        email: "",
        firstName: "",
        lastName: "",
        gender: "male"
    });

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await register(form);
            alert("Регистрация успешна");
            window.location.href = "/login";
        } catch (err) {
            console.error(err);
            alert("Ошибка регистрации");
        }
    };

    return (
        <div style={{ padding: 40 }}>
            <h2>Register</h2>
            <form onSubmit={handleSubmit}>
                <input name="username" placeholder="Username" onChange={handleChange} /><br/><br/>
                <input name="email" placeholder="Email" onChange={handleChange} /><br/><br/>
                <input name="firstName" placeholder="First name" onChange={handleChange} /><br/><br/>
                <input name="lastName" placeholder="Last name" onChange={handleChange} /><br/><br/>
                <input type="password" name="password" placeholder="Password" onChange={handleChange} /><br/><br/>

                <select name="gender" onChange={handleChange}>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </select><br/><br/>

                <button type="submit">Register</button>
            </form>
        </div>
    );
}
