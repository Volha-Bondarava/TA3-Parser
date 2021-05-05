package models.xml;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.simpleframework.xml.*;

import java.util.List;

@NoArgsConstructor
@Data
@Root(name = "suite")
public class Suite {
    @Attribute(required = false)
    private String name;
    @Attribute(required = false)
    private String verbose;
    @Attribute(required = false)
    private String parallel;
    @Attribute(name = "thread-count", required = false)
    private String threadCount;
    @Attribute(name = "data-provider-thread-count", required = false)
    private String dataProviderThreadCount;
    @Path("listeners")
    @ElementList(required = false, inline=true)
    private List<Listener> listeners;
    @Element(required = false)
    private Parameter parameter;
    @ElementList(inline=true)
    private List<Test> tests;

    public Suite(List<Test> tests) {
        this.tests = tests;
    }
}
