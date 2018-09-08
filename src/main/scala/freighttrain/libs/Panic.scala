package freighttrain.libs

object Panic {
  def handled(message : String, code: Int = 1, e: Option[Throwable] = None): Unit = {
    StdLogger.error(message, e)
    System.exit(code)
  }

  def wut(contextMessage: String = "I don't know please open an issue :)", e: Option[Throwable] = None): Unit = {
    StdLogger.error(s"Unkown error happend: $contextMessage", e)
    System.exit(137)
  }
}
