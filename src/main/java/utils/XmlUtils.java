package utils;

import models.xml.suite_tag.SuiteTag;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

public final class XmlUtils {

    private static final Serializer serializer = new Persister();

    private XmlUtils() {}

    public static SuiteTag readInputTestSuite() {
        return readInputTestSuite(PropertyUtils.getPropertyData(PropertyUtils.FILE_INPUT));
    }

    public static SuiteTag readInputTestSuite(String fileInput) {
        SuiteTag suite = new SuiteTag();
        try {
            //read source xml
            File source = new File(fileInput);
            suite = serializer.read(SuiteTag.class, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suite;
    }

    public static void writeTestSuite(SuiteTag suite) {
        File result = new File(PropertyUtils.getPropertyData(PropertyUtils.FILE_OUTPUT));
        try {
            serializer.write(suite, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
