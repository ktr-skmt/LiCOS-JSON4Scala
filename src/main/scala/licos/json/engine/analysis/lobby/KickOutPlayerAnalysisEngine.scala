package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonKickOutPlayer
import play.api.libs.json.JsValue

trait KickOutPlayerAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, kickOutPlayer: JsonKickOutPlayer): Option[JsValue]
}
