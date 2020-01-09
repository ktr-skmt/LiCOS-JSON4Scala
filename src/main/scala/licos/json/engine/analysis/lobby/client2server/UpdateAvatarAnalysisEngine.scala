package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonUpdateAvatar
import licos.json.engine.BOX
import play.api.libs.json.JsValue

/** The analysis engine for updating avatar.
  *
  * @author Kotaro Sakamoto
  */
trait UpdateAvatarAnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param updateAvatar a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, updateAvatar: JsonUpdateAvatar): Either[JsValue, JsValue]
}

object UpdateAvatarAnalysisEngine {

  /**
    * Update-avatar analysis engine name.
    */
  val name:         String  = "lobby.client2server.UpdateAvatarAnalysisEngine"
  val isFromServer: Boolean = false
}
