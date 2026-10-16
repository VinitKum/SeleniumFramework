package tests;

import org.testng.annotations.Factory;

public class TestFactory {

    @Factory
    public Object[] createTestInstances() {
        return new Object[] {
                new FactoryTest("Admin"),
                new FactoryTest("Manager"),
                new FactoryTest("Employee")
        };
    }
}