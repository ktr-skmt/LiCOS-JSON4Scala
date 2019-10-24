package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonVillage(name: String,
                       id: Long,
                       idForSearching: Long,
                       hostPlayer: JsonHostPlayer,
                       playerSetting: JsonPlayerSetting,
                       roleSetting: JsonRoleSetting,
                       avatar: String,
                       comment: Option[String])

object JsonVillage {
  implicit val jsonFormat: OFormat[JsonVillage] = Json.format[JsonVillage]
}

case class JsonHostPlayer(name: String,
                          isAnonymous: Boolean,
                          isHuman: Boolean)
object JsonHostPlayer {
  implicit val jsonFormat: OFormat[JsonHostPlayer] = Json.format[JsonHostPlayer]
}

case class JsonPlayerSetting(number: Int,
                             current: Int,
                             robot: JsonRobot,
                             human: JsonHuman)

object JsonPlayerSetting {
  implicit val jsonFormat: OFormat[JsonPlayerSetting] = Json.format[JsonPlayerSetting]
}

case class JsonRobot(min: Int,
                     current: Int)

object JsonRobot {
  implicit val jsonFormat: OFormat[JsonRobot] = Json.format[JsonRobot]
}

case class JsonHuman(max: Int,
                     current: Int)

object JsonHuman {
  implicit val jsonFormat: OFormat[JsonHuman] = Json.format[JsonHuman]
}

case class JsonRoleSetting(villager: Int,
                           werewolf: Int,
                           seer: Int,
                           medium: Int,
                           madman: Int,
                           hunter: Int,
                           mason: Int,
                           werehamster: Int)

object JsonRoleSetting {
  implicit val jsonFormat: OFormat[JsonRoleSetting] = Json.format[JsonRoleSetting]
}