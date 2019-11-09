package licos.json.engine.processing

import licos.json.engine.BOX
import licos.json.flow.FlowController
import play.api.libs.json.JsValue

/** This lets classes extend process to return a play.api.libs.json.JsValue response from a JSON message.
  *
  * @author Kotaro Sakamoto
  */
trait ProcessingEngine {

  protected val flowController: FlowController

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param msg a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, msg: String): Either[JsValue, JsValue]
}
