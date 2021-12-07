package CommonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FetchElement {
    public WebElement getElement(String elementType, String elementName){
        WebElement e= BaseTest.driver.findElement(By.xpath(elementName));
        return  e;
    }
}
