package licos.json.element.lobby

import licos.json.element.Element
import licos.json.validation.lobby.{BuildVillageValidation, IdSearchValidation}
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonBuildVillage(
    `type`:         String,
    token:          String,
    name:           String,
    id:             Long,
    idForSearching: Int,
    hostPlayer:     JsonHostPlayer,
    playerSetting:  JsonPlayerSetting,
    roleSetting:    JsonRoleSetting,
    avatar:         String,
    comment:        String
) extends TypeSystem(`type`)
    with Element {
  override protected def validType: String = JsonBuildVillage.`type`
}

object JsonBuildVillage {

  val `type`: String = "buildVillage"

  import play.api.libs.json._
  import play.api.libs.json.Reads._
  import play.api.libs.functional.syntax._

  implicit val jsonReads: Reads[JsonBuildVillage] = (
    (JsPath \ "type").read[String](pattern(`type`.r)) and
      (JsPath \ "token").read[String](AvatarValidation.token) and
      (JsPath \ "name").read[String](VillageValidation.name) and
      (JsPath \ "id").read[Long](VillageValidation.id) and
      (JsPath \ "idForSearching").read[Int](IdSearchValidation.idForSearching) and
      (JsPath \ "hostPlayer").read[JsonHostPlayer] and
      (JsPath \ "playerSetting").read[JsonPlayerSetting] and
      (JsPath \ "roleSetting").read[JsonRoleSetting] and
      (JsPath \ "avatar").read[String](BuildVillageValidation.avatar) and
      (JsPath \ "comment").read[String](BuildVillageValidation.comment)
  )(JsonBuildVillage.apply _)

  implicit val jsonWrites: OWrites[JsonBuildVillage] = Json.writes[JsonBuildVillage]

  def generate(
      token:          String,
      name:           String,
      id:             Long,
      idForSearching: Int,
      hostPlayer:     JsonHostPlayer,
      playerSetting:  JsonPlayerSetting,
      roleSetting:    JsonRoleSetting,
      avatar:         String,
      comment:        String
  ): JsonBuildVillage = {
    JsonBuildVillage(`type`, token, name, id, idForSearching, hostPlayer, playerSetting, roleSetting, avatar, comment)
  }
}
