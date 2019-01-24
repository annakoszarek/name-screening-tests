package pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class DecisionTreesListPage {

    @FindBy(tagName = "app-decision-trees")
    private WebElement appDecisionTreesSection;

    @FindAll({@FindBy(css = ".inactive-decision-trees .name a")})
    private List<WebElement> inactiveDecisionTreeLinks;

    public DecisionTreesListPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageDisplayed() throws Throwable {
        return appDecisionTreesSection.isDisplayed();
    }

    public String selectNthInactiveDecisionTree(int index) {
        String decisionTreeName = inactiveDecisionTreeLinks.get(index - 1).getText();
        inactiveDecisionTreeLinks.get(index - 1).click();
        return decisionTreeName;
    }
}
