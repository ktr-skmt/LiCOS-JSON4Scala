package licos.json.element.lobby

import licos.json.validation.lobby.{
  BuildVillageValidation,
  IdSearchValidation,
  PlayerSettingValidation,
  RoleSettingValidation
}
import licos.json.validation.village.{AvatarValidation, VillageValidation}

final case class JsonVillage(
    name:           String,
    id:             Long,
    idForSearching: Long,
    hostPlayer:     JsonHostPlayer,
    playerSetting:  JsonPlayerSetting,
    roleSetting:    JsonRoleSetting,
    avatar:         String,
    comment:        Option[String]
)

object JsonVillage {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonVillage] = (
    (JsPath \ "name").read[String](VillageValidation.name) and
      (JsPath \ "id").read[Long](VillageValidation.id) and
      (JsPath \ "idForSearching").read[Long](IdSearchValidation.idForSearching) and
      (JsPath \ "hostPlayer").read[JsonHostPlayer] and
      (JsPath \ "playerSetting").read[JsonPlayerSetting] and
      (JsPath \ "roleSetting").read[JsonRoleSetting] and
      (JsPath \ "avatar").read[String](BuildVillageValidation.avatar) and
      (JsPath \ "comment").readNullable[String](BuildVillageValidation.comment)
  )(JsonVillage.apply _)

  implicit val jsonWrites: OWrites[JsonVillage] = Json.writes[JsonVillage]
}

final case class JsonHostPlayer(name: String, isAnonymous: Boolean, isHuman: Boolean)

object JsonHostPlayer {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonHostPlayer] = (
    (JsPath \ "name").read[String](AvatarValidation.name) and
      (JsPath \ "isAnonymous").read[Boolean] and
      (JsPath \ "isHuman").read[Boolean]
  )(JsonHostPlayer.apply _)

  implicit val jsonWrites: OWrites[JsonHostPlayer] = Json.writes[JsonHostPlayer]
}

final case class JsonPlayerSetting(number: Int, current: Int, robot: JsonRobot, human: JsonHuman)

object JsonPlayerSetting {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonPlayerSetting] = (
    (JsPath \ "number").read[Int](VillageValidation.totalNumberOfPlayers) and
      (JsPath \ "current").read[Int](PlayerSettingValidation.current) and
      (JsPath \ "robot").read[JsonRobot] and
      (JsPath \ "human").read[JsonHuman]
  )(JsonPlayerSetting.apply _)

  implicit val jsonWrites: OWrites[JsonPlayerSetting] = Json.writes[JsonPlayerSetting]
}

final case class JsonRobot(min: Int, current: Int)

object JsonRobot {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonRobot] = (
    (JsPath \ "min").read[Int](PlayerSettingValidation.robot.min) and
      (JsPath \ "current").read[Int](PlayerSettingValidation.robot.current)
  )(JsonRobot.apply _)

  implicit val jsonWrites: OWrites[JsonRobot] = Json.writes[JsonRobot]
}

final case class JsonHuman(max: Int, current: Int)

object JsonHuman {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonReads: Reads[JsonHuman] = (
    (JsPath \ "max").read[Int](PlayerSettingValidation.human.max) and
      (JsPath \ "current").read[Int](PlayerSettingValidation.human.current)
  )(JsonHuman.apply _)

  implicit val jsonWrites: OWrites[JsonHuman] = Json.writes[JsonHuman]
}

final case class JsonRoleSetting(
    villager:    Int,
    werewolf:    Int,
    seer:        Int,
    medium:      Int,
    madman:      Int,
    hunter:      Int,
    mason:       Int,
    werehamster: Int
)

object JsonRoleSetting {

  import play.api.libs.json._
  import play.api.libs.functional.syntax._

  @SuppressWarnings(Array[String]("org.wartremover.warts.Any", "org.wartremover.warts.Nothing"))
  implicit val jsonRoleSetting: Reads[JsonRoleSetting] = (
    (JsPath \ "villager").read[Int](RoleSettingValidation.villager) and
      (JsPath \ "werewolf").read[Int](RoleSettingValidation.werewolf) and
      (JsPath \ "seer").read[Int](RoleSettingValidation.seer) and
      (JsPath \ "medium").read[Int](RoleSettingValidation.medium) and
      (JsPath \ "madman").read[Int](RoleSettingValidation.madman) and
      (JsPath \ "hunter").read[Int](RoleSettingValidation.hunter) and
      (JsPath \ "mason").read[Int](RoleSettingValidation.mason) and
      (JsPath \ "werehamster").read[Int](RoleSettingValidation.werehamster)
  )(JsonRoleSetting.apply _)

  implicit val jsonWrites: OWrites[JsonRoleSetting] = Json.writes[JsonRoleSetting]
}
