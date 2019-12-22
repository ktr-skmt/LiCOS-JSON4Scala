package licos.json2protocol.village

import licos.entity.VillageInfoFromLobby
import licos.json.element.lobby.client2server.{JsonBuildVillage, JsonLeaveWaitingPage, JsonReady}
import licos.json.element.village.{JsonAnonymousAudienceChat, JsonError, JsonOnymousAudienceChat}
import licos.json.element.village.client2server.{
  JsonBoard,
  JsonChatFromClient,
  JsonOnymousAudienceBoard,
  JsonOnymousAudienceScroll,
  JsonScroll,
  JsonStar,
  JsonVote
}
import licos.json.element.village.invite.{JsonNextGameInvitation, JsonNextGameInvitationIsClosed}
import licos.json.element.village.receipt.{
  JsonReceivedChatMessage,
  JsonReceivedFlavorTextMessage,
  JsonReceivedSystemMessage
}
import licos.json.element.village.server2client.{JsonChatFromServer, JsonFlavorText, JsonGameResult, JsonPhase}
import licos.json.flow.{FlowController, VillageFlowController}
import licos.json2protocol.Json2Protocol
import licos.knowledge.{Morning, Night, Noon, PostMortemDiscussion}
import licos.protocol.element.Protocol
import licos.protocol.element.village.client2server.{
  AnonymousAudienceChatFromClientProtocol,
  BoardProtocol,
  BuildVillageProtocol,
  ChatFromClientProtocol,
  ErrorFromClientProtocol,
  LeaveWaitingPageProtocol,
  OnymousAudienceBoardProtocol,
  OnymousAudienceChatFromClientProtocol,
  OnymousAudienceScrollProtocol,
  ReadyProtocol,
  ReceivedChatMessageProtocol,
  ReceivedFlavorTextMessageProtocol,
  ReceivedSystemMessageProtocol,
  ScrollProtocol,
  StarProtocol,
  VoteProtocol
}
import licos.protocol.element.village.server2client.{
  AnonymousAudienceChatFromServerProtocol,
  ChatFromServerProtocol,
  ErrorFromServerProtocol,
  FirstMorningPhaseProtocol,
  FlavorTextProtocol,
  GameResultProtocol,
  MorningPhaseProtocol,
  NextGameInvitationIsClosedProtocol,
  NextGameInvitationProtocol,
  NightPhaseProtocol,
  NoonPhaseProtocol,
  OnymousAudienceChatFromServerProtocol,
  PostMortemDiscussionProtocol
}
import play.api.libs.json.JsValue

object Json2VillageMessageProtocol extends Json2Protocol {
  override protected val flowController: FlowController = new VillageFlowController()

  def toProtocolOpt(json: JsValue, villageInfoFromLobby: VillageInfoFromLobby): Option[Protocol] = {
    flowController.flow(json) match {
      case Right(json: JsonAnonymousAudienceChat) =>
        if (json.isFromServer) {
          AnonymousAudienceChatFromServerProtocol.read(json, villageInfoFromLobby)
        } else {
          AnonymousAudienceChatFromClientProtocol.read(json, villageInfoFromLobby)
        }
      case Right(json: JsonBoard) =>
        BoardProtocol.read(json, villageInfoFromLobby)
      case Right(json: JsonBuildVillage) =>
        BuildVillageProtocol.read(json, villageInfoFromLobby)
      case Right(json: JsonChatFromClient) =>
        ChatFromClientProtocol.read(json, villageInfoFromLobby)
      case Right(json: JsonError) =>
        if (json.isFromServer) {
          ErrorFromServerProtocol.read(json, villageInfoFromLobby)
        } else {
          ErrorFromClientProtocol.read(json, villageInfoFromLobby)
        }
      case Right(json: JsonLeaveWaitingPage) =>
        LeaveWaitingPageProtocol.read(json)
      case Right(json: JsonOnymousAudienceBoard) =>
        OnymousAudienceBoardProtocol.read(json, villageInfoFromLobby)
      case Right(json: JsonOnymousAudienceChat) =>
        if (json.isFromServer) {
          OnymousAudienceChatFromServerProtocol.read(json, villageInfoFromLobby)
        } else {
          OnymousAudienceChatFromClientProtocol.read(json, villageInfoFromLobby)
        }
      case Right(json: JsonOnymousAudienceScroll) =>
        OnymousAudienceScrollProtocol.read(json, villageInfoFromLobby)
      case Right(json: JsonReady) =>
        ReadyProtocol.read(json)
      case Right(json: JsonReceivedChatMessage) =>
        ReceivedChatMessageProtocol.read(json)
      case Right(json: JsonReceivedFlavorTextMessage) =>
        ReceivedFlavorTextMessageProtocol.read(json)
      case Right(json: JsonReceivedSystemMessage) =>
        ReceivedSystemMessageProtocol.read(json)
      case Right(json: JsonScroll) =>
        ScrollProtocol.read(json, villageInfoFromLobby)
      case Right(json: JsonStar) =>
        StarProtocol.read(json, villageInfoFromLobby)
      case Right(json: JsonVote) =>
        VoteProtocol.read(json, villageInfoFromLobby)
      case Right(json: JsonChatFromServer) =>
        ChatFromServerProtocol.read(json, villageInfoFromLobby)
      case Right(json: JsonPhase) =>
        json.base.phase match {
          case Morning.label =>
            import cats.implicits._
            if (json.base.day === 1) {
              FirstMorningPhaseProtocol.read(json, villageInfoFromLobby)
            } else {
              MorningPhaseProtocol.read(json, villageInfoFromLobby)
            }
          case Noon.label =>
            NoonPhaseProtocol.read(json, villageInfoFromLobby)
          case Night.label =>
            NightPhaseProtocol.read(json, villageInfoFromLobby)
          case PostMortemDiscussion.label =>
            PostMortemDiscussionProtocol.read(json, villageInfoFromLobby)
          case _ => None
        }
      case Right(json: JsonFlavorText) =>
        FlavorTextProtocol.read(json, villageInfoFromLobby)
      case Right(json: JsonGameResult) =>
        GameResultProtocol.read(json, villageInfoFromLobby)
      case Right(json: JsonNextGameInvitation) =>
        NextGameInvitationProtocol.read(json)
      case Right(json: JsonNextGameInvitationIsClosed) =>
        NextGameInvitationIsClosedProtocol.read(json)
      case _ => None
    }
  }
}
