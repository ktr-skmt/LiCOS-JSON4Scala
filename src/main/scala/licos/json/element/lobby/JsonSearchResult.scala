package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat}

final case class JsonSearchResult(`type`: String, villages: Seq[JsonVillage], error: Option[String])
    extends TypeSystem(`type`) {
  override protected def validType: String = JsonSearchResult.`type`
}

object JsonSearchResult {
  implicit val jsonFormat: OFormat[JsonSearchResult] = Json.format[JsonSearchResult]

  val `type`: String = "searchResult"

  def generate(villages: Seq[JsonVillage], error: Option[String]): JsonSearchResult = {
    JsonSearchResult(`type`, villages, error)
  }
}
