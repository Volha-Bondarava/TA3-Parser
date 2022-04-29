package models.xml.suite_tag;

import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import lombok.Data;

@Data
@Root(name = "class")
public class TestClass {

    @Attribute
    private String name;

    @Element(required = false)
    private List<MethodsTag> methods;
}
