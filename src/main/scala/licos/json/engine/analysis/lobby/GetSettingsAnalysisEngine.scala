package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonGetSettings
import play.api.libs.json.JsValue

trait GetSettingsAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, getSettings: JsonGetSettings): Option[JsValue]
}
