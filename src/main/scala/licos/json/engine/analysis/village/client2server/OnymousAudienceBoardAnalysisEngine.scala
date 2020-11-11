package licos.json.engine.analysis.village.client2server

import licos.json.element.village.client2server.JsonOnymousAudienceBoard
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for onymous audience board.
  *
  * @author Kotaro Sakamoto
  */
trait OnymousAudienceBoardAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param onymousAudienceBoard a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, onymousAudienceBoard: JsonOnymousAudienceBoard): Either[JsValue, JsValue]

}

object OnymousAudienceBoardAnalysisEngine {

  /** Onymous-audience-board analysis engine name.
    */
  val name:         String  = "village.client2server.OnymousAudienceBoardAnalysisEngine"
  val isFromServer: Boolean = false

}
