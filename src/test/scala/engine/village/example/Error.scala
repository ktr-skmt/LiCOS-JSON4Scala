package engine.village.example

import engine.VillageExample

case class Error(filePath: String) extends VillageExample(filePath) {
  override val `type`: String = Error.`type`
}

object Error {
  val `type`: String = "Error"
}