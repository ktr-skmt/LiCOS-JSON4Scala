package engine.lobby.example

import engine.Example

case class GetSettings(filePath: String) extends Example(filePath) {
  override val `type`: String = GetSettings.`type`
}

object GetSettings {
  val `type`: String = "GetSettings"
}