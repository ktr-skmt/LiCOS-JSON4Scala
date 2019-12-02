package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonSelectVillage
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for selecting a village.
  *
  * @author Kotaro Sakamoto
  */
trait SelectVillageAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param selectVillage a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, selectVillage: JsonSelectVillage): Either[JsValue, JsValue]

}

object SelectVillageAnalysisEngine {

  /**
    * Select-village analysis engine name.
    */
  val name:         String  = "lobby.client2server.SelectVillageAnalysisEngine"
  val isFromServer: Boolean = false

}
