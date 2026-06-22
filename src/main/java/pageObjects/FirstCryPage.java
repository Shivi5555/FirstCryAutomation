package pageObjects;

import java.time.Duration;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstCryPage {

    WebDriver driver;
    WebDriverWait wait;

    public FirstCryPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void closeLocationPopup() {
        try {
            WebElement closePopup = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//span[contains(@class,'close')]"
                            + " | //div[contains(@class,'close')]"
                            + " | //button[contains(text(),'No Thanks')]"
                            + " | //span[contains(text(),'×')]"
                            + " | //button[contains(text(),'✕')]")));

            closePopup.click();

            System.out.println("Location popup closed successfully.");

        } catch (Exception e) {
            System.out.println("Location popup not displayed or already closed.");
        }
    }

    public void goToGirlsFashion() {
        try {
            Actions actions = new Actions(driver);

            WebElement girlsFashion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[normalize-space()='GIRL FASHION']"
                            + " | //span[normalize-space()='GIRL FASHION']"
                            + " | //*[normalize-space()='GIRL FASHION']")));

            actions.moveToElement(girlsFashion).pause(Duration.ofSeconds(2)).perform();

            System.out.println("Hovered on Girl Fashion section.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to hover on Girl Fashion section.", e);
        }
    }

    public void selectFrocksAndDresses() {
        try {
            Actions actions = new Actions(driver);
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Step 1: Hover on GIRL FASHION
            WebElement girlsFashion = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//a[normalize-space()='GIRL FASHION']"
                            + " | //span[normalize-space()='GIRL FASHION']"
                            + " | //*[normalize-space()='GIRL FASHION']")));

            actions.moveToElement(girlsFashion)
                    .pause(Duration.ofSeconds(1))
                    .perform();

            System.out.println("Hovered on Girl Fashion section.");

            // Step 2: Locate Frocks & Dresses using exact tag
            WebElement frocksAndDresses = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//a[@title='Frocks & Dresses' and contains(@href,'frocks-and-dresses')]")));

            try {
                // Step 3: Try actual mouse move and click first
                actions.moveToElement(frocksAndDresses)
                        .pause(Duration.ofSeconds(1))
                        .click()
                        .perform();

                System.out.println("Actually clicked on Frocks & Dresses using mouse action.");

            } catch (Exception normalClickFailed) {

                System.out.println("Normal mouse click failed, using JavaScript fallback.");

                // Step 4: Make hidden dropdown visible if parallel hover did not keep it open
                js.executeScript(
                        "document.querySelectorAll('div.option-container.submenu1-2').forEach(function(e) {" +
                        "e.style.display='block';" +
                        "e.style.visibility='visible';" +
                        "e.style.opacity='1';" +
                        "});"
                );

                Thread.sleep(1000);

                js.executeScript("arguments[0].scrollIntoView({block:'center'});", frocksAndDresses);

                Thread.sleep(500);

                // Step 5: JS click exact anchor
                js.executeScript("arguments[0].click();", frocksAndDresses);

                System.out.println("Clicked on Frocks & Dresses using JavaScript fallback.");
            }

            Thread.sleep(3000);

            // Step 6: Validate page opened
            if (driver.getCurrentUrl().contains("frocks-and-dresses")) {
                System.out.println("Frocks & Dresses page opened successfully.");
            } else {
                throw new RuntimeException("Frocks & Dresses page did not open. Current URL: " + driver.getCurrentUrl());
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to hover and click Frocks & Dresses.", e);
        }
    }
    public void validateFrocksAndDressesPage() {
        try {
            Thread.sleep(3000);

            String currentUrl = driver.getCurrentUrl();
            String pageTitle = driver.getTitle();

            System.out.println("Current URL: " + currentUrl);
            System.out.println("Page Title: " + pageTitle);

            if (currentUrl.contains("frocks-and-dresses")
                    || pageTitle.toLowerCase().contains("frocks")
                    || pageTitle.toLowerCase().contains("dresses")) {

                System.out.println("Validation Passed: User is on Frocks & Dresses page.");

            } else {
                throw new RuntimeException("Validation Failed: User is not on Frocks & Dresses page.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to validate Frocks & Dresses page.", e);
        }
    }
    public void sortPriceLowToHigh() {
        try {
            Thread.sleep(3000);

            JavascriptExecutor js = (JavascriptExecutor) driver;
            Actions actions = new Actions(driver);

            // Step 1: Click Sort by dropdown
            WebElement sortDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class,'sort-select') and @role='combobox']")));

            actions.moveToElement(sortDropdown).click().perform();

            System.out.println("Clicked on Sort By dropdown.");

            Thread.sleep(1500);

            // Step 2: Make sure sort menu is visible
            js.executeScript(
                    "document.querySelectorAll('ul.sort-menu').forEach(function(e) {" +
                    "e.classList.remove('sort-hide');" +
                    "e.style.display='block';" +
                    "e.style.visibility='visible';" +
                    "e.style.opacity='1';" +
                    "});"
            );

            Thread.sleep(1000);

            // Step 3: Click ONLY Price inside sort menu
            WebElement priceOption = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//ul[contains(@class,'sort-menu')]//li[normalize-space()='Price']"
                            + " | //ul[contains(@class,'sort-menu')]//*[normalize-space()='Price']")));

            actions.moveToElement(priceOption).click().perform();

            System.out.println("Clicked on Price option.");

            Thread.sleep(4000);

            // Step 4: Validate selected sort
            WebElement selectedSort = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class,'sort-select') and @role='combobox']")));

            String sortText = selectedSort.getText();
            String ariaLabel = selectedSort.getAttribute("aria-label");

            System.out.println("Selected Sort Text: " + sortText);
            System.out.println("Selected Sort Aria Label: " + ariaLabel);

            if ((sortText != null && sortText.toLowerCase().contains("price"))
                    || (ariaLabel != null && ariaLabel.toLowerCase().contains("price"))) {

                System.out.println("Price Low To High sorting applied successfully.");

            } else {
                throw new RuntimeException("Price sorting was not applied. Current sort text: "
                        + sortText + " | aria-label: " + ariaLabel);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to apply Price Low To High sorting.", e);
        }
    }
    public void scrollToColorFilter() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Scroll page until color filter section is available
            WebElement colorContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@id='fltColor']")));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", colorContainer);

            Thread.sleep(1500);

            System.out.println("Scrolled to Colors filter section.");

        } catch (Exception e) {
            throw new RuntimeException("Failed to scroll to Colors filter section.", e);
        }
    }
    public void selectPinkColor() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            Thread.sleep(2000);

            // Step 1: Locate Colors filter container
            WebElement colorContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@id='fltColor']")));

            // Step 2: Bring Colors section into view
            js.executeScript("arguments[0].scrollIntoView({block:'center'});", colorContainer);
            Thread.sleep(1000);

            // Step 3: Scroll inside color container to top because Pink is at top
            js.executeScript("arguments[0].scrollTop = 0;", colorContainer);
            Thread.sleep(1000);

            // Step 4: Locate actual Pink checkbox/swatch
            WebElement pinkCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@id='fltColor']//span[@title='Pink']")));

            js.executeScript("arguments[0].scrollIntoView({block:'center'});", pinkCheckbox);
            Thread.sleep(1000);

            String beforeClass = pinkCheckbox.getAttribute("class");
            System.out.println("Pink class before click: " + beforeClass);

            // Step 5: If already checked, do not click again
            if (beforeClass != null && beforeClass.contains("chkspanckh")) {
                System.out.println("Pink is already selected.");
                return;
            }

            // Step 6: Click actual Pink checkbox using JavaScript mouse events
            js.executeScript(
                    "arguments[0].dispatchEvent(new MouseEvent('mouseover', {bubbles:true}));" +
                    "arguments[0].dispatchEvent(new MouseEvent('mousedown', {bubbles:true}));" +
                    "arguments[0].dispatchEvent(new MouseEvent('mouseup', {bubbles:true}));" +
                    "arguments[0].dispatchEvent(new MouseEvent('click', {bubbles:true}));",
                    pinkCheckbox
            );

            Thread.sleep(4000);

            // Step 7: Re-locate after page refresh/update
            WebElement pinkCheckboxAfter = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[@id='fltColor']//span[@title='Pink']")));

            String afterClass = pinkCheckboxAfter.getAttribute("class");
            System.out.println("Pink class after click: " + afterClass);

            // Step 8: Validate selected
            if (afterClass != null && afterClass.contains("chkspanckh")) {
                System.out.println("Validation Passed: Pink checkbox is selected.");
            } else {
                throw new RuntimeException("Validation Failed: Pink checkbox is not selected.");
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to select and validate Pink color filter.", e);
        }
    }
    public void printFilteredResults() throws InterruptedException {

        Thread.sleep(3000);

        List<WebElement> products = driver.findElements(
                By.xpath("//div[contains(@class,'lblock')]"
                        + " | //div[contains(@class,'ltn')]"
                        + " | //div[contains(@class,'product')]"));

        System.out.println("========================================");
        System.out.println("Filtered Product Results");
        System.out.println("========================================");

        System.out.println("Total Products Found: " + products.size());

        if (products.size() > 0) {

            for (int i = 0; i < products.size(); i++) {

                String productText = products.get(i).getText();

                if (!productText.trim().isEmpty()) {
                    System.out.println("Product " + (i + 1) + ":");
                    System.out.println(productText);
                    System.out.println("----------------------------------------");
                }
            }

            System.out.println("Validation Passed: Filtered products are displayed.");

        } else {
            System.out.println("Validation Failed: No products found.");
        }
    }
}
