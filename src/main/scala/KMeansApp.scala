import java.util.concurrent.ThreadLocalRandom

import org.apache.spark.mllib.clustering.KMeans
import org.apache.spark.mllib.linalg
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

  val sparkConf = new SparkConf().setAppName("Second Homework").setMaster("local[2]")
  val sc = new SparkContext(sparkConf)

  val x: Seq[linalg.Vector] = testData map { td => Vectors.dense(td._1, td._2) }

  val clusters = KMeans.train(sc.makeRDD(x), 5, 100)

  val cost = clusters.computeCost(sc.makeRDD(x))

  println("ClusterCenters: "+clusters.clusterCenters.to)
  println("Sum of squared errors: " + cost)
  sc.stop()
}