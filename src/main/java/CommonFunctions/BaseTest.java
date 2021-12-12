    package CommonFunctions;

    import CommonFunctions.ConfigReader;
    import com.aventstack.extentreports.ExtentReports;
    import com.aventstack.extentreports.ExtentTest;
    import com.aventstack.extentreports.Status;
    import com.aventstack.extentreports.markuputils.ExtentColor;
    import com.aventstack.extentreports.markuputils.Markup;
    import com.aventstack.extentreports.markuputils.MarkupHelper;
    import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
    import com.aventstack.extentreports.reporter.configuration.Theme;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.testng.ITestResult;
    import org.testng.annotations.*;

    import java.io.IOException;
    import java.lang.reflect.Method;
    import java.util.List;
    import java.util.Set;
    import java.util.concurrent.TimeUnit;

    public class BaseTest {

    public static WebDriver driver;
    public static ConfigReader configReader;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports reporter;
    public static ExtentTest logger;


        @BeforeMethod

        public void beforeTestMethod( Method testMethod) throws IOException {
            logger=reporter.createTest(testMethod.getName());
        getDriver("Chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        configReader=new ConfigReader("QA");
        driver.get(configReader.readPropertyFile("applicationUrl"));
        }


        @AfterMethod
        public void afterTestMethod(ITestResult result){
            if(result.getStatus()==ITestResult.SUCCESS){
                String methodName=result.getMethod().getMethodName();
                String logtext=" Test Case "+methodName+" Passed";
                Markup m= MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
                logger.log(Status.PASS,m);
            }

           else if(result.getStatus()==ITestResult.FAILURE){
                String methodName=result.getMethod().getMethodName();
                String logtext=" Test Case "+methodName+" Failed";
                Markup m= MarkupHelper.createLabel(logtext, ExtentColor.RED);
                logger.log(Status.FAIL,m);
            }
            else {
                String methodName=result.getMethod().getMethodName();
                String logtext=" Test Case "+methodName+" Skipped";
                Markup m= MarkupHelper.createLabel(logtext, ExtentColor.AMBER);
                logger.log(Status.SKIP,m);
            }

        driver.close();

        }
    @BeforeTest
    public void beforeTest(){
        htmlReporter=new ExtentHtmlReporter("D:/MyGitCodeBase/qastack/Reports/ExecutionReport.html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Execution Report");
        htmlReporter.config().setReportName("Execution Results");
        htmlReporter.config().setTheme(Theme.DARK);

        reporter=new ExtentReports();
        reporter.attachReporter(htmlReporter);
        reporter.setSystemInfo("Executed By ","QAStack");


        }

        public WebDriver getDriver(String browserName){

            switch(browserName.toUpperCase()) {
                case "CHROME":
                    System.setProperty("webdriver.chrome.driver","D:/MyGitCodeBase/qastack/drivers/chromedriver.exe");
                    driver=new ChromeDriver();
                    break;
                case "EDGE":
                    System.setProperty("webdriver.edge.driver","D:/MyGitCodeBase/qastack/drivers/msedgedriver.exe");
                    driver=new ChromeDriver();
                    break;

                default:
                    System.setProperty("webdriver.chrome.driver","D:/MyGitCodeBase/qastack/drivers/chromedriver.exe");
                    driver=new ChromeDriver();
            }
            return driver;
        }
    }
