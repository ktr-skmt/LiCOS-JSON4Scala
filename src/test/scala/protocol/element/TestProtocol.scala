package protocol.element

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.village.VillageMessageProtocol
import play.api.libs.json.JsValue

abstract class TestProtocol(text: String)

final case class AuthMessageTestProtocol(text: String) extends TestProtocol(text) with AuthMessageProtocol {
  override def toJsonOpt: Option[JsValue] = None
}

final case class LobbyMessageTestProtocol(text: String) extends TestProtocol(text) with LobbyMessageProtocol {
  override def toJsonOpt: Option[JsValue] = None
}

final case class VillageMessageTestProtocol(text: String) extends TestProtocol(text) with VillageMessageProtocol {
  override def toJsonOpt: Option[JsValue] = None
}
