package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.server2client.JsonNewAvatarToken
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for new avatar token.
  *
  * @author Kotaro Sakamoto
  */
trait NewAvatarTokenAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param newAvatarToken a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, newAvatarToken: JsonNewAvatarToken): Either[JsValue, JsValue]
}

object NewAvatarTokenAnalysisEngine {

  /**
    * New-avatar-token analysis engine name.
    */
  val name:         String  = "lobby.server2client.NewAvatarTokenAnalysisEngine"
  val isFromServer: Boolean = true

}
