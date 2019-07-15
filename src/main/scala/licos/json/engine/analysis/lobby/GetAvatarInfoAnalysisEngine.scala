package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.engine.analysis.AnalysisEngine
import licos.json.lobby.JsonGetAvatarInfo
import play.api.libs.json.JsValue

trait GetAvatarInfoAnalysisEngine extends AnalysisEngine {
  def process(box: BOX, getAvatarInfo: JsonGetAvatarInfo): Option[JsValue]
}
