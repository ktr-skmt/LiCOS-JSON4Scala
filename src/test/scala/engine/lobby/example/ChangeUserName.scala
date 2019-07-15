package engine.lobby.example

import engine.Example

case class ChangeUserName(filePath: String) extends Example(filePath) {
  override val `type`: String = ChangeUserName.`type`
}

object ChangeUserName {
  val `type`: String = "ChangeUserName"
}