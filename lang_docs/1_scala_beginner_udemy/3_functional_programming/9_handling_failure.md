# Handling failure

* Handle exceptions gracefully
* Avoid runtime crashes resulting from uncaught exceptions
* Avoid endless amount of try-catches

* When a code block can throw exception, we can use `Try` to enclose the failure or a success computation.

! [Try](./try.png)

## `Success` and `Failure`

```Scala
import scala.util.{Success, Failure, Try}

val aSuccess = Success(3)
val aFailure = Failure(new RuntimeException("Failure"))
```

* Usually the code that can throw is put inside `Try` apply method.

```Scala
def unsafeMethod(): String = throw new RuntimeException("Some failure")

val potentialFailure = Try(unsafeMethod())

// Syntax sugar for Try
val anotherPotentialFailure = Try {
    unsafeMethod()
}

def backupMethod(): String = "Some default value"
val result = Try(unsafeMethod()).orElse(Try(backupMethod))

// Method to check on Try
println(potentialFailure.isSuccess)
println(potentialFailure.isFailure)

def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException("Some failure"))

def betterBackupMethod(): Try[String] = Success("Default value")

val result = betterUnsafeMethod() orElse betterBackupMethod()
```

## Functions on `Try`

* Since `Try` has map, flatMap, withFilter and foreach implemented, we can use it in the for comprehensions.

```Scala
val result = Success(3)

result.map(_ * 2)
result.flatMap(x => Success(x * 10))

// On filter condition returning false,
// we get Failure(NoSuchElementException)
result.filter(_ > 10)
```

## Example

```Scala
import scala.util.{Try, Success, Failure, Random}

val hostname = "localhost"
val port = "8080"

def renderHTML(page: String) = println(page)

class Connection {
    def get(url: String): String = {
        val random = new Random(System.nanoTime())
        if (random.nextBoolean()) "<html>...</html>"
        else throw new RuntimeException("Connection Interrupted")
    }

    def getSafe(url: String): Try[String] = Try(get(url))
}

object HttpService {
    val random = new Random(System.nanoTime())

    def getConnection(host: String, port: String): Connection = {
        if (random.nextBoolean()) new Connection
        else throw new RuntimeException("Someone else took the port")
    }

    def getConnectionSafe(host: String, port: String): Try[Connection] = Try(getConnection(host, port))
}

// If you get the html page from the connection
// print it to the console using renderHTML
Try(HttpService.getConnection(hostname, port)).
flatMap(connection => Try(connection.get(s"http://$hostname:$port/index"))).
foreach(renderHTML)

// Using safe methods
HttpService.getConnectionSafe(hostname, port).
flatMap(connection => connection.getSafe(s"http://$hostname:$port/index")).
foreach(renderHTML)

// Implementing the same using the for comprehensions
for {
    connection <- Try(HttpService.getConnection(hostname, port))
    page <- Try(connection.get(s"http://$hostname:$port/index"))
} renderHTML(page)

// Using safe methods
for {
    connection <- HttpService.getConnectionSafe(hostname, port)
    page <- connection.getSafe(s"http://$hostname:$port/index")
} renderHTML(page)


```

---

## References

* [Scala & Functional Programming for Beginners | Rock the JVM](https://www.udemy.com/share/1013xsCUMfd1lVR34=/)
