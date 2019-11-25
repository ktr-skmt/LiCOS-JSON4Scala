package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonGetSettings
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
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, getSettings: JsonGetSettings): Either[JsValue, JsValue]
}

object GetSettingsAnalysisEngine {

  /**
    * Get-settings analysis engine name.
    */
  val name:         String  = "lobby.client2server.GetSettingsAnalysisEngine"
  val isFromServer: Boolean = false

}
