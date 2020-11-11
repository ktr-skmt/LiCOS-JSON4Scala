package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonChangeLanguage
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for changing a language.
  */
trait ChangeLanguageAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param changeLanguage a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, changeLanguage: JsonChangeLanguage): Either[JsValue, JsValue]
}

object ChangeLanguageAnalysisEngine {

  /**  Change-language analysis engine name.
    */
  val name:         String  = "lobby.client2server.ChangeLanguageAnalysisEngine"
  val isFromServer: Boolean = false

}
