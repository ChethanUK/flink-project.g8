package $organization$

import java.util

import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala._
import org.scalatest.freespec.AsyncFreeSpec
import org.scalatest.matchers.should.Matchers

class FlinkITTest extends AsyncFreeSpec with FlinkClusterTrait with Matchers {

  "Map" - {
    "Pass" - {
      "Map Function pass case" in {
        val env = StreamExecutionEnvironment.getExecutionEnvironment

        CollectSink.values.clear()

        env
          .fromElements(1L, 21L, 22L)
          .map(x => x + 2)
          .addSink(new CollectSink())

        env.execute()

        CollectSink.values should contain.allOf(3L, 23L, 24L)
      }
    }

    "Fail" - {
      "Map Function pass case" in {
        val env = StreamExecutionEnvironment.getExecutionEnvironment

        CollectSink.values.clear()

        env
          .fromElements(1L, 21L, 22L)
          .map(x => x + 2)
          .filter(x => x / 2 == 0)
          .addSink(new CollectSink())

        env.execute()

        CollectSink.values should not contain oneOf(3L, 23L)
      }
    }

  }

}

class CollectSink extends SinkFunction[Long] {

  override def invoke(value: Long): Unit = {
    synchronized {
      CollectSink.values.add(value)
    }
  }
}

object CollectSink {
  // Must be static
  val values: util.ArrayList[Long] = new util.ArrayList()
}
