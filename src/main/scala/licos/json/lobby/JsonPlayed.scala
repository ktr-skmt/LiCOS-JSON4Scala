package licos.json.lobby

import play.api.libs.json.{Json, OFormat}

case class JsonPlayed(`type`: String,
                      lang: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonPlayed.`type`
}

object JsonPlayed {
  implicit val jsonFormat: OFormat[JsonPlayed] = Json.format[JsonPlayed]

  def generate(lang: String): JsonPlayed = JsonPlayed(`type`, lang)

  val `type`: String = "played"
}