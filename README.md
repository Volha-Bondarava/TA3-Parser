# TA3-Parser

First of all - add your user.token (can be found in TA) & url.run.id (id of the desired run) to `config.properties` file

## Getting failed tests: 
1. Paste `url.run.id` (id of the desired run). 
2. Run `TestAnalyticsParser#getFailedTests` test. 

## Getting not executed tests: 
1. Paste `url.run.id` (id of the desired run). 
2. Paste into `input_test_suite.xml` file your tests. Paste only list of tests, that are inside the `<test>` tags. 
3. Run `TestAnalyticsParser#getNotExecutedTests` test. 
