import React, { useState } from 'react';
import {
    Container,
    Typography,
    TextField,
    Button,
    Box
} from '@mui/material';
import { Link, useNavigate } from 'react-router-dom';
import axios from '../api/axios';

const Register: React.FC = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleRegister = async () => {
        setError('');
        if (!username || !password) {
            setError('Username and password are required.');
            return;
        }

        try {
            await axios.post('/users', {
                username,
                password
            });
            navigate('/login');
        } catch (err: any) {
            setError(err.response?.data?.message || 'Registration failed. Please try again.');
            console.error(err);
        }
    };

    return (
        <Container maxWidth="xs">
            <Box sx={{ mt: 8 }}>
                <Typography variant="h4" align="center" gutterBottom>
                    Register
                </Typography>
                <TextField
                    label="Username"
                    fullWidth
                    margin="normal"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <TextField
                    label="Email (optional)"
                    fullWidth
                    margin="normal"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <TextField
                    label="Password"
                    type="password"
                    fullWidth
                    margin="normal"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                />
                {error && (
                    <Typography color="error" variant="body2" align="center" sx={{ mt: 1 }}>
                        {error}
                    </Typography>
                )}
                <Button variant="contained" fullWidth sx={{ mt: 2 }} onClick={handleRegister}>
                    Register
                </Button>
                <Typography variant="body2" align="center" sx={{ mt: 2 }}>
                    Already have an account?{" "}
                    <Link to="/login" style={{ textDecoration: "none", color: "#1976d2" }}>
                        Login
                    </Link>
                </Typography>
            </Box>
        </Container>
    );
};

export default Register;
