package freighttrain.cli

import org.rogach.scallop.ScallopConf

class Conf(arguments: Seq[String]) extends ScallopConf(arguments) {
  val ship = opt[Int]()
  verify()
}
