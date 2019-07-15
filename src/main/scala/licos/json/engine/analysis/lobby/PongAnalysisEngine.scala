package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonPong
import play.api.libs.json.JsValue

trait PongAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, pong: JsonPong): Option[JsValue]
}
