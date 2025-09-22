package cotiinformatica.tests;

import static org.junit.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

import cotiinformatica.helpers.ScreenshotHelper;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class CriarContaTestSteps {

	//Atributos
	private ChromeDriver driver;
	private WebDriverWait wait;
	
	@Dado("Eu estou na página inicial da loja virtual")
	public void eu_estou_na_página_inicial_da_loja_virtual() {
		
		ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // necessário em CI
        options.addArguments("--no-sandbox"); // necessário em GitHub Actions
        options.addArguments("--disable-dev-shm-usage"); // necessário em CI/Linux
        options.addArguments("--remote-allow-origins=*");
		
		//abrir o navegador
		driver = new ChromeDriver(options);
		
		//definir a configuração do wait (aguardar)
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		//maximizar a janela do navegador
		driver.manage().window().maximize();
		
		//acessar a página da loja virtual
		driver.navigate().to("http://www.automationpractice.pl/index.php");
	}

	@Dado("Eu acesso o link de criação de conta")
	public void eu_acesso_o_link_de_criação_de_conta() {
		
		//Capturar o elemento (link de navegação) através do XPATH
		WebElement link = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a"));
		
		//Ação de clicar no elemento
		link.click();
	}

	@Dado("Eu informo o meu email e solicito a criação da conta")
	public void eu_informo_o_meu_email_e_solicito_a_criação_da_conta() {

		Faker faker = new Faker();
		
		//Capturar o elemento (campo de texto) através do XPATH
		WebElement email = driver.findElement(By.xpath("//*[@id=\"email_create\"]"));
		
		//limpando o conteúdo do campo
		email.clear();
		
		//preenchendo o campo com um endereço de email fake		
		email.sendKeys(faker.internet().emailAddress());
		
		//Capturar o elemento (botão) para criação da conta
		WebElement botao = driver.findElement(By.xpath("//*[@id=\"SubmitCreate\"]"));
		
		//clicar no botão
		botao.click();
	}
	
	@Dado("Eu preencho o primeiro nome {string}")
	public void eu_preencho_o_primeiro_nome(String primeironome) {
	    
		WebElement firstName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"customer_firstname\"]")));
		firstName.clear();
		firstName.sendKeys(primeironome);
	}

	@Dado("Eu preencho o sobrenome {string}")
	public void eu_preencho_o_sobrenome(String sobrenome) {

		WebElement lastName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"customer_lastname\"]")));
		lastName.clear();
		lastName.sendKeys(sobrenome);
	}

	@Dado("Eu preencho a senha {string}")
	public void eu_preencho_a_senha(String senha) {

		WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"passwd\"]")));
		password.clear();
		password.sendKeys(senha);	
	}

	@Dado("Eu informo minha data de nascimento")
	public void eu_informo_minha_data_de_nascimento() {
	    
		Faker faker = new Faker();
		
		WebElement dayOfBirth = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"days\"]")));
		WebElement monthOfBirth = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"months\"]")));
		WebElement yearOfBirth = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"years\"]")));
		
		new Select(dayOfBirth).selectByValue(String.valueOf(faker.number().numberBetween(1, 28)));
		new Select(monthOfBirth).selectByValue(String.valueOf(faker.number().numberBetween(1, 12)));
		new Select(yearOfBirth).selectByValue(String.valueOf(faker.number().numberBetween(1970, 2020)));
	}

	@Quando("Eu solicito a realização do cadastro")
	public void eu_solicito_a_realização_do_cadastro() {

		//Capturar o elemento do botão
		WebElement botao = driver.findElement(By.xpath("//*[@id=\"submitAccount\"]"));
		
		//Clicar no botão
		botao.click();
	}

	@Entao("Sistema informa que o a conta foi criada com sucesso")
	public void sistema_informa_que_o_a_conta_foi_criada_com_sucesso() {
		
		//Capturar o elemento que exibe mensagem de sucesso
		WebElement mensagem = driver.findElement(By.xpath("//*[@id=\"center_column\"]/p[1]"));
		
		//Definindo as mensagens
		String resultadoEsperado = "Your account has been created.";
		String resultadoObtido = mensagem.getText();
		
		//Asserção (veriricar o resultado esperado X resultado obtido
		assertEquals(resultadoEsperado, resultadoObtido);
		
		//Gerando a evidência do teste
		ScreenshotHelper.captureScreenshot(driver, "Criar conta de cliente com sucesso");
		
		//Fechar o navegador
		driver.quit();
	}
}


