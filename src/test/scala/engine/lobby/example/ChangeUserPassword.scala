package engine.lobby.example

import engine.Example

case class ChangeUserPassword(filePath: String) extends Example(filePath) {
  override val `type`: String = ChangeUserPassword.`type`
}

object ChangeUserPassword {
  val `type`: String = "ChangeUserPassword"
}