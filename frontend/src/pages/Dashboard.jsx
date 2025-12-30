import React, { useEffect, useState } from "react";
import { getMe, getPosts } from "../services/api";

export default function Dashboard() {
    const [user, setUser] = useState(null);
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        getMe()
            .then(res => setUser(res.data))
            .catch(err => console.error(err));

        getPosts()
            .then(res => setPosts(res.data))
            .catch(err => console.error(err));
    }, []);

    const handleLogout = () => {
        localStorage.removeItem("accessToken");
        window.location.href = "/login";
    };

    return (
        <div style={{ padding: 40 }}>
            <h1>Dashboard</h1>
            {user && <h3>Welcome, {user.username || user.email}</h3>}
            <button onClick={handleLogout}>Logout</button>
            <h2>Posts</h2>
            <ul>
                {posts.map(post => (
                    <li key={post.id}>{post.title || post.name}</li>
                ))}
            </ul>
        </div>
    );
}
