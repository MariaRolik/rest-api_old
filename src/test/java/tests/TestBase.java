package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import configs.DriverConfig;
import helpers.Attach;

import java.util.Map;
import java.util.Objects;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = "https://demoqa.com";
        DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = driverConfig.pageLoadStrategy();
        Configuration.browser = driverConfig.browser();
        Configuration.browserVersion = driverConfig.browserVersion();
        Configuration.browserSize = driverConfig.browserSize();
        SelenideLogger.addListener("allure", new AllureSelenide());
        if (driverConfig.isRemote())
        {
            Configuration.remote = driverConfig.browserRemoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
        Configuration.holdBrowserOpen = true;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Cкриншот");
        Attach.pageSource();
        if (!Objects.equals(Configuration.browser, "firefox")) Attach.browserConsoleLogs();
        if (Configuration.remote != null) Attach.addVideo();
        Selenide.closeWebDriver();
    }
}