package licos.json.element.lobby

import licos.json.element.village.JsonSubError
import licos.json.validation.village.AvatarValidation

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
final case class JsonWaitingPage(
    `type`:  String,
    village: JsonVillage,
    players: Seq[JsonPlayerInWaitingPage],
    error:   Option[JsonSubError]
) extends TypeSystem(`type`) {

  override protected def validType: String = JsonWaitingPage.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(village: JsonVillage, players: Seq[JsonPlayerInWaitingPage], error: Option[JsonSubError]) = {
    this(JsonWaitingPage.`type`, village, players, error)
  }
}

object JsonWaitingPage {

  val `type`: String = "waitingPage"

  import play.api.libs.json._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonWaitingPage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "village").read[JsonVillage] and
      (JsPath \ "players").read[Seq[JsonPlayerInWaitingPage]] and
      (JsPath \ "error").readNullable[JsonSubError]
  )(JsonWaitingPage.apply _)

  implicit val jsonWrites: OWrites[JsonWaitingPage] = Json.writes[JsonWaitingPage]
}

final case class JsonPlayerInWaitingPage(
    token:       String,
    name:        String,
    avatarImage: String,
    isAnonymous: Boolean,
    isHost:      Boolean,
    isMe:        Boolean
)

object JsonPlayerInWaitingPage {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonPlayerInWaitingPage] = (
    (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "name").read[String](AvatarValidation.name) and
      (JsPath \ "avatarImage").read[String](AvatarValidation.image) and
      (JsPath \ "isAnonymous").read[Boolean] and
      (JsPath \ "isHost").read[Boolean] and
      (JsPath \ "isMe").read[Boolean]
  )(JsonPlayerInWaitingPage.apply _)

  implicit val jsonWrites: OWrites[JsonPlayerInWaitingPage] = Json.writes[JsonPlayerInWaitingPage]

}
