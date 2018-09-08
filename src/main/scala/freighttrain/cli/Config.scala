package freighttrain.cli

import org.rogach.scallop.ScallopConf

class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
  val apples = opt[Int]()
  val bananas = opt[Int]()
  val name = opt[String]()
  verify()
}
