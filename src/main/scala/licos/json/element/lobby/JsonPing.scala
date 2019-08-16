package licos.json.element.lobby

import play.api.libs.json.{OFormat, Json}


/**
  * <pre>
  * Created on 2018/01/12.
  * </pre>
  *
  * @author K.Sakamoto
  */
case class JsonPing(`type`: String,
                    id: String,
                    results: Seq[JsonPingResult]) extends TypeSystem(`type`) {
  override protected def validType: String = JsonPing.`type`
}

object JsonPing {
  implicit val jsonFormat: OFormat[JsonPing] = Json.format[JsonPing]

  val `type`: String = "ping"
}

case class JsonPingResult(token: String,
                          ping: String,
                          status: String)

object JsonPingResult {
  implicit val jsonFormat: OFormat[JsonPingResult] = Json.format[JsonPingResult]
}