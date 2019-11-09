package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat}

/**
  * <pre>
  * Created on 2018/01/04.
  * </pre>
  *
  * @author K.Sakamoto
  */
final case class JsonEnterLobby(`type`: String, token: String, lobby: String, page: Int) extends TypeSystem(`type`) {
  override protected def validType: String = JsonEnterLobby.`type`
}

object JsonEnterLobby {
  implicit val jsonFormat: OFormat[JsonEnterLobby] = Json.format[JsonEnterLobby]

  val `type`: String = "enterLobby"
}
