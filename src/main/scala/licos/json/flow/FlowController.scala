package licos.json.flow

import play.api.libs.json.JsValue

/** This lets classes extend a method to control a flow of parsing play.api.libs.json.JsValue.
  *
  * @author Kotaro Sakamoto
  */
trait FlowController {

  /** Controls the flow of parsing play.api.libs.json.JsValue.
    *
    * @param jsValue a play.api.libs.json.JsValue to parse.
    * @return a parsing result.
    */
  def flow(jsValue: JsValue): Option[Any]
}
