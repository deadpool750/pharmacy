import React, { useEffect, useState } from 'react';
import {
    Box, Typography, Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Snackbar, Alert
} from '@mui/material';
import axios from '../api/axios';

interface Sale {
    id: number;
    customerId: number;
    medicationId: number;
    quantity: number;
    totalPrice: number;
    saleDate: string;
}

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
                            <TableCell>Customer ID</TableCell>
                            <TableCell>Medication ID</TableCell>
                            <TableCell>Quantity</TableCell>
                            <TableCell>Total Price</TableCell>
                            <TableCell>Sale Date</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {sales.map((sale) => (
                            <TableRow key={sale.id}>
                                <TableCell>{sale.id}</TableCell>
                                <TableCell>{sale.customerId}</TableCell>
                                <TableCell>{sale.medicationId}</TableCell>
                                <TableCell>{sale.quantity}</TableCell>
                                <TableCell>${sale.totalPrice.toFixed(2)}</TableCell>
                                <TableCell>{sale.saleDate}</TableCell>
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
