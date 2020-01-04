package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonStopRobotPlayer
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for stopping a robot player.
  *
  * @author Kotaro Sakamoto
  */
trait StopRobotPlayerAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param stopRobotPlayer a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, stopRobotPlayer: JsonStopRobotPlayer): Either[JsValue, JsValue]
}

object StopRobotPlayerAnalysisEngine {

  /**
    * Stop-robot-player analysis engine name.
    */
  val name:         String  = "lobby.client2server.StopRobotPlayerAnalysisEngine"
  val isFromServer: Boolean = false
}
