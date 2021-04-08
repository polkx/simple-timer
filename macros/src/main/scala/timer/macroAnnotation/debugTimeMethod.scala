package timer.macroAnnotation

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.language.experimental.macros
import scala.reflect.macros.whitebox

@compileTimeOnly("enable macro paradise to expand macro annotations")
class debugTimeMethod(message: String = "") extends StaticAnnotation {

  def macroTransform(annottees: Any*): Any = macro DebugTimeMethodMacros.debugMacroImpl
}

private final class DebugTimeMethodMacros(val c: whitebox.Context) extends TimeMethod {

  import c.universe._

  def debugMacroImpl(annottees: Tree*): Tree = macroImpl(annottees: _*)(q"debugTime")
}