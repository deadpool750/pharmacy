// src/pages/AdminSuppliersPage.tsx
import React, { useEffect, useState } from 'react';
import {
    Box, Typography, Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Snackbar, Alert, Button, Dialog, DialogTitle,
    DialogContent, TextField, DialogActions
} from '@mui/material';
import axios from '../api/axios';

interface Supplier {
    id: number;
    name: string;
    phone: string;
    email: string;
    address: string;
}

/**
 * AdminSuppliersPage Component
 *
 * This admin interface allows viewing and managing suppliers.
 * Admins can:
 * - Fetch and display all supplier entries from the backend (`/suppliers`)
 * - Add a new supplier using a dialog form
 * - Edit existing supplier details
 * - Get feedback via snackbar notifications
 *
 * State:
 * - `suppliers`: list of all suppliers fetched from the server
 * - `form`: holds the input data for create/update actions
 * - `editSupplier`: tracks the supplier being edited (null means create mode)
 *
 * Dependencies:
 * - React (useState, useEffect)
 * - Axios for HTTP communication
 * - MUI for UI components like Table, Dialog, Snackbar, etc.
 *
 * Usage:
 * ```tsx
 * <AdminSuppliersPage />
 * ```
 */


const AdminSuppliersPage: React.FC = () => {
    const [suppliers, setSuppliers] = useState<Supplier[]>([]);
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState<'success' | 'error'>('success');

    const [dialogOpen, setDialogOpen] = useState(false);
    const [editSupplier, setEditSupplier] = useState<Supplier | null>(null);
    const [form, setForm] = useState({ name: '', phone: '', email: '', address: '' });

    useEffect(() => {
        fetchSuppliers();
    }, []);

    const fetchSuppliers = () => {
        axios.get('/suppliers')
            .then(res => setSuppliers(res.data))
            .catch(() => showSnackbar('Failed to fetch suppliers', 'error'));
    };

    const showSnackbar = (message: string, severity: 'success' | 'error' = 'success') => {
        setSnackbarMessage(message);
        setSnackbarSeverity(severity);
        setSnackbarOpen(true);
    };

    const handleDialogOpen = (supplier?: Supplier) => {
        if (supplier) {
            setEditSupplier(supplier);
            setForm({ name: supplier.name, phone: supplier.phone, email: supplier.email, address: supplier.address });
        } else {
            setEditSupplier(null);
            setForm({ name: '', phone: '', email: '', address: '' });
        }
        setDialogOpen(true);
    };

    const handleDialogClose = () => {
        setDialogOpen(false);
    };

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSave = () => {
        if (editSupplier) {
            axios.put(`/suppliers/${editSupplier.id}`, form)
                .then(() => {
                    showSnackbar('Supplier updated', 'success');
                    fetchSuppliers();
                })
                .catch(() => showSnackbar('Update failed', 'error'));
        } else {
            axios.post('/suppliers', form)
                .then(() => {
                    showSnackbar('Supplier created', 'success');
                    fetchSuppliers();
                })
                .catch(() => showSnackbar('Creation failed', 'error'));
        }
        handleDialogClose();
    };

    return (
        <Box padding="2rem">
            <Typography variant="h4" gutterBottom>
                Suppliers
            </Typography>

            <Button variant="contained" sx={{ mb: 2 }} onClick={() => handleDialogOpen()}>
                Add Supplier
            </Button>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Phone</TableCell>
                            <TableCell>Email</TableCell>
                            <TableCell>Address</TableCell>
                            <TableCell>Actions</TableCell>
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
                                <TableCell>
                                    <Button size="small" onClick={() => handleDialogOpen(supplier)}>Edit</Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

            <Dialog open={dialogOpen} onClose={handleDialogClose} fullWidth maxWidth="sm">
                <DialogTitle>{editSupplier ? 'Edit Supplier' : 'Add Supplier'}</DialogTitle>
                <DialogContent>
                    <TextField
                        fullWidth
                        label="Name"
                        name="name"
                        value={form.name}
                        onChange={handleInputChange}
                        margin="dense"
                    />
                    <TextField
                        fullWidth
                        label="Phone"
                        name="phone"
                        value={form.phone}
                        onChange={handleInputChange}
                        margin="dense"
                    />
                    <TextField
                        fullWidth
                        label="Email"
                        name="email"
                        value={form.email}
                        onChange={handleInputChange}
                        margin="dense"
                    />
                    <TextField
                        fullWidth
                        label="Address"
                        name="address"
                        value={form.address}
                        onChange={handleInputChange}
                        margin="dense"
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleDialogClose}>Cancel</Button>
                    <Button onClick={handleSave} variant="contained">Save</Button>
                </DialogActions>
            </Dialog>

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
