# Packaging and imports

## Packages

* Packages are used to group member definitions under same name

* Members within same package can just be referenced using **simple name**.

* Members outside the package needs to be imported to refer using simple name or refer the members using their **fully qualified name**.

* Packages are in hierarchy

## Package object

* Package object - scala specific. Only one per package. Naming convention is to name the file as **package.scala**

```Scala
package object learning {
    def sayHello: Unit  = println("Hello World")
}
```

## imports

```Scala
// import all from a package
import learning._

// Multiple members from a package
import learning.{SimpleClass, ComplexClass}

// Aliasing to avoid conflicts
// Otherwise we need to use the fully qualified name
// everywhere which is very verbose
import learning.{SimpleClass => SimpleImp}
```

* default imports. `java.lang`, `scala` (top level scala package), `scala.predef` (consists of `println`, `???`)
