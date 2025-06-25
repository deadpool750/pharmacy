import React from 'react';
import {
    Container,
    Typography,
    TextField,
    Button,
    Box
} from '@mui/material';
import { Link } from 'react-router-dom';

/**
 * Register Component
 *
 * This page provides a registration form for new users to create an account.
 *
 * Features:
 * - Input fields for username, email, and password
 * - Submit button to register the user (currently not connected to backend logic)
 * - Navigation link to the login page for existing users
 *
 * Behavior:
 * - Currently only renders the UI; does not yet handle form submission logic or validation
 *
 * Dependencies:
 * - React, MUI components for layout and styling
 * - React Router's `Link` for navigation to the login page
 */


const Register: React.FC = () => {
    return (
        <Container maxWidth="xs">
            <Box sx={{ mt: 8 }}>
                <Typography variant="h4" align="center" gutterBottom>
                    Register
                </Typography>
                <TextField label="Username" fullWidth margin="normal" />
                <TextField label="Email" fullWidth margin="normal" />
                <TextField label="Password" type="password" fullWidth margin="normal" />
                <Button variant="contained" fullWidth sx={{ mt: 2 }}>
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
