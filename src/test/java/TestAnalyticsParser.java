import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import models.analytics.AnalyticsTestsModel;
import models.xml.suite_tag.SuiteTag;
import models.xml.suite_tag.TestTag;
import utils.HttpClientUtils;
import utils.XmlUtils;

public class TestAnalyticsParser {
    private static final Logger LOG = Logger.getLogger(TestAnalyticsParser.class);

    @Test
    public void getFailedTests() {
        MultiValuedMap<String, String> parameters = new ArrayListValuedHashMap<>(3);
        parameters.put("status", "failed");
        parameters.put("status", "skipped");
        parameters.put("failureReason", "unknown");

        AnalyticsTestsModel responseResult = HttpClientUtils.getSpecifiedTests(parameters);

        if (responseResult.getData().isEmpty()) {
            LOG.info("Response from analytics is null");
            return;
        }

        Map<String, TestTag> failedTestsMap = new HashMap<>();
        responseResult.getData().forEach(test -> {
            var testTag = test.getTestTag();
            var t = new TestTag(test);
            if (failedTestsMap.containsKey(testTag)) {
                failedTestsMap.get(testTag).getTestClasses().addAll(t.getTestClasses());
            } else {
                failedTestsMap.put(testTag, t);
            }
        });

        XmlUtils.writeTestSuite(new SuiteTag(new ArrayList<>(failedTestsMap.values())));
    }

    @Test
    public void getNotExecutedTests() {
        MultiValuedMap<String, String> parameters = new ArrayListValuedHashMap<>(2);
        parameters.put("status", "failed");
        parameters.put("failureReason", "unknown");

        SuiteTag suiteFromTA = new SuiteTag(HttpClientUtils.getSpecifiedTests(parameters)
                .getData().stream().map(TestTag::new).collect(Collectors.toList()));
        SuiteTag suiteFull = XmlUtils.readInputTestSuite();

        List<TestTag> notExecutedTests = new ArrayList<>();

        suiteFull.getTests().forEach(test -> {
            if (!suiteFromTA.getTests().contains(test)) {
                notExecutedTests.add(test);
            }
        });
        suiteFull.setTests(notExecutedTests);

        XmlUtils.writeTestSuite(suiteFull);
    }
}
