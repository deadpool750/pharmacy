import React, { useEffect, useState } from 'react';
import {
    Box, Typography, Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Snackbar, Alert,
    Button, Dialog, DialogTitle, DialogContent, DialogActions, TextField
} from '@mui/material';
import axios from '../api/axios';

interface Employee {
    id?: number;
    name: string;
    position: string;
    salary: number;
    hireDate: string;
}

/**
 * AdminEmployeesPage Component
 *
 * This page allows administrators to view and manage employee records.
 * Features include:
 * - Viewing a table of all employees
 * - Adding new employees via a dialog form
 * - Editing existing employee data
 * - Displaying success/error notifications using Material-UI Snackbars
 *
 * API Endpoints:
 * - GET `/employees` — fetch all employees
 * - POST `/employees` — create new employee
 * - PUT `/employees/{id}` — update existing employee
 *
 * Technologies Used:
 * - React (with hooks)
 * - Material UI (Table, Dialog, Snackbar, TextField, etc.)
 * - Axios (custom instance with JWT handling)
 *
 * Usage:
 * ```tsx
 * <AdminEmployeesPage />
 * ```
 */

const AdminEmployeesPage: React.FC = () => {
    const [employees, setEmployees] = useState<Employee[]>([]);
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState<'success' | 'error'>('success');
    const [dialogOpen, setDialogOpen] = useState(false);
    const [editingEmployee, setEditingEmployee] = useState<Employee | null>(null);

    useEffect(() => {
        fetchEmployees();
    }, []);

    const fetchEmployees = () => {
        axios.get('/employees')
            .then(res => setEmployees(res.data))
            .catch(() => showSnackbar('Failed to fetch employees', 'error'));
    };

    const showSnackbar = (message: string, severity: 'success' | 'error' = 'success') => {
        setSnackbarMessage(message);
        setSnackbarSeverity(severity);
        setSnackbarOpen(true);
    };

    const handleOpenDialog = (employee?: Employee) => {
        if (employee) {
            setEditingEmployee({ ...employee });
        } else {
            setEditingEmployee({ name: '', position: '', salary: 0, hireDate: '' });
        }
        setDialogOpen(true);
    };

    const handleCloseDialog = () => {
        setDialogOpen(false);
        setEditingEmployee(null);
    };

    const handleSave = () => {
        if (!editingEmployee) return;

        const request = editingEmployee.id
            ? axios.put(`/employees/${editingEmployee.id}`, editingEmployee)
            : axios.post('/employees', editingEmployee);

        request
            .then(() => {
                fetchEmployees();
                showSnackbar(`Employee ${editingEmployee.id ? 'updated' : 'added'} successfully`);
                handleCloseDialog();
            })
            .catch(() => showSnackbar('Failed to save employee', 'error'));
    };

    return (
        <Box padding="2rem">
            <Typography variant="h4" gutterBottom>
                Employees
            </Typography>

            <Button variant="contained" onClick={() => handleOpenDialog()} sx={{ mb: 2 }}>
                Add Employee
            </Button>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Position</TableCell>
                            <TableCell>Salary</TableCell>
                            <TableCell>Hire Date</TableCell>
                            <TableCell>Edit</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {employees.map((emp) => (
                            <TableRow key={emp.id}>
                                <TableCell>{emp.id}</TableCell>
                                <TableCell>{emp.name}</TableCell>
                                <TableCell>{emp.position}</TableCell>
                                <TableCell>${emp.salary.toFixed(2)}</TableCell>
                                <TableCell>{emp.hireDate}</TableCell>
                                <TableCell>
                                    <Button variant="outlined" size="small" onClick={() => handleOpenDialog(emp)}>
                                        Edit
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

            {/* Employee Dialog */}
            <Dialog open={dialogOpen} onClose={handleCloseDialog} fullWidth maxWidth="sm">
                <DialogTitle>{editingEmployee?.id ? 'Edit Employee' : 'Add Employee'}</DialogTitle>
                <DialogContent>
                    <Box display="flex" flexDirection="column" gap={2} mt={1}>
                        <TextField
                            label="Name"
                            value={editingEmployee?.name || ''}
                            onChange={(e) => setEditingEmployee({ ...editingEmployee!, name: e.target.value })}
                        />
                        <TextField
                            label="Position"
                            value={editingEmployee?.position || ''}
                            onChange={(e) => setEditingEmployee({ ...editingEmployee!, position: e.target.value })}
                        />
                        <TextField
                            label="Salary"
                            type="number"
                            value={editingEmployee?.salary || ''}
                            onChange={(e) => setEditingEmployee({ ...editingEmployee!, salary: parseFloat(e.target.value) || 0 })}
                        />
                        <TextField
                            label="Hire Date"
                            type="date"
                            InputLabelProps={{ shrink: true }}
                            value={editingEmployee?.hireDate || ''}
                            onChange={(e) => setEditingEmployee({ ...editingEmployee!, hireDate: e.target.value })}
                        />
                    </Box>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleCloseDialog}>Cancel</Button>
                    <Button variant="contained" onClick={handleSave}>Save</Button>
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

export default AdminEmployeesPage;
