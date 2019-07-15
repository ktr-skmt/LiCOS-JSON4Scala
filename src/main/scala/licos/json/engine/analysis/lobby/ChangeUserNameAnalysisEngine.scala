package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonChangeUserName
import play.api.libs.json.JsValue

trait ChangeUserNameAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, changeUserName: JsonChangeUserName): Option[JsValue]
}
