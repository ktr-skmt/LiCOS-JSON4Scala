package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat}

/**
  * <pre>
  * Created on 2018/01/04.
  * </pre>
  *
  * @author K.Sakamoto
  */
case class JsonKickOutPlayer(`type`: String, token: String, players: Seq[JsonPlayerTokenInKickOutPlayer])
    extends TypeSystem(`type`) {
  override protected def validType: String = JsonKickOutPlayer.`type`
}

object JsonKickOutPlayer {
  implicit val jsonFormat: OFormat[JsonKickOutPlayer] = Json.format[JsonKickOutPlayer]

  val `type`: String = "kickOutPlayer"
}

case class JsonPlayerTokenInKickOutPlayer(token: String)

object JsonPlayerTokenInKickOutPlayer {
  implicit val jsonFormat: OFormat[JsonPlayerTokenInKickOutPlayer] = Json.format[JsonPlayerTokenInKickOutPlayer]
}
