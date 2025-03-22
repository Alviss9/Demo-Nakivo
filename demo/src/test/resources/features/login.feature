Feature: Login Feature

    Examples:
      | username                                                                                                                                                                                                                                                         | password                                                                                               |
      | Alviss                                                                                                                                                                                                                                                           | Nakavo123                                                                                              |
      | Alviss9                                                                                                                                                                                                                                                          | Nakavo123456                                                                                           |
      | Alviss9.com                                                                                                                                                                                                                                                      | Nakavo123456                                                                                           |
      | qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq.vn | Nakavo123456                                                                                           |
      | Alviss                                                                                                                                                                                                                                                           | Nakavo123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123 |

  # Scenario 1: Verify that the login page loads successfully
  Scenario: Verify that the login page loads successfully
    Given I open the login page
    Then I should see the login form
    And I close browser

  #Scenario 2: Verify successful login with valid credentials
  Scenario: Verify successful login with valid credentials
    Given I open the login page
    When I enter username "Alviss" and password "Nakavo123"
    And I click on the login button
    Then I should be redirected to the dashboardmvn clean test
    And I close browser

  #Scenario 3: Verify unsuccessful when logging in with invalid credentials
  Scenario: Verify successful login with valid credentials
    Given I open the login page
    When I enter username "Alviss9" and password "Nakavo123456"
    And I click on the login button
    Then I verify the message
    And I close browser

  #Scenario 4: Verify wanring message when leaving the email or password field empty
  Scenario: Verify successful login with valid credentials
    Given I open the login page
    When I enter username "Alviss9" and password ""
    And I click on the login button
    Then I verify the message "Incorrect credentials." and element "ext-gen1172"
    And I verify button is enabled "augmentedButton-1043"
    Then I clear input textbox "ext-gen1178"

    When I enter username "" and password "Nakavo1234"
    Then I verify the message "Incorrect credentials." and element "ext-gen1172"
    And I verify button is disable "augmentedButton-1043"
    And I close browser

  #Scenario 5: Verify wanring message when inputting an invalid email format
  Scenario: Verify successful login with valid credentials
    Given I open the login page
    When I enter username "Alviss9.com" and password "Nakavo123456"
    And I click on the login button
    Then I hover mouse into textbox: message "Incorrect credentials." and elementid "ext-gen1178" and xpath "//div[@id='msg-error-username']//li[@class=\"last\"]"
    Then I hover mouse into textbox: message "Incorrect credentials." and elementid "ext-gen1183" and xpath "//div[@id='msg-error-password']//li[@class=\"last\"]"
    And I close browser

  #Scenario 6: Verify wanring message when inputting long character email
  Scenario: Verify successful login with valid credentials
    Given I open the login page
    When I enter username "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq.vn" and password "Nakavo123456"
    And I click on the login button
    Then I hover mouse into textbox: message "Incorrect credentials." and elementid "ext-gen1178" and xpath "//div[@id='msg-error-username']//li[@class=\"last\"]"
    And I close browser

  #Scenario 7: Verify wanring message when inputting long character password
  Scenario: Verify successful login with valid credentials
    Given I open the login page
    When I enter username "Alviss" and password "Nakavo123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123123"
    And I click on the login button
    Then I hover mouse into textbox: message "Incorrect credentials." and elementid "ext-gen1183" and xpath "//div[@id='msg-error-password']//li[@class=\"last\"]"
    And I close browser