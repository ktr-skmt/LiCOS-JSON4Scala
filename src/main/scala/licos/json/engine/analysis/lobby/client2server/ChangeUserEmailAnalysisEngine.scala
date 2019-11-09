package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.JsonChangeUserEmail
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for changing a user email.
  *
  * @author Kotaro Sakamoto
  */
trait ChangeUserEmailAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param changeUserEmail a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, changeUserEmail: JsonChangeUserEmail): Either[JsValue, JsValue]
}

object ChangeUserEmailAnalysisEngine {

  /**
    * Change-user-email analysis engine name.
    */
  val name:         String  = "lobby.client2server.ChangeUserEmailAnalysisEngine"
  val isFromServer: Boolean = false

}
