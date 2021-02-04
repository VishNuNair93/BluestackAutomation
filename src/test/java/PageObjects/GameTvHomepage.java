package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GameTvHomepage {
	WebDriver driver;

	public GameTvHomepage(WebDriver driver) {
		this.driver = driver;
	}

	By availableGamesHeader = By.xpath("//h3[text()='Available Games']");
	By gamesInFooter = By.xpath("//div[@id='game_list']/ul/li/a");
	By gameHeader = By.xpath("//h1[@class='heading']");
	By gameTournaments = By.xpath("//h2/span[@class='count-tournaments']");

	public WebElement availableGamesHeader() {
		return driver.findElement(availableGamesHeader);
	}

	public List<WebElement> listofGamesinFooter() {
		return driver.findElements(gamesInFooter);
	}

	public String getGameHeading() {
		return driver.findElement(gameHeader).getText();
	}

	public String getGameTournamentCount() {
		return driver.findElement(gameTournaments).getText();
	}
}
