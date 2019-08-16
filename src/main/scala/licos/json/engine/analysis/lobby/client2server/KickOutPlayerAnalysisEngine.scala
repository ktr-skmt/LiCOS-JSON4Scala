package licos.json.engine.analysis.lobby.client2server

import licos.json.element.lobby.JsonKickOutPlayer
import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import play.api.libs.json.JsValue

/** The analysis engine for kicking out a player.
  *
  * @author Kotaro Sakamoto
  */
trait KickOutPlayerAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param kickOutPlayer a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, kickOutPlayer: JsonKickOutPlayer): Option[JsValue]
}
