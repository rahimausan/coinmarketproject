    @Coinmarket
    Feature: CoinMarket_TC_01
    Background:
    Given navigate to home page
    Then click on popup message and cookies button
    Scenario Outline:Data_for_comparison
    And click on the box next to rows and select the value "<value>"
    And capture all the contents under name and price rows
    And click on the "<button>"
    And click on Algorithm as "<button2>" then select Pow
    And click on Add filter as "<button3>"
    Then toggle Mineable on new window
    And select the All Cryptocurrencies as "<button4>"
    And click on Coins as "<button5>"
    And select Price and change the value "<minimum>" and "<maximum>"
    When click Apply filter as "<filter>" then Show results as "<result>"
    And compare page contents to before filter page content
        Examples:
        |value|button |button2    |button3   |button4             |button5|minimum|maximum|filter    |result|
        |20   |Filters|Algorithm  |Add filter|All Cryptocurrencies|Coins  |100   |10000   |Apply filter|Show results|



