package engine.village.example

import engine.Example

case class ReceivedFlavorTextMessage(filePath: String) extends Example(filePath) {
  override val `type`: String = "ReceivedFlavorTextMessage"
}
