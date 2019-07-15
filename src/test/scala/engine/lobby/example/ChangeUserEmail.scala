package engine.lobby.example

import engine.Example

case class ChangeUserEmail(filePath: String) extends Example(filePath) {
  override val `type`: String = ChangeUserEmail.`type`
}

object ChangeUserEmail {
  val `type`: String = "ChangeUserEmail"
}