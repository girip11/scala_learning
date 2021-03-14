# Logging in Scala

- SLF4J - Simple Logging Facade For Java. It's just a logging API. It requires a backend to do the actual logging(just like keras and tensorflow).
- Log levels supported by SLF4J are `error`, `warning`, `info`, `debug`, `trace`
- Debug and trace are used only in special circumstances.
- SLF4J implementations - logback and log4j2. As described in the scala-logging documentation, I will go with **logback** as the backend.

## Using logback

- We can use **scala-logging** along with **logback**

```Scala
// This is using sbt
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
```

- [Scala-logging](https://github.com/lightbend/scala-logging) is a scala wrapper over SLF4J.

## Example

> The `LazyLogging` and `StrictLogging` traits from the `com.typesafe.scalalogging` package define the logger member as a lazy or strict value respectively. In both cases the underlying SLF4J logger is named according to the class into which these traits are mixed.

```Scala
import com.typesafe.scalalogging._

val logger = Logger[MyClass]
logger.info("This is an info log")
```

- [File and line number logging](https://github.com/lightbend/scala-logging#line-numbers-in-log-message) is possible using [sourcecode library](https://github.com/lihaoyi/sourcecode#logging)

**NOTE**: Be careful when using string interpolations to log the exceptions. Instead use the SLF4J string interpolations which use `{}`

### Logback configuration

- We have to place the `logback.xml` inside **src/main/resources** folder.

```XML
<!-- sample logback configuration -->
<configuration>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- encoders are assigned the type
        ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- here the level controls what log messages are filtered -->
    <root level="debug">
        <appender-ref ref="STDOUT" />
    </root>
</configuration>
```

- Appenders write our logs somewhere. The underlying medium could be console, a file or something like syslog. [List of appenders provided by logback](http://logback.qos.ch/manual/appenders.html)

- Encoders transforms our log message in to specific pattern. **Think of appenders as logging handlers and encoders as formatters in python logging**. As in python, where handlers contain formatters, here appenders contain encoders. [Encoder syntax](http://logback.qos.ch/manual/layouts.html#conversionWord)

---

## References

- [Introduction to logging in Scala](https://engineering.footballradar.com/introduction-to-logging-in-scala/)
- [Complete “Scala Logging” Example](https://stackoverflow.com/a/32003907/2369053)
- [Get Started Quickly With Scala Logging](https://www.scalyr.com/blog/get-started-scala-logging/)
- [Scala logging documentation](https://github.com/lightbend/scala-logging)
