import React, { useEffect, useState } from 'react';
import {
    Box, Typography, Button, Dialog, DialogTitle, DialogContent,
    DialogActions, TextField, Snackbar, Alert, Table, TableBody,
    TableCell, TableContainer, TableHead, TableRow, Paper
} from '@mui/material';
import axios from '../api/axios';

interface Drug {
    id: number;
    name: string;
    manufacturer: string;
    price: number;
    expirationDate: string;
    stockQuantity: number;
}

/**
 * AdminDrugsPage Component
 *
 * This component provides a management interface for administrators to view, create, edit, and delete drugs.
 *
 * Features:
 * - Displays a table of all drugs (name, manufacturer, price, expiration date, stock quantity)
 * - Allows creation of new drugs through a modal form
 * - Allows editing and deletion of existing drugs
 * - Provides snackbar notifications for user feedback (success/error)
 *
 * API Endpoints:
 * - GET `/drugs` — fetch all drugs
 * - POST `/drugs` — create new drug
 * - PUT `/drugs/{id}` — update drug
 * - DELETE `/drugs/{id}` — delete drug
 *
 * Technologies:
 * - React
 * - Material UI (Table, Dialog, Snackbar, Alert, etc.)
 * - Axios (preconfigured instance with JWT)
 *
 * Usage:
 * ```tsx
 * <AdminDrugsPage />
 * ```
 */

const AdminDrugsPage: React.FC = () => {
    const [drugs, setDrugs] = useState<Drug[]>([]);
    const [openDialog, setOpenDialog] = useState(false);
    const [newDrug, setNewDrug] = useState({
        name: '',
        manufacturer: '',
        price: '',
        expirationDate: '',
        stockQuantity: ''
    });
    const [editDrugId, setEditDrugId] = useState<number | null>(null);

    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState<'success' | 'error'>('success');

    useEffect(() => {
        fetchDrugs();
    }, []);

    const fetchDrugs = () => {
        axios.get('/drugs')
            .then(res => setDrugs(res.data))
            .catch(() => showSnackbar('Failed to fetch drugs', 'error'));
    };

    const handleDelete = (id: number) => {
        axios.delete(`/drugs/${id}`)
            .then(() => {
                showSnackbar('Drug deleted successfully');
                fetchDrugs();
            })
            .catch(() => showSnackbar('Failed to delete drug', 'error'));
    };

    const handleSave = () => {
        const payload = {
            name: newDrug.name,
            manufacturer: newDrug.manufacturer,
            price: parseFloat(newDrug.price || '0'),
            expirationDate: newDrug.expirationDate,
            stock_quantity: parseInt(newDrug.stockQuantity || '0'),
        };

        const request = editDrugId
            ? axios.put(`/drugs/${editDrugId}`, payload)
            : axios.post('/drugs', payload);

        request
            .then(() => {
                showSnackbar(editDrugId ? 'Drug updated successfully' : 'Drug created successfully');
                fetchDrugs();
                setOpenDialog(false);
                resetForm();
            })
            .catch((err) => {
                const msg = err.response?.data?.message || 'Failed to save drug';
                showSnackbar(msg, 'error');
            });
    };

    const resetForm = () => {
        setNewDrug({
            name: '',
            manufacturer: '',
            price: '',
            expirationDate: '',
            stockQuantity: ''
        });
        setEditDrugId(null);
    };

    const handleEdit = (drug: Drug) => {
        setNewDrug({
            name: drug.name || '',
            manufacturer: drug.manufacturer || '',
            price: drug.price?.toString() || '',
            expirationDate: drug.expirationDate || '',
            stockQuantity: drug.stockQuantity?.toString() || '',
        });
        setEditDrugId(drug.id);
        setOpenDialog(true);
    };

    const showSnackbar = (message: string, severity: 'success' | 'error' = 'success') => {
        setSnackbarMessage(message);
        setSnackbarSeverity(severity);
        setSnackbarOpen(true);
    };

    return (
        <Box padding="2rem">
            <Typography variant="h4" gutterBottom>
                Manage Drugs
            </Typography>

            <Button variant="contained" color="primary" onClick={() => { resetForm(); setOpenDialog(true); }} sx={{ mb: 2 }}>
                Add New Drug
            </Button>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>Name</TableCell>
                            <TableCell>Manufacturer</TableCell>
                            <TableCell>Price</TableCell>
                            <TableCell>Expiration Date</TableCell>
                            <TableCell>Stock</TableCell>
                            <TableCell>Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {drugs.map((drug) => (
                            <TableRow key={drug.id}>
                                <TableCell>{drug.name}</TableCell>
                                <TableCell>{drug.manufacturer}</TableCell>
                                <TableCell>${drug.price.toFixed(2)}</TableCell>
                                <TableCell>{drug.expirationDate}</TableCell>
                                <TableCell>{drug.stockQuantity}</TableCell>
                                <TableCell>
                                    <Button color="error" onClick={() => handleDelete(drug.id)}>
                                        Delete
                                    </Button>
                                    <Button color="primary" onClick={() => handleEdit(drug)}>
                                        Edit
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

            <Dialog open={openDialog} onClose={() => setOpenDialog(false)}>
                <DialogTitle>{editDrugId ? 'Edit Drug' : 'Create New Drug'}</DialogTitle>
                <DialogContent>
                    <TextField
                        label="Name"
                        fullWidth
                        margin="dense"
                        value={newDrug.name || ''}
                        onChange={(e) => setNewDrug({ ...newDrug, name: e.target.value })}
                    />
                    <TextField
                        label="Manufacturer"
                        fullWidth
                        margin="dense"
                        value={newDrug.manufacturer || ''}
                        onChange={(e) => setNewDrug({ ...newDrug, manufacturer: e.target.value })}
                    />
                    <TextField
                        label="Price"
                        type="number"
                        fullWidth
                        margin="dense"
                        value={newDrug.price || ''}
                        onChange={(e) => setNewDrug({ ...newDrug, price: e.target.value })}
                    />
                    <TextField
                        label="Expiration Date (YYYY-MM-DD)"
                        fullWidth
                        margin="dense"
                        value={newDrug.expirationDate || ''}
                        onChange={(e) => setNewDrug({ ...newDrug, expirationDate: e.target.value })}
                    />
                    <TextField
                        label="Stock Quantity"
                        type="number"
                        fullWidth
                        margin="dense"
                        value={newDrug.stockQuantity || ''}
                        onChange={(e) => setNewDrug({ ...newDrug, stockQuantity: e.target.value })}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setOpenDialog(false)}>Cancel</Button>
                    <Button variant="contained" onClick={handleSave}>{editDrugId ? 'Save Changes' : 'Create'}</Button>
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

export default AdminDrugsPage;
