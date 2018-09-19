package freighttrain

import freighttrain.docker.{CommandGenerator, Maestro}
import freighttrain.libs.{Panic, StdLogger}
import play.api.libs.json.{JsArray, JsObject, JsValue}

import sys.process._

object Main {
  def greet =
      """
        |
        |                    ___                  _     __ _  _       _     _____                  _
        |     o O O         | __|   _ _   ___    (_)   / _` || |_    | |_  |_   _|   _ _  __ _    (_)   _ _
        |    o        _     | _|   | '_| / -_)   | |   \__, || ' \   |  _|   | |    | '_|/ _` |   | |  | ' \     _      _     ___
        |    S__[O] _(_)_  _|_|_  _|_|_  \___|  _|_|_  |___/ |_||_|  _\__|  _|_|_  _|_|_ \__,_|  _|_|_ |_||_|  _(_)_  _(_)_  (0.1)
        | ){======_|'''''_| ''' _|'''''_|'''''_|'''''_|''''_|''''''_|'''''_|'''''_|'''''_|'''''_|'''''_|'''''_|'''''_|'''"'_|'''''|
        | ./o--000"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-"`-0-0-'
        |
      """.stripMargin

  def main(args: Array[String]) {
    val conf = new freighttrain.cli.Conf(args)  // Note: This line also works for "object freighttrain.Main extends App"
    StdLogger.info("Welcome in FreighTrain !")
    StdLogger.info(greet)
    val configs = YamlReader.readConfig()

    //if (conf.ship.isDefined) {
      Maestro.kickFromConfig(configs)
    // }

    StdLogger.info("All cargo delivered`")
  }
}