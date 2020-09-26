package licos.json.element.lobby.client2server

import licos.json.element.lobby.{JsonHostPlayer, JsonPlayerSetting, JsonRoleSetting, TypeSystem}
import licos.json.validation.lobby.{BuildVillageValidation, IdSearchValidation}
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonBuildVillage(
    `type`:         String,
    token:          String,
    name:           String,
    id:             Long,
    idForSearching: Long,
    hostPlayer:     JsonHostPlayer,
    playerSetting:  JsonPlayerSetting,
    roleSetting:    JsonRoleSetting,
    avatar:         String,
    comment:        Option[String]
) extends TypeSystem(`type`) {
  override protected def validType: String = JsonBuildVillage.`type`

  @SuppressWarnings(Array[String]("org.wartremover.warts.Overloading"))
  def this(
      token:          String,
      name:           String,
      id:             Long,
      idForSearching: Long,
      hostPlayer:     JsonHostPlayer,
      playerSetting:  JsonPlayerSetting,
      roleSetting:    JsonRoleSetting,
      avatar:         String,
      comment:        Option[String]
  ) = {
    this(
      JsonBuildVillage.`type`,
      token,
      name,
      id,
      idForSearching,
      hostPlayer,
      playerSetting,
      roleSetting,
      avatar,
      comment
    )
  }
}

object JsonBuildVillage {

  val `type`: String = "buildVillage"

  import play.api.libs.functional.syntax._
  import play.api.libs.json.Reads.pattern
  import play.api.libs.json._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonBuildVillage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "name").read[String](VillageValidation.name) and
      (JsPath \ "id").read[Long](BuildVillageValidation.id) and
      (JsPath \ "idForSearching").read[Long](IdSearchValidation.idForSearching) and
      (JsPath \ "hostPlayer").read[JsonHostPlayer] and
      (JsPath \ "playerSetting").read[JsonPlayerSetting] and
      (JsPath \ "roleSetting").read[JsonRoleSetting] and
      (JsPath \ "avatar").read[String](BuildVillageValidation.avatar) and
      (JsPath \ "comment").readNullable[String](BuildVillageValidation.comment)
  )(JsonBuildVillage.apply _)

  implicit val jsonWrites: OWrites[JsonBuildVillage] = Json.writes[JsonBuildVillage]
}
