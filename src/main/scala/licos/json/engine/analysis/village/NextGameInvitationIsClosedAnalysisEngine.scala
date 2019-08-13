package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.invite.JsonNextGameInvitationIsClosed
import play.api.libs.json.JsValue

trait NextGameInvitationIsClosedAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, nextGameInvitationIsClosed: JsonNextGameInvitationIsClosed): Option[JsValue]
}