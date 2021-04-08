package timer.macroAnnotation

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.language.experimental.macros
import scala.reflect.macros.whitebox

@compileTimeOnly("enable macro paradise to expand macro annotations")
class infoTimeMethod(message: String = "") extends StaticAnnotation {

  def macroTransform(annottees: Any*): Any = macro InfoTimeMethodMacros.infoMacroImpl
}

private final class InfoTimeMethodMacros(val c: whitebox.Context) extends TimeMethod {

  import c.universe._

  def infoMacroImpl(annottees: Tree*): Tree = macroImpl(annottees: _*)(q"infoTime")
}