package engine.village.example

import engine.Example

case class Board(filePath: String) extends Example(filePath) {
  override val `type`: String = "Board"
}
