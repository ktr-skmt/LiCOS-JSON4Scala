package json.engine.lobby.analysis

import json.engine.lobby.LobbyBox
import json.engine.lobby.example.SelectVillage
import json.element.JsonTest
import licos.json.element.lobby.client2server.JsonSelectVillage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.SelectVillageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class SelectVillageAE extends SelectVillageAnalysisEngine {
  override def process(box: BOX, selectVillage: JsonSelectVillage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(SelectVillage.`type`)))
      case _ => Left(Json.toJson(selectVillage))
    }
  }
}
