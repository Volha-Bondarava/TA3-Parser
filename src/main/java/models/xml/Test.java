package models.xml;

import lombok.Data;
import models.analytics.TestModel;
import org.simpleframework.xml.*;

import java.util.Collections;
import java.util.List;

@Data
@Root(name = "test")
public class Test {
    //attributes
    @Attribute
    private String name;
    @Attribute(name = "preserve-order", required = false)
    private String preserveOrder;
    @Attribute(required = false)
    private String parallel;

    //elements
    @Element(required = false)
    private Parameter parameter;
//
//    @Path("packages")
//    @ElementList(inline = true, required = false)
//    private List<Package> packages;

    @Path("classes")
    @ElementList(inline = true, required = false)
    private List<TestClass> testClasses;

    @Element(required = false)
    private Methods methods;


    public Test() {
    }

    public Test(TestModel analyticsTestModel) {
        this.setName(analyticsTestModel.getTestTag());

        TestClass failedTestClassTag = new TestClass();
        Methods method = new Methods();
        method.setInclude(Collections.singletonList(new TestName(analyticsTestModel.getName().substring(0, analyticsTestModel.getName().lastIndexOf(".")))));

        failedTestClassTag.setName(analyticsTestModel.getName().substring(0, analyticsTestModel.getName().lastIndexOf(".")));
        this.setTestClasses(Collections.singletonList(failedTestClassTag));
    }
}
