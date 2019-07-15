package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonLobby
import play.api.libs.json.JsValue

trait LobbyAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, lobby: JsonLobby): Option[JsValue]
}
