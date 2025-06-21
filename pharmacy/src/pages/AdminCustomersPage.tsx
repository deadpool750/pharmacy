import React, { useEffect, useState } from 'react';
import {
    Box, Typography, Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Snackbar, Alert
} from '@mui/material';
import axios from '../api/axios';

interface Customer {
    id: number;
    name: string;
    phone: string;
    email: string;
}

const AdminCustomersPage: React.FC = () => {
    const [customers, setCustomers] = useState<Customer[]>([]);
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState<'success' | 'error'>('success');

    useEffect(() => {
        axios.get('/customers')
            .then(res => setCustomers(res.data))
            .catch(() => showSnackbar('Failed to fetch customers', 'error'));
    }, []);

    const showSnackbar = (message: string, severity: 'success' | 'error' = 'success') => {
        setSnackbarMessage(message);
        setSnackbarSeverity(severity);
        setSnackbarOpen(true);
    };

    return (
        <Box padding="2rem">
            <Typography variant="h4" gutterBottom>
                Customers
            </Typography>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Phone</TableCell>
                            <TableCell>Email</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {customers.map((cust) => (
                            <TableRow key={cust.id}>
                                <TableCell>{cust.id}</TableCell>
                                <TableCell>{cust.name}</TableCell>
                                <TableCell>{cust.phone}</TableCell>
                                <TableCell>{cust.email}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

            <Snackbar
                open={snackbarOpen}
                autoHideDuration={4000}
                onClose={() => setSnackbarOpen(false)}
                anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
            >
                <Alert
                    onClose={() => setSnackbarOpen(false)}
                    severity={snackbarSeverity}
                    variant="filled"
                    sx={{ width: '100%' }}
                >
                    {snackbarMessage}
                </Alert>
            </Snackbar>
        </Box>
    );
};

export default AdminCustomersPage;
