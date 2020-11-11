package licos.json.engine.analysis.village.server2client

import licos.json.element.village.server2client.JsonPhase
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for a phase.
  *
  * @author Kotaro Sakamoto
  */
trait PhaseAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param phase a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, phase: JsonPhase): Either[JsValue, JsValue]

}

object PhaseAnalysisEngine {

  /** Phase analysis engine name.
    */
  val name:         String  = "village.server2client.PhaseAnalysisEngine"
  val isFromServer: Boolean = true

}
