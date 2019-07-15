package licos.json.lobby

import play.api.libs.json.{Json, OFormat}

/**
  * <pre>
  * Created on 2018/01/12.
  * </pre>
  *
  * @author K.Sakamoto
  */
case class JsonPong(`type`: String,
                    token: String,
                    id: String) extends TypeSystem(`type`) {
  override protected def validType: String = `type`
}

object JsonPong {
  implicit val jsonFormat: OFormat[JsonPong] = Json.format[JsonPong]

  val `type`: String = "pong"
}