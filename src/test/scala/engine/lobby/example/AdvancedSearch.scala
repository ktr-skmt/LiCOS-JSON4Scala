package engine.lobby.example

import engine.Example

case class AdvancedSearch(filePath: String) extends Example(filePath) {
  override val `type`: String = AdvancedSearch.`type`
}

object AdvancedSearch {
  val `type`: String = "AdvancedSearch"
}
