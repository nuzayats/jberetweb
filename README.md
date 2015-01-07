jberetweb
=========

jBeret job repository viewer

### Tested environment

- WildFly 8.0.0.Final
- Oracle JDK7u51
- PostgreSQL 9.2.4

### Preconditions

- JDBC job repository is registered as java:jboss/jdbc/JBatchDS
- JSF project stage is defined in JNDI resource 'java:/env/jsf/ProjectStage' on the application server
    - example: ./jboss-cli.sh --connect --command='/subsystem=naming/binding=java\\:\/env\/jsf\/ProjectStage:add(binding-type=simple,value=Development,class=java.lang.String)'
- (Option) Exposed JobOperator remote EJB interface is installed in the batch application archive

### How to use

1. git clone https://github.com/lbtc-xxx/jberetweb
2. cd jberetweb; mvn -DdataSourceName=java:jboss/jdbc/JBatchDS -DjobOperator.jndi=java:global/batchapp/JobOperatorFacade clean package
3. deploy target/jberetweb.war
4. access http://localhost:8080/jberetweb/

### Function

- It shows that recent job executions with tables.
    - comes with executionId, jobName, startTime, endTime, batchStatus
- Various job operations such as start, restart, stop, abandon etc

### Notes

- More information can be found at [author's blog](http://www.nailedtothex.org/roller/kyle/category/jberetweb)
- Suppress mvn parameter "-DjobOperator.jndi" to invoke BatchRuntime.getJobOperator() directly (in that case, you have to put your batch artifacts into jberetweb deployment).