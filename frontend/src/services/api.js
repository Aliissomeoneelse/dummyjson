import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080",
    headers: { "Content-Type": "application/json", "Accept": "application/json" }
});

API.interceptors.request.use(config => {
    const token = localStorage.getItem("accessToken");
    if (token) config.headers["Authorization"] = `Bearer ${token}`;
    return config;
});

export const login = (data) => {
    console.log("Login request:", data);
    return API.post("/auth/login", data)
        .then(res => {
            console.log("Login response:", res);
            return res;
        })
        .catch(err => {
            console.error("Login ERROR full:", err.response || err);
            throw err;
        });
};


export const getMe = () => API.get("/auth/me");
export const getPosts = () => API.get("/posts");

export default API;
