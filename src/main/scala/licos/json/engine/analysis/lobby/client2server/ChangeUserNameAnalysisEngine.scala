package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.JsonChangeUserName
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
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, changeUserName: JsonChangeUserName): Option[JsValue]
}
