package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonRenewAvatarToken
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for renewing avatar token.
  *
  * @author Kotaro Sakamoto
  */
trait RenewAvatarTokenAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param renewAvatarToken a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, renewAvatarToken: JsonRenewAvatarToken): Either[JsValue, JsValue]
}

object RenewAvatarTokenAnalysisEngine {

  /**
    * Renew-avatar-token analysis engine name.
    */
  val name:         String  = "lobby.client2server.RenewAvatarTokenAnalysisEngine"
  val isFromServer: Boolean = false

}