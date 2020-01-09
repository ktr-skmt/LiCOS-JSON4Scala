package json.engine.lobby.analysis.server2client

import json.element.JsonTest
import json.engine.lobby.LobbyBox
import json.engine.lobby.example.server2client.OnymousAudienceSelectionPage
import licos.json.element.lobby.server2client.JsonOnymousAudienceSelectionPage
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.server2client.OnymousAudienceSelectionPageAnalysisEngine
import play.api.libs.json.{JsValue, Json}

final class OnymousAudienceSelectionPageAE extends OnymousAudienceSelectionPageAnalysisEngine {
  override def process(
      box:                          BOX,
      onymousAudienceSelectionPage: JsonOnymousAudienceSelectionPage
  ): Either[JsValue, JsValue] = {
    box match {
      case _: LobbyBox => Right(Json.toJson(JsonTest(OnymousAudienceSelectionPage.`type`)))
      case _ => Left(Json.toJson(onymousAudienceSelectionPage))
    }
  }
}
