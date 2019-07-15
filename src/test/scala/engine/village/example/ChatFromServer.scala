package engine.village.example

import engine.Example

case class ChatFromServer(filePath: String) extends Example(filePath) {
  override val `type`: String = "ChatFromServer"
}
