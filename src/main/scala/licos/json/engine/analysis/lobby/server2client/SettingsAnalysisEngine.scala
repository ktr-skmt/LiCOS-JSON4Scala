package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.JsonSettings
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for settings.
  *
  * @author Kotaro Sakamoto
  */
trait SettingsAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param settings a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, settings: JsonSettings): Option[JsValue]
}
