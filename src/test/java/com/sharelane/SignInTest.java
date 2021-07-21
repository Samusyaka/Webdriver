package com.sharelane;

//import com.sun.jdi.Value;
//import javax.swing.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;




public class SignInTest {
    @Test
    public void signInPositive() {

        System.setProperty("webdriver.chrome.driver", "d:\\TMS\\Webdriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
//        open sharelane.com
//        click on the "Sign up" button
        driver.get("https://sharelane.com/cgi-bin/register.py");

//        fill the form with ZIP code
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        WebElement continueButton = driver.findElement(By.cssSelector("[type='submit'][value='Continue']"));
        continueButton.click();
        //        fill the fields
        WebElement firstnameInput = driver.findElement(By.name("first_name"));
        firstnameInput.sendKeys("Sergey");
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys("Sergey@mail.ru");
        WebElement passwordInput = driver.findElement(By.name("password1"));
        passwordInput.sendKeys("12345");
        WebElement confirmPasswordInput = driver.findElement(By.name("password2"));
        confirmPasswordInput.sendKeys("12345");
        WebElement registerButton = driver.findElement(By.cssSelector("[type='submit'][value='Register']"));
        registerButton.click();
        WebElement accountCreateMessage = driver.findElement(By.className("confirmation_message"));
        String accountCreate = accountCreateMessage.getText();
        Assert.assertEquals(accountCreate, "Account is created!");




//находим и сохраняем в переменную емайл
        By selectorEmail = By.xpath(".//td[contains(text(),'Email')]/following::td/b");
        WebElement email = driver.findElement(selectorEmail);
        String copyEmail = email.getText();
        System.out.println(copyEmail); //вывод для проверки что поле скопировано
//находим и сохраняем в переменную пароль
        By selectorPassword = By.xpath(".//td[contains(text(),'Password')]/following::td");
        WebElement password = driver.findElement(selectorPassword);
        String copyPassword = password.getText();
        System.out.println(copyPassword); //вывод для проверки что поле скопировано

//логинимся
        driver.get("https://sharelane.com/cgi-bin/main.py");
        WebElement eMailInput = driver.findElement(By.name("email"));
        eMailInput.sendKeys(copyEmail);
        WebElement newPasswordInput = driver.findElement(By.name("password"));
        newPasswordInput.sendKeys(copyPassword);
        WebElement loginButton = driver.findElement(By.cssSelector("[type='submit'][value='Login']"));
        loginButton.click();


    //логаут

        driver.get("https://sharelane.com/cgi-bin/log_out.py");

        WebElement logOutMessage = driver.findElement(By.className("confirmation_message"));
        String logOut = logOutMessage.getText();
        Assert.assertEquals(logOut, "You've been logged out");
    }
}
