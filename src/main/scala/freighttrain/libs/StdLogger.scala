package freighttrain.libs

import java.time.{Clock, ZonedDateTime}

object StdLogger {
  val SPACER = "\r\n\r\n"
  val DEBUG_ENABLED = true
  val STACK_ENABLED = true//true

  def ztdNow = ZonedDateTime.now(Clock.systemDefaultZone())
  def now = s"${ztdNow getHour}:${ztdNow getMinute}:${ztdNow getSecond}"


  def info(message: String) = {
    log(s"[INFO] $message")
  }

  def debug(message: String) = {
    if (DEBUG_ENABLED) log(s"[DEBUG] $message")
  }

  def error(message: String, e: Option[Throwable] = None) = {
    spacer; log(s"[ERROR] $message")

    e.map { e: Throwable => {
      log(s"[ERROR] JAVA MESSAGE : ${e.getLocalizedMessage}")
      if (STACK_ENABLED) {
        println(s"$SPACER${getLog("[STACK]")}  ${e.getStackTrace.mkString(s"\r\n${getLog("[STACK]")}  ")}")
        log(s"[ERROR] Class Origin : ${e.getClass.toString}"); e
      }
    }}

    spacer; log(s"[ERROR] $message")
  }

  def ANSIFactory = {

  }

  def log(message: String) = {
    println(getLog(message))
  }

  def getLog(message: String) = s"[$now] $message "
  def spacer = println(SPACER)
}
