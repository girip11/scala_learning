package org.scala.patternMatching

trait Expr

case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Product(e1: Expr, e2: Expr) extends Expr

object ExprPatternMatching extends App {

  def showExpression(expr: Expr): String = {
    expr match {
      case Number(n) => {
//        println(s"I am a number with value $n")
        n.toString()
      }

      case Sum(e1, e2) => s"${showExpression(e1)} + ${showExpression(e2)}"

      case Product(e1, e2) => {
        def withParenthesis(exp: Expr): String = {
          exp match {
            case Number(_) | Product(_, _) => showExpression(exp)
            case _ => s"(${showExpression(exp)})"
          }
        }

        s"${withParenthesis(e1)} * ${withParenthesis(e2)}"
      }

      case _ => ""
    }
  }

  var expr: Expr = Sum(Number(2), Number(3))
  println(showExpression(expr)) // 2 + 3

  expr = Sum(Number(2), Sum(Number(3), Number(4)))
  println(showExpression(expr)) // 2 + 3 + 4

  expr = Sum(Product(Number(2), Number(4)), Number(6))
  println(showExpression(expr)) // 2 * 4 + 6

  expr = Product(Product(Number(2), Number(4)), Number(6))
  println(showExpression(expr)) // 2 * 4 * 6

//    With paranthesis
  expr = Product(Sum(Number(2), Number(4)), Number(6))
  println(showExpression(expr)) // (2 + 4) * 6

  expr = Product(Number(2), Sum(Number(4), Number(6)))
  println(showExpression(expr)) // 2 * (4 + 6)

  expr = Product(Sum(Number(1), Number(2)), Sum(Number(4), Number(6)))
  println(showExpression(expr)) // (1 + 2) * (4 + 6)
}
