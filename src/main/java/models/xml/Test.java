package models.xml;

import lombok.Data;
import models.analytics.TestModel;
import org.simpleframework.xml.*;

import java.util.Collections;
import java.util.List;

@Data
@Root(name = "test")
public class Test {
    @Attribute
    private String name;
    @Attribute(name = "preserve-order", required=false)
    private String preserveOrder;
    @Attribute(required = false)
    private String parallel;

    @Element(required = false)
    private Parameter parameter;

//    @Path("packages")
//    @ElementList(inline = true, required = false)
//    private List<Package> packages;
//
//    @Path("packages")
//    @Element(required = false)
//    private Package aPackage;

    @Path("classes")
    @ElementList(inline = true, required = false)
    private List<TestClass> testClasses;

//    @Path("classes")
//    @Element(required = false)
//    private TestClass testClass;


    public Test() {
    }

    public Test(TestModel analyticsTestModel) {
        this.setName(analyticsTestModel.getTestTag());

        TestClass failedTestClassTag = new TestClass();
        failedTestClassTag.setName(analyticsTestModel.getName());
        this.setTestClasses(Collections.singletonList(failedTestClassTag));
    }
}
