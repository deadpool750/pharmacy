package com.example.pharmacy.service;

import com.example.pharmacy.controller.dto.sale.CreateSaleDto;
import com.example.pharmacy.controller.dto.user.CreateDepositRequestDto;
import com.example.pharmacy.controller.dto.user.CreateUserRequestDto;
import com.example.pharmacy.controller.dto.user.CreateUserResponseDto;
import com.example.pharmacy.controller.dto.user.UserResponseDto;
import com.example.pharmacy.infrastructure.entity.MedicationsEntity;
import com.example.pharmacy.infrastructure.entity.UserEntity;
import com.example.pharmacy.repository.IUserRepository;
import com.example.pharmacy.repository.DrugRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.pharmacy.controller.dto.user.UserResponseDto;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final DrugRepository drugRepository;
    private final PasswordEncoder passwordEncoder;
    private final SaleService saleService; // ← add this

    public UserService(
            IUserRepository userRepository,
            DrugRepository drugRepository,
            PasswordEncoder passwordEncoder,
            SaleService saleService // ← add this
    ) {
        this.userRepository = userRepository;
        this.drugRepository = drugRepository;
        this.passwordEncoder = passwordEncoder;
        this.saleService = saleService; // ← assign it
    }

    public UserResponseDto getUser(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserResponseDto(user.getId(), user.getUsername(), user.getRole(), user.getBalance());
    }

    public CreateUserResponseDto createUser(CreateUserRequestDto dto) {
        var user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole() != null ? dto.getRole() : "CUSTOMER");
        user.setBalance(BigDecimal.ZERO);
        userRepository.save(user);
        return new CreateUserResponseDto(user.getId(), user.getUsername());
    }


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



    private UserEntity getUserByPrincipal(Principal principal) {
        return userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

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

    private boolean isValidCard(String cardNumber, String expiryDate, String cvc) {
        return cardNumber != null && cardNumber.matches("\\d{16}") &&
                expiryDate != null && expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}") &&
                cvc != null && cvc.matches("\\d{3}");
    }

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

}
