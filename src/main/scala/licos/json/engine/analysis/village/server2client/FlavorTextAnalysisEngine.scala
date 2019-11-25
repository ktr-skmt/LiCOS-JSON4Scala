package licos.json.engine.analysis.village.server2client

import licos.json.element.village.server2client.JsonFlavorText
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for a flavor text.
  *
  * @author Kotaro Sakamoto
  */
trait FlavorTextAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param flavorText a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, flavorText: JsonFlavorText): Either[JsValue, JsValue]

}

object FlavorTextAnalysisEngine {

  /**
    * Flavor-text analysis engine name.
    */
  val name:         String  = "village.server2client.FlavorTextAnalysisEngine"
  val isFromServer: Boolean = true

}
