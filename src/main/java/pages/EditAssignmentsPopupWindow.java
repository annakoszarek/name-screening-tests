package pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;

public class EditAssignmentsPopupWindow {
	
	WebDriver webDriver;

    @FindBy(css = ".popup-window.is-active")
    private WebElement editAssignmentWindow;

    @FindAll({@FindBy(xpath = "//section/h3[text()='Available']/following-sibling::ul//div[contains(@class, 'name')]")})
    private List<WebElement> availableAssignmentsNameList;

    @FindAll({@FindBy(xpath = "//section/h3[text()='Available']/following-sibling::ul//button[contains(@class, 'add')]")})
    private List<WebElement> addButtonsList;

    @FindAll({@FindBy(xpath = "//section/h3[text()='Assigned']/following-sibling::ul//div[contains(@class, 'name')]")})
    private List<WebElement> assignedAssignmentsNameList;

    @FindAll({@FindBy(className = "remove")})
    private List<WebElement> removeButtonsList;

    @FindBy(id = "saveButton")
    private WebElement saveButton;

    @FindBy(id = "closeButton")
    private WebElement cancelButton;

    public EditAssignmentsPopupWindow(WebDriver webDriver) {
		this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageDisplayed() throws Throwable {
        return editAssignmentWindow.isDisplayed();
    }

    public void addAssignments(String assignment) {
        for (int i = 0; i < availableAssignmentsNameList.size(); i++) {
            if (availableAssignmentsNameList.get(i).getText().contains(assignment)) {
                addButtonsList.get(i).click();
                break;
            }
        }
    }

    public void removeAssignments(String assignment) {
        for (int i = 0; i < assignedAssignmentsNameList.size(); i++) {
            if (assignedAssignmentsNameList.get(i).getText().contains(assignment)) {
				removeButtonsList.get(i).click();
                break;
            }
        }
    }

    public boolean verifyAssignedAssignments(String assignment) {
		PageFactory.initElements(webDriver, this);
        boolean result = false;
        for (int i = 0; i < assignedAssignmentsNameList.size(); i++) {
            if (assignedAssignmentsNameList.get(i).getText().contains(assignment)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void saveChanges() {
		new WebDriverWait(webDriver, 10, 5).until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    public void cancelChanges() {
        cancelButton.click();
    }
}
