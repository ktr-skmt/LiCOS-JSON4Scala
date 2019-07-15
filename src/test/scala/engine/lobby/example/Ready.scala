package engine.lobby.example

import engine.Example

case class Ready(filePath: String) extends Example(filePath) {
  override val `type`: String = Ready.`type`
}

object Ready {
  val `type`: String = "Ready"
}