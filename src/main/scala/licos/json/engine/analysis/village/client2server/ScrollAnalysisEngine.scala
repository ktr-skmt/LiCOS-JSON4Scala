package licos.json.engine.analysis.village.client2server

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.element.village.JsonScroll
import play.api.libs.json.JsValue

/** The analysis engine for scrolling.
  *
  * @author Kotaro Sakamoto
  */
trait ScrollAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param scroll a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, scroll: JsonScroll): Either[JsValue, JsValue]

}

object ScrollAnalysisEngine {

  /**
    * Scroll analysis engine name.
    */
  val name:         String  = "village.client2server.ScrollAnalysisEngine"
  val isFromServer: Boolean = false

}
