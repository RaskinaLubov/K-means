import java.util.concurrent.ThreadLocalRandom

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkContext, SparkConf}

object KMeansApp extends App {
  def makeData(points: Int, min: Int, max: Int) = {
    for (i <- 0 until points)
      yield (
        ThreadLocalRandom.current().nextDouble(min, max + 1),
        ThreadLocalRandom.current().nextDouble(min, max + 1)
        )
  }

  val testData = makeData(500, 0, 100)
  testData take 5 foreach println



  sc.stop()
}