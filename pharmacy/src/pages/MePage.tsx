import React, { useEffect, useState } from 'react';
import {
    Container, Typography, TextField, Button, Snackbar, Alert, Box, AppBar, Toolbar
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import axios from '../api/axios';

const MePage: React.FC = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState<'success' | 'error'>('success');

    const navigate = useNavigate();

    useEffect(() => {
        axios.get('/users/me')
            .then((res) => {
                setUsername(res.data.username);
            })
            .catch(() => {
                showSnackbar('Failed to load user info', 'error');
            });
    }, []);

    const showSnackbar = (message: string, severity: 'success' | 'error' = 'success') => {
        setSnackbarMessage(message);
        setSnackbarSeverity(severity);
        setSnackbarOpen(true);
    };

    const handleUpdate = () => {
        if (password && password !== confirmPassword) {
            showSnackbar('Passwords do not match', 'error');
            return;
        }

        axios.put('/users/me', {
            username,
            password: password || undefined
        })
            .then(() => {
                showSnackbar('Profile updated, re-logging in...', 'success');

                // Re-login with new credentials
                axios.post('/auth/login', {
                    username,
                    password
                })
                    .then((res) => {
                        localStorage.setItem('token', res.data.token);
                        setPassword('');
                        setConfirmPassword('');
                        navigate('/'); // or your home route
                    })
                    .catch(() => {
                        showSnackbar('Updated, but failed to re-login. Please login manually.', 'error');
                        localStorage.removeItem('token');
                        navigate('/login');
                    });
            })
            .catch((err) => {
                showSnackbar(err.response?.data?.message || 'Update failed', 'error');
            });
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/login');
    };

    return (
        <>
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h6" sx={{ flexGrow: 1 }}>
                        My Profile
                    </Typography>
                    <Button color="inherit" onClick={handleLogout}>
                        Logout
                    </Button>
                </Toolbar>
            </AppBar>

            <Container maxWidth="sm" sx={{ mt: 4 }}>
                <Typography variant="h4" gutterBottom>
                    Edit Your Information
                </Typography>
                <TextField
                    label="Username"
                    fullWidth
                    margin="normal"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                />
                <TextField
                    label="New Password"
                    type="password"
                    fullWidth
                    margin="normal"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    helperText="Leave blank to keep your current password"
                />
                <TextField
                    label="Confirm Password"
                    type="password"
                    fullWidth
                    margin="normal"
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                />
                <Box mt={2}>
                    <Button variant="contained" color="primary" onClick={handleUpdate}>
                        Update Profile
                    </Button>
                </Box>
            </Container>

            <Snackbar
                open={snackbarOpen}
                autoHideDuration={4000}
                onClose={() => setSnackbarOpen(false)}
                anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
            >
                <Alert
                    onClose={() => setSnackbarOpen(false)}
                    severity={snackbarSeverity}
                    sx={{ width: '100%' }}
                    variant="filled"
                >
                    {snackbarMessage}
                </Alert>
            </Snackbar>
        </>
    );
};

export default MePage;
