package modulos.produto;

import dev.failsafe.internal.util.Assert;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


@DisplayName("Testes Mobile Produto")
public class ProdutoTest {

    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void antesDoTeste() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options().setUdid("RXCTA0BE8XR").setApp("src/main/resources/app/lojinha-nativa.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void testeDeLoginComSucesso()  {
        WebElement userInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Usuário']"));
                userInput.sendKeys("admin");

        WebElement passInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Senha']"));
        passInput.sendKeys("admin");

        WebElement enterButton = driver.findElement(AppiumBy.id("com.lojinha:id/button"));
        enterButton.click();

        WebElement floatingActionButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.lojinha:id/floatingActionButton")));

        Assertions.assertTrue(floatingActionButton.isDisplayed());
    }

    @Test
    public void testeDeLoginSemSucesso()  {
        WebElement userInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Usuário']"));
        userInput.sendKeys("usuário inválido");

        WebElement passInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Senha']"));
        passInput.sendKeys("senha inválida");

        WebElement enterButton = driver.findElement(AppiumBy.id("com.lojinha:id/button"));
        enterButton.click();

        WebElement floatingActionButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.lojinha:id/floatingActionButton")));

        Assertions.assertFalse(floatingActionButton.isDisplayed());
    }
    @Test
    public void TesteInserindoValorErrado ()   {
        WebElement userInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Usuário']"));
        userInput.sendKeys("admin");

        WebElement passInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Senha']"));
        passInput.sendKeys("admin");

        WebElement enterButton = driver.findElement(AppiumBy.id("com.lojinha:id/button"));
        enterButton.click();

        WebElement floatingActionButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.lojinha:id/floatingActionButton")));

        Assertions.assertTrue(floatingActionButton.isDisplayed());

        driver.findElement(AppiumBy.id("com.lojinha:id/floatingActionButton")).click();

        driver.findElement(AppiumBy.id("com.lojinha:id/productName")).click();
        driver.findElement(AppiumBy.id("com.lojinha:id/productName")).findElement(AppiumBy.id("com.lojinha:id/editText")).sendKeys("PS5");

        driver.findElement(AppiumBy.id("com.lojinha:id/productValue")).click();
        driver.findElement(AppiumBy.id("com.lojinha:id/productValue")).findElement(AppiumBy.id("com.lojinha:id/editText")).sendKeys("700001");

        driver.findElement(AppiumBy.id("com.lojinha:id/productColors")).click();
        driver.findElement(AppiumBy.id("com.lojinha:id/productColors")).findElement(AppiumBy.id("com.lojinha:id/editText")).sendKeys("Branco");

        driver.findElement(AppiumBy.id("com.lojinha:id/saveButton")).click();
        String mensagemApresentada = driver.findElement(AppiumBy.xpath("//android.widget.Toast")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$0,01 E R$7.000,00",mensagemApresentada);
    }
    @Test
    public void TesteIncluirProduto()  {
        WebElement userInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Usuário']"));
        userInput.sendKeys("admin");

        WebElement passInput = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@text='Senha']"));
        passInput.sendKeys("admin");

        WebElement enterButton = driver.findElement(AppiumBy.id("com.lojinha:id/button"));
        enterButton.click();

        WebElement floatingActionButton = wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.lojinha:id/floatingActionButton")));

        Assertions.assertTrue(floatingActionButton.isDisplayed());

        driver.findElement(AppiumBy.id("com.lojinha:id/floatingActionButton")).click();

        driver.findElement(AppiumBy.id("com.lojinha:id/productName")).click();
        driver.findElement(AppiumBy.id("com.lojinha:id/productName")).findElement(AppiumBy.id("com.lojinha:id/editText")).sendKeys("PS5 Slim");

        driver.findElement(AppiumBy.id("com.lojinha:id/productValue")).click();
        driver.findElement(AppiumBy.id("com.lojinha:id/productValue")).findElement(AppiumBy.id("com.lojinha:id/editText")).sendKeys("5000");

        driver.findElement(AppiumBy.id("com.lojinha:id/productColors")).click();
        driver.findElement(AppiumBy.id("com.lojinha:id/productColors")).findElement(AppiumBy.id("com.lojinha:id/editText")).sendKeys("Preto");

        driver.findElement(AppiumBy.id("com.lojinha:id/saveButton")).click();
        String mensagemApresentada = driver.findElement(AppiumBy.xpath("//android.widget.Toast")).getText();
        Assertions.assertEquals("Produto adicionado com sucesso",mensagemApresentada);

    }

    }
