/* Copyright © 2021 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 *  CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package models.xml;

import lombok.Data;
import org.simpleframework.xml.Attribute;

@Data
public class TestName {

    @Attribute
    private String name;

    public TestName() {
    }

    public TestName(String name) {
        this.name = name;
    }
}
