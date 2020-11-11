package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonChangeUserPassword
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for changing a user password.
  *
  * @author Kotaro Sakamoto
  */
trait ChangeUserPasswordAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param changeUserPassword a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, changeUserPassword: JsonChangeUserPassword): Either[JsValue, JsValue]
}

object ChangeUserPasswordAnalysisEngine {

  /** Change-user-password analysis engine name.
    */
  val name:         String  = "lobby.client2server.ChangeUserPasswordAnalysisEngine"
  val isFromServer: Boolean = false

}
