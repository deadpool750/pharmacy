package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.sale.CreateSaleDto;
import com.example.pharmacy.controller.dto.user.*;
import com.example.pharmacy.infrastructure.entity.MedicationsEntity;
import com.example.pharmacy.infrastructure.entity.UserEntity;
import com.example.pharmacy.repository.IUserRepository;
import com.example.pharmacy.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsible for user-related operations, including user creation, balance management,
 * purchasing medications, and fetching user data.
 */
@Service
public class UserService {

    private final IUserRepository userRepository;
    private final DrugRepository drugRepository;
    private final PasswordEncoder passwordEncoder;
    private final SaleService saleService;

    /**
     * Constructs a UserService with necessary dependencies.
     *
     * @param userRepository   repository for user persistence
     * @param drugRepository   repository for medications
     * @param passwordEncoder  encoder for secure password storage
     * @param saleService      service to record sales
     */
    @Autowired
    public UserService(
            IUserRepository userRepository,
            DrugRepository drugRepository,
            PasswordEncoder passwordEncoder,
            SaleService saleService
    ) {
        this.userRepository = userRepository;
        this.drugRepository = drugRepository;
        this.passwordEncoder = passwordEncoder;
        this.saleService = saleService;
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id user ID
     * @return user response DTO
     */
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDto getUser(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDto(user.getId(), user.getUsername(), user.getRole(), user.getBalance());
    }

    /**
     * Registers a new user.
     *
     * @param dto user creation request
     * @return newly created user response
     */
    public CreateUserResponseDto createUser(CreateUserRequestDto dto) {
        var user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole() != null ? dto.getRole() : "CUSTOMER");
        user.setBalance(BigDecimal.ZERO);
        userRepository.save(user);
        return new CreateUserResponseDto(user.getId(), user.getUsername());
    }

    /**
     * Processes a medication purchase by a user.
     *
     * @param principal     authenticated user principal
     * @param medicationId  ID of the medication to buy
     * @param quantity      number of units to buy
     */
    @PreAuthorize("hasRole('CUSTOMER')")
    public void buyMedication(Principal principal, Long medicationId, int quantity) {
        var user = getUserByPrincipal(principal);
        MedicationsEntity drug = drugRepository.findById(medicationId)
                .orElseThrow(() -> new RuntimeException("Medication not found"));

        if (quantity <= 0) {
            throw new RuntimeException("Quantity must be at least 1");
        }

        if (drug.getStockQuantity() < quantity) {
            throw new RuntimeException("Not enough stock available");
        }

        BigDecimal totalPrice = drug.getPrice().multiply(BigDecimal.valueOf(quantity));

        if (totalPrice.compareTo(user.getBalance()) > 0) {
            throw new RuntimeException("Insufficient funds");
        }

        user.setBalance(user.getBalance().subtract(totalPrice));
        userRepository.save(user);

        drug.setStockQuantity(drug.getStockQuantity() - quantity);
        drugRepository.save(drug);

        CreateSaleDto saleDto = new CreateSaleDto();
        saleDto.setCustomerId(user.getId().intValue());
        saleDto.setMedicationId(drug.getId());
        saleDto.setQuantity(quantity);
        saleDto.setTotalPrice(totalPrice);

        saleService.create(saleDto);
    }

    /**
     * Retrieves a UserEntity by Principal (usually from authentication).
     *
     * @param principal security principal
     * @return corresponding user
     */
    private UserEntity getUserByPrincipal(Principal principal) {
        return userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Retrieves a UserEntity by username.
     *
     * @param username the user's username
     * @return the user entity
     */
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    /**
     * Deposits money into a user's balance after validating fake card details.
     *
     * @param principal authenticated user
     * @param dto       deposit request
     */
    @PreAuthorize("hasRole('CUSTOMER')")
    public void depositMoney(Principal principal, CreateDepositRequestDto dto) {
        UserEntity user = getUserByPrincipal(principal);

        if (!isValidCard(dto.getCardNumber(), dto.getExpiryDate(), dto.getCvc())) {
            throw new RuntimeException("Invalid card details");
        }

        if (dto.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be positive");
        }

        user.setBalance(user.getBalance().add(dto.getAmount()));
        userRepository.save(user);
    }

    /**
     * Validates a mock credit card number, expiry date, and CVC.
     *
     * @param cardNumber card number (16-digit)
     * @param expiryDate expiration date in MM/YY format
     * @param cvc        3-digit CVC
     * @return true if card details are valid
     */
    private boolean isValidCard(String cardNumber, String expiryDate, String cvc) {
        return cardNumber != null && cardNumber.matches("\\d{16}") &&
                expiryDate != null && expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}") &&
                cvc != null && cvc.matches("\\d{3}");
    }

    /**
     * Returns a list of users filtered by role.
     *
     * @param role user role to filter (e.g., "CUSTOMER", "ADMIN")
     * @return list of matching users
     */
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDto> getUsersByRole(String role) {
        return userRepository.findByRole(role).stream()
                .map(user -> new UserResponseDto(
                        user.getId(),
                        user.getUsername(),
                        user.getRole(),
                        user.getBalance()
                ))
                .collect(Collectors.toList());
    }

    public void updateUser(Principal principal, UpdateUserRequestDto dto) {
        var user = getUserByPrincipal(principal);

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getUsername() != null && !dto.getUsername().isBlank()) {
            user.setUsername(dto.getUsername());
        }

        userRepository.save(user);
    }
}
