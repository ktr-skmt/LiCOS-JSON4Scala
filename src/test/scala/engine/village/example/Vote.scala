package engine.village.example

import engine.Example

case class Vote(filePath: String) extends Example(filePath) {
  override val `type`: String = "Vote"
}
