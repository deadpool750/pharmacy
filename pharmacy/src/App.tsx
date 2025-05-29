// src/App.tsx
import React from 'react';
import { Routes, Route, Link } from 'react-router-dom';
import Register from './Register';
import { Container, Typography, TextField, Button, Box } from '@mui/material';

function Login() {
    return (
        <Container maxWidth="xs">
            <Box sx={{ mt: 8 }}>
                <Typography variant="h4" align="center" gutterBottom>
                    Login
                </Typography>
                <TextField label="Username" fullWidth margin="normal" />
                <TextField label="Password" type="password" fullWidth margin="normal" />
                <Button variant="contained" fullWidth sx={{ mt: 2 }}>
                    Login
                </Button>
                <Typography variant="body2" align="center" sx={{ mt: 2 }}>
                    Don't have an account?{' '}
                    <Link to="/register" style={{ textDecoration: 'none', color: '#1976d2' }}>
                        Register
                    </Link>
                </Typography>
            </Box>
        </Container>
    );
}

export default function App() {
    return (
        <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/register" element={<Register />} />
        </Routes>
    );
}
