package json.engine.village.example.client2server

import json.engine.VillageExample

case class Error(filePath: String) extends VillageExample(filePath) {
  override val `type`: String = Error.`type`
}

object Error {
  val `type`: String = "ErrorFromClient"
}
