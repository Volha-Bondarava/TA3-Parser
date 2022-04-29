package models.xml.suite_tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import lombok.Data;
import models.analytics.TestModel;
import models.xml.NameValue;

@Data
@Root(name = "test")
public class TestTag {
    //attributes
    @Attribute
    private String name;
    @Attribute(name = "preserve-order", required = false)
    private String preserveOrder;
    @Attribute(required = false)
    private String parallel;

    //elements
    @Element(required = false)
    private ParameterTag parameter;

//    @Path("packages")
//    @ElementList(inline = true, required = false, empty = false)
//    private List<PackageTag> packages;

    @Path("classes")
    @ElementList(inline = true, required = false, empty = false)
    private List<TestClass> testClasses;


    public TestTag() {
    }

    public TestTag(TestModel analyticsTestModel) {
        this.setName(analyticsTestModel.getTestTag());

        int endPointIndex = analyticsTestModel.getName().lastIndexOf(".");

        MethodsTag method = new MethodsTag();
        method.setInclude(Collections.singletonList(new NameValue(analyticsTestModel.getName().substring(0, endPointIndex))));

        TestClass failedTestClassTag = new TestClass();
        failedTestClassTag.setName(analyticsTestModel.getName().substring(0, endPointIndex));
        List<TestClass> classList = new ArrayList<>(1);
        classList.add(failedTestClassTag);
        this.setTestClasses(classList);
    }
}
