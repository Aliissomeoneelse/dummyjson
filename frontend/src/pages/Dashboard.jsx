import React, { useEffect, useState } from "react";
import { getMe, getPosts, createPost } from "../services/api";

export default function Dashboard() {
    const [user, setUser] = useState(null);
    const [posts, setPosts] = useState([]);
    const [title, setTitle] = useState("");
    const [body, setBody] = useState("");

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

    const handleCreatePost = async (e) => {
        e.preventDefault();
        try {
            const res = await createPost({ title, body });
            setPosts(prev => [...prev, res.data]);
            setTitle("");
            setBody("");
        } catch (err) {
            alert("Ошибка при создании поста");
            console.error(err);
        }
    };

    return (
        <div style={{ padding: 40 }}>
            <h1>Dashboard</h1>
            {user && <h3>Welcome, {user.username || user.email}</h3>}
            <button onClick={handleLogout}>Logout</button>

            <h2>Create Post</h2>
            <form onSubmit={handleCreatePost}>
                <input
                    type="text"
                    placeholder="Title"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    required
                /><br/>
                <textarea
                    placeholder="Body"
                    value={body}
                    onChange={(e) => setBody(e.target.value)}
                    required
                /><br/>
                <button type="submit">Add Post</button>
            </form>

            <h2>Posts</h2>
            <ul>
                {posts.map(post => (
                    <li key={post.id}>{post.title || post.name}</li>
                ))}
            </ul>
        </div>
    );
}