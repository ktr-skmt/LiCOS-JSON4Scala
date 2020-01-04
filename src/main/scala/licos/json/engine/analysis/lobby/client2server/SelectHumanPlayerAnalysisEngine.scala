package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonSelectHumanPlayer
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for selecting a human player.
  *
  * @author Kotaro Sakamoto
  */
trait SelectHumanPlayerAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param selectHumanPlayer a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, selectHumanPlayer: JsonSelectHumanPlayer): Either[JsValue, JsValue]
}

object SelectHumanPlayerAnalysisEngine {

  /**
    * Select-human-player analysis engine name.
    */
  val name:         String  = "lobby.client2server.SelectHumanPlayerAnalysisEngine"
  val isFromServer: Boolean = false
}
