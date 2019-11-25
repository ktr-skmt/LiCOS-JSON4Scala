package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.server2client.JsonSettings
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
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, settings: JsonSettings): Either[JsValue, JsValue]

}

object SettingsAnalysisEngine {

  /**
    * Settings analysis engine name.
    */
  val name:         String  = "lobby.server2client.SettingsAnalysisEngine"
  val isFromServer: Boolean = true

}
