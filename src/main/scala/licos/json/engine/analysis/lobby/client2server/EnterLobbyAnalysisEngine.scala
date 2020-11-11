package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonEnterLobby
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for entering a lobby.
  *
  * @author Kotaro Sakamoto
  */
trait EnterLobbyAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param enterLobby a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, enterLobby: JsonEnterLobby): Either[JsValue, JsValue]
}

object EnterLobbyAnalysisEngine {

  /** Enter-lobby analysis engine name.
    */
  val name:         String  = "lobby.client2server.EnterLobbyAnalysisEngine"
  val isFromServer: Boolean = false

}
