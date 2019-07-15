package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonChatFromClient
import play.api.libs.json.JsValue

trait ChatFromClientAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, chatFromClient: JsonChatFromClient): Option[JsValue]
}
