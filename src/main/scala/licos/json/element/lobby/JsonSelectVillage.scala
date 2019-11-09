package licos.json.element.lobby

import play.api.libs.json.{Json, OFormat};

/**
  * <pre>
  * Created on 2018/01/04.
  * </pre>
  *
  * @author K.Sakamoto
  */
final case class JsonSelectVillage(`type`: String, token: String, villageId: Long) extends TypeSystem(`type`) {
  override protected def validType: String = JsonSelectVillage.`type`
}

object JsonSelectVillage {
  implicit val jsonFormat: OFormat[JsonSelectVillage] = Json.format[JsonSelectVillage]

  val `type`: String = "selectVillage"
}
