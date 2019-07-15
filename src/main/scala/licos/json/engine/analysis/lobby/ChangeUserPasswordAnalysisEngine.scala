package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonChangeUserPassword
import play.api.libs.json.JsValue

trait ChangeUserPasswordAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, changeUserPassword: JsonChangeUserPassword): Option[JsValue]
}
