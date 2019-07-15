package licos.json.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonAdvancedSearch(`type`: String,
                              token: String,
                              lobby: String,
                              villageName: Option[String],
                              hostName: Option[String],
                              minimum: Option[Int],
                              maximum: Option[Int],
                              avatar: String,
                              comment: Option[String]) extends TypeSystem(`type`) {
  override protected def validType: String = JsonAdvancedSearch.`type`
}

object JsonAdvancedSearch {
  implicit val jsonFormat: OFormat[JsonAdvancedSearch] = Json.format[JsonAdvancedSearch]

  val `type`: String = "advancedSearch"
}