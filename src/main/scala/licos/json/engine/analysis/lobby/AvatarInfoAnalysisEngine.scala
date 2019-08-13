package licos.json.engine.analysis.lobby

import licos.json.engine.BOX
import licos.json.lobby.JsonAvatarInfo
import play.api.libs.json.JsValue

trait AvatarInfoAnalysisEngine {
  def process(box: BOX, avatarInfo: JsonAvatarInfo): Option[JsValue]
}
