package models.xml.suite_tag;

import lombok.Data;
import org.simpleframework.xml.Attribute;

@Data
public class ListenerTag {
    @Attribute(name = "class-name")
    private String className;
}
