package licos.json.element.lobby

import licos.json.element.Element
import play.api.libs.json.{Json, OFormat}

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
    comment:        String
) extends TypeSystem(`type`)
    with Element {
  override protected def validType: String = JsonBuildVillage.`type`
}

object JsonBuildVillage {
  implicit val jsonFormat: OFormat[JsonBuildVillage] = Json.format[JsonBuildVillage]

  val `type`: String = "buildVillage"

  def generate(
      token:          String,
      name:           String,
      id:             Long,
      idForSearching: Long,
      hostPlayer:     JsonHostPlayer,
      playerSetting:  JsonPlayerSetting,
      roleSetting:    JsonRoleSetting,
      avatar:         String,
      comment:        String
  ): JsonBuildVillage = {
    JsonBuildVillage(`type`, token, name, id, idForSearching, hostPlayer, playerSetting, roleSetting, avatar, comment)
  }
}
