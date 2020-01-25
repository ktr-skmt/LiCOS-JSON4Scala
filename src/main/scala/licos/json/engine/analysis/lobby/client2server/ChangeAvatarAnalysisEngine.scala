package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonChangeAvatar
import licos.json.engine.BOX
import play.api.libs.json.JsValue

/** The analysis engine for changing avatar.
  *
  * @author Kotaro Sakamoto
  */
trait ChangeAvatarAnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param changeAvatar a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, changeAvatar: JsonChangeAvatar): Either[JsValue, JsValue]
}

object ChangeAvatarAnalysisEngine {

  /**
    * Change-avatar analysis engine name.
    */
  val name:         String  = "lobby.client2server.ChangeAvatarAnalysisEngine"
  val isFromServer: Boolean = false
}
