package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonDeleteAvatar
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for deleting avatars.
  *
  * @author Kotaro Sakamoto
  */
trait DeleteAvatarAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param deleteAvatar a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, deleteAvatar: JsonDeleteAvatar): Either[JsValue, JsValue]
}

object DeleteAvatarAnalysisEngine {

  /**
    * Delete-avatar analysis engine name.
    */
  val name:         String  = "lobby.client2server.DeleteAvatarAnalysisEngine"
  val isFromServer: Boolean = false
}
