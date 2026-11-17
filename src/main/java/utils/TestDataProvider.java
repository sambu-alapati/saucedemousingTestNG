package utils;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        return new Object[][] {
            // { username, password, expectedResult }
            { "standard_user", "secret_sauce", "SUCCESS" },
            { "problem_user", "secret_sauce", "SUCCESS" },
            { "performance_glitch_user", "secret_sauce", "SUCCESS" },
            { "locked_out_user", "secret_sauce", "Sorry, this user has been locked out." },
            { "invalid_user", "wrong_pass", "Username and password do not match" },
            { "", "secret_sauce", "Username is required" }
        };
    }
}