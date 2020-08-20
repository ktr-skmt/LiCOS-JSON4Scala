package protocol.element

import licos.protocol.element.auth.AuthMessageProtocol
import licos.protocol.element.lobby.LobbyMessageProtocol
import licos.protocol.element.village.VillageMessageProtocol
import play.api.libs.json.JsValue

abstract class TestProtocol(val text: String)

final case class AuthMessageTestProtocol(override val text: String)
    extends TestProtocol(text)
    with AuthMessageProtocol {
  override def toJsonOpt: Option[JsValue] = Option.empty[JsValue]
}

final case class LobbyMessageTestProtocol(override val text: String)
    extends TestProtocol(text)
    with LobbyMessageProtocol {
  override def toJsonOpt: Option[JsValue] = Option.empty[JsValue]
}

final case class VillageMessageTestProtocol(override val text: String)
    extends TestProtocol(text)
    with VillageMessageProtocol {
  override def toJsonOpt: Option[JsValue] = Option.empty[JsValue]
}
