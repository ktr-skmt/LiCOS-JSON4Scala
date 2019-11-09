package engine.village.analysis.client2server

import engine.lobby.example.BuildVillage
import engine.village.VillageBox
import element.JsonTest
import licos.json.element.lobby.JsonBuildVillage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.BuildVillageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class BuildVillageAE extends BuildVillageAnalysisEngine {
  override def process(box: BOX, buildVillage: JsonBuildVillage): Either[JsValue, JsValue] = {
    box match {
      case _: VillageBox => Right(Json.toJson(JsonTest(BuildVillage.`type`)))
      case _ => Left(Json.toJson(buildVillage))
    }
  }
}
