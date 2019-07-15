package engine.lobby.example

import engine.Example

case class Pong(filePath: String) extends Example(filePath) {
  override val `type`: String = Pong.`type`
}

object Pong {
  val `type`: String = "Pong"
}