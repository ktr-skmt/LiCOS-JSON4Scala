package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.client2server.JsonBuildVillage
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for building a village.
  *
  * @author Kotaro Sakamoto
  */
trait BuildVillageAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param buildVillage a JSON message.
    * @return either play.api.libs.json.JsValue.
    */
  def process(box: BOX, buildVillage: JsonBuildVillage): Either[JsValue, JsValue]
}

object BuildVillageAnalysisEngine {

  /**
    * Build-village analysis engine name.
    */
  val name:         String  = "lobby.client2server.BuildVillageAnalysisEngine"
  val isFromServer: Boolean = false

}
