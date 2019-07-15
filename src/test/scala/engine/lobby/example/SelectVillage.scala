package engine.lobby.example

import engine.Example

case class SelectVillage(filePath: String) extends Example(filePath) {
  override val `type`: String = SelectVillage.`type`
}

object SelectVillage {
  val `type`: String = "SelectVillage"
}
