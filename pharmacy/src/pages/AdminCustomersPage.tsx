import React, { useEffect, useState } from 'react';
import {
    Box,
    Typography,
    Paper,
    Table,
    TableHead,
    TableRow,
    TableCell,
    TableBody,
    TableContainer,
    CircularProgress
} from '@mui/material';
import axios from '../api/axios';

interface Customer {
    id: number;
    username: string;
    role: string;
    balance?: number; // Optional to avoid undefined errors
}

const AdminCustomersPage: React.FC = () => {
    const [customers, setCustomers] = useState<Customer[]>([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        axios.get('/users/customers')
            .then((res) => {
                setCustomers(res.data);
                setLoading(false);
            })
            .catch((err) => {
                console.error('Failed to fetch customers:', err);
                setLoading(false);
            });
    }, []);

    return (
        <Box padding="2rem">
            <Typography variant="h4" gutterBottom>
                Customer Accounts
            </Typography>

            {loading ? (
                <Box display="flex" justifyContent="center" alignItems="center" height="200px">
                    <CircularProgress />
                </Box>
            ) : (
                <TableContainer component={Paper}>
                    <Table>
                        <TableHead>
                            <TableRow>
                                <TableCell>ID</TableCell>
                                <TableCell>Username</TableCell>
                                <TableCell>Role</TableCell>
                                <TableCell>Balance ($)</TableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {customers.map((customer) => (
                                <TableRow key={customer.id}>
                                    <TableCell>{customer.id}</TableCell>
                                    <TableCell>{customer.username}</TableCell>
                                    <TableCell>{customer.role}</TableCell>
                                    <TableCell>{(customer.balance ?? 0).toFixed(2)}</TableCell>
                                </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </TableContainer>
            )}
        </Box>
    );
};

export default AdminCustomersPage;
