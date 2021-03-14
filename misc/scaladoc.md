# Scaladoc

- Scaladoc are specially written comments.Comments like below structure contain the documentation of a package, class, method, traits, objects etc.

```Scala
/**
 * This is a scaladoc comment
 */
```

## Style guide

- Enclose links/references to classes,package etc. in double square brackets `[[ ]]`
- Enclose code snippets inside triple curly braces `{{{ }}}`
- Use hyphens for writing unordered lists(just like markdown). For ordered lists, we can use either number or letters.

Scaladoc contains the following tags

- `@author`
- `@constructor`
- `@param`
- `@return`
- `@version`
- `@since` - Which version this entity was added
- `@throws`
- `@deprecated`
- `@todo`

## Package documentation

- Contains information about the package and its classes. References(links) to any class using fully qualified name is enclosed within `[[]]`

```Scala
/** Package for Scaladoc tutorial.
  * Provides examples of Scaladoc and their elements such as tags and formattings.
  *
  * Class implemented in this package is [[com.baeldung.scala.scaladoc.IntervalTimer]].
  */
```

## Class documentation

```Scala
package com.baeldung.scala.scaladoc

/** Represents a timer with interval.
  *
  * Specify how many `reps` desired for the timer and the `interval` between `reps`
  *
  * @constructor Create a timer with a specified `reps` and `interval`
  * @param reps Number of repetitions the timer will run.
  * @param interval Time between repetitions, in seconds. The default is 30 seconds.
  */
class IntervalTimer(val reps: Int, val interval: Int = 30)
```

## Method documentation

```Scala
/** Get total time, in seconds, that will be counted for this timer.
  *
  * @return The total number of seconds elapsed for this timer.
  */
def getTotalSeconds: Int = {
  interval * reps
}
```

## Trait documentation

```Scala
/** Defines a meat eater.
  *
  * movement must be specified in class using this.
  */
trait Carnivore {
  def food: String = "meat"
  def movement: String
}
```

## Objects

```Scala
package com.baeldung.scala.scaladoc

/** Factory for [[com.baeldung.scala.scaladoc.TasmanianDevil]] instances.
  *
  * Extends [[com.baeldung.scala.scaladoc.Carnivore]].
  */
object TasmanianDevil extends Carnivore {
    /** Creates a tasmanian devil with a given length.
        *
        * @param length the length of tasmanian devil in cm.
        */
    def apply(length: Int) = {}

    def movement: String = "Walk"
}
```

## Generating scaladoc

### Using maven

- Use the **maven-site-plugin** in the pom.xml. Refer to the below snippet for usage. `mvn scala:doc` goal generates the scala doc

```XML
<!-- POM.xml -->
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-site-plugin</artifactId>
            <version>3.9.1</version>
            <!--All plugins under reportPlugins should be placed inside the reporting element.
            http://wiki.bitplan.com/index.php/MNG-6189-->
        </plugin>
    </plugins>
</build>

<reporting>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-project-info-reports-plugin</artifactId>
            <version>3.1.1</version>
        </plugin>
        <plugin>
            <groupId>net.alchim31.maven</groupId>
            <artifactId>scala-maven-plugin</artifactId>
            <version>4.4.1</version>
            <configuration>
                <jvmArgs>
                <jvmArg>-Xms64m</jvmArg>
                <jvmArg>-Xmx1024m</jvmArg>
                </jvmArgs>
            </configuration>
        </plugin>
    </plugins>
</reporting>
```

### Using SBT

- `sbt doc` generates the scaladoc

## Keyboard shortcut to generate scaladoc in IntelliJ

> Typing /\*\* and then pressing `Enter` above a method signature will create Javadoc stubs for you. -[Autogenerate Javadoc](https://stackoverflow.com/questions/16671418/what-is-the-intellij-shortcut-key-to-create-a-javadoc-comment/16671554)

---

## References

- [Scaladoc](https://docs.scala-lang.org/style/scaladoc.html)
- [Guide to Scaladoc](https://www.baeldung.com/scala/scaladoc)
- [How to generate Scala documentation with the `scaladoc` command](https://alvinalexander.com/scala/how-to-generate-scala-documentation-scaladoc-command-examples/)
