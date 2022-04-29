package models.xml.suite_tag;

import lombok.Data;
import org.simpleframework.xml.Attribute;

@Data
public class ParameterTag {
    @Attribute(required = false)
    private String name;
    @Attribute(required = false)
    private String value;
}
