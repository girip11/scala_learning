# Testing with ScalaTest

- Suite, which is simply a collection of zero or more tests. `trait Suite` declares several lifecycle methods that define the way we can write and run tests.
- ScalaTest already offers many style traits that extend `Suite` to support different testing styles.
- We can mixin these style traits together with other stackable traits such as `Matchers` or `BeforeAndAfter`.

---

## References

- [Introduction to Testing With ScalaTest](https://www.baeldung.com/scala/scalatest)
- [Scalatest 3.0.9 documentation](https://www.scalatest.org/scaladoc/3.0.9/org/scalatest/FunSpec.html)
