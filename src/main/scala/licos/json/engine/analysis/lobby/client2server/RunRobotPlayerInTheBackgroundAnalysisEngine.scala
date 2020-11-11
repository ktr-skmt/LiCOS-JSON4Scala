package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonRunRobotPlayerInTheBackground
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for running a robot player in the background.
  *
  * @author Kotaro Sakamoto
  */
trait RunRobotPlayerInTheBackgroundAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param runRobotPlayerInTheBackground a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, runRobotPlayerInTheBackground: JsonRunRobotPlayerInTheBackground): Either[JsValue, JsValue]
}

object RunRobotPlayerInTheBackgroundAnalysisEngine {

  /** Run-robot-player-in-the-background analysis engine name.
    */
  val name:         String  = "lobby.client2server.RunRobotPlayerInTheBackgroundAnalysisEngine"
  val isFromServer: Boolean = false
}
