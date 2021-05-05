package models.xml;

import lombok.Data;
import org.simpleframework.xml.Attribute;

@Data
public class Listener {
    @Attribute(name = "class-name")
    private String className;
}
