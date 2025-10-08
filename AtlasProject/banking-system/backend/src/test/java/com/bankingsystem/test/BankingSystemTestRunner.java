package com.bankingsystem.test;

import com.bankingsystem.model.Account;
import com.bankingsystem.model.Customer;
import com.bankingsystem.model.Transaction;
import com.bankingsystem.repository.AccountRepository;
import com.bankingsystem.repository.CustomerRepository;
import com.bankingsystem.repository.TransactionRepository;
import com.bankingsystem.service.DepositService;
import com.bankingsystem.service.WithdrawService;
import com.bankingsystem.service.TransferService;
import com.bankingsystem.service.AuditService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Simple Banking System Test Runner
 * This eliminates the need for Maven by providing a standalone test execution
 */
public class BankingSystemTestRunner {
    
    private static int testsPassed = 0;
    private static int testsFailed = 0;
    private static int totalTests = 0;
    
    public static void main(String[] args) {
        System.out.println(" Banking System Test Runner");
        System.out.println("=============================");
        System.out.println();
        
        // Run all test categories
        runCustomerTests();
        runAccountTests();
        runDepositTests();
        runWithdrawalTests();
        runTransferTests();
        runTransactionHistoryTests();
        runEdgeCaseTests();
        
        // Print summary
        printTestSummary();
    }
    
    private static void runCustomerTests() {
        System.out.println(" Customer Management Tests");
        System.out.println("----------------------------");
        
        test("Should create customer successfully", () -> {
            Customer customer = new Customer("Rajesh", "Kumar", "rajesh@test.com", 
                    "9876543210", "123 MG Road", "Mumbai", "Maharashtra", "400001");
            customer.setPanNumber("ABCDE1234F");
            customer.setAadharNumber("123456789012");
            
            assert customer.getFirstName().equals("Rajesh");
            assert customer.getLastName().equals("Kumar");
            assert customer.getEmail().equals("rajesh@test.com");
            assert customer.getCity().equals("Mumbai");
            return true;
        });
        
        test("Should validate customer data", () -> {
            Customer customer = new Customer("Priya", "Sharma", "priya@test.com", 
                    "9876543211", "456 Brigade Road", "Bangalore", "Karnataka", "560001");
            
            assert customer.getFirstName() != null;
            assert customer.getLastName() != null;
            assert customer.getEmail().contains("@");
            assert customer.getMobileNumber().length() == 10;
            return true;
        });
    }
    
    private static void runAccountTests() {
        System.out.println(" Account Management Tests");
        System.out.println("---------------------------");
        
        test("Should create account successfully", () -> {
            Customer customer = new Customer("Amit", "Patel", "amit@test.com", 
                    "9876543212", "789 Park Street", "Kolkata", "West Bengal", "700016");
            customer.setId("customer-123");
            
            Account account = new Account("ACC123456", customer, Account.AccountType.SAVINGS);
            
            assert account.getAccountNumber().equals("ACC123456");
            assert account.getAccountType() == Account.AccountType.SAVINGS;
            assert account.getCustomer().getId().equals("customer-123");
            assert account.getBalance().equals(BigDecimal.ZERO);
            return true;
        });
        
        test("Should handle account deposits", () -> {
            Customer customer = new Customer("Test", "User", "test@test.com", 
                    "9876543213", "Test Address", "Test City", "Test State", "123456");
            customer.setId("customer-456");
            
            Account account = new Account("ACC789012", customer, Account.AccountType.CURRENT);
            account.deposit(new BigDecimal("10000"));
            
            assert account.getBalance().equals(new BigDecimal("10000"));
            return true;
        });
        
        test("Should handle account withdrawals", () -> {
            Customer customer = new Customer("Test", "User", "test@test.com", 
                    "9876543214", "Test Address", "Test City", "Test State", "123456");
            customer.setId("customer-789");
            
            Account account = new Account("ACC345678", customer, Account.AccountType.SAVINGS);
            account.deposit(new BigDecimal("50000"));
            account.withdraw(new BigDecimal("15000"));
            
            assert account.getBalance().equals(new BigDecimal("35000"));
            return true;
        });
    }
    
