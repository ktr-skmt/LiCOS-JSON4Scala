package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonChangeUserEmail
import play.api.libs.json.JsValue

trait ChangeUserEmailAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, changeUserEmail: JsonChangeUserEmail): Option[JsValue]
}
