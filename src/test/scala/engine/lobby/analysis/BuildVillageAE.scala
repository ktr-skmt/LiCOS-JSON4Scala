package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.BuildVillage
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.BuildVillageAnalysisEngine
import licos.json.lobby.JsonBuildVillage
import play.api.libs.json.{JsValue, Json}

class BuildVillageAE extends BuildVillageAnalysisEngine {
  override def process(box: BOX, buildVillage: JsonBuildVillage): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(BuildVillage.`type`)))
      case _ => None
    }
  }
}
