package licos.protocol.element.lobby

import play.api.libs.json.JsValue

final case class NoLobbyMessageProtocol() extends LobbyMessageProtocol {
  override def toJsonOpt: Option[JsValue] = Option.empty[JsValue]
}
