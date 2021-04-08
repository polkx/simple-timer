package timer

import timer.TimeMeasurement._
import com.typesafe.scalalogging.LazyLogging

trait TimeMeasurement extends LazyLogging {

  final def infoTime[T](message: String)(block: => T): T = {
    executionTimeLog(message, block)(logMsg => logger.info(logMsg))
  }

  final def debugTime[T](message: String)(block: => T): T = {
    executionTimeLog(message, block)(logMsg => logger.debug(logMsg))
  }

}

object TimeMeasurement {

  private def executionTimeLog[T](message: String, block: => T)
                                 (log: String => Unit): T = {
    val (result, executionTime) = time(block)
    log(s"[$executionTime ms] $message")
    result
  }

  private def time[T](block: => T): (T, Long) = {
    val start = System.currentTimeMillis
    val result = block
    val end = System.currentTimeMillis
    val executionTime = end - start
    (result, executionTime)
  }
}