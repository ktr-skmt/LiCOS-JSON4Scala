package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.server2client.JsonWaitingPage
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
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, waitingPage: JsonWaitingPage): Either[JsValue, JsValue]

}

object WaitingPageAnalysisEngine {

  /** Waiting-page analysis engine name.
    */
  val name:         String  = "lobby.server2client.WaitingPageAnalysisEngine"
  val isFromServer: Boolean = true

}
