package PageWiseActions;

import CommonFunctions.FetchElement;
import PageObjects.HomePage;

public class HomePageActions {
    public void clickAboutUs(){
        FetchElement element= new FetchElement();
        element.getElement("Xpath", HomePage.element).click();


    }
}
