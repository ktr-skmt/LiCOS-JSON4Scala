package engine.village.example

import engine.Example

case class Error(filePath: String) extends Example(filePath) {
  override val `type`: String = "Error"
}
