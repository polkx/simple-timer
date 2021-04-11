Program based on macro annotation that measure and log method execution time.

## Using program
You must use @debugTimeMethod or @infoTimeMethod with TimeMeasurement trait.

    class ExampleClass extends TimeMeasurement {

     @infoTimeMethod("Specified info message")
      def withInfoMessage(): String = {
       "withInfoMessage"
      }
    }
  
### See example in object simple-timer/core/src/main/scala/timer/Test.scala
