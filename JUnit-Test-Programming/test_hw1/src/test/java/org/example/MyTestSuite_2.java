package org.example;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        TransactionTest.class,
        UserTest.class
})
public class MyTestSuite_2 {
}
