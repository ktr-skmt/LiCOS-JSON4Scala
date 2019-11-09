package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.JsonAvatarInfo
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for an avatar information.
  *
  * @author Kotaro Sakamoto
  */
trait AvatarInfoAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param avatarInfo a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, avatarInfo: JsonAvatarInfo): Either[JsValue, JsValue]
}

object AvatarInfoAnalysisEngine {

  /**
    * Avatar-info analysis engine name.
    */
  val name:         String  = "lobby.server2client.AvatarInfoAnalysisEngine"
  val isFromServer: Boolean = true

}
