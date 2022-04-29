/* Copyright © 2021 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 *  CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package models.xml.suite_tag;

import lombok.Data;
import models.xml.NameValue;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.util.List;

@Data
@Root(name = "methods")
public class MethodsTag {

    @Element(required = false)
    private List<NameValue> include;

    @Element(required = false)
    private List<NameValue> exclude;
}
