package freighttrain

import java.io.{FileNotFoundException, InputStreamReader}

import freighttrain.libs.{Panic, StdLogger}
import io.circe.yaml.parser
import org.yaml.snakeyaml.scanner.ScannerException
import cats.syntax.either._
import play.api.libs.json
import play.api.libs.json.{JsObject, JsValue, Json}

import scala.io.Source

object YamlReader {

  val FREIGHT_TRAIN_CONF = ".freight"

  def readConfig(): JsObject = {

    try {
        val path = s"${System.getProperty("user.dir")}/$FREIGHT_TRAIN_CONF"
        StdLogger.info(s"Opening path :  $path")
        val configs = Source.fromFile(path).getLines.mkString("\n")
        StdLogger.info(s"$configs")

        val jsons: JsValue = play.api.libs.json.Json.parse(parser.parse(configs)
          .leftMap(err => Panic.handled("Parsing error when converting Yaml to js", e = Some(err.underlying)))
          .right.getOrElse(Panic.handled("Somethign went wrong with Yaml -> Json :/")).toString)
        StdLogger.info((jsons \\ "MainContainer").mkString)
      jsons.as[JsObject]

    } catch {
      case fnf: FileNotFoundException => Panic.handled(
        s"Could not find the main FreightTrain configuration file. Do you have a $FREIGHT_TRAIN_CONF file in current directory ?",
      ); Json.obj()
      case pars: ScannerException => Panic.handled(s"Badly formatted $FREIGHT_TRAIN_CONF file", e = Some(pars)); Json.obj()
      case x: Throwable => Panic.wut(s"opening $FREIGHT_TRAIN_CONF", e = Some(x)); Json.obj()
    }
  }
}
