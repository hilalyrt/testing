package org.example;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ATMTest.class,
        AccountTest.class,
        BankTest.class
})
public class MyTestSuite_1 {
}
