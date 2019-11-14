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
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, nextGameInvitationIsClosed: JsonNextGameInvitationIsClosed): Either[JsValue, JsValue]

}

object NextGameInvitationIsClosedAnalysisEngine {

  /**
    * Next-game-invitation-is-closed analysis engine name.
    */
  val name:         String  = "village.server2client.NextGameInvitationIsClosedAnalysisEngine"
  val isFromServer: Boolean = true

}
