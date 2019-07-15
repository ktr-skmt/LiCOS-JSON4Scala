package engine.village.example

import engine.Example

case class Phase(filePath: String) extends Example(filePath) {
  override val `type`: String = Phase.`type`
}

object Phase {
  val `type`: String = "Phase"
}