package engine.lobby.example

import engine.Example

case class Ping(filePath: String) extends Example(filePath) {
  override val `type`: String = Ping.`type`
}

object Ping {
  val `type`: String = "Ping"
}