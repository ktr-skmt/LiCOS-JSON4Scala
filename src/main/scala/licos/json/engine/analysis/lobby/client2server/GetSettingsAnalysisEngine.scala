package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.JsonGetSettings
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for getting settings.
  *
  * @author Kotaro Sakamoto
  */
trait GetSettingsAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param getSettings a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, getSettings: JsonGetSettings): Option[JsValue]
}
