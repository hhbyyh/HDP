import org.apache.spark.mllib.clustering.OnlineHDPOptimizer
import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.linalg.{Vectors, Vector}

/**
* Created by yuhao on 6/18/15.
*/
object Driver {

  def main(args: Array[String]) {
    def toydata: Array[(Long, Vector)] = Array(
      Vectors.sparse(6, Array(0, 1), Array(1, 1)),
      Vectors.sparse(6, Array(1, 2), Array(1, 1)),
      Vectors.sparse(6, Array(0, 2), Array(1, 1)),
      Vectors.sparse(6, Array(3, 4), Array(1, 1)),
      Vectors.sparse(6, Array(3, 5), Array(1, 1)),
      Vectors.sparse(6, Array(4, 5), Array(1, 1))
    ).zipWithIndex.map { case (wordCounts, docId) => (docId.toLong, wordCounts) }

    val conf = new SparkConf().setMaster("local").setAppName("sss")
    val sc = new SparkContext(conf)

    val docs = sc.parallelize(toydata)

    val op = new OnlineHDPOptimizer(docs)
    op.update(docs)

    println(op.m_lambda)

  }

}
