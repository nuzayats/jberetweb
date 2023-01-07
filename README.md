jberetweb
=========

jBeret job repository viewer

### Tested environment

- WildFly 27.0.1.Final (Jakarta EE 10)
- Eclipse Temurin OpenJDK 17
- Oracle 21.8 (driver)

### Prerequisites

- JDBC job repository is registered as ```java:jboss/jdbc/JBatchDS```
- JSF project stage is defined in JNDI resource ```java:/env/jsf/ProjectStage``` on the application server
    - example: ```./jboss-cli.sh --connect --command='/subsystem=naming/binding=java\\:\/env\/jsf\/ProjectStage:add(binding-type=simple,value=Development,class=java.lang.String)'```
- (Option) Exposed JobOperator remote EJB interface is installed in the batch application archive

### How to use

1. ```git clone https://github.com/lbtc-xxx/jberetweb```
2. ```cd jberetweb; mvn -DdataSourceName=java:jboss/jdbc/JBatchDS -DjobOperator.jndi=java:global/batchapp/JobOperatorFacade clean package```
3. deploy target/jberetweb.war
4. access http://localhost:8080/jberetweb/

### Function

- It shows that recent job executions with tables.
    - comes with executionId, jobName, startTime, endTime, batchStatus
- Various job operations such as start, restart, stop, abandon etc

### For multiple deployment use

jberetweb offers multiple deployment use mode. in this mode,

- "App Name" column will be added to Job Execution table
- "App Name" input field will be added to Start/Restart Job window
- jberetweb will change a part of JNDI name of JobOperator for each lookup accordingly

Make sure that your facade beans are deployed on each deployment and named on JNDI like ```java:global/${war-name}/${facade-class-name}```. normally you don't have to care about it because it's standardized naming rule for EJB which packed in WAR archives.

To enable multiple deployment use mode, suppress ```-DjobOperator.jndi```, and specify ```-DjobOperator.name=${facade-class-name}```` in mvn option.

For EAR deployments, you can specify ```-DjobOperator.format``` to override formatting rule of JNDI which jberetweb uses. default is ```java:global/{0}/{1}```. {0} will be replaced by deployment name (application name) and {1} will be replaced by jobOperator.name.

### Notes

- More information can be found at [author's blog](http://www.nailedtothex.org/roller/kyle/category/jberetweb)
- Suppress mvn parameter ```-DjobOperator.jndi``` to invoke ```BatchRuntime.getJobOperator()``` directly (in that case, you have to put your batch artifacts into jberetweb deployment).
