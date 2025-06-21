// src/pages/AdminHomePage.tsx
import React from 'react';
import {
    Box, Typography, Card, CardContent, Button, AppBar, Toolbar
} from '@mui/material';
import { useNavigate } from 'react-router-dom';

const AdminHomePage: React.FC = () => {
    const navigate = useNavigate();

    const sections = [
        {
            title: 'Customers',
            description: 'View all registered customers',
            route: '/admin/customers'
        },
        {
            title: 'Employees',
            description: 'View employees and their roles',
            route: '/admin/employees'
        },
        {
            title: 'Suppliers',
            description: 'View suppliers and contact details',
            route: '/admin/suppliers'
        },
        {
            title: 'Sales',
            description: 'View sales transactions and history',
            route: '/admin/sales'
        },
        {
            title: 'Drugs',
            description: 'View, create and delete drug records',
            route: '/admin/drugs'
        }
    ];

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/login');
    };

    return (
        <>
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h6" sx={{ flexGrow: 1 }}>
                        Admin Dashboard
                    </Typography>
                    <Button color="inherit" onClick={handleLogout}>
                        Logout
                    </Button>
                </Toolbar>
            </AppBar>

            <Box padding="2rem">
                <Typography variant="h4" gutterBottom>
                    Admin Dashboard
                </Typography>

                <Box display="flex" flexWrap="wrap" gap="1.5rem">
                    {sections.map((section) => (
                        <Box
                            key={section.title}
                            flex="1 1 calc(33.333% - 1rem)"
                            minWidth="250px"
                            maxWidth="100%"
                        >
                            <Card sx={{ height: '100%' }}>
                                <CardContent>
                                    <Typography variant="h6">{section.title}</Typography>
                                    <Typography variant="body2" color="textSecondary">
                                        {section.description}
                                    </Typography>
                                    <Button
                                        variant="contained"
                                        color="primary"
                                        onClick={() => navigate(section.route)}
                                        sx={{ mt: 2 }}
                                        fullWidth
                                    >
                                        Go to {section.title}
                                    </Button>
                                </CardContent>
                            </Card>
                        </Box>
                    ))}
                </Box>
            </Box>
        </>
    );
};

export default AdminHomePage;
