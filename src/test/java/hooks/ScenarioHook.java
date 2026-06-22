
package hooks;

import base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class ScenarioHook extends Base {

    @Before
    public void setup() throws Exception {
        Base base = new Base();
        base.initializedDriver();

        System.out.println("Browser launched successfully.");
    }

    @After
    public void tearDown() {
        if (Base.getDriver() != null) {
            Base.getDriver().quit();
            Base.removeDriver();

            System.out.println("Browser closed successfully.");
        }
    }
}
