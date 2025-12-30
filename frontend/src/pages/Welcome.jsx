import { Link, Navigate } from "react-router-dom";

const Welcome = () => {
    const isAuth = !!localStorage.getItem("accessToken");
    if (isAuth) return <Navigate to="/dashboard" />;

    return (
        <div style={{ textAlign: "center", marginTop: "100px" }}>
            <h1>Добро пожаловать</h1>
            <p>Выберите действие</p>
            <div style={{ display: "flex", gap: "20px", justifyContent: "center" }}>
                <Link to="/login"><button>Login</button></Link>
                <Link to="/register"><button>Register</button></Link>
            </div>
        </div>
    );
};

export default Welcome;
