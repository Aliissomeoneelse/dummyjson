import axios from "axios";

const API = axios.create({
    baseURL: "http://localhost:8080", // без /api
    headers: {
        "Content-Type": "application/json",
    },
});

API.interceptors.request.use((config) => {
    const token = localStorage.getItem("accessToken");
    if (token) config.headers["Authorization"] = `Bearer ${token}`;
    return config;
});

export const login = (data) => API.post("/auth/login", data);
export const register = (data) => API.post("/users/add", data);
export const getMe = () => API.get("/auth/me");
export const getPosts = () => API.get("/posts");
export const createPost = (data) => API.post("/posts/add", data);

export default API;
