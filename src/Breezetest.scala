/**
 * Created by yuhao on 6/12/15.
 */

import breeze.linalg.{DenseMatrix => BDM, DenseVector => BDV, Vector => BV, _}
import breeze.numerics._

object Breezetest {

  def main(args: Array[String]) {

    val m = new BDM(2, 3, Array(1,4,2,5,3,6.0))
    println(m)

//    val v = BDV[Double](1, 2, 3, 4, 5, 6)
//    println("v")
//    println(v)
//    println(log_normalize(v))
//
//    val m2 = new BDM(2, 2, Array(1, 2, 3.0, 4))
//    println(m)
//    println(m(::, Array(1,2).toList))
//    println(m(::, *))
//    println(sum(m(*, ::)))
//    m(::, *) :=  m(::, *) +  m(::, *)
//    m(0, ::) := new BDV((6 to 8).map(i => i.toDouble).toArray).t
//    println(BDM.rand(3, 5) * (2.toDouble) * 100.0 / (3 * 3).toDouble - 1.0)

//    println(sum(m(*, ::)))
//
//    val m_timestamp = BDV.zeros[Int](5)
//    val word_list = List(1, 3)
//    val m_updatect = 1
//
//    m_timestamp(word_list) := m_updatect
//
//    println(m_timestamp(2 to 4))

//    val v = BDV[Double](1, 2, 3, 4, 5, 6)
//    println (accumulate(v))
//    println(m(List(0, 1), ::))
//    val v = BDV[Double](1, 2)
////    for(i <- 0 to m.rows - 1){
////      m(i, ::) := (m(i, ::).t :* v).t
////    }
////    println(m :* BDV(1.0,2,3))
//
//    val mCopy = m.copy
//    for(i <- 0 until m.cols){
//      mCopy(::, i) := mCopy(::, i) - v
//    }
//    println(mCopy)

//    var i = 5
//    var t =
//      while(i > 1){
//        i -= 1
//      }
//    println(t.getClass)

//    val t = m(::, 0 to 1)
//    println(flipud(sum(t(::, *))))

    val list = List(0,2)
    m(::, list) := m(::, list) * 2.0
    println(m)

  }

  def log_normalize(v: BDV[Double]): (BDV[Double], Double) = {
    val log_max = 100.0
    val max_val = v.toArray.max
    val log_shift = log_max - log(v.size + 1.0) - max_val
    val tot: Double = sum(exp(v + log_shift))
    val log_norm = log(tot) - log_shift
    (v - log_norm, log_norm)
  }

  def log_normalize(m: BDM[Double]): (BDM[Double], BDV[Double]) = {
    val log_max = 100.0
    // get max for every row
    val max_val: BDV[Double] = m(*, ::).map(v => max(v))
    val log_shift: BDV[Double] = log_max - log(m.cols + 1.0) - max_val

    val m_shift: BDM[Double] = exp(m(::, *) + log_shift)
    val tot: BDV[Double] = sum(m_shift(*, ::))

    val log_norm: BDV[Double] = log(tot) - log_shift
    (m(::, *) - log_norm, log_norm)
  }

}

object Phone{
  def test(): Unit ={

  }
}

class Phone{
  def ship(): Unit ={
    Phone.test
  }
}
