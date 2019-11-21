package licos.json.engine.analysis.village.client2server

import licos.json.engine.BOX
import licos.json.engine.analysis.JsonAnalysisEngine
import licos.json.element.village.JsonBoard
import play.api.libs.json.JsValue

/** The analysis engine for a board.
  *
  * @author Kotaro Sakamoto
  */
trait JsonBoardAnalysisEngine extends JsonAnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param board a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, board: JsonBoard): Either[JsValue, JsValue]

}

object JsonBoardAnalysisEngine {

  /**
    * Board analysis engine name.
    */
  val name:         String  = "village.client2server.JsonBoardAnalysisEngine"
  val isFromServer: Boolean = false

}
