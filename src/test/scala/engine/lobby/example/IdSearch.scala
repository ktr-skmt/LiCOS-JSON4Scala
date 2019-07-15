package engine.lobby.example

import engine.Example

case class IdSearch(filePath: String) extends Example(filePath) {
  override val `type`: String = IdSearch.`type`
}

object IdSearch {
  val `type`: String = "IdSearch"
}