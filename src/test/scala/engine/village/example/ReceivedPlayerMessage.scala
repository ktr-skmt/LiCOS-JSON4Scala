package engine.village.example

import engine.Example

case class ReceivedPlayerMessage(filePath: String) extends Example(filePath) {
  override val `type`: String = "ReceivedPlayerMessage"
}
