package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonRunRobotPlayerInTheForeground
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for running a robot player in the foreground.
  *
  * @author Kotaro Sakamoto
  */
trait RunRobotPlayerInTheForegroundAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param runRobotPlayerInTheForeground a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, runRobotPlayerInTheForeground: JsonRunRobotPlayerInTheForeground): Either[JsValue, JsValue]
}

object RunRobotPlayerInTheForegroundAnalysisEngine {

  /**
    * Run-robot-player-in-the-foreground analysis engine name.
    */
  val name:         String  = "lobby.client2server.RunRobotPlayerInTheForegroundAnalysisEngine"
  val isFromServer: Boolean = false
}
