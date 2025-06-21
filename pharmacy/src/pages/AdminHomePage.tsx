// src/pages/AdminHomePage.tsx
import React from 'react';
import { Box, Typography, Card, CardContent, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

const AdminHomePage: React.FC = () => {
    const navigate = useNavigate();

    const sections = [
        { title: 'Customers', route: '/admin/customers' },
        { title: 'Employees', route: '/admin/employees' },
        { title: 'Suppliers', route: '/admin/suppliers' },
        { title: 'Sales', route: '/admin/sales' },
        { title: 'Drugs', route: '/admin/drugs' },
    ];

    return (
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
                        <Card>
                            <CardContent>
                                <Typography variant="h6">{section.title}</Typography>
                                <Button
                                    variant="contained"
                                    color="primary"
                                    onClick={() => navigate(section.route)}
                                    style={{ marginTop: '1rem' }}
                                >
                                    View {section.title}
                                </Button>
                            </CardContent>
                        </Card>
                    </Box>
                ))}
            </Box>
        </Box>
    );
};

export default AdminHomePage;
