package engine.lobby.example

import engine.Example

case class BuildVillage(filePath: String) extends Example(filePath) {
  override val `type`: String = BuildVillage.`type`
}

object BuildVillage {
  val `type`: String = "BuildVillage"
}