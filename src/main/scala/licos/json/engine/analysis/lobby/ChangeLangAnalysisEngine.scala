package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonChangeLang
import play.api.libs.json.JsValue

trait ChangeLangAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, changeLang: JsonChangeLang): Option[JsValue]
}
