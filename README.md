AutomationSuite

A comprehensive automation suite following the Page Object Model (POM) pattern and integrated with TestNG for efficient test execution.
This project uses Maven for build management and is designed to work with Jenkins.

Prerequisites
Ensure the following are installed:

1. **Java** (version 17 or 21)  
   Note: Jenkins supports Java 17 and Java 21, but **Java 22 is not yet supported**.
   ```bash
   java -version

2. **Maven**
   ```bash
   mvn -v

3. TestNG 
4. Jenkins (for CI/CD integration)

**Installation**
1. Clone the repository:
   ```bash
   git clone https://github.com/Attri-Inc/AutomationSuite.git
2. Navigate to the project directory:
   ```bash
   cd AutomationSuite
3. Build the project using Maven:
   ```bash
   mvn clean install

** Key Components:**

**pageObjects**/: Contains classes that represent web pages, following the Page Object Model.

**tests**/: Contains TestNG test classes, each corresponding to a feature or a set of functionalities.

**utils**/: Utility classes for WebDriver setup, ExtentReports integration, and other reusable components.

**homePage_singleLogin.xml**: The XML configuration for running specific TestNG tests.


**Usage**

Run the tests using Maven and TestNG:

```bash
# Run tests defined in the TestNG XML file
mvn test
````

To specify a different TestNG suite XML, update the <suiteXmlFile> in pom.xml accordingly:
```bash
<build>
	<plugins>
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-surefire-plugin</artifactId>
			<version>3.0.0-M5</version>
			<configuration>
				<suiteXmlFiles>
					<suiteXmlFile>src/main/resources/homePage_singleLogin.xml</suiteXmlFile>
				</suiteXmlFiles>
			</configuration>
		</plugin>
	</plugins>
</build>
```


**Contributing**

To contribute:

1. Fork the repository.
   
2. Create a new branch:
```bash
git checkout -b feature-branch
```
3. Make your changes.

4. Commit your changes:
```bash
git commit -m 'Add feature'
```
5. Push to the branch:
   ```bash
   git push origin feature-branch
   ```
6. Create a Pull Request. 
