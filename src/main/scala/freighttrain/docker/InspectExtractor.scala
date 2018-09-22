package freighttrain.docker

import freighttrain.libs.StdLogger
import play.api.libs.json.JsObject

object InspectExtractor {

  def getInspectMainInfos(inspectJson: JsObject): Unit = {

  }

  def getIPPort(inspectJson: JsObject) = {
    val configChunk = (inspectJson \ "HostConfig" \ "NetworkSettings" \ "Ports" ).as[JsObject]

    StdLogger.info(s"Inspector chunks : ${configChunk}")

    s"${}"
  }

}
