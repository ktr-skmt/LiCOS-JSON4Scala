package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.server2client.JsonGetAvatarInfo
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for getting an avatar information.
  *
  * @author Kotaro Sakamoto
  */
trait GetAvatarInfoAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param getAvatarInfo a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, getAvatarInfo: JsonGetAvatarInfo): Either[JsValue, JsValue]
}

object GetAvatarInfoAnalysisEngine {

  /** Get-avatar-info analysis engine name.
    */
  val name:         String  = "lobby.client2server.GetAvatarInfoAnalysisEngine"
  val isFromServer: Boolean = false

}
