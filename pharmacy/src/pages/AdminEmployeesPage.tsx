import React, { useEffect, useState } from 'react';
import {
    Box, Typography, Table, TableBody, TableCell, TableContainer,
    TableHead, TableRow, Paper, Snackbar, Alert
} from '@mui/material';
import axios from '../api/axios';

interface Employee {
    id: number;
    name: string;
    position: string;
    salary: number;
    hireDate: string; // ISO date
}

const AdminEmployeesPage: React.FC = () => {
    const [employees, setEmployees] = useState<Employee[]>([]);
    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState<'success' | 'error'>('success');

    useEffect(() => {
        axios.get('/employees')
            .then(res => setEmployees(res.data))
            .catch(() => showSnackbar('Failed to fetch employees', 'error'));
    }, []);

    const showSnackbar = (message: string, severity: 'success' | 'error' = 'success') => {
        setSnackbarMessage(message);
        setSnackbarSeverity(severity);
        setSnackbarOpen(true);
    };

    return (
        <Box padding="2rem">
            <Typography variant="h4" gutterBottom>
                Employees
            </Typography>

            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>Name</TableCell>
                            <TableCell>Position</TableCell>
                            <TableCell>Salary</TableCell>
                            <TableCell>Hire Date</TableCell>
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

export default AdminEmployeesPage;
