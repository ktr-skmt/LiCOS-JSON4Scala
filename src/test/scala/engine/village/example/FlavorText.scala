package engine.village.example

import engine.Example

case class FlavorText(filePath: String) extends Example(filePath) {
  override val `type`: String = "FlavorText"
}
