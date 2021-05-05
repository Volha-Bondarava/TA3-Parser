/* Copyright © 2021 EIS Group and/or one of its affiliates. All rights reserved. Unpublished work under U.S. copyright laws.
 CONFIDENTIAL AND TRADE SECRET INFORMATION. No portion of this work may be copied, distributed, modified, or incorporated into any other media without EIS Group prior written consent.*/
package models.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestModel {
    private String id;
    private String runId;
    private String name; //path to the test in the suite file
    private boolean isTest;
    private List<String> suiteTag;
    private String testTag; //test name in the suite file
    private List<String> groups;
    private String status; //passed, failed
    private String failureReason; //prod, unknown, in_analysis, perf, auto, env
    private String started;
    private String finished;
}
