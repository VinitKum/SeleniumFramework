package tests;

import org.testng.annotations.Test;

public class SystemPropertyTest
{
    @Test
    public void systempropertiesTest()
    {
        String property=System.getProperty("browser","chrome") ;
        System.out.println(property);

    }
}
