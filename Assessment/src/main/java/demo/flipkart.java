package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class flipkart 
{
	public static void main(String[] args) throws InterruptedException 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\QQA0429\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		
		driver.get("https://www.flipkart.com/");
		
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		
		Thread.sleep(5000);
	WebElement	electronics=driver.findElement(By.xpath("(//div[@class='xtXmba'])[5]"));
		
		Actions act=new Actions(driver);
		
		
		act.moveToElement(electronics).build().perform();
		
		WebElement headphone = driver.findElement(By.xpath("//a[text()='Wired Headphones']"));
		
		act.click(headphone).build().perform();
		
		
		
	}

}