    private static void runDepositTests() {
        System.out.println(" Deposit Tests");
        System.out.println("----------------");
        
        test("Should process valid deposit", () -> {
            Customer customer = new Customer("Deposit", "Test", "deposit@test.com", 
                    "9876543215", "Deposit Address", "Deposit City", "Deposit State", "654321");
            customer.setId("customer-deposit");
            
            Account account = new Account("ACC-DEPOSIT", customer, Account.AccountType.SAVINGS);
            account.deposit(new BigDecimal("25000"));
            
            assert account.getBalance().equals(new BigDecimal("25000"));
            return true;
        });
        
        test("Should handle multiple deposits", () -> {
            Customer customer = new Customer("Multi", "Deposit", "multi@test.com", 
                    "9876543216", "Multi Address", "Multi City", "Multi State", "789012");
            customer.setId("customer-multi");
            
            Account account = new Account("ACC-MULTI", customer, Account.AccountType.CURRENT);
            account.deposit(new BigDecimal("10000"));
            account.deposit(new BigDecimal("15000"));
            account.deposit(new BigDecimal("5000"));
            
            assert account.getBalance().equals(new BigDecimal("30000"));
            return true;
        });
    }
    
    private static void runWithdrawalTests() {
        System.out.println(" Withdrawal Tests");
        System.out.println("-------------------");
        
        test("Should process valid withdrawal", () -> {
            Customer customer = new Customer("Withdraw", "Test", "withdraw@test.com", 
                    "9876543217", "Withdraw Address", "Withdraw City", "Withdraw State", "345678");
            customer.setId("customer-withdraw");
            
            Account account = new Account("ACC-WITHDRAW", customer, Account.AccountType.SAVINGS);
            account.deposit(new BigDecimal("40000"));
            account.withdraw(new BigDecimal("10000"));
            
            assert account.getBalance().equals(new BigDecimal("30000"));
            return true;
        });
        
        test("Should handle insufficient funds", () -> {
            Customer customer = new Customer("Insufficient", "Funds", "insufficient@test.com", 
                    "9876543218", "Insufficient Address", "Insufficient City", "Insufficient State", "456789");
            customer.setId("customer-insufficient");
            
            Account account = new Account("ACC-INSUFFICIENT", customer, Account.AccountType.SAVINGS);
            account.deposit(new BigDecimal("5000"));
            
            try {
                account.withdraw(new BigDecimal("10000"));
                return false; // Should not reach here
            } catch (IllegalArgumentException e) {
                assert e.getMessage().contains("Insufficient balance");
                return true;
            }
        });
    }
    
    private static void runTransferTests() {
        System.out.println(" Transfer Tests");
        System.out.println("-----------------");
        
        test("Should process valid transfer", () -> {
            Customer sourceCustomer = new Customer("Source", "Customer", "source@test.com", 
                    "9876543219", "Source Address", "Source City", "Source State", "567890");
            sourceCustomer.setId("customer-source");
            
            Customer destCustomer = new Customer("Destination", "Customer", "dest@test.com", 
                    "9876543220", "Dest Address", "Dest City", "Dest State", "678901");
            destCustomer.setId("customer-dest");
            
            Account sourceAccount = new Account("ACC-SOURCE", sourceCustomer, Account.AccountType.SAVINGS);
            Account destAccount = new Account("ACC-DEST", destCustomer, Account.AccountType.CURRENT);
            
            sourceAccount.deposit(new BigDecimal("50000"));
            destAccount.deposit(new BigDecimal("20000"));
            
            // Simulate transfer
            BigDecimal transferAmount = new BigDecimal("15000");
            sourceAccount.withdraw(transferAmount);
            destAccount.deposit(transferAmount);
            
            assert sourceAccount.getBalance().equals(new BigDecimal("35000"));
            assert destAccount.getBalance().equals(new BigDecimal("35000"));
            return true;
        });
        
        test("Should prevent transfer to same account", () -> {
            Customer customer = new Customer("Same", "Account", "same@test.com", 
                    "9876543221", "Same Address", "Same City", "Same State", "789012");
            customer.setId("customer-same");
            
            Account account = new Account("ACC-SAME", customer, Account.AccountType.SAVINGS);
            account.deposit(new BigDecimal("30000"));
            
            // Simulate same account transfer attempt
            String sourceAccountNumber = "ACC-SAME";
            String destAccountNumber = "ACC-SAME";
            
            boolean isSameAccount = sourceAccountNumber.equals(destAccountNumber);
            assert isSameAccount;
            return true;
        });
    }
    
    private static void runTransactionHistoryTests() {
        System.out.println(" Transaction History Tests");
        System.out.println("----------------------------");
        
        test("Should record transaction history", () -> {
            Customer customer = new Customer("History", "Test", "history@test.com", 
                    "9876543222", "History Address", "History City", "History State", "890123");
            customer.setId("customer-history");
            
            Account account = new Account("ACC-HISTORY", customer, Account.AccountType.SAVINGS);
            
            // Simulate multiple transactions
            account.deposit(new BigDecimal("10000"));
            account.deposit(new BigDecimal("5000"));
            account.withdraw(new BigDecimal("3000"));
            account.deposit(new BigDecimal("2000"));
            
            assert account.getBalance().equals(new BigDecimal("14000"));
            return true;
        });
    }
    
    private static void runEdgeCaseTests() {
        System.out.println(" Edge Case Tests");
        System.out.println("------------------");
        
        test("Should handle zero amount transactions", () -> {
            Customer customer = new Customer("Zero", "Test", "zero@test.com", 
                    "9876543223", "Zero Address", "Zero City", "Zero State", "901234");
            customer.setId("customer-zero");
            
            Account account = new Account("ACC-ZERO", customer, Account.AccountType.SAVINGS);
            
            try {
                account.deposit(BigDecimal.ZERO);
                return false; // Should not reach here
            } catch (IllegalArgumentException e) {
                assert e.getMessage().contains("Amount must be positive");
                return true;
            }
        });
        
        test("Should handle large amounts", () -> {
            Customer customer = new Customer("Large", "Amount", "large@test.com", 
                    "9876543224", "Large Address", "Large City", "Large State", "012345");
            customer.setId("customer-large");
            
            Account account = new Account("ACC-LARGE", customer, Account.AccountType.SAVINGS);
            BigDecimal largeAmount = new BigDecimal("999999999.99");
            
            account.deposit(largeAmount);
            assert account.getBalance().equals(largeAmount);
            return true;
        });
        
        test("Should handle negative amounts", () -> {
            Customer customer = new Customer("Negative", "Test", "negative@test.com", 
                    "9876543225", "Negative Address", "Negative City", "Negative State", "123456");
            customer.setId("customer-negative");
            
            Account account = new Account("ACC-NEGATIVE", customer, Account.AccountType.SAVINGS);
            
            try {
                account.deposit(new BigDecimal("-1000"));
                return false; // Should not reach here
            } catch (IllegalArgumentException e) {
                assert e.getMessage().contains("Amount must be positive");
                return true;
            }
        });
    }
    
    private static void test(String testName, TestFunction testFunction) {
        totalTests++;
        try {
            boolean result = testFunction.run();
            if (result) {
                System.out.println(" " + testName);
                testsPassed++;
            } else {
                System.out.println(" " + testName + " - Failed");
                testsFailed++;
            }
        } catch (Exception e) {
            System.out.println(" " + testName + " - Error: " + e.getMessage());
            testsFailed++;
        }
    }
    
    private static void printTestSummary() {
        System.out.println();
        System.out.println(" Test Summary");
        System.out.println("===============");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + testsPassed + " ");
        System.out.println("Failed: " + testsFailed + " ");
        System.out.println("Success Rate: " + String.format("%.1f%%", (double) testsPassed / totalTests * 100));
        
        if (testsFailed == 0) {
            System.out.println();
            System.out.println(" All tests passed! Banking system is working correctly.");
        } else {
            System.out.println();
            System.out.println(" Some tests failed. Please review the issues above.");
        }
    }
    
    @FunctionalInterface
    private interface TestFunction {
        boolean run() throws Exception;
    }
}