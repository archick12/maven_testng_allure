1.
<dependency>
    <groupId>ru.yandex.qatools.allure</groupId>
    <artifactId>allure-testng-adaptor</artifactId>
    <version>${allure.version}</version>
    <exclusions>
        <exclusion>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </exclusion>
    </exclusions>
</dependency>

2.
<build>
    <plugins>
        <!-- Below plug-in is used to execute tests -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.18.1</version>
            <configuration>
                <argLine>
                    -javaagent:"${settings.localRepository}/org/aspectj/aspectjweaver/${aspectj.version}/aspectjweaver-${aspectj.version}.jar"
                </argLine>
                    <suiteXmlFiles>
                    <!-- TestNG suite XML files -->
                    <suiteXmlFile>suite-test-testng.xml</suiteXmlFile>
                </suiteXmlFiles>
            </configuration>
            <dependencies>
                <dependency>
                    <groupId>org.aspectj</groupId>
                    <artifactId>aspectjweaver</artifactId>
                    <version>${aspectj.version}</version>
                </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>

3.
<reporting>
    <excludeDefaults>true</excludeDefaults>
    <plugins>
        <plugin>
            <groupId>ru.yandex.qatools.allure</groupId>
            <artifactId>allure-maven-plugin</artifactId>
            <version>2.5</version>
        </plugin>
    </plugins>
</reporting>

4. mvn test