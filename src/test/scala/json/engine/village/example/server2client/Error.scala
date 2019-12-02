package json.engine.village.example.server2client

import json.engine.VillageExample

final case class Error(filePath: String) extends VillageExample(filePath) {
  override val `type`: String = Error.`type`
}

object Error {
  val `type`: String = "ErrorFromServer"
}
