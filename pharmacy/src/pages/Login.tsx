import React, { useState } from "react";
import {
    Container,
    Typography,
    TextField,
    Button,
    Box
} from "@mui/material";
import { Link } from "react-router-dom"; // ⬅️ Add this
import { useAuth } from "../hooks/useAuth";

/**
 * Login Component
 *
 * This page allows users (both admins and customers) to log in using their credentials.
 *
 * Features:
 * - Takes `username` and `password` as input fields
 * - Uses the `useAuth` hook to send a login request
 * - Displays an error message if login fails
 * - Provides a link to the registration page for new users
 *
 * Behavior:
 * - On successful login, redirects to the appropriate dashboard based on user role
 * - On failure, shows a centered error message
 *
 * Dependencies:
 * - React, MUI components for layout and styling
 * - `useAuth` hook for authentication logic
 * - React Router's `Link` for navigation to the register page
 */


const Login: React.FC = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const { login, error } = useAuth();

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        login({ username, password });
    };

    return (
        <Container maxWidth="xs">
            <Box sx={{ mt: 8 }}>
                <Typography variant="h4" align="center" gutterBottom>
                    Login
                </Typography>
                <form onSubmit={handleSubmit}>
                    <TextField
                        label="Username"
                        fullWidth
                        margin="normal"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                    />
                    <TextField
                        label="Password"
                        type="password"
                        fullWidth
                        margin="normal"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                    <Button type="submit" variant="contained" fullWidth sx={{ mt: 2 }}>
                        Login
                    </Button>
                    {error && (
                        <Typography color="error" align="center" sx={{ mt: 2 }}>
                            {error}
                        </Typography>
                    )}
                </form>

                <Typography variant="body2" align="center" sx={{ mt: 2 }}>
                    Don't have an account?{" "}
                    <Link to="/register" style={{ textDecoration: "none", color: "#1976d2" }}>
                        Register
                    </Link>
                </Typography>
            </Box>
        </Container>
    );
};

export default Login;
