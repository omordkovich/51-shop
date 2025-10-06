package ait.shop.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Oleg Mordkovich
 * {@code @date} 06.10.2025
 */
@Service
public class TestVarService {
    private final String testEnvVar;

    public TestVarService(@Value("${test.env.var}") String testEnvVar) {
        this.testEnvVar = testEnvVar;
    }

    public void test() {
        System.out.println(testEnvVar);
    }
}
