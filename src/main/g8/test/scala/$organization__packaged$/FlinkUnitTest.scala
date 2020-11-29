package $organization$

import org.apache.flink.api.common.functions.MapFunction
import org.scalamock.scalatest.MockFactory
import org.scalatest.freespec.AsyncFreeSpec
import org.scalatest.matchers.should.Matchers

class IncrementMapFunction extends MapFunction[Long, Long] {

  override def map(record: Long): Long = {
    record + 1
  }
}

class FlinkUnitTest extends AsyncFreeSpec with Matchers with MockFactory {

  "Stateless" - {
    "Following Map should incriment values" in {
      // instantiate your function
      val incrementer: IncrementMapFunction = new IncrementMapFunction()

      val out = incrementer.map(1)

      assert(out == 1)
    }
  }

}
