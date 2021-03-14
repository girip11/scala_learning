# Maven Build Lifecycle

- Each stage in a lifecycle is called a phase. Each phase can have a number of goals to be executed.

## Maven lifecycles

Following are the maven's builtin lifecycles.

- Default lifecycle - Handles the build and deployment.
- Clean lifecycle - cleans up files and folders produced by Maven.
- Site lifecycle - Documentation

- Maven infers the lifecycle from the phase provided. `mvn package`, the **package** phase invokes the default lifecycle.

### Clean lifecycle

- This lifecycle has **clean** phase. `mvn clean`

### Site lifecycle

- Contains **site** phase. `mvn site`

### Default lifecycle

This lifecycle has the following phases.

- validate - validate project information
- process-resources - copies project resources
- compile - compile source code
- test - run tests
- package - Packages the compiled code
- integration-test - Runs integration test using the package
- verify - verify the package
- install - install the package in local repository
- deploy - install in the required repository

Each phase can be associated with a number of plugin goals. For commonly used
goals in each phase refer the book.

## POM structure

- `<dependencies></dependencies>` contain the third party dependencies
- `<plugins></plugins>` - contain maven plugins

## Maven settings

**settings.xml** is used for Maven settings.

- Per user settings - Located at the `.m2` directory of the current user.
- Systemwide settings - Located at the conf folder of maven installation aka `M2_HOME`

## Maven profiles

- We can have several profiles per project. Each profile can perform build satisfying particular needs. For instance we could have separate build profiles for staging and production.

- Profiles can also be defined in **settings.xml** making it user wide or system wide. User level or system level profiles override project level profiles of the same name.

```XML
<profiles>
    <profile>
        <id>dev</id>
        <activation>
            <activeByDefault>false</activeByDefault>
        </activation>
        <!-- All other child nodes of profile that can be present in POM.xml -->
        <build>...</build>
        <modules>...</modules>
        <repositories>...</repositories>
        <pluginRepositories>...</pluginRepositories>
        <dependencies>...</dependencies>
        <reporting>...</reporting>
        <dependencyManagement>...</dependencyManagement>
        <distributionManagement>...</distributionManagement>
    </profile>
</profiles>
```

- `mvn help:active-profiles` - lists all the active profiles.

- `mvn -P dev package` - triggers the dev profile and runs the package phase from the default lifecycle.

## Dependency management

- Maven dependency plugin is used to get a report of dependencies. `mvn dependency:list` lists all the dependencies of the project. To get the dependency tree `mvn dependency:tree` is used. Such a tree visualization helps to figure out the transitive dependencies and troubleshoot dependency hell.

```XML
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>3.8.1</version>
    <scope>test</scope>
</dependency>
```

- We can specify the following scopes: compile(default), test, runtime, provided, system, import.

- For how dependencies are resolved, refer the book. `dependencyManagement` element can be used to handle conflicting versions of transitive dependencies.

- `mvn dependency:copy-dependencies` copies all the dependent libraries to a target folder.

---

## References

- [Apache cookbook](https://www.amazon.com/Apache-Maven-Cookbook-Raghuram-Bharathan/dp/1785286129)
