package licos.json.engine.analysis.village

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.village.JsonAudienceChat
import play.api.libs.json.JsValue

/** The analysis engine for an audience chat.
  *
  * @author Kotaro Sakamoto
  */
trait AudienceChatAnalysisEngine extends AnalysisEngine {

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param audienceChat a JSON message.
    * @return a play.api.libs.json.JsValue option.
    */
  def process(box: BOX, audienceChat: JsonAudienceChat): Option[JsValue]
}
