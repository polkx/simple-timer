package timer.macroAnnotation

import scala.reflect.macros.whitebox

abstract class TimeMethod {
  val c: whitebox.Context

  import c.universe._

  private lazy val macroName: Tree = {
    c.prefix.tree match {
      case Apply(Select(New(name), _), _) => name
      case _ => c.abort(c.enclosingPosition, "Wrong macro annotation")
    }
  }

  private val message: Option[String] =
    c.prefix.tree match {
      case Apply(_, List()) =>
        None
      case Apply(_, List(q"${info: String}")) =>
        Some(info)
      case Apply(_, List(q"message = ${info: String}")) =>
        Some(info)
      case _ =>
        c.warning(c.enclosingPosition, s"Unsupported arguments supplied to @$macroName")
        None
    }

  private val defaultMessage: String => String = (methodName: String) => s"Full processing of method $methodName"

  def macroImpl(annottees: Tree*)(logType: Tree): Tree = {
    annottees.head match {
      case DefDef(_, _, _, _, _, rhs) if rhs.isEmpty =>
        c.abort(c.enclosingPosition, s"@$macroName can only be used on defined method")

      case DefDef(mods, termName@TermName(name), tparams, vparamss, tpt, rhs) =>
        val operationMessage = message.getOrElse(defaultMessage(name))
        val rhsWithLoggingTim = q"$logType($operationMessage)($rhs)"
        DefDef(mods, termName, tparams, vparamss, tpt, rhsWithLoggingTim)

      case _ =>
        c.abort(c.enclosingPosition, s"@$macroName can only be used on methods")
    }
  }

}