package timer

object Test extends App {
  private val measureTimeMethod = new MeasureTimeMethod()

  measureTimeMethod.withDefaultInfoMessage()
  measureTimeMethod.withInfoMessage("")
  measureTimeMethod.withNamedArgumentInfoMessage()
  measureTimeMethod.withDefaultDebugMessage()
}