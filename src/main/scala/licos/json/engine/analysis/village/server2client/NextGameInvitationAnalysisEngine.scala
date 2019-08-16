package licos.json.engine.analysis.village.server2client

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.element.village.invite.JsonNextGameInvitation
import play.api.libs.json.JsValue

/** The analysis engine for a next-game invitation.
  *
  * @author Kotaro Sakamoto
  */
trait NextGameInvitationAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param nextGameInvitation a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, nextGameInvitation: JsonNextGameInvitation): Option[JsValue]
}
