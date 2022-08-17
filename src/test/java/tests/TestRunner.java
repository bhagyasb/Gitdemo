package tests;

import appmanager.*;
import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.util.ArrayList;


@CucumberOptions(features ={"classpath:features/GreenKart.feature",
       // "classpath:features/GreenKart.feature",
                              },
        glue = "tests",
        plugin ={"appmanager.ExtentCucumberFormatter"},
//       plugin = {"pretty"},
 // dryRun = true,
        //tags={"@Smoke11,@Regression11,@Functional11"})
        tags={"@GreenKart1"})


public class TestRunner extends AbstractTestNGCucumberTests {
    String sourceDir = "./src/test/resources/";
    private Logger logger= LoggerFactory.getLogger(TestRunner.class);

    public TestRunner() {
    }

    private static ExtentCucumberFormatter formatter;

    static ArrayList<String> listOfScenarios = new ArrayList<>();
    static ArrayList<String> results = new ArrayList<>();
    private static final Regex myRegex = new Regex("[^\\\\p{Alpha}\\\\p{Digit}]+");
    String emailSenderSwitch= "";
    String[] emailAddresses = null;



    @BeforeSuite
    public void setUp() {
//        WebDriverUpdater.updateChromeDriver();
        formatter = new ExtentCucumberFormatter();
        ExtentCucumberFormatter.initiateExtentCucumberFormatter();
        ExtentCucumberFormatter.loadConfig(new File(sourceDir + "extent-config.xml"));


    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {

        ApplicationManager.stop();

    }


    @Before
    public void startScenario(Scenario scenario) {
       logger.info("Start scenario"+scenario.getName());

    }

    @After
    public void endScenario(Scenario scenario) {
      // ApplicationManager.stop();
        listOfScenarios.add(scenario.getStatus().toUpperCase() + " - " + scenario.getName());
        formatter.stopVideoRecording();
    }
}

