package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonSettings
import play.api.libs.json.JsValue

trait SettingsAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, settings: JsonSettings): Option[JsValue]
}
