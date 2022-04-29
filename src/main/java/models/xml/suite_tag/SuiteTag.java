package models.xml.suite_tag;

import java.util.List;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Root(name = "suite")
public class SuiteTag {
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
    private List<ListenerTag> listeners;
    @Element(required = false)
    private ParameterTag parameter;
    @ElementList(inline=true)
    private List<TestTag> tests;

    public SuiteTag(List<TestTag> tests) {
        this.tests = tests;
    }
}
