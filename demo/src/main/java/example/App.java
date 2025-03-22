package example;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {

    public static void main(String[] args) {
        // Đặt đường dẫn ChromeDriver
        validate_password_masking();
    }

    public static void login_page_successful() {
        System.setProperty("webdriver.chrome.driver", "C:\\Windows\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-blink-features=AutomationControlled");

        WebDriver driver = new ChromeDriver(options);
        Keyword kw = new Keyword();

        kw.i_open_the_login_page("https://localhost:4443/c/login", "Alviss", "Nakavo123");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("docbody")));
            System.out.println("Page load successful!");
        } catch (Exception e) {
            System.out.println("Page load unsuccessful!: " + e.getMessage());
        } finally {
            try {
                // Chờ 5 giây
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }

    public static void check_page_login_load_successful() {
        // Truyền WebDriver vào class keyword
        Keyword kw = new Keyword();
        kw.i_should_see_the_login_form("https://localhost:4443/c/login");
        WebDriverWait wait = new WebDriverWait(kw.getDriver(), Duration.ofSeconds(10));

        // Đóng trình duyệt sau khi kiểm tra xong
        try {
            // Kiểm tra phần tử đăng nhập có hiển thị không
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1167")));
            System.out.println("Page load successful!");
        } catch (Exception e) {
            System.out.println("Page load unsuccessful!: " + e.getMessage());
        } finally {
            try {
                // Chờ 5 giây
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            kw.quitDriver();
        }
        kw.quitDriver();
    }
    // Đóng trình duyệt sau khi kiểm tra xong
    //kw.closeBrowser();

    public static void check_massage_warning_when_login_faile() {
        // Truyền WebDriver vào class keyword
        Keyword kw = new Keyword();
        kw.i_open_the_login_page("https://localhost:4443/c/login", "Alviss9", "Nakavo1234");
        WebDriverWait wait = new WebDriverWait(kw.getDriver(), Duration.ofSeconds(10));

        // Kiểm tra thông báo lỗi đăng nhập
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1172")));

        kw.I_verify_the_message("ext-gen1172", "Incorrect credentials.");
        //clear textbox
        kw.I_clear_input_textbox("ext-gen1178");
        kw.I_clear_input_textbox("ext-gen1183");
        //check button is disable
        kw.I_verify_button_is_disable("augmentedButton-1043");
        //input into
        kw.I_input_into_textbox("Nakavo", "ext-gen1183");
        //check button is disable
        kw.I_verify_button_is_disable("augmentedButton-1043");

        // Đóng trình duyệt sau khi kiểm tra xong
        kw.quitDriver();
    }

    public static void check_message_warning_hover_textbox_when_login_faile() {

        // Truyền WebDriver vào class keyword
        Keyword kw = new Keyword();

        kw.i_open_the_login_page("https://localhost:4443/c/login", "Alviss9.com", "Nakavo1234");
        WebDriverWait wait = new WebDriverWait(kw.getDriver(), Duration.ofSeconds(10));

        // Kiểm tra thông báo lỗi đăng nhập
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1172")));

        // input wrong format user
        kw.I_hover_mouse_into_textbox("Incorrect credentials.", "ext-gen1178", "//div[@id='msg-error-username']//li[@class=\"last\"]");
        // input wrong password
        kw.I_hover_mouse_into_textbox("Incorrect credentials.", "ext-gen1183", "//div[@id='msg-error-password']//li[@class=\"last\"]");

        // Đóng trình duyệt sau khi kiểm tra xongmsg-error-password
        kw.quitDriver();
    }

    public static void Validate_the_visibility_and_functionality_of_the_Forgot_your_password() {
        // Khởi tạo class Keyword (WebDriver sẽ tự động được setup trong Keyword)
        Keyword kw = new Keyword();

        // Mở trang đăng nhập
        kw.i_open_the_login_page("https://localhost:4443/c/login", "Alviss9.com", "Nakavo1234");

        WebDriverWait wait = new WebDriverWait(kw.getDriver(), Duration.ofSeconds(10));

        // Kiểm tra thông báo lỗi đăng nhập
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen1172")));

        kw.i_click_on("simpleLink-1042");

        // Validate visibility and functionality of "Forgot your password?" link
        kw.i_validate_forgot_password_link("simpleLink-1042", "https://localhost:4443/c/login");

        // Đóng trình duyệt sau khi kiểm tra xong
        kw.quitDriver();
    }

    public static void validate_password_masking() {
        // Khởi tạo class Keyword (WebDriver sẽ tự động được setup trong Keyword)
        Keyword kw = new Keyword();

        // Mở trang đăng nhập
        kw.i_open_the_login_page("https://localhost:4443/c/login", "Alviss9.com", "Nakavo1234");

        WebDriverWait wait = new WebDriverWait(kw.getDriver(), Duration.ofSeconds(10));

        // Locate the password field
        WebElement passwordField = kw.getDriver().findElement(By.id("ext-gen1183"));  // Replace with your actual password field locator

        wait.until(ExpectedConditions.visibilityOf(passwordField));
        wait.until(ExpectedConditions.elementToBeClickable(passwordField));

        // Send test password to the field
        String testPassword = "Nakavo1234";
        passwordField.sendKeys(testPassword);

        // Get the value of the password field
        String passwordValue = passwordField.getAttribute("value");

        // Validate if the password is masked (not equal to the entered password)
        if (!passwordValue.equals(testPassword)) {
            System.out.println("Password field is correctly masked.");
        } else {
            System.out.println("Password field is not masked.");
        }

        // Đóng trình duyệt sau khi kiểm tra xong
        kw.quitDriver();
    }
}
