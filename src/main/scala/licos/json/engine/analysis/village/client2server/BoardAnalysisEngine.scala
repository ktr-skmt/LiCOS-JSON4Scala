package licos.json.engine.analysis.village.client2server

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.element.village.JsonBoard
import play.api.libs.json.JsValue

/** The analysis engine for a board.
  *
  * @author Kotaro Sakamoto
  */
trait BoardAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param board a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, board: JsonBoard): Either[JsValue, JsValue]

}

object BoardAnalysisEngine {

  /**
    * Board analysis engine name.
    */
  val name:         String  = "village.client2server.BoardAnalysisEngine"
  val isFromServer: Boolean = false

}
