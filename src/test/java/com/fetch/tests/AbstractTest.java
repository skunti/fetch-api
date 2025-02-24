package com.fetch.tests;

import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;

/**
 * AbstractTest serves as a base test class for configuring common test setup.
 * It initializes Log4j configuration before running any test cases.
 */
public class AbstractTest {

    /**
     * Configures Log4j properties before running any tests.
     * This ensures that logging is properly set up for all test classes extending AbstractTest.
     */
    @BeforeTest
    public void beforeTest() {
        PropertyConfigurator.configure(AbstractTest.class.getClassLoader().getResourceAsStream("log4j.properties"));
    }
}
