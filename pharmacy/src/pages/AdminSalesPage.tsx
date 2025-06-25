import React, { useEffect, useState } from 'react';
import {
    Box, Typography, Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Snackbar, Alert
} from '@mui/material';
import axios from '../api/axios';

interface Sale {
    id: number;
    customerName: string;
    medicationName: string;
    quantity: number;
    totalPrice: number;
    saleDate: string;
}
/**
 * AdminSalesPage Component
 *
 * Displays a list of all sales transactions in a table format for admin users.
 *
 * Features:
 * - Fetches sales data from the backend (`/sales` endpoint)
 * - Shows customer name, medication name, quantity, total price, and sale date
 * - Displays error notification (Snackbar) if fetching fails
 *
 * Technologies Used:
 * - React (useState, useEffect)
 * - Axios for API requests
 * - Material-UI for UI components (Table, Snackbar, etc.)
 *
 * Usage:
 * ```tsx
 * <AdminSalesPage />
 * ```
 */


const AdminSalesPage: React.FC = () => {
    const [sales, setSales] = useState<Sale[]>([]);
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState<'success' | 'error'>('success');

    useEffect(() => {
        axios.get('/sales')
            .then(res => setSales(res.data))
            .catch(() => showSnackbar('Failed to fetch sales', 'error'));
    }, []);

    const showSnackbar = (message: string, severity: 'success' | 'error' = 'success') => {
        setSnackbarMessage(message);
        setSnackbarSeverity(severity);
        setSnackbarOpen(true);
    };

    return (
        <Box padding="2rem">
            <Typography variant="h4" gutterBottom>
                Sales Records
            </Typography>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Customer Name</TableCell>
                            <TableCell>Medication Name</TableCell>
                            <TableCell>Quantity</TableCell>
                            <TableCell>Total Price</TableCell>
                            <TableCell>Sale Date</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {sales.map((sale) => (
                            <TableRow key={sale.id}>
                                <TableCell>{sale.id}</TableCell>
                                <TableCell>{sale.customerName}</TableCell>
                                <TableCell>{sale.medicationName}</TableCell>
                                <TableCell>{sale.quantity}</TableCell>
                                <TableCell>${sale.totalPrice.toFixed(2)}</TableCell>
                                <TableCell>{new Date(sale.saleDate).toLocaleString()}</TableCell>
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

export default AdminSalesPage;
