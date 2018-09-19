package freighttrain.docker

import freighttrain.libs.{Panic, StdLogger}
import play.api.libs.json.{JsArray, JsObject, JsValue, Json}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import sys.process._

object CommandGenerator {

  def missingArgument(argName: String) = Panic.handled(s"You are missing an argunent : $argName")


  def getContainerRunCommand(implicit container: JsObject, preArgs: String = ""): String =
    s"$preArgs $generatePortsChunk $generateVolumesChunk ${(container \ "image").as[String]} "


  def generatePortsChunk(implicit container: JsObject) = {
    // TODO : Proper execpetion handking
    (container \\ "ports")
      .map(_.as[JsArray])
      .flatMap { portDescription: JsArray =>
        StdLogger.debug(portDescription.toString())
          portDescription.as[Seq[Seq[Int]]].map { p =>
            s"-p ${p.mkString(":")} "
          }
        }.mkString
      }

  def generateVolumesChunk(implicit container: JsObject) = {
    (container \\ "volumes")
      .map(_.as[JsArray])
      .flatMap { portDescription: JsArray =>
        StdLogger.debug(portDescription.toString())
          portDescription.as[Seq[Seq[String]]].map { p =>
            s"-v ${p.mkString(":")} "
          }
        }.mkString
  }
}
