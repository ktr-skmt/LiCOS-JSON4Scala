package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonChatFromServer
import play.api.libs.json.JsValue

trait ChatFromServerAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, chatFromServer: JsonChatFromServer): Option[JsValue]
}
