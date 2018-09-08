package freighttrain

import freighttrain.libs.StdLogger

object Main {
  def main(args: Array[String]) {
    // val conf = new freighttrain.Conf(args)  // Note: This line also works for "object freighttrain.Main extends App"
    // println("apples are: " + conf.apples())
    StdLogger.info("Welcome in FreighTrain !")
    YamlReader.readConfig()
  }
}