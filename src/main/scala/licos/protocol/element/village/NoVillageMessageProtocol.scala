package licos.protocol.element.village

import play.api.libs.json.JsValue

final case class NoVillageMessageProtocol() extends VillageMessageProtocol {
  override def toJsonOpt: Option[JsValue] = Option.empty[JsValue]
}
