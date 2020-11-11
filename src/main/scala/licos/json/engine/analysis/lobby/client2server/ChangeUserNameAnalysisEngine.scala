package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonChangeUserName
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for changing a user name.
  *
  * @author Kotaro Sakamoto
  */
trait ChangeUserNameAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param changeUserName a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, changeUserName: JsonChangeUserName): Either[JsValue, JsValue]
}

object ChangeUserNameAnalysisEngine {

  /** Change-user-name analysis engine name.
    */
  val name:         String  = "lobby.client2server.ChangeUserNameAnalysisEngine"
  val isFromServer: Boolean = false

}
