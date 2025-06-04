import React from "react";
import { Container, Typography, Box } from "@mui/material";

const HomePage: React.FC = () => {
    return (
        <Container maxWidth="sm">
            <Box
                display="flex"
                flexDirection="column"
                alignItems="center"
                justifyContent="center"
                minHeight="100vh"
            >
                <Typography variant="h2" component="h1" gutterBottom>
                    Hello
                </Typography>
            </Box>
        </Container>
    );
};

export default HomePage;
