package $organization$

import org.apache.flink.runtime.testutils.MiniClusterResourceConfiguration
import org.apache.flink.test.util.MiniClusterWithClientResource
import org.scalatest.BeforeAndAfter
import org.scalatest.Suite

trait FlinkClusterTrait extends BeforeAndAfter {
  _: Suite =>

  val parallelism = 2

  val flinkCluster = new MiniClusterWithClientResource(
    new MiniClusterResourceConfiguration.Builder()
      .setNumberTaskManagers(1)
      .setNumberSlotsPerTaskManager(parallelism)
      .build()
  )

  before {
    flinkCluster.before()
  }

  after {
    flinkCluster.after()
  }
}
