package licos.json.engine.analysis.village.client2server

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.element.village.JsonStar
import play.api.libs.json.JsValue

/** The analysis engine for a star.
  *
  * @author Kotaro Sakamoto
  */
trait StarAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param star a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, star: JsonStar): Either[JsValue, JsValue]

}

object StarAnalysisEngine {

  /**
    * Star analysis engine name.
    */
  val name:         String  = "village.client2server.StarAnalysisEngine"
  val isFromServer: Boolean = false

}
