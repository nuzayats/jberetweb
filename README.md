jberetweb
=========

jBeret job repository viewer

### Tested environment

- WildFly 8.0.0.Final
- Oracle JDK7u51
- PostgreSQL 9.2.4

### How to use

1. git clone https://github.com/lbtc-xxx/jberetweb
2. cd jberetweb; mvn -DdataSourceName=java:jboss/jdbc/JBatchDS clean package
3. deploy target/jberetweb.war
4. access http://localhost:8080/jberetweb/

### Function

- It shows that recent job executions with jQuery DataTables.
    - comes with executionId, jobName, startTime, endTime, batchStatus
