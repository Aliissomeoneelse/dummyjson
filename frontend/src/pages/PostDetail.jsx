import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import Navbar from "../components/Navbar.jsx";
import { getPostById } from "../services/api.js";

export default function PostDetail() {
    const { id } = useParams();
    const [post, setPost] = useState(null);

    useEffect(() => {
        const fetchPost = async () => {
            const res = await getPostById(id);
            setPost(res.data);
        };
        fetchPost();
    }, [id]);

    if (!post) return <p>Loading...</p>;

    return (
        <div>
            <Navbar />
            <h2>{post.title}</h2>
            <p>{post.body}</p>
        </div>
    );
}
