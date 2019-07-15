package engine.lobby.example

import engine.Example

case class SearchResult(filePath: String) extends Example(filePath) {
  override val `type`: String = SearchResult.`type`
}

object SearchResult {
  val `type`: String = "SearchResult"
}