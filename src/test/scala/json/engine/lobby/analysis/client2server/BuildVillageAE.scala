package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.BuildVillage
import licos.json.element.lobby.client2server.JsonBuildVillage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.BuildVillageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class BuildVillageAE extends BuildVillageAnalysisEngine {

  override def process(box: BOX, buildVillage: JsonBuildVillage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(BuildVillage.`type`)))
      case _ => Left(Json.toJson(buildVillage))
    }
  }

}
