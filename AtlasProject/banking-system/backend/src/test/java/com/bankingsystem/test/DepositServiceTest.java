package com.bankingsystem.test;

import com.bankingsystem.model.Account;
import com.bankingsystem.model.Customer;
import com.bankingsystem.model.Transaction;
import com.bankingsystem.repository.AccountRepository;
import com.bankingsystem.repository.CustomerRepository;
import com.bankingsystem.repository.TransactionRepository;
import com.bankingsystem.service.AuditService;
import com.bankingsystem.service.DepositService;
import com.bankingsystem.util.UndoRedoStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Deposit Service Unit Tests")
class DepositServiceTest {

    @Mock
    private AccountRepository accountRepository;
    
    @Mock
    private CustomerRepository customerRepository;
    
    @Mock
    private TransactionRepository transactionRepository;
    
    @Mock
    private UndoRedoStack undoRedoStack;
    
    @Mock
    private AuditService auditService;
    
    @InjectMocks
    private DepositService depositService;
    
    private Account testAccount;
    private Customer testCustomer;
    private final String testUserId = "TEST_USER";

    @BeforeEach
    void setUp() {
        testCustomer = new Customer("Rajesh", "Kumar", "rajesh@test.com", 
                "9876543210", "123 MG Road", "Mumbai", "Maharashtra", "400001");
        testCustomer.setId("customer-123");
        
        testAccount = new Account("ACC123456", testCustomer, Account.AccountType.SAVINGS);
        testAccount.setId("account-123");
        testAccount.deposit(new BigDecimal("50000"));
    }

    @Test
    @DisplayName("Should process deposit successfully")
    void shouldProcessDepositSuccessfully() {
        // Given
        BigDecimal depositAmount = new BigDecimal("10000");
        String description = "Test deposit";
        
        when(accountRepository.findByAccountNumber("ACC123456"))
                .thenReturn(Optional.of(testAccount));
        when(accountRepository.save(any(Account.class)))
                .thenReturn(testAccount);
        when(transactionRepository.save(any(Transaction.class)))
                .thenReturn(new Transaction("TEST123", Transaction.TransactionType.DEPOSIT, 
                        depositAmount, testAccount, description));
        
        // When
        Transaction transaction = depositService.processDeposit(
                "ACC123456", 
                depositAmount, 
                description, 
                testUserId
        );
        
        // Then
        assertThat(transaction).isNotNull();
        assertThat(transaction.getType()).isEqualTo(Transaction.TransactionType.DEPOSIT);
        assertThat(transaction.getAmount()).isEqualTo(depositAmount);
        assertThat(transaction.getDescription()).isEqualTo(description);
        assertThat(transaction.getStatus()).isEqualTo(Transaction.TransactionStatus.COMPLETED);
        
        verify(accountRepository).findByAccountNumber("ACC123456");
        verify(accountRepository).save(testAccount);
    }

    @Test
    @DisplayName("Should throw exception for invalid account")
    void shouldThrowExceptionForInvalidAccount() {
        // Given
        String invalidAccountNumber = "INVALID_ACC";
        BigDecimal depositAmount = new BigDecimal("1000");
        String description = "Test deposit";
        
        when(accountRepository.findByAccountNumber(invalidAccountNumber))
                .thenReturn(Optional.empty());
        
        // When & Then
        assertThatThrownBy(() -> depositService.processDeposit(
                invalidAccountNumber, 
                depositAmount, 
                description, 
                testUserId
        )).isInstanceOf(RuntimeException.class)
          .hasMessageContaining("Account not found");
        
        verify(accountRepository).findByAccountNumber(invalidAccountNumber);
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Should throw exception for negative amount")
    void shouldThrowExceptionForNegativeAmount() {
        // Given
        BigDecimal negativeAmount = new BigDecimal("-1000");
        String description = "Test deposit";
        
        when(accountRepository.findByAccountNumber("ACC123456"))
                .thenReturn(Optional.of(testAccount));
        
        // When & Then
        assertThatThrownBy(() -> depositService.processDeposit(
                "ACC123456", 
                negativeAmount, 
                description, 
                testUserId
        )).isInstanceOf(RuntimeException.class)
          .hasMessageContaining("Deposit amount must be greater than zero");
        
        verify(accountRepository).findByAccountNumber("ACC123456");
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Should throw exception for zero amount")
    void shouldThrowExceptionForZeroAmount() {
        // Given
        BigDecimal zeroAmount = BigDecimal.ZERO;
        String description = "Test deposit";
        
        when(accountRepository.findByAccountNumber("ACC123456"))
                .thenReturn(Optional.of(testAccount));
        
        // When & Then
        assertThatThrownBy(() -> depositService.processDeposit(
                "ACC123456", 
                zeroAmount, 
                description, 
                testUserId
        )).isInstanceOf(RuntimeException.class)
          .hasMessageContaining("Deposit amount must be greater than zero");
        
        verify(accountRepository).findByAccountNumber("ACC123456");
        verify(accountRepository, never()).save(any(Account.class));
    }
}