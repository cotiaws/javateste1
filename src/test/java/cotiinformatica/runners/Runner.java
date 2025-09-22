package cotiinformatica.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/cotiinformatica/features", //local das features
		glue = "cotiinformatica/tests", //pacote onde estão as classes de testes
		plugin = { "pretty", "html:target/cucumber-report.html" } //relatórios
		)
public class Runner {

}
