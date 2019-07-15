package engine.lobby.example

import engine.Example

case class GetAvatarInfo(filePath: String) extends Example(filePath) {
  override val `type`: String = GetAvatarInfo.`type`
}

object GetAvatarInfo {
  val `type`: String = "GetAvatarInfo"
}