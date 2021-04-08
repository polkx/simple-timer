package timer

import timer.macroAnnotation.{debugTimeMethod, infoTimeMethod}

class MeasureTimeMethod extends TimeMeasurement {

  @infoTimeMethod("Specified info message")
  def withInfoMessage(arg1: String, arg2: Int = 2): String = {
     Thread.sleep(1000)
    "withInfoMessage"
  }

  @infoTimeMethod()
  def withDefaultInfoMessage(): String = {
    "withDefaultInfoMessage"
  }

  @infoTimeMethod(message = "Specified info message with named argument")
  def withNamedArgumentInfoMessage(): String = {
    "withNamedArgumentInfoMessage"
  }

  @debugTimeMethod()
  def withDefaultDebugMessage(): String = {
    "withDefaultDebugMessage"
  }
}