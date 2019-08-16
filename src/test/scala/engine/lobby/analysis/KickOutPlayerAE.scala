package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.KickOutPlayer
import element.JsonTest
import licos.json.element.lobby.JsonKickOutPlayer
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.KickOutPlayerAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class KickOutPlayerAE extends KickOutPlayerAnalysisEngine {
  override def process(box: BOX, kickOutPlayer: JsonKickOutPlayer): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(KickOutPlayer.`type`)))
      case _ => None
    }
  }
}
