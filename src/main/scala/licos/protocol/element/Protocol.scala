package licos.protocol.element

import play.api.libs.json.JsValue

trait Protocol {
  def toJsonOpt: Option[JsValue]
}
