package models.xml.suite_tag;

import lombok.Data;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Data
@Root(name = "package")
public class PackageTag {

    @Attribute
    private String name;
}
