package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonEnterAvatarSelectionPage
import licos.json.engine.BOX
import play.api.libs.json.JsValue

/** The analysis engine for entering an avatar selection page.
  *
  * @author Kotaro Sakamoto
  */
trait EnterAvatarSelectionPageAnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param enterAvatarSelectionPage a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, enterAvatarSelectionPage: JsonEnterAvatarSelectionPage): Either[JsValue, JsValue]
}

object EnterAvatarSelectionPageAnalysisEngine {

  /**
    * Enter-avatar-selection-page analysis engine name.
    */
  val name:         String  = "lobby.client2server.EnterAvatarSelectionPageAnalysisEngine"
  val isFromServer: Boolean = false
}
