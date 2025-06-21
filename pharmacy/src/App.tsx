import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Register from "./pages/Register";
import UserHomePage from "./pages/UserHomePage";
import AdminHomePage from "./pages/AdminHomePage";

import AdminDrugsPage from "./pages/AdminDrugsPage";
import AdminCustomersPage from "./pages/AdminCustomersPage";
import AdminEmployeesPage from "./pages/AdminEmployeesPage";
import AdminSuppliersPage from "./pages/AdminSuppliersPage";
import AdminSalesPage from "./pages/AdminSalesPage";

export default function App() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/login" />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />

            <Route path="/user/home" element={<UserHomePage />} />

            <Route path="/admin/home" element={<AdminHomePage />} />
            <Route path="/admin/drugs" element={<AdminDrugsPage />} />
            <Route path="/admin/customers" element={<AdminCustomersPage />} />
            <Route path="/admin/employees" element={<AdminEmployeesPage />} />
            <Route path="/admin/suppliers" element={<AdminSuppliersPage />} />
            <Route path="/admin/sales" element={<AdminSalesPage />} />
        </Routes>
    );
}
