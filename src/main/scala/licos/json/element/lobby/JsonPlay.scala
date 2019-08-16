package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonPlay(`type`: String,
                    token: String,
                    villageId: Long) extends TypeSystem(`type`) {
  override protected def validType: String = JsonPlay.`type`
}

object JsonPlay {
  implicit val jsonFormat: OFormat[JsonPlay] = Json.format[JsonPlay]

  val `type`: String = "play"
}