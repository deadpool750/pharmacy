// src/pages/AdminSuppliersPage.tsx
import React, { useEffect, useState } from 'react';
import {
    Box, Typography, Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Snackbar, Alert
} from '@mui/material';
import axios from '../api/axios';

interface Supplier {
    id: number;
    name: string;
    phone: string;
    email: string;
    address: string;
}

const AdminSuppliersPage: React.FC = () => {
    const [suppliers, setSuppliers] = useState<Supplier[]>([]);
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState<'success' | 'error'>('success');

    useEffect(() => {
        axios.get('/suppliers')
            .then(res => setSuppliers(res.data))
            .catch(() => showSnackbar('Failed to fetch suppliers', 'error'));
    }, []);

    const showSnackbar = (message: string, severity: 'success' | 'error' = 'success') => {
        setSnackbarMessage(message);
        setSnackbarSeverity(severity);
        setSnackbarOpen(true);
    };

    return (
        <Box padding="2rem">
            <Typography variant="h4" gutterBottom>
                Suppliers
            </Typography>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Phone</TableCell>
                            <TableCell>Email</TableCell>
                            <TableCell>Address</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {suppliers.map((supplier) => (
                            <TableRow key={supplier.id}>
                                <TableCell>{supplier.id}</TableCell>
                                <TableCell>{supplier.name}</TableCell>
                                <TableCell>{supplier.phone}</TableCell>
                                <TableCell>{supplier.email}</TableCell>
                                <TableCell>{supplier.address}</TableCell>
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

export default AdminSuppliersPage;
