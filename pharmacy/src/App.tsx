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
import MePage from "./pages/MePage";

/**
 * App Component
 *
 * Main routing configuration for the pharmacy application using React Router.
 *
 * Routes:
 * - "/" → Redirects to login page
 * - "/login" → Login screen
 * - "/register" → Registration screen
 * - "/user/home" → Customer dashboard (view drugs, cart, deposit, buy)
 * - "/user/me" → Profile page for editing user data (email, password)
 * - "/admin/home" → Admin dashboard with navigation to management panels
 * - "/admin/drugs" → Manage drugs (CRUD operations)
 * - "/admin/customers" → View registered customer accounts
 * - "/admin/employees" → Manage employee records
 * - "/admin/suppliers" → Manage supplier details
 * - "/admin/sales" → View sales transaction history
 *
 * Purpose:
 * Provides centralized routing to separate admin and customer flows, as well as entry points (login/register).
 * Access control (e.g., protecting routes) is assumed to be handled elsewhere (e.g., by checking tokens).
 */

export default function App() {
    return (
        <Routes>
            <Route path="/" element={<Navigate to="/login" />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />

            <Route path="/user/home" element={<UserHomePage />} />
            <Route path="/user/me" element={<MePage />} />

            <Route path="/admin/home" element={<AdminHomePage />} />
            <Route path="/admin/drugs" element={<AdminDrugsPage />} />
            <Route path="/admin/customers" element={<AdminCustomersPage />} />
            <Route path="/admin/employees" element={<AdminEmployeesPage />} />
            <Route path="/admin/suppliers" element={<AdminSuppliersPage />} />
            <Route path="/admin/sales" element={<AdminSalesPage />} />
        </Routes>
    );
}
