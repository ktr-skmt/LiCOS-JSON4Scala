package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat}

final case class JsonIdSearch(`type`: String, token: String, lobby: String, idForSearching: Int)
    extends TypeSystem(`type`) {
  override protected def validType: String = JsonIdSearch.`type`
}

object JsonIdSearch {
  implicit val jsonFormat: OFormat[JsonIdSearch] = Json.format[JsonIdSearch]

  val `type`: String = "idSearch"
}
