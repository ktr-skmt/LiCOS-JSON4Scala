package licos.json.lobby

import play.api.libs.json.{Json, OFormat}

/**
  * <pre>
  * Created on 2018/01/04.
  * </pre>
  *
  * @author K.Sakamoto
  */
case class JsonLeaveWaitingPage(`type`: String,
                                token: String,
                                villageId: Long,
                                lobby: String) extends TypeSystem(`type`) {
  override protected def validType: String = JsonLeaveWaitingPage.`type`
}

object JsonLeaveWaitingPage {
  implicit val jsonFormat: OFormat[JsonLeaveWaitingPage] = Json.format[JsonLeaveWaitingPage]

  val `type`: String = "leaveWaitingPage"
}