import models.analytics.AnalyticsTestsModel;
import models.xml.Suite;
import org.testng.annotations.Test;
import utils.HttpClientUtils;
import utils.XmlUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestAnalyticsParser {

    @Test
    public void getFailedTests() {
        AnalyticsTestsModel responseResult = HttpClientUtils.sendRequest();

        if (responseResult == null) {
            System.err.println("Response from analytics is null");
            return;
        }

        System.out.println(responseResult);
        List<models.xml.Test> failedTests = new ArrayList<>();
        responseResult.getData().forEach(test -> {
            if (test.getStatus().equalsIgnoreCase("failed") || test.getStatus().equalsIgnoreCase("skipped")) {
                failedTests.add(new models.xml.Test(test));
            }
        });

        XmlUtils.writeTestSuite(new Suite(failedTests));
    }

    @Test
    public void getNotExecutedTests() {
        Suite suiteFromTA = new Suite(HttpClientUtils.sendRequest().getData().stream().map(models.xml.Test::new).collect(Collectors.toList()));
        Suite suiteFull = XmlUtils.readInputTestSuite();

        List<models.xml.Test> notExecutedTests = new ArrayList<>();

        suiteFull.getTests().forEach(test -> {
            if (!suiteFromTA.getTests().contains(test)) {
                notExecutedTests.add(test);
            }
        });
        suiteFull.setTests(notExecutedTests);

        XmlUtils.writeTestSuite(suiteFull);
    }
}
