package licos.json.element.lobby

import licos.json.element.village.JsonSubError

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonSearchResult(`type`: String, villages: Seq[JsonVillage], error: Option[JsonSubError])
    extends TypeSystem(`type`) {

  override protected def validType: String = JsonSearchResult.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(villages: Seq[JsonVillage], error: Option[JsonSubError]) = {
    this(JsonSearchResult.`type`, villages, error)
  }
}

object JsonSearchResult {

  val `type`: String = "searchResult"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonSearchResult] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "villages").read[Seq[JsonVillage]] and
      (JsPath \ "error").readNullable[JsonSubError]
  )(JsonSearchResult.apply _)

  implicit val jsonWrites: OWrites[JsonSearchResult] = Json.writes[JsonSearchResult]
}
