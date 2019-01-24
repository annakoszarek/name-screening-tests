package pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class DecisionTreePage {

    WebDriver webDriver;

    @FindBy(tagName = "app-decision-tree")
    private WebElement appDecisionTreeSection;

    @FindAll({@FindBy(css = ".informations td")})
    private List<WebElement> basicInformations;

    @FindBy(xpath = "//button[text()='Edit Assignments']")
    private WebElement editAssignmentsButton;
	
    @FindAll({@FindBy(xpath = "//section/h3[text()='Assigned']/following-sibling::ul/li")})
    private List<WebElement> assignedAssignmentsNameList;	
		
	@FindBy(css = ".loading")
    private WebElement loader;

    public DecisionTreePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageDisplayed() throws Throwable {
		new WebDriverWait(webDriver, 100, 10).until(ExpectedConditions.invisibilityOf(loader));
        return appDecisionTreeSection.isDisplayed();
    }

    public String getDecisionTreeName() {
        return basicInformations.get(3).getText();
    }

    public void openEditAssignment() {
		new WebDriverWait(webDriver, 10, 10).until(ExpectedConditions.elementToBeClickable(editAssignmentsButton));
        editAssignmentsButton.click();
    }

    public boolean verifyAssignedAssignments(String assignment) {
        boolean result = false;
		for (int i = 0; i < assignedAssignmentsNameList.size(); i++) {
			if (assignedAssignmentsNameList.get(i).getText().contains(assignment)) {
				result = true;
				break;
			}
		}     		  
        return result;
    }
}
