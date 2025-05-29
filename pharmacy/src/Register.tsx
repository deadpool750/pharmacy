// src/Register.tsx
import React from 'react';
import { Container, Typography, TextField, Button, Box } from '@mui/material';

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
            </Box>
        </Container>
    );
};

export default Register;
