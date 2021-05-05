package models.xml;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Data
@Root(name = "class")
public class TestClass {

    @Attribute
    private String name;
}
