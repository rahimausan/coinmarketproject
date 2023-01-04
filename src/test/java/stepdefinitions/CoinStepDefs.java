package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import pages.CoinHomePage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/*
Filtering UI and extracting data for comparison
1.	go to https://coinmarketcap.com
2.	show rows by 20
3.	capture all page contents - we will let you decide what is the correct information to capture, suggestions e.g. price, name, position
4.	filter by Algorith - "PoW"
5.	followed by "+ Add Filter"
6.	toggle "Mineable"
7.	then select "All Cryptocurrencies"
8.	select "Coins"
9.	then select price and set minimum value to 100
10.	and maximum to 10,000
11.	compare page content to the content in step 3

    Test Execution Steps:
1)	User navigate to home page
2)	Click on popup message and cookies button
3)	Click on the box next to rows and select the value "<value>"
4)	Capture all the contents under name and price rows
5)	Click on the "<button>"
6)	Click on Algorithm as "<button2>" then select Pow
7)	Click on Add filter as "<button3>"
8)	Toggle Mineable on new window
9)	Select the All Cryptocurrencies as "<button4>"
10)	Click on Coins as "<button5>"
11)	Select Price and change the value "<minimum>" and "<maximum>"
12)	Click Apply filter as "<button6>" then Show results as "<button7>"
13)	Compare page contents to before filter page content
 */

public class CoinStepDefs {
    CoinHomePage coinHomePage = new CoinHomePage();
    Actions actions = new Actions(Driver.getDriver());
    Map<String, Integer> result = new HashMap<>();
    Map<String, Integer> lastResult = new HashMap<>();

    // 1) User navigate to home page
    @Given("navigate to home page")
    public void navigate_to_home_page() {
        Driver.getDriver().get(ConfigReader.getProperty("coin_homepage_url"));
    }
    // 2) Click on popup message and cookies button
    @Then("click on popup message and cookies button")
    public void click_on_popup_message_and_cookies_button() {
        Driver.waitAndClick(coinHomePage.cmcWrap);
        Driver.waitAndClick(coinHomePage.cookies);
    }
    //3) Click on the box next to rows and select the value "<value>"
    @Then("click on the box next to rows and select the value {string}")
    public void click_on_the_box_next_to_rows_and_select_the_value(String string) {
        Driver.clickWithJS(coinHomePage.rows);
        Driver.getDriver().findElement(By.xpath("//button[contains(text(), '20')]")).click();
    }
    //4) Capture all the contents under name and price rows
    @Then("capture all the contents under name and price rows")
    public void capture_all_the_contents_under_name_and_price_rows() {
        Driver.wait(2);
        Driver.getDriver().findElement(By.xpath("//table//tbody//tr['+row+']//td['+column+']"));
        result.putAll(ReusableMethods.collectInfo());
        for (Map.Entry<String, Integer> e : result.entrySet()){
                System.out.println(e.getKey() + " - " + e.getValue());
         }
        }
    //5) Click on the "<button>"
    @Then("click on the {string}")
     public void click_on_the(String button){
         Driver.waitAndClick(coinHomePage.arrow);
         Driver.clickWithJS(coinHomePage.highlight);
         Driver.getDriver().findElement(By.xpath("(//button[contains(text(),'Filters')])[2]")).click();
        }
    // 6) Click on Algorithm as "<button2>" then select Pow
    @Then("click on Algorithm as {string} then select Pow")
    public void click_on_algorithm_as_then_select_pow(String button2) throws IOException {
       Driver.getDriver().findElement(By.xpath("//button[contains(text(),'Algorithm')]")).click();
        ReusableMethods.getScreenshot("Algorithm");
        Driver.waitAndClick(coinHomePage.PoW);
   }
   // 7) Click on Add filter as "<button3>"
   @Then("click on Add filter as {string}")
   public void click_on_add_filter_as(String button3) {
        Driver.getDriver().findElement(By.xpath("//button[contains(text(),'+')]")).click();
   }
  // 8)	Toggle Mineable on new window
   @Then("toggle Mineable on new window")
   public void toggle_mineable_on_new_window() throws IOException {
       Driver.waitAndClick(coinHomePage.mineable);
       ReusableMethods.getScreenshot("Mineable");
   }
  // 9)	Select the All Cryptocurrencies as "<button4>"
    @Then("select the All Cryptocurrencies as {string}")
    public void select_the_all_cryptocurrencies_as(String button4) {
        Driver.getDriver().findElement(By.xpath("//button[contains(text(),'All Cryptocurrencies')]")).click();
     }
    // 10)	Click on Coins as "<button5>"
    @Then("click on Coins as {string}")
    public void click_on_coins_as(String button5){
        Driver.getDriver().findElement(By.xpath("//button[contains(text(),'Coins')]")).click();
    }
    // 11)	Select Price and change the value "<minimum>" and "<maximum>"
    @Then("select Price and change the value {string} and {string}")
    public void select_price_and_change_the_value_and(String minimum, String maximum) throws IOException {
        Driver.getDriver().findElement(By.xpath("//button[contains(text(),'Price')]")).click();
        Driver.getDriver().findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys("100");
        Driver.getDriver().findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("10000");
        ReusableMethods.getScreenshot("Price");
    }
    // 12)	Click Apply filter as "<button6>" then Show results as "<button7>"
    @When("click Apply filter as {string} then Show results as {string}")
    public void click_apply_filter_as_then_show_results_as(String button6, String button7) throws IOException {
        Driver.getDriver().findElement(By.xpath("//button[contains(text(),'Apply Filter')]")).click();
        Driver.getDriver().findElement(By.xpath("//button[contains(text(),'Show results')]")).click();
        ReusableMethods.getScreenshot("Apply Filter");
    }
    // 13) Compare page contents to before filter page content
    @When("compare page contents to before filter page content")
    public void compare_page_contents_to_before_filter_page_content(){
        Driver.wait(2);
        Driver.getDriver().findElement(By.xpath("//table//tbody//tr['+row+']//td['+column+']"));
        lastResult.putAll(ReusableMethods.collectInfo());
        for (Map.Entry<String, Integer> e : lastResult.entrySet()){
           lastResult.putAll(ReusableMethods.collectInfo());
            System.out.println(e.getKey() + " - " + e.getValue());
            if(result.containsKey(e.getKey())){
               // System.out.println(e.getKey());
                System.out.println("previousResult: " + result.get(e.getKey()) +"  latestResult: " + e.getValue() );
             //   System.out.println();

            }else {
                System.out.println("No matches were found after filtering the data");
           }
        }
    }
  }

