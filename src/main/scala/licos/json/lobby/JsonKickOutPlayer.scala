package licos.json.lobby

import play.api.libs.json.{Json, OFormat}

/**
  * <pre>
  * Created on 2018/01/04.
  * </pre>
  *
  * @author K.Sakamoto
  */
case class JsonKickOutPlayer(`type`: String,
                             token: String,
                             players: Seq[JsonPlayerToken]) extends TypeSystem(`type`) {
  override protected def validType: String = JsonKickOutPlayer.`type`
}

object JsonKickOutPlayer {
  implicit val jsonFormat: OFormat[JsonKickOutPlayer] = Json.format[JsonKickOutPlayer]

  val `type`: String = "kickOutPlayer"
}

case class JsonPlayerToken(token: String)

object JsonPlayerToken {
  implicit val jsonFormat: OFormat[JsonPlayerToken] = Json.format[JsonPlayerToken]
}