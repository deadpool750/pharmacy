/**
 * Custom React hook for handling user authentication.
 *
 * - Sends login request to the backend
 * - Stores JWT token in localStorage on success
 * - Redirects user to the appropriate homepage based on role ("ADMIN" or "CUSTOMER")
 * - Sets error state on failure
 *
 * @returns { login, error }
 * - `login`: function to authenticate with username and password
 * - `error`: current error message (if any)
 *
 * Usage:
 * ```tsx
 * const { login, error } = useAuth();
 * login({ username: "john", password: "secret" });
 * ```
 */

import { useState } from "react";
import { useNavigate } from "react-router-dom";

interface LoginRequestDto {
    username: string;
    password: string;
}

interface LoginResponseDto {
    token: string;
    role: string; // "ADMIN" or "CUSTOMER"
}

export const useAuth = () => {
    const [error, setError] = useState<string | null>(null);
    const navigate = useNavigate();

    const login = async (credentials: LoginRequestDto): Promise<void> => {
        try {
            if (!credentials.username || !credentials.password) {
                throw new Error("Username and password are required.");
            }

            console.debug("Sending login request with credentials:", credentials);

            const response = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(credentials),
            });

            if (!response.ok) {
                const errorText = await response.text();
                console.warn("Server responded with:", errorText);
                throw new Error("Invalid credentials");
            }

            const data: LoginResponseDto = await response.json();

            localStorage.setItem("token", data.token);
            setError(null);

            const role = data.role.toLowerCase();
            if (role === "admin") {
                navigate("/admin/home");
            } else {
                navigate("/user/home");
            }

        } catch (err: any) {
            console.error("Login failed:", err);
            setError("Login failed: " + err.message);
        }
    };

    return { login, error };
};
