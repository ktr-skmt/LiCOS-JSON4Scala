package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.server2client.JsonHumanPlayerSelectionPage
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for a human player selection page.
  *
  * @author Kotaro Sakamoto
  */
trait HumanPlayerSelectionPageAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param humanPlayerSelectionPage a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, humanPlayerSelectionPage: JsonHumanPlayerSelectionPage): Either[JsValue, JsValue]
}

object HumanPlayerSelectionPageAnalysisEngine {

  /**
    *Human-player-selection-page analysis engine name.
    */
  val name:         String  = "lobby.server2client.HumanPlayerSelectionPageAnalysisEngine"
  val isFromServer: Boolean = true
}
