import scala.reflect.runtime.universe._
import scala.reflect.macros.{Context => Ctx}

object Impls {
  def foo[T: c.AbsTypeTag, U: c.AbsTypeTag](c: Ctx)(x: c.Expr[U]) = {
    import c.universe._
    val T = implicitly[c.AbsTypeTag[T]]
    val U = implicitly[c.AbsTypeTag[U]]
    val body = Apply(Select(Ident(definitions.PredefModule), newTermName("println")), List(Literal(Constant(T.toString + " " + U.toString))))
    c.Expr[Unit](body)
  }
}