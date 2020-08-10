package licos.protocol.element

import play.api.libs.json.JsValue

trait Protocol extends Serializable {
  def toJsonOpt: Option[JsValue]
}
