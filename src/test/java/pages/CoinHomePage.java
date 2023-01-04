package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CoinHomePage {
    public CoinHomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
        @FindBy (xpath ="//*[@class='sc-aef7b723-0 fKbUaI close-button']")
        public WebElement cmcWrap;
        @FindBy(xpath = "//div[@class=\"cmc-cookie-policy-banner__close\"]")
        public WebElement cookies;
        @FindBy(xpath = "(//div[@data-sensors-click=\"true\"])[10]")
        public WebElement rows;

    @FindBy(xpath = "//*[@id='mineable']/span")
    public WebElement mineable;
    @FindBy(xpath = "(//li[contains(text(), 'PoW')])[2]")
    public WebElement PoW;
    @FindBy(xpath = "(//button[contains(text(), 'Filters')])[2]")
    public WebElement Filters;
    @FindBy(xpath = "//span[@class= 'icon-Chevron-up']")
    public WebElement arrow;
    @FindBy(xpath = "//*[@for='1']")
    public WebElement highlight;

    }

