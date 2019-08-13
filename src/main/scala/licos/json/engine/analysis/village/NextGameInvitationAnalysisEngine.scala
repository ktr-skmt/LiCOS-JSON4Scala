package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.invite.JsonNextGameInvitation
import play.api.libs.json.JsValue

trait NextGameInvitationAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, nextGameInvitation: JsonNextGameInvitation): Option[JsValue]
}
