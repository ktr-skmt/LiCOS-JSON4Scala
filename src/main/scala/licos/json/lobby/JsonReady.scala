package licos.json.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonReady(`type`: String,
                     token: String,
                     villageId: Long) extends TypeSystem(`type`) {
  override protected def validType: String = JsonReady.`type`
}

object JsonReady {
  implicit val jsonFormat: OFormat[JsonReady] = Json.format[JsonReady]

  val `type`: String = "ready"
}
