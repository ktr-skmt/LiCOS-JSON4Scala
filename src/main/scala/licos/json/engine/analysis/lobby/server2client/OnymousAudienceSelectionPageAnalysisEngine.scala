package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.server2client.JsonOnymousAudienceSelectionPage
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for an onymous audience selection page.
  *
  * @author Kotaro Sakamoto
  */
trait OnymousAudienceSelectionPageAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param onymousAudienceSelectionPage a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, onymousAudienceSelectionPage: JsonOnymousAudienceSelectionPage): Either[JsValue, JsValue]
}

object OnymousAudienceSelectionPageAnalysisEngine {

  /** Onymous-audience-selection-page analysis engine name.
    */
  val name:         String  = "lobby.server2client.OnymousAudienceSelectionPageAnalysisEngine"
  val isFromServer: Boolean = true
}
