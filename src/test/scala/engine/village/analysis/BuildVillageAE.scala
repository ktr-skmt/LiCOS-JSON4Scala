package engine.village.analysis

import engine.village.Box
import entity.JsonTest
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.BuildVillageAnalysisEngine
import licos.json.lobby.JsonBuildVillage
import play.api.libs.json.{JsValue, Json}

class BuildVillageAE extends BuildVillageAnalysisEngine {
  override def process(box: BOX, buildVillage: JsonBuildVillage): Option[JsValue] = {
    box match {
      case _: Box => Option(Json.toJson(JsonTest("BuildVillage")))
      case _ => None
    }
  }
}