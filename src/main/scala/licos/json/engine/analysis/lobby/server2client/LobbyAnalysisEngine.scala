package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.JsonLobby
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for a lobby.
  *
  * @author Kotaro Sakamoto
  */
trait LobbyAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param lobby a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, lobby: JsonLobby): Either[JsValue, JsValue]

}

object LobbyAnalysisEngine {

  /**
    * Lobby analysis engine name.
    */
  val name:         String  = "lobby.server2client.LobbyAnalysisEngine"
  val isFromServer: Boolean = true

}
