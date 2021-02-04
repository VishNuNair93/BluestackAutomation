package BluestackPackage;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import PageObjects.GameTvHomepage;
import ReusableMethods.BasicMethods;
import ReusableMethods.FileOperations;

public class BSTest extends BasicMethods{

	GameTvHomepage gameObject;
	public String URL;
	WebDriver driver;
	public String browserName;
	FileOperations fw;
	String resultFile = "res\\results.txt";

	@BeforeClass
	public void initialize() throws IOException {
		browserName = readFromConfig("BROWSER");
		URL = readFromConfig("URL");
		System.out.println(browserName);
		fw = new FileOperations(resultFile);
		driver = launchBrowser(browserName);
	}

	@Test
	public void readGameData() throws Exception {
		navigateToURL(URL);
		gameObject = new GameTvHomepage(driver);
		WebElement footerHeader = gameObject.availableGamesHeader();
		scrollIntoView(driver, footerHeader, "GamesinFooter");

		List<WebElement> listofGamesinFooter = gameObject.listofGamesinFooter();
		int totalGamesinFooter = listofGamesinFooter.size();
		System.out.println("Total No. of Games in Footer: " + totalGamesinFooter);

		// System.out.println("# Game name Page URL Page Status Tournament
		// Count");
		fw.writeHeader("#", "Game name", "Page URL", "Page Status", "Tournament count");
		for (int i = 0; i < totalGamesinFooter; i++) {
			listofGamesinFooter = gameObject.listofGamesinFooter();
			click(driver, listofGamesinFooter.get(i));

			String gameHeader = gameObject.getGameHeading();
			String url = driver.getCurrentUrl();
			int response = getStatusCode(driver, url);
			String gameTournamentCount = gameObject.getGameTournamentCount();
			System.out.println((i + 1) + "   " + gameHeader + "   " + url + "   " + response + "   "
					+ gameTournamentCount + "   ");
			fw.writeData(i+1, gameHeader, url, response, gameTournamentCount);

			back(driver);
		}

	}

	@AfterClass
	public void shutdown() {
		driver.close();
	}
	
}
