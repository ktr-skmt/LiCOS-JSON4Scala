package licos.json.engine.analysis.lobby.server2client

import licos.json.element.lobby.JsonAvatarInfo
import licos.json.engine.BOX
import play.api.libs.json.JsValue

/** The analysis engine for an avatar information.
  *
  * @author Kotaro Sakamoto
  */
trait AvatarInfoAnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param avatarInfo a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, avatarInfo: JsonAvatarInfo): Option[JsValue]
}
