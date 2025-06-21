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

            // 🔁 Navigate based on role (case-insensitive)
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
