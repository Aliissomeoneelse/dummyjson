import React from "react";
import { Link } from "react-router-dom";

export default function PostItem({ post }) {
    return (
        <div style={{ border: "1px solid #ccc", margin: "10px", padding: "10px" }}>
            <h3>{post.title}</h3>
            <p>{post.body}</p>
            <Link to={`/posts/${post.id}`}>View</Link>
        </div>
    );
}
