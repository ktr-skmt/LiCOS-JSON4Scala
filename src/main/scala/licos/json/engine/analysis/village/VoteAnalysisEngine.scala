package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonVote
import play.api.libs.json.JsValue

trait VoteAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, vote: JsonVote): Option[JsValue]
}
