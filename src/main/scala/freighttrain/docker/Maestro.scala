package freighttrain.docker

import java.time.ZonedDateTime

import cats.kernel.instances.StringMonoid
import freighttrain.cli.Conf
import freighttrain.libs.{Panic, StdLogger}
import org.joda.time.Minutes
import play.api.libs.json.{JsObject, Json}

import scala.collection.mutable._
import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import sys.process._

case class ContainerState (
  containerName: String,
  registeredName: String,
  runCommand: String,
  state: Boolean
                          )

object Maestro {

  val MAIN_CONTAINER_KEY = "MainContainer";

  var poppedInstances = 0;
  var hive: Map[String, ContainerState] = Map()
  var sessionConf: Conf = null

  def kickFromConfig(conf: Conf, mainJson: JsObject) = {

    sessionConf = conf;

    val mainContainerChunk = (mainJson \\ MAIN_CONTAINER_KEY).headOption
      .getOrElse({
        CommandGenerator.missingArgument("a"); Json.obj()
      })
      .as[JsObject]

    val mainRunnerCommand: String = CommandGenerator.getContainerRunCommand(mainContainerChunk)


    val subContainers = mainJson.keys
      .filterNot(_.==(MAIN_CONTAINER_KEY))
      .map { x => val
        c = (mainJson \ x).as[JsObject]
        registerContainer(x, CommandGenerator.getContainerRunCommand(c))
      }

    registerContainer(MAIN_CONTAINER_KEY, mainRunnerCommand)

    rollingClose()
    createNetwork()

    Thread.sleep(4000)

    val res = mainJson.keys.map { x => killContainer(x); runContainer(x);}

    // TODO : Check results and emt exceptions if necessary

    Await.result(Future.sequence(res), Duration(10, "minutes"))

    StdLogger.info("Your cargo is shipping ...")

      Thread.sleep(3000)

    "docker exec -it MainContainer sh"!

    inspectLoop()
    inspectLoop()

    StdLogger.info("SUCCESS !! Grabbing usefull informations from Main container ....");

    // TODO : Grab output and display only usefull
    "docker inspect MainContainer"!

    StdLogger.info("Train arrived at IP:PORT @" + ZonedDateTime.now().toLocalDateTime)

  }

  def inspectLoop() = {
    hive.keys.foreach { x =>
      Thread.sleep(2000)
      s"docker ps"!

      StdLogger.info(s"Latests logs for : $x")
      s"docker logs --tail 10 $x"!
    }
  }

  def rollingClose(): Unit = {
    StdLogger.info("Rolling closing of previous dockers (You can disable it in cli)")
    hive.foreach(x => killContainer(x._2.registeredName))
  }

  def createNetwork() ={
    StdLogger.info("Creating shuntingyard network ...")
    "docker network rm shuntingyard"!

    "docker network create shuntingyard"!
  }

  def registerContainer(name: String, command: String, runAfterReg: Boolean = false): String = {
     hive = hive ++ Map((name, ContainerState(name, name, command, false)))
    if (runAfterReg) {
      runContainer(name, name == "MainContainer")
    }
    name
  }

  def killContainer(name: String)= {

    s"docker stop $name"!

    s"docker rm $name"!
  }

  def runContainer(name: String, enterAfterRun: Boolean = false): Future[Int] = {

    def caps = (run: String) => {
      StdLogger.info(s"Launching $name : $run")
      StdLogger.debug(s"HIVE HIVE : $hive")
      if(enterAfterRun) Future.successful(run!) else Future { run!; 0 }
    }

    // TODO : Give run name
    hive.find(x => x._2.containerName == name)
      .map(x => caps(s"docker run --name $name --network shuntingyard -d ${x._2.runCommand}"))
      .getOrElse { Panic.wut(s"Tried to run a non registered docker $name"); Future(256)}
  }



}
