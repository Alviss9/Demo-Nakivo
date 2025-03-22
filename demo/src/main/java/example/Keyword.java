package example; // Đảm bảo cùng package với App.java

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Keyword {  // Viết hoa chữ cái đầu

    WebDriver driver;
    WebDriverWait wait;

    public Keyword() {
        // Kiểm tra nếu `driver` là null thì mới khởi tạo WebDriver mới
        if (driver == null) {
            // Đặt đường dẫn ChromeDriver và khởi tạo WebDriver nếu không có `driver` được truyền vào
            System.setProperty("webdriver.chrome.driver", "C:\\Windows\\chromedriver.exe");

            // Khởi tạo WebDriver với ChromeOptions
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--disable-blink-features=AutomationControlled");

            this.driver = new ChromeDriver(options);  // Khởi tạo WebDriver
        } else {
            // Nếu có `driver` truyền vào, sử dụng nó
            this.driver = driver;
        }

// Khởi tạo WebDriverWait
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.driver.manage().window().maximize();
    }

    @Given("I_open_the_login_page url {string} username {string} and password {string}")
    public void i_open_the_login_page(String url, String user, String password) {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            driver.get(url);
            WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1178")));
            userField.sendKeys(user);
            WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1183")));
            passField.sendKeys(password);
            WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("augmentedButton-1043")));
            loginBtn.click();
            System.out.println("Login successful");
        } catch (Exception e) {
            System.out.println("Login unsuccessful: " + e.getMessage());
        }
    }

    // Getter for WebDriver (if needed in tests)
    public WebDriver getDriver() {
        return this.driver;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @And("I close browser")
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser was closed");
        }
    }

    @Given("I_open_the_login_page")
    public void i_should_see_the_login_form(String url) {
        driver.get(url);
    }

    @When("I enter username {string} and password {string}")
    public void i_enter_username_and_password(String username, String password) {
        driver.findElement(By.id("ext-gen1178")).sendKeys(username);
        driver.findElement(By.id("ext-gen1183")).sendKeys(password);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        driver.findElement(By.id("augmentedButton-1043")).click();
    }

    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        String expectedUrl = "https://localhost:4443/dashboard";
        Assert.assertEquals("User should be redirected to dashboard", expectedUrl, driver.getCurrentUrl());
        driver.quit();
    }

    @Then("I should see the login form")
    public void i_should_see_the_login_form() {
        driver.findElement(By.id("ext-gen1167"));
    }

    @And("I wait 5 second")
    public void i_wait_5_second() {
        try {
            // Chờ 5 giây
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // In ra stack trace chi tiết của ngoại lệ
            e.printStackTrace();
        }
    }

    @And("I verify the message {string} and element {string}")
    public void I_verify_the_message(String element, String message) {
        // Chờ cho phần tử hiển thị trong thời gian nhất định
        WebDriverWait waitForElement = new WebDriverWait(driver, Duration.ofSeconds(10)); // Đổi tên biến
        WebElement errorMessage = waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.id(element)));

        // Kiểm tra sự hiện diện của phần tử và văn bản thông báo lỗi
        Assert.assertTrue("Error message should be displayed for invalid login.", errorMessage.isDisplayed());
        Assert.assertEquals(message, errorMessage.getText());

        // In thông báo lỗi (text) ra console để kiểm tra
        System.out.println("Error Message: " + errorMessage.getText());
    }

    @Then("I clear input textbox {string}")
    public void I_clear_input_textbox(String user) {
        WebElement inputuser = driver.findElement(By.id(user));
        inputuser.clear();  // Xóa toàn bộ văn bản trong input field
    }

    @And("I verify button is disable {string}")
    public void I_verify_button_is_disable(String buttonId) {
        WebElement button = driver.findElement(By.id(buttonId));

        // Kiểm tra xem nút có thuộc tính "disabled" hay không
        boolean isDisabled = button.getAttribute("class").contains("x-btn-disabled");

        Assert.assertTrue("Button should be disabled", isDisabled);
        System.out.println("Button with ID '" + buttonId + "' is disabled.");
    }

    @And("I verify button is enabled {string}")
    public void I_verify_button_is_enabled(String buttonId) {
        WebElement button = driver.findElement(By.id(buttonId));

        // Lấy lớp CSS của nút
        String classAttribute = button.getAttribute("class");

        // Kiểm tra nếu không có lớp "x-btn-default-disabled", có nghĩa là nút đã được kích hoạt
        boolean isEnabled = !classAttribute.contains("x-btn-default-disabled");

        Assert.assertTrue("Button should be enabled", isEnabled);
        System.out.println("Button with ID '" + buttonId + "' is enabled.");
    }

    @Then("I input into textbox {string} and element {string}")
    public void I_input_into_textbox(String username, String userid) {
        WebElement userField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(userid)));
        userField.sendKeys(username);
    }

    @Then("I hover mouse into textbox: message {string} and elementid {string} and xpath {string}")
    public void I_hover_mouse_into_textbox(String message, String userid, String xpath) {
        // Tìm phần tử mà bạn muốn di chuột qua (ví dụ: nút "Log In")
        WebElement button = driver.findElement(By.id(userid));

        // Tạo đối tượng Actions để mô phỏng hành động
        Actions actions = new Actions(driver);

        // Di chuột qua phần tử
        actions.moveToElement(button).perform();

        // WebElement errorElement = driver.findElement(By.xpath(xpath));
        WebDriverWait waitForElement = new WebDriverWait(driver, Duration.ofSeconds(10)); // Đổi tên biến
        WebElement errorMessage = waitForElement.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        Assert.assertTrue("Error message should be displayed after hover", errorMessage.isDisplayed());
        Assert.assertEquals(message, errorMessage.getText());

        // In thông báo lỗi (nếu cần)
        System.out.println("Error message: " + errorMessage.getText());
    }

    public WebDriver initDriver(String chromeDriverPath) {
        // Đặt đường dẫn đến ChromeDriver (Đảm bảo bạn đã cài đặt đúng phiên bản)
        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\chromedriver.exe");

        // Tạo đối tượng ChromeOptions để cấu hình các tùy chọn
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-blink-features=AutomationControlled"); // Ẩn thông báo "Chrome is being controlled..."

        // Khởi tạo WebDriver với ChromeOptions
        driver = new ChromeDriver(options);

        return driver;
    }

    @Then("I validate forgot password link {string} and {string}")
    public void i_validate_forgot_password_link(String linkId, String expectedUrl) {
        try {
            // Validate visibility of the "Forgot your password?" link
            WebElement forgotPasswordLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(linkId)));
            if (forgotPasswordLink.isDisplayed()) {
                System.out.println("The 'Forgot your password?' link is visible.");

                // Click on the link
                forgotPasswordLink.click();

                // Wait for the next page to load or validate if the correct page is loaded
                wait.until(ExpectedConditions.urlContains(expectedUrl));
                System.out.println("The link successfully redirected to the expected page: " + expectedUrl);
            } else {
                System.out.println("The 'Forgot your password?' link is not visible.");
            }
        } catch (Exception e) {
            System.out.println("Error validating 'Forgot your password?' link: " + e.getMessage());
        }
    }

    @And("I click on {string}")
    public void i_click_on(String element) {
        driver.findElement(By.id(element)).click();
    }
}
