package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonChangeLang
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for changing a language.
  *
  */
trait ChangeLangAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param changeLang a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, changeLang: JsonChangeLang): Either[JsValue, JsValue]
}

object ChangeLangAnalysisEngine {

  /**
    *  Change-lang analysis engine name.
    */
  val name:         String  = "lobby.client2server.ChangeLangAnalysisEngine"
  val isFromServer: Boolean = false

}
