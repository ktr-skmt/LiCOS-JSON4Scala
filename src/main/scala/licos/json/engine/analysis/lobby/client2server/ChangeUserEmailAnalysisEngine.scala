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
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, changeUserEmail: JsonChangeUserEmail): Option[JsValue]
}
