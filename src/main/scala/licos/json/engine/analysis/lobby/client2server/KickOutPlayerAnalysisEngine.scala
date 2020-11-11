package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonKickOutPlayer
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for kicking out a player.
  *
  * @author Kotaro Sakamoto
  */
trait KickOutPlayerAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param kickOutPlayer a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, kickOutPlayer: JsonKickOutPlayer): Either[JsValue, JsValue]
}

object KickOutPlayerAnalysisEngine {

  /** Kick-out-player analysis engine name.
    */
  val name:         String  = "lobby.client2server.KickOutPlayerAnalysisEngine"
  val isFromServer: Boolean = false

}
