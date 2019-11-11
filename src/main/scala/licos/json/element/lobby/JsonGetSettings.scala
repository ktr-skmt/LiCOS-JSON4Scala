package licos.json.element.lobby

final case class JsonGetSettings(`type`: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonGetSettings.`type`
}

object JsonGetSettings {

  val `type`: String = "getSettings"

  import play.api.libs.json._
  import play.api.libs.json.Reads._

  implicit val jsonReads: Reads[JsonGetSettings] = {
    (JsPath \ "type").read[String](pattern(`type`.r)).map(JsonGetSettings.apply)
  }

  implicit val jsonWrites: OWrites[JsonGetSettings] = Json.writes[JsonGetSettings]
}
