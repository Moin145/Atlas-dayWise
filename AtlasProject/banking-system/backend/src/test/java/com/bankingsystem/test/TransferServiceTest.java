package com.bankingsystem.test;

import com.bankingsystem.model.Account;
import com.bankingsystem.model.Customer;
import com.bankingsystem.model.Transaction;
import com.bankingsystem.repository.AccountRepository;
import com.bankingsystem.repository.TransactionRepository;
import com.bankingsystem.service.AuditService;
import com.bankingsystem.service.TransferService;
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
@DisplayName("Transfer Service Unit Tests")
class TransferServiceTest {

    @Mock
    private AccountRepository accountRepository;
    
    @Mock
    private TransactionRepository transactionRepository;
    
    @Mock
    private UndoRedoStack undoRedoStack;
    
    @Mock
    private AuditService auditService;
    
    @InjectMocks
    private TransferService transferService;
    
    private Account sourceAccount;
    private Account destinationAccount;
    private Customer sourceCustomer;
    private Customer destinationCustomer;
    private final String testUserId = "TEST_USER";

    @BeforeEach
    void setUp() {
        sourceCustomer = new Customer("Rajesh", "Kumar", "rajesh@test.com", 
                "9876543210", "123 MG Road", "Mumbai", "Maharashtra", "400001");
        sourceCustomer.setId("customer-1");
        
        destinationCustomer = new Customer("Priya", "Sharma", "priya@test.com", 
                "9876543211", "456 Brigade Road", "Bangalore", "Karnataka", "560001");
        destinationCustomer.setId("customer-2");
        
        sourceAccount = new Account("ACC123456", sourceCustomer, Account.AccountType.SAVINGS);
        sourceAccount.setId("account-1");
        sourceAccount.deposit(new BigDecimal("50000"));
        
        destinationAccount = new Account("ACC123457", destinationCustomer, Account.AccountType.CURRENT);
        destinationAccount.setId("account-2");
        destinationAccount.deposit(new BigDecimal("30000"));
    }

    @Test
    @DisplayName("Should process transfer successfully")
    void shouldProcessTransferSuccessfully() {
        // Given
        BigDecimal transferAmount = new BigDecimal("10000");
        String description = "Test transfer";
        
        when(accountRepository.findByAccountNumber("ACC123456"))
                .thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findByAccountNumber("ACC123457"))
                .thenReturn(Optional.of(destinationAccount));
        when(accountRepository.save(any(Account.class)))
                .thenReturn(sourceAccount, destinationAccount);
        when(transactionRepository.save(any(Transaction.class)))
                .thenReturn(new Transaction("TEST123", Transaction.TransactionType.TRANSFER, 
                        transferAmount, sourceAccount, description));
        
        // When
        Transaction transaction = transferService.processTransfer(
                "ACC123456", 
                "ACC123457", 
                transferAmount, 
                description, 
                testUserId
        );
        
        // Then
        assertThat(transaction).isNotNull();
        assertThat(transaction.getType()).isEqualTo(Transaction.TransactionType.TRANSFER);
        assertThat(transaction.getAmount()).isEqualTo(transferAmount);
        assertThat(transaction.getDescription()).isEqualTo(description);
        assertThat(transaction.getStatus()).isEqualTo(Transaction.TransactionStatus.COMPLETED);
        
        verify(accountRepository).findByAccountNumber("ACC123456");
        verify(accountRepository).findByAccountNumber("ACC123457");
        verify(accountRepository, times(2)).save(any(Account.class));
    }

    @Test
    @DisplayName("Should fail transfer with insufficient funds")
    void shouldFailTransferWithInsufficientFunds() {
        // Given
        BigDecimal excessiveAmount = new BigDecimal("100000");
        String description = "Test transfer";
        
        when(accountRepository.findByAccountNumber("ACC123456"))
                .thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findByAccountNumber("ACC123457"))
                .thenReturn(Optional.of(destinationAccount));
        
        // When & Then
        assertThatThrownBy(() -> transferService.processTransfer(
                "ACC123456", 
                "ACC123457", 
                excessiveAmount, 
                description, 
                testUserId
        )).isInstanceOf(RuntimeException.class)
          .hasMessageContaining("Insufficient balance");
        
        verify(accountRepository).findByAccountNumber("ACC123456");
        verify(accountRepository).findByAccountNumber("ACC123457");
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Should fail transfer to same account")
    void shouldFailTransferToSameAccount() {
        // Given
        BigDecimal transferAmount = new BigDecimal("1000");
        String description = "Test transfer";
        
        when(accountRepository.findByAccountNumber("ACC123456"))
                .thenReturn(Optional.of(sourceAccount));
        
        // When & Then
        assertThatThrownBy(() -> transferService.processTransfer(
                "ACC123456", 
                "ACC123456", 
                transferAmount, 
                description, 
                testUserId
        )).isInstanceOf(RuntimeException.class)
          .hasMessageContaining("Cannot transfer to the same account");
        
        verify(accountRepository, times(2)).findByAccountNumber("ACC123456");
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Should fail transfer with invalid source account")
    void shouldFailTransferWithInvalidSourceAccount() {
        // Given
        String invalidSourceAccount = "INVALID_SOURCE";
        BigDecimal transferAmount = new BigDecimal("1000");
        String description = "Test transfer";
        
        when(accountRepository.findByAccountNumber(invalidSourceAccount))
                .thenReturn(Optional.empty());
        
        // When & Then
        assertThatThrownBy(() -> transferService.processTransfer(
                invalidSourceAccount, 
                "ACC123457", 
                transferAmount, 
                description, 
                testUserId
        )).isInstanceOf(RuntimeException.class)
          .hasMessageContaining("Source account not found");
        
        verify(accountRepository).findByAccountNumber(invalidSourceAccount);
        verify(accountRepository, never()).findByAccountNumber("ACC123457");
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Should fail transfer with invalid destination account")
    void shouldFailTransferWithInvalidDestinationAccount() {
        // Given
        String invalidDestinationAccount = "INVALID_DEST";
        BigDecimal transferAmount = new BigDecimal("1000");
        String description = "Test transfer";
        
        when(accountRepository.findByAccountNumber("ACC123456"))
                .thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findByAccountNumber(invalidDestinationAccount))
                .thenReturn(Optional.empty());
        
        // When & Then
        assertThatThrownBy(() -> transferService.processTransfer(
                "ACC123456", 
                invalidDestinationAccount, 
                transferAmount, 
                description, 
                testUserId
        )).isInstanceOf(RuntimeException.class)
          .hasMessageContaining("Destination account not found");
        
        verify(accountRepository).findByAccountNumber("ACC123456");
        verify(accountRepository).findByAccountNumber(invalidDestinationAccount);
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Should fail transfer with negative amount")
    void shouldFailTransferWithNegativeAmount() {
        // Given
        BigDecimal negativeAmount = new BigDecimal("-1000");
        String description = "Test transfer";
        
        when(accountRepository.findByAccountNumber("ACC123456"))
                .thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findByAccountNumber("ACC123457"))
                .thenReturn(Optional.of(destinationAccount));
        
        // When & Then
        assertThatThrownBy(() -> transferService.processTransfer(
                "ACC123456", 
                "ACC123457", 
                negativeAmount, 
                description, 
                testUserId
        )).isInstanceOf(RuntimeException.class)
          .hasMessageContaining("Transfer amount must be greater than zero");
        
        verify(accountRepository).findByAccountNumber("ACC123456");
        verify(accountRepository).findByAccountNumber("ACC123457");
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    @DisplayName("Should fail transfer with zero amount")
    void shouldFailTransferWithZeroAmount() {
        // Given
        BigDecimal zeroAmount = BigDecimal.ZERO;
        String description = "Test transfer";
        
        when(accountRepository.findByAccountNumber("ACC123456"))
                .thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findByAccountNumber("ACC123457"))
                .thenReturn(Optional.of(destinationAccount));
        
        // When & Then
        assertThatThrownBy(() -> transferService.processTransfer(
                "ACC123456", 
                "ACC123457", 
                zeroAmount, 
                description, 
                testUserId
        )).isInstanceOf(RuntimeException.class)
          .hasMessageContaining("Transfer amount must be greater than zero");
        
        verify(accountRepository).findByAccountNumber("ACC123456");
        verify(accountRepository).findByAccountNumber("ACC123457");
        verify(accountRepository, never()).save(any(Account.class));
    }
}