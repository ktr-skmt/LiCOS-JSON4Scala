package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.SelectVillage
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.SelectVillageAnalysisEngine
import licos.json.lobby.JsonSelectVillage
import play.api.libs.json.{JsValue, Json}

class SelectVillageAE extends SelectVillageAnalysisEngine {
  override def process(box: BOX, selectVillage: JsonSelectVillage): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(SelectVillage.`type`)))
      case _ => None
    }
  }
}
