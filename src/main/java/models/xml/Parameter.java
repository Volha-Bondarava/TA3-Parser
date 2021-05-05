package models.xml;

import lombok.Data;
import org.simpleframework.xml.Attribute;

@Data
public class Parameter {
    @Attribute(required = false)
    private String name;
    @Attribute(required = false)
    private String value;
}
