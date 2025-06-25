package com.example.pharmacy.repository;

import com.example.pharmacy.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing user data.
 * Provides methods for retrieving users by username and role.
 */
@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the user if found
     */
    Optional<UserEntity> findByUsername(String username);

    /**
     * Finds all users with the specified role.
     *
     * @param role the role to filter by (e.g., "CUSTOMER", "ADMIN")
     * @return a list of users with the specified role
     */
    List<UserEntity> findByRole(String role);
}
