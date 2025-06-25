// src/pages/UserHomePage.tsx
import React, { useEffect, useState } from 'react';
import {
    Typography, Card, CardContent, Button, Box, AppBar, Toolbar,
    Dialog, DialogTitle, DialogContent, DialogActions, TextField,
    List, ListItem, Divider, Snackbar, Alert
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

interface CartItem extends Medication {
    quantity: number;
}

/**
 * UserHomePage Component
 *
 * This component serves as the main interface for customers using the pharmacy application.
 *
 * Key Features:
 * - Displays a list of available medications, including name, manufacturer, stock, and price
 * - Allows users to add medications to a cart with quantity selection
 * - Calculates total cost and facilitates a "Buy All" purchase flow
 * - Shows the user's current account balance
 * - Includes a dialog to simulate deposits using fake card inputs
 * - Displays feedback messages using snackbars for success/error states
 * - Includes logout functionality and navigation using React Router
 *
 * Behavior:
 * - Fetches medication list and user balance on mount
 * - Handles cart state and updates quantities safely
 * - Prevents purchasing more than the available stock
 * - Communicates with backend via authenticated Axios instance
 *
 * Technologies Used:
 * - React, MUI (Material UI) for layout and UI components
 * - Axios for API communication
 * - React Router for navigation
 */


const UserHomePage: React.FC = () => {
    const [medications, setMedications] = useState<Medication[]>([]);
    const [balance, setBalance] = useState<number>(0);
    const [open, setOpen] = useState(false);
    const [cardNumber, setCardNumber] = useState('');
    const [expiryDate, setExpiryDate] = useState('');
    const [cvc, setCvc] = useState('');
    const [amount, setAmount] = useState('');
    const [cart, setCart] = useState<CartItem[]>([]);

    const [snackbarOpen, setSnackbarOpen] = useState(false);
    const [snackbarMessage, setSnackbarMessage] = useState('');
    const [snackbarSeverity, setSnackbarSeverity] = useState<'success' | 'error'>('success');

    const navigate = useNavigate();

    useEffect(() => {
        fetchDrugs();
        fetchBalance();
    }, []);

    const showSnackbar = (message: string, severity: 'success' | 'error' = 'success') => {
        setSnackbarMessage(message);
        setSnackbarSeverity(severity);
        setSnackbarOpen(true);
    };

    const fetchDrugs = () => {
        axios.get('/drugs')
            .then(response => setMedications(response.data))
            .catch(() => showSnackbar('Failed to fetch drugs', 'error'));
    };

    const fetchBalance = () => {
        axios.get('/users/me')
            .then(res => setBalance(res.data.balance))
            .catch(() => showSnackbar('Failed to fetch balance', 'error'));
    };

    const addToCart = (med: Medication) => {
        if (!cart.some(item => item.id === med.id)) {
            setCart(prev => [...prev, { ...med, quantity: 1 }]);
        }
    };

    const removeFromCart = (id: number) => {
        setCart(prev => prev.filter(item => item.id !== id));
    };

    const updateQuantity = (id: number, quantity: number, max: number) => {
        const safeQuantity = Math.max(1, Math.min(quantity, max));
        setCart(prev =>
            prev.map(item =>
                item.id === id ? { ...item, quantity: safeQuantity } : item
            )
        );
    };

    const handleBuyAll = () => {
        if (cart.length === 0) return;
        Promise.all(
            cart.map(item =>
                axios.post(`/users/buy/${item.id}`, { quantity: item.quantity })
            )
        )
            .then(() => {
                showSnackbar('Purchase successful!', 'success');
                setCart([]);
                fetchBalance();
                fetchDrugs();
            })
            .catch(err => {
                showSnackbar(`Purchase failed: ${err.response?.data?.message || 'Unknown error'}`, 'error');
            });
    };

    const handleDeposit = () => {
        axios.post('/users/deposit', {
            cardNumber,
            expiryDate,
            cvc,
            amount: parseFloat(amount),
        }).then(() => {
            showSnackbar('Deposit successful!', 'success');
            setOpen(false);
            fetchBalance();
            setCardNumber('');
            setExpiryDate('');
            setCvc('');
            setAmount('');
        }).catch(err => {
            showSnackbar(`Deposit failed: ${err.response?.data?.message || 'Unknown error'}`, 'error');
        });
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        navigate('/login');
    };

    const cartTotal = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);

    return (
        <>
            <AppBar position="static">
                <Toolbar>
                    <Typography variant="h6" sx={{ flexGrow: 1 }}>
                        Walter.White.inc
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

            <Box padding="2rem" display="flex" gap="3rem" alignItems="flex-start">
                <Box flex={2}>
                    <Typography variant="h4" gutterBottom>
                        Available Medications
                    </Typography>
                    <List>
                        {medications.map((med) => (
                            <ListItem key={med.id} disableGutters>
                                <Card sx={{ width: '100%', padding: '1rem' }}>
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
                                        <Box mt={1}>
                                            <Button
                                                variant="contained"
                                                disabled={
                                                    med.stockQuantity === 0 ||
                                                    cart.some(item => item.id === med.id)
                                                }
                                                onClick={() => addToCart(med)}
                                            >
                                                {cart.some(item => item.id === med.id)
                                                    ? 'In Cart'
                                                    : 'Add to Cart'}
                                            </Button>
                                        </Box>
                                    </CardContent>
                                </Card>
                            </ListItem>
                        ))}
                    </List>
                </Box>

                <Box flex={1}>
                    <Typography variant="h5" gutterBottom>
                        Cart ({cart.length})
                    </Typography>
                    <List>
                        {cart.map((item) => (
                            <ListItem key={item.id} divider>
                                <Box display="flex" justifyContent="space-between" alignItems="center" width="100%" gap={2}>
                                    <span>{item.name}</span>
                                    <TextField
                                        type="number"
                                        size="small"
                                        label="Qty"
                                        value={item.quantity}
                                        inputProps={{
                                            min: 1,
                                            max: item.stockQuantity,
                                        }}
                                        onChange={(e) =>
                                            updateQuantity(
                                                item.id,
                                                parseInt(e.target.value),
                                                item.stockQuantity
                                            )
                                        }
                                        sx={{ width: 80 }}
                                    />
                                    <span>${(item.price * item.quantity).toFixed(2)}</span>
                                    <Button size="small" onClick={() => removeFromCart(item.id)}>Remove</Button>
                                </Box>
                            </ListItem>
                        ))}
                    </List>
                    <Divider sx={{ my: 2 }} />
                    <Typography variant="body1" fontWeight="bold">
                        Total: ${cartTotal.toFixed(2)}
                    </Typography>
                    <Button
                        variant="contained"
                        color="success"
                        sx={{ mt: 2 }}
                        disabled={cart.length === 0}
                        onClick={handleBuyAll}
                    >
                        Buy All
                    </Button>
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

            <Snackbar
                open={snackbarOpen}
                autoHideDuration={4000}
                onClose={() => setSnackbarOpen(false)}
                anchorOrigin={{ vertical: 'bottom', horizontal: 'center' }}
            >
                <Alert
                    onClose={() => setSnackbarOpen(false)}
                    severity={snackbarSeverity}
                    sx={{ width: '100%' }}
                    variant="filled"
                >
                    {snackbarMessage}
                </Alert>
            </Snackbar>
        </>
    );
};

export default UserHomePage;
