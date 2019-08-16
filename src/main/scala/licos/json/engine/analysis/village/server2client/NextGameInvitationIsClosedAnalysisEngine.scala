package licos.json.engine.analysis.village.server2client

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.element.village.invite.JsonNextGameInvitationIsClosed
import play.api.libs.json.JsValue

/** The analysis engine for a next-game-invitation-is-closed message.
  *
  * @author Kotaro Sakamoto
  */
trait NextGameInvitationIsClosedAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param nextGameInvitationIsClosed a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, nextGameInvitationIsClosed: JsonNextGameInvitationIsClosed): Option[JsValue]
}
