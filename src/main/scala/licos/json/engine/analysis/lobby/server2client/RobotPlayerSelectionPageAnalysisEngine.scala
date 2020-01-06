package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.server2client.JsonRobotPlayerSelectionPage
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for a robot player selection page.
  *
  * @author Kotaro Sakamoto
  */
trait RobotPlayerSelectionPageAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param robotPlayerSelectionPage a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, robotPlayerSelectionPage: JsonRobotPlayerSelectionPage): Either[JsValue, JsValue]
}

object RobotPlayerSelectionPageAnalysisEngine {

  /**
    * Robot-player-selection-page analysis engine name.
    */
  val name:         String  = "lobby.server2client.RobotPlayerSelectionPageAnalysisEngine"
  val isFromServer: Boolean = true
}
