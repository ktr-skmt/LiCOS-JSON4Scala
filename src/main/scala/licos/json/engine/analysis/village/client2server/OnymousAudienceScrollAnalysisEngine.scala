package licos.json.engine.analysis.village.client2server

import licos.json.element.village.JsonOnymousAudienceScroll
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for scrolling.
  *
  * @author Kotaro Sakamoto
  */
trait OnymousAudienceScrollAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param scroll a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, scroll: JsonOnymousAudienceScroll): Option[JsValue]
}
