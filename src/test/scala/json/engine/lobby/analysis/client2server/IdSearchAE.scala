package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.IdSearch
import licos.json.element.lobby.client2server.JsonIdSearch
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.IdSearchAnalysisEngine
import play.api.libs.json.{JsValue, Json}

class IdSearchAE extends IdSearchAnalysisEngine {
  override def process(box: BOX, idSearch: JsonIdSearch): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(IdSearch.`type`)))
      case _ => Left(Json.toJson(idSearch))
    }
  }
}
