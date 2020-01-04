package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonCreateRobotPlayer
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for creating a robot player.
  *
  * @author Kotaro Sakamoto
  */
trait CreateRobotPlayerAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param createRobotPlayer a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, createRobotPlayer: JsonCreateRobotPlayer): Either[JsValue, JsValue]
}

object CreateRobotPlayerAnalysisEngine {

  /**
    * Create-robot-player analysis engine name.
    */
  val name:         String  = "lobby.client2server.CreateRobotPlayerAnalysisEngine"
  val isFromServer: Boolean = false
}
