package json.engine.lobby.analysis.client2server

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.client2server.EnterAvatarSelectionPage
import licos.json.element.lobby.client2server.JsonEnterAvatarSelectionPage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server.EnterAvatarSelectionPageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class EnterAvatarSelectionPageAE extends EnterAvatarSelectionPageAnalysisEngine {
  override def process(box: BOX, enterAvatarSelectionPage: JsonEnterAvatarSelectionPage): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(EnterAvatarSelectionPage.`type`)))
      case _ => Left(Json.toJson(enterAvatarSelectionPage))
    }
  }
}
