package freighttrain

import freighttrain.libs.StdLogger

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
    // val conf = new freighttrain.Conf(args)  // Note: This line also works for "object freighttrain.Main extends App"
    // println("apples are: " + conf.apples())
    StdLogger.info("Welcome in FreighTrain !")
    StdLogger.info(greet)
    YamlReader.readConfig()
  }
}