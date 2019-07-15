package engine.lobby.example

import engine.Example

case class ChangeLang(filePath: String) extends Example(filePath) {
  override val `type`: String = ChangeLang.`type`
}

object ChangeLang {
  val `type`: String = "ChangeLang"
}