package com.bankingsystem.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Banking System Test Suite")
@SelectClasses({
    DepositServiceTest.class,
    TransferServiceTest.class
})
public class BankingSystemTestSuite {
    // Test suite runner
}