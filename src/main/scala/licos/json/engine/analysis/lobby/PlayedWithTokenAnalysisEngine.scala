package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonPlayedWithToken
import play.api.libs.json.JsValue

trait PlayedWithTokenAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, playedWithToken: JsonPlayedWithToken): Option[JsValue]
}
