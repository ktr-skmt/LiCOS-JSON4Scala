package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.JsonWaitingPage
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for a waiting page.
  *
  * @author Kotaro Sakamoto
  */
trait WaitingPageAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param waitingPage a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, waitingPage: JsonWaitingPage): Option[JsValue]
}
