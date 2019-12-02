package licos.protocol.element.auth

import play.api.libs.json.JsValue

final case class NoAuthMessageProtocol() extends AuthMessageProtocol {
  override def toJsonOpt: Option[JsValue] = None
}
