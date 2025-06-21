// src/pages/UserHomePage.tsx
import React, { useEffect, useState } from 'react';
import {
    Typography, Card, CardContent, CardActions, Button,
    Box, AppBar, Toolbar, Dialog, DialogTitle, DialogContent, DialogActions,
    TextField
} from '@mui/material';
import axios from '../api/axios';
import { useNavigate } from 'react-router-dom';

interface Medication {
    id: number;
    name: string;
    manufacturer: string;
    price: number;
    stockQuantity: number;
}

const UserHomePage: React.FC = () => {
    const [medications, setMedications] = useState<Medication[]>([]);
    const [balance, setBalance] = useState<number>(0);
    const [open, setOpen] = useState(false);
    const [cardNumber, setCardNumber] = useState('');
    const [expiryDate, setExpiryDate] = useState('');
    const [cvc, setCvc] = useState('');
    const [amount, setAmount] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        fetchDrugs();
        fetchBalance();
    }, []);

    const fetchDrugs = () => {
        axios.get('/drugs')
            .then(response => setMedications(response.data))
            .catch(error => console.error('Failed to fetch drugs:', error));
    };

    const fetchBalance = () => {
        axios.get('/users/me')
            .then(res => setBalance(res.data.balance))
            .catch(err => console.error('Failed to fetch balance:', err));
    };

    const handleBuy = (id: number) => {
        axios.post(`/users/buy/${id}`)
            .then(() => {
                alert('Purchase successful!');
                fetchBalance();
                fetchDrugs(); // Update stock after purchase
            })
            .catch(error => alert(`Error: ${error.response?.data?.message || 'Failed to buy medication'}`));
    };

    const handleDeposit = () => {
        axios.post('/users/deposit', {
            cardNumber,
            expiryDate,
            cvc,
            amount: parseFloat(amount),
        }).then(() => {
            alert('Deposit successful!');
            setOpen(false);
            fetchBalance();
            setCardNumber('');
            setExpiryDate('');
            setCvc('');
            setAmount('');
        }).catch(err => {
            alert(`Deposit failed: ${err.response?.data?.message || 'Unknown error'}`);
        });
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/login');
    };

    return (
        <>
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h6" sx={{ flexGrow: 1 }}>
                        User Dashboard
                    </Typography>
                    <Typography variant="body1" sx={{ marginRight: 2 }}>
                        Balance: ${balance.toFixed(2)}
                    </Typography>
                    <Button color="inherit" onClick={() => setOpen(true)}>
                        Deposit
                    </Button>
                    <Button color="inherit" onClick={handleLogout}>
                        Logout
                    </Button>
                </Toolbar>
            </AppBar>

            <Box padding="2rem">
                <Typography variant="h4" gutterBottom>
                    Available Medications
                </Typography>

                <Box display="flex" flexWrap="wrap" gap="1.5rem">
                    {medications.map((med) => (
                        <Box
                            key={med.id}
                            flex="1 1 calc(33.333% - 1rem)"
                            minWidth="250px"
                            maxWidth="100%"
                        >
                            <Card>
                                <CardContent>
                                    <Typography variant="h6">{med.name}</Typography>
                                    <Typography variant="body2" color="textSecondary">
                                        Manufacturer: {med.manufacturer}
                                    </Typography>
                                    <Typography variant="body2" color="textSecondary">
                                        In Stock: {med.stockQuantity}
                                    </Typography>
                                    <Typography variant="body2">
                                        Price: ${med.price.toFixed(2)}
                                    </Typography>
                                </CardContent>
                                <CardActions>
                                    <Button
                                        variant="contained"
                                        color="primary"
                                        onClick={() => handleBuy(med.id)}
                                        disabled={med.stockQuantity <= 0}
                                    >
                                        {med.stockQuantity > 0 ? 'Buy' : 'Out of Stock'}
                                    </Button>
                                </CardActions>
                            </Card>
                        </Box>
                    ))}
                </Box>
            </Box>

            <Dialog open={open} onClose={() => setOpen(false)}>
                <DialogTitle>Deposit Funds</DialogTitle>
                <DialogContent>
                    <TextField
                        label="Card Number"
                        fullWidth
                        margin="dense"
                        value={cardNumber}
                        onChange={(e) => setCardNumber(e.target.value)}
                    />
                    <TextField
                        label="Expiry Date (MM/YY)"
                        fullWidth
                        margin="dense"
                        value={expiryDate}
                        onChange={(e) => setExpiryDate(e.target.value)}
                    />
                    <TextField
                        label="CVC"
                        fullWidth
                        margin="dense"
                        value={cvc}
                        onChange={(e) => setCvc(e.target.value)}
                    />
                    <TextField
                        label="Amount"
                        type="number"
                        fullWidth
                        margin="dense"
                        value={amount}
                        onChange={(e) => setAmount(e.target.value)}
                    />
                </DialogContent>
                <DialogActions>
                    <Button onClick={() => setOpen(false)}>Cancel</Button>
                    <Button variant="contained" onClick={handleDeposit}>Confirm</Button>
                </DialogActions>
            </Dialog>
        </>
    );
};

export default UserHomePage;
