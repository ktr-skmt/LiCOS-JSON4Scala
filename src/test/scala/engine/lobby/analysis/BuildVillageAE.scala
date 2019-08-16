package engine.lobby.analysis

import engine.lobby.LobbyBox
import engine.lobby.example.BuildVillage
import element.JsonTest
import licos.json.element.lobby.JsonBuildVillage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.BuildVillageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class BuildVillageAE extends BuildVillageAnalysisEngine {

  override def process(box: BOX, buildVillage: JsonBuildVillage): Option[JsValue] = {
    box match {
      case _: LobbyBox => Option(Json.toJson(JsonTest(BuildVillage.`type`)))
      case _ => None
    }
  }

}