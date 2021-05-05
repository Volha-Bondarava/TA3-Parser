package models.xml;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Data
@Root(name = "package")
public class Package {

    @Attribute
    private String name;
}
