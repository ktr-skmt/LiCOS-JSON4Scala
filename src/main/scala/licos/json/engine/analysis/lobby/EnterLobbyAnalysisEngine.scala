package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonEnterLobby
import play.api.libs.json.JsValue

trait EnterLobbyAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, enterLobby: JsonEnterLobby): Option[JsValue]
}
