package support.context;

import support.managers.PageObjectManager;
import support.managers.WebDriverFactory;

public class TestContext {
    PageObjectManager pageObjectManager;
    ScenarioContext scenarioContext;

    public TestContext(){
        WebDriverFactory.setUpDriver();
        pageObjectManager = new PageObjectManager();
        scenarioContext = new ScenarioContext();
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}