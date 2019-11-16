package licos.json.element.lobby

import licos.json.validation.lobby.PingValidation
import licos.json.validation.village.AvatarValidation

final case class JsonPing(`type`: String, id: String, results: Seq[JsonPingResult]) extends TypeSystem(`type`) {

  override protected def validType: String = JsonPing.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(id: String, results: Seq[JsonPingResult]) = {
    this(JsonPing.`type`, id, results)
  }
}

object JsonPing {

  val `type`: String = "ping"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonPing] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "id").read[String](AvatarValidation.token) and
      (JsPath \ "results").read[Seq[JsonPingResult]]
  )(JsonPing.apply _)

  implicit val jsonWrites: OWrites[JsonPing] = Json.writes[JsonPing]
}

final case class JsonPingResult(token: String, ping: String, status: String)

object JsonPingResult {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonPingResult] = (
    (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "ping").read[String](PingValidation.results.ping) and
      (JsPath \ "status").read[String](PingValidation.results.status)
  )(JsonPingResult.apply _)

  implicit val jsonWrites: OWrites[JsonPingResult] = Json.writes[JsonPingResult]
}
