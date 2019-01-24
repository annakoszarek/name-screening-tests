package steps;

import cucumber.api.java.*;
import cucumber.api.java.en.*;
import static org.junit.jupiter.api.Assertions.*;
import pages.*;
import utils.PropertyReader;
import utils.WebDriverFactory;

import org.openqa.selenium.WebDriver;

public class StepsDefinitions {

    protected WebDriver webDriver;
    protected WebDriverFactory factory;

    @Before
    public void beforeScenario() {
    	factory = new WebDriverFactory();
        webDriver = factory.getDriver();
    }

    @After
    public void afterScenario() {
        factory.quitDriver();
    }

    @Given("^open application$")
    public void openApplication() throws Throwable {
        webDriver.manage().window().maximize();
        webDriver.get(new PropertyReader().readProperty("url"));
    }

    @And("^login as test user$")
    public void loginAsTestUser() throws Throwable {
    	PropertyReader property = new PropertyReader();
        new LoginPage(webDriver).login(property.readProperty("username"), property.readProperty("password"));
        assertTrue(new DecisionTreesListPage(webDriver).isPageDisplayed());
    }

    @When("^select first inactive decision tree$")
    public void selectFirstInactiveDecisionTree() throws Throwable {
        String decisionTreeName = new DecisionTreesListPage(webDriver).selectNthInactiveDecisionTree(1);
        DecisionTreePage decisionTree = new DecisionTreePage(webDriver);
        assertTrue(decisionTree.isPageDisplayed());
        assertEquals(decisionTreeName, decisionTree.getDecisionTreeName());
    }

    @When("^add assignment ([^\"]*)$")
    public void addAssignment(String assignment) throws Throwable {
        DecisionTreePage decisionTree = new DecisionTreePage(webDriver);
        decisionTree.openEditAssignment();
        EditAssignmentsPopupWindow editAssignments = new EditAssignmentsPopupWindow(webDriver);
        assertTrue(editAssignments.isPageDisplayed());
        editAssignments.addAssignments(assignment);
        assertTrue(editAssignments.verifyAssignedAssignments(assignment));
        editAssignments.saveChanges();
    }

    @Then("^verify if ([^\"]*) is (not )?assigned$")
    public void verifyAssignment(String assignment, String isOrIsNot) throws Throwable {
        DecisionTreePage decisionTree = new DecisionTreePage(webDriver);
        assertTrue(decisionTree.isPageDisplayed());
        if (isOrIsNot != null) {
            assertFalse(decisionTree.verifyAssignedAssignments(assignment));
        } else {
            assertTrue(decisionTree.verifyAssignedAssignments(assignment));
        }
    }

    @When("^remove assignment ([^\"]*)$")
    public void removeAssignment(String assignment) throws Throwable {
        DecisionTreePage decisionTree = new DecisionTreePage(webDriver);
        assertTrue(decisionTree.isPageDisplayed());
        decisionTree.openEditAssignment();
        EditAssignmentsPopupWindow editAssignments = new EditAssignmentsPopupWindow(webDriver);
        assertTrue(editAssignments.isPageDisplayed());
        editAssignments.removeAssignments(assignment);
        assertFalse(editAssignments.verifyAssignedAssignments(assignment));
        editAssignments.saveChanges();
    }
}
