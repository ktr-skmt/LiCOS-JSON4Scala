package licos.json.engine.analysis.village.client2server

import licos.json.element.village.client2server.JsonVote
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for a vote.
  *
  * @author Kotaro Sakamoto
  */
trait VoteAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param vote a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, vote: JsonVote): Either[JsValue, JsValue]

}

object VoteAnalysisEngine {

  /**
    * Vote analysis engine name.
    */
  val name:         String  = "village.client2server.VoteAnalysisEngine"
  val isFromServer: Boolean = false

}
