package licos.json.element.lobby.client2server

import licos.json.element.lobby.TypeSystem
import licos.json.validation.lobby.{BuildVillageValidation, LobbyValidation}
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonAdvancedSearch(
    `type`:      String,
    token:       String,
    lobby:       String,
    villageName: Option[String],
    hostName:    Option[String],
    minimum:     Option[Int],
    maximum:     Option[Int],
    avatar:      Option[String],
    comment:     Option[String]
) extends TypeSystem(`type`) {

  override protected def validType: String = JsonAdvancedSearch.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      token:       String,
      lobby:       String,
      villageName: Option[String],
      hostName:    Option[String],
      minimum:     Option[Int],
      maximum:     Option[Int],
      avatar:      Option[String],
      comment:     Option[String]
  ) = {
    this(JsonAdvancedSearch.`type`, token, lobby, villageName, hostName, minimum, maximum, avatar, comment)
  }
}

object JsonAdvancedSearch {

  val `type`: String = "advancedSearch"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  implicit val jsonReads: Reads[JsonAdvancedSearch] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "lobby").read[String](LobbyValidation.lobby) and
      (JsPath \ "villageName").readNullable[String](VillageValidation.name) and
      (JsPath \ "hostName").readNullable[String](AvatarValidation.name) and
      (JsPath \ "minimum").readNullable[Int](VillageValidation.totalNumberOfPlayers) and
      (JsPath \ "maximum").readNullable[Int](VillageValidation.totalNumberOfPlayers) and
      (JsPath \ "avatar").readNullable[String](BuildVillageValidation.avatar) and
      (JsPath \ "comment").readNullable[String](BuildVillageValidation.comment)
  )(JsonAdvancedSearch.apply _)

  implicit val jsonWrites: OWrites[JsonAdvancedSearch] = Json.writes[JsonAdvancedSearch]
}
