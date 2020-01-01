package licos.json.engine.processing

import com.typesafe.scalalogging.Logger
import licos.json.element.lobby.client2server.{JsonBuildVillage, JsonLeaveWaitingPage, JsonReady}
import licos.json.engine.BOX
import licos.json.engine.analysis.lobby.client2server._
import licos.json.engine.analysis.village
import licos.json.engine.analysis.village.client2server._
import licos.json.engine.analysis.village.server2client._
import licos.json.flow.{FlowController, VillageFlowController}
import licos.json.element.village._
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
import licos.json.engine.analysis.village.{client2server, server2client}
import play.api.libs.json.{JsValue, Json}

/** This class implements the processing engine that aggregates and runs analysis engines for village.
  *
  * @param readyEngine the analysis engine for Ready JSON.
  * @param receivedChatMessageEngine the analysis engine for Received-player-message JSON.
  * @param receivedSystemMessageEngine the analysis engine for Received-system-message JSON.
  * @param receivedFlavorTextMessageEngine the analysis engine for Flavor-text-message JSON.
  * @param chatFromClientEngine the analysis engine for Chat-from-client JSON.
  * @param chatFromServerEngine the analysis engine for Chat-from-server JSON.
  * @param onymousAudienceChatFromClientEngine the analysis engine for Onymous-audience-chat-from-client JSON.
  * @param onymousAudienceChatFromServerEngine the analysis engine for Onymous-audience-chat-from-server JSON.
  * @param anonymousAudienceChatFromClientEngine the analysis engine for Anonymous-audience-chat-from-client JSON.
  * @param anonymousAudienceChatFromServerEngine the analysis engine for Anonymous-audience-chat-from-server JSON.
  * @param boardEngine the analysis engine for Board JSON.
  * @param onymousAudienceBoardEngine the analysis engine for Onymous-audience-board JSON.
  * @param voteEngine the analysis engine for Vote JSON.
  * @param scrollEngine the analysis engine for Scroll JSON.
  * @param onymousAudienceScrollEngine the analysis engine for Onymous-audience-scroll JSON.
  * @param starEngine the analysis engine for Star JSON.
  * @param phaseEngine the analysis engine for Phase JSON.
  * @param flavorTextEngine the analysis engine for Flavor-text JSON.
  * @param gameResultEngine the analysis engine for Game-result JSON.
  * @param buildVillageEngine the analysis engine for Build-village JSON.
  * @param leaveWaitingPageEngine the analysis engine for Leave-waiting-page JSON.
  * @param errorFromClientEngine the analysis engine for Error-from-client JSON.
  * @param errorFromServerEngine the analysis engine for Error-from-server JSON.
  * @author Kotaro Sakamoto
  */
final class VillageProcessingEngine(
    readyEngine:                           Option[ReadyAnalysisEngine],
    receivedChatMessageEngine:             Option[ReceivedChatMessageAnalysisEngine],
    receivedSystemMessageEngine:           Option[ReceivedSystemMessageAnalysisEngine],
    receivedFlavorTextMessageEngine:       Option[ReceivedFlavorTextMessageAnalysisEngine],
    chatFromClientEngine:                  Option[village.client2server.ChatAnalysisEngine],
    chatFromServerEngine:                  Option[village.server2client.ChatAnalysisEngine],
    onymousAudienceChatFromClientEngine:   Option[village.client2server.OnymousAudienceChatAnalysisEngine],
    onymousAudienceChatFromServerEngine:   Option[village.server2client.OnymousAudienceChatAnalysisEngine],
    anonymousAudienceChatFromClientEngine: Option[village.client2server.AnonymousAudienceChatAnalysisEngine],
    anonymousAudienceChatFromServerEngine: Option[village.server2client.AnonymousAudienceChatAnalysisEngine],
    boardEngine:                           Option[BoardAnalysisEngine],
    onymousAudienceBoardEngine:            Option[OnymousAudienceBoardAnalysisEngine],
    voteEngine:                            Option[VoteAnalysisEngine],
    scrollEngine:                          Option[ScrollAnalysisEngine],
    onymousAudienceScrollEngine:           Option[OnymousAudienceScrollAnalysisEngine],
    starEngine:                            Option[StarAnalysisEngine],
    phaseEngine:                           Option[PhaseAnalysisEngine],
    flavorTextEngine:                      Option[FlavorTextAnalysisEngine],
    gameResultEngine:                      Option[GameResultAnalysisEngine],
    buildVillageEngine:                    Option[BuildVillageAnalysisEngine],
    leaveWaitingPageEngine:                Option[LeaveWaitingPageAnalysisEngine],
    nextGameInvitationEngine:              Option[NextGameInvitationAnalysisEngine],
    nextGameInvitationIsClosedEngine:      Option[NextGameInvitationIsClosedAnalysisEngine],
    errorFromClientEngine:                 Option[village.client2server.ErrorAnalysisEngine],
    errorFromServerEngine:                 Option[village.server2client.ErrorAnalysisEngine]
) extends ProcessingEngine {

  override protected val flowController: FlowController = new VillageFlowController()

  private final val logger: Logger = Logger[VillageProcessingEngine]

  /** Returns a play.api.libs.json.JsValue response from a JSON message.
    *
    * @param box a box.
    * @param msg a JSON message.
    * @return a play.api.libs.json.JsValue Either. Right(json: JsValue) if succeeded, Left(error: JsValue) if failed.
    */
  override def process(box: BOX, msg: String): Either[JsValue, JsValue] = {

    val jsValue: JsValue = Json.parse(msg)

    def log(label: String): Unit = {
      val format: String = "process %s"
      logger.info(format.format(label))
    }

    @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
    def noAnalysisEngine(name: String, isFromServer: Boolean): Either[JsValue, JsValue] = {
      Left(
        Json.toJson(
          new JsonSubError(
            new JsonName(
              en = s"No $name is set. Please set it to the processing engine.",
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None
            ),
            "warning",
            "Nothing",
            isFromServer
          )
        )
      )
    }

    @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
    def otherwise: Either[JsValue, JsValue] = {
      Right(
        Json.toJson(
          new JsonSubError(
            new JsonName(
              en = "VillageProcessingEngine returns nothing",
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None,
              None
            ),
            "warning",
            jsValue.toString,
            isFromServer = true
          )
        )
      )
    }

    flowController.flow(jsValue) match {
      case Right(ready: JsonReady) =>
        log("JsonReady")
        readyEngine match {
          case Some(engine) =>
            engine.process(box, ready)
          case None =>
            noAnalysisEngine(ReadyAnalysisEngine.name, ReadyAnalysisEngine.isFromServer)
        }
      case Right(receivedChatMessage: JsonReceivedChatMessage) =>
        log("JsonReceivedChatMessage")
        receivedChatMessageEngine match {
          case Some(engine) =>
            engine.process(box, receivedChatMessage)
          case None =>
            noAnalysisEngine(ReceivedChatMessageAnalysisEngine.name, ReceivedChatMessageAnalysisEngine.isFromServer)
        }
      case Right(receivedSystemMessage: JsonReceivedSystemMessage) =>
        log("JsonReceivedSystemMessage")
        receivedSystemMessageEngine match {
          case Some(engine) =>
            engine.process(box, receivedSystemMessage)
          case None =>
            noAnalysisEngine(ReceivedSystemMessageAnalysisEngine.name, ReceivedSystemMessageAnalysisEngine.isFromServer)
        }
      case Right(receivedFlavorTextMessage: JsonReceivedFlavorTextMessage) =>
        log("JsonReceivedFlavorTestMessage")
        receivedFlavorTextMessageEngine match {
          case Some(engine) =>
            engine.process(box, receivedFlavorTextMessage)
          case None =>
            noAnalysisEngine(
              ReceivedFlavorTextMessageAnalysisEngine.name,
              ReceivedFlavorTextMessageAnalysisEngine.isFromServer
            )
        }
      case Right(chatFromClient: JsonChatFromClient) =>
        log("JsonChatFromClient")
        chatFromClientEngine match {
          case Some(engine) =>
            engine.process(box, chatFromClient)
          case None =>
            noAnalysisEngine(client2server.ChatAnalysisEngine.name, client2server.ChatAnalysisEngine.isFromServer)
        }
      case Right(chatFromServer: JsonChatFromServer) =>
        log("JsonChatFromServer")
        chatFromServerEngine match {
          case Some(engine) =>
            engine.process(box, chatFromServer)
          case None =>
            noAnalysisEngine(server2client.ChatAnalysisEngine.name, server2client.ChatAnalysisEngine.isFromServer)
        }
      case Right(onymousAudienceChat: JsonOnymousAudienceChat) =>
        log("JsonOnymousAudienceChat")
        if (onymousAudienceChat.isFromServer) {
          log("JsonOnymousAudienceChatFromServer")
          onymousAudienceChatFromServerEngine match {
            case Some(engine) =>
              engine.process(box, onymousAudienceChat)
            case None =>
              noAnalysisEngine(
                server2client.OnymousAudienceChatAnalysisEngine.name,
                server2client.OnymousAudienceChatAnalysisEngine.isFromServer
              )
          }
        } else {
          log("JsonOnymousAudienceChatFromClient")
          onymousAudienceChatFromClientEngine match {
            case Some(engine) =>
              engine.process(box, onymousAudienceChat)
            case None =>
              noAnalysisEngine(
                client2server.OnymousAudienceChatAnalysisEngine.name,
                client2server.OnymousAudienceChatAnalysisEngine.isFromServer
              )
          }
        }
      case Right(anonymousAudienceChat: JsonAnonymousAudienceChat) =>
        log("JsonAnonymousAudienceChat")
        if (anonymousAudienceChat.isFromServer) {
          log("JsonAnonymousAudienceChatFromServer")
          anonymousAudienceChatFromServerEngine match {
            case Some(engine) =>
              engine.process(box, anonymousAudienceChat)
            case None =>
              noAnalysisEngine(
                server2client.AnonymousAudienceChatAnalysisEngine.name,
                client2server.AnonymousAudienceChatAnalysisEngine.isFromServer
              )
          }
        } else {
          log("JsonAnonymousAudienceChatFromClient")
          anonymousAudienceChatFromClientEngine match {
            case Some(engine) =>
              engine.process(box, anonymousAudienceChat)
            case None =>
              noAnalysisEngine(
                client2server.AnonymousAudienceChatAnalysisEngine.name,
                client2server.AnonymousAudienceChatAnalysisEngine.isFromServer
              )
          }
        }
      case Right(board: JsonBoard) =>
        log("JsonBoard")
        boardEngine match {
          case Some(engine) =>
            engine.process(box, board)
          case None =>
            noAnalysisEngine(BoardAnalysisEngine.name, BoardAnalysisEngine.isFromServer)
        }
      case Right(onymousAudienceBoard: JsonOnymousAudienceBoard) =>
        log("JsonOnymousAudienceBoard")
        onymousAudienceBoardEngine match {
          case Some(engine) =>
            engine.process(box, onymousAudienceBoard)
          case None =>
            noAnalysisEngine(OnymousAudienceBoardAnalysisEngine.name, OnymousAudienceBoardAnalysisEngine.isFromServer)
        }
      case Right(vote: JsonVote) =>
        log("JsonVote")
        voteEngine match {
          case Some(engine) =>
            engine.process(box, vote)
          case None =>
            noAnalysisEngine(VoteAnalysisEngine.name, VoteAnalysisEngine.isFromServer)
        }
      case Right(scroll: JsonScroll) =>
        log("JsonScroll")
        scrollEngine match {
          case Some(engine) =>
            engine.process(box, scroll)
          case None =>
            noAnalysisEngine(ScrollAnalysisEngine.name, ScrollAnalysisEngine.isFromServer)
        }
      case Right(onymousAudienceScroll: JsonOnymousAudienceScroll) =>
        log("JsonOnymousAudienceScroll")
        onymousAudienceScrollEngine match {
          case Some(engine) =>
            engine.process(box, onymousAudienceScroll)
          case None =>
            noAnalysisEngine(OnymousAudienceScrollAnalysisEngine.name, OnymousAudienceScrollAnalysisEngine.isFromServer)
        }
      case Right(star: JsonStar) =>
        log("JsonStar")
        starEngine match {
          case Some(engine) =>
            engine.process(box, star)
          case None =>
            noAnalysisEngine(StarAnalysisEngine.name, StarAnalysisEngine.isFromServer)
        }
      case Right(phase: JsonPhase) =>
        log("JsonPhase")
        phaseEngine match {
          case Some(engine) =>
            engine.process(box, phase)
          case None =>
            noAnalysisEngine(PhaseAnalysisEngine.name, PhaseAnalysisEngine.isFromServer)
        }
      case Right(flavorText: JsonFlavorText) =>
        log("JsonFlavorText")
        flavorTextEngine match {
          case Some(engine) =>
            engine.process(box, flavorText)
          case None =>
            noAnalysisEngine(FlavorTextAnalysisEngine.name, FlavorTextAnalysisEngine.isFromServer)
        }
      case Right(gameResult: JsonGameResult) =>
        log("JsonGameResult")
        gameResultEngine match {
          case Some(engine) =>
            engine.process(box, gameResult)
          case None =>
            noAnalysisEngine(GameResultAnalysisEngine.name, GameResultAnalysisEngine.isFromServer)
        }
      case Right(buildVillage: JsonBuildVillage) =>
        log("JsonBuildVillage")
        buildVillageEngine match {
          case Some(engine) =>
            engine.process(box, buildVillage)
          case None =>
            noAnalysisEngine(BuildVillageAnalysisEngine.name, BuildVillageAnalysisEngine.isFromServer)
        }
      case Right(leaveWaitingPage: JsonLeaveWaitingPage) =>
        log("JsonLeaveWaitingPage")
        leaveWaitingPageEngine match {
          case Some(engine) =>
            engine.process(box, leaveWaitingPage)
          case None =>
            noAnalysisEngine(LeaveWaitingPageAnalysisEngine.name, LeaveWaitingPageAnalysisEngine.isFromServer)
        }
      case Right(nextGameInvitation: JsonNextGameInvitation) =>
        log("JsonNextGameInvitation")
        nextGameInvitationEngine match {
          case Some(engine) =>
            engine.process(box, nextGameInvitation)
          case None =>
            noAnalysisEngine(NextGameInvitationAnalysisEngine.name, NextGameInvitationAnalysisEngine.isFromServer)
        }
      case Right(nextGameInvitationIsClosed: JsonNextGameInvitationIsClosed) =>
        log("JsonNextGameInvitationIsClosed")
        nextGameInvitationIsClosedEngine match {
          case Some(engine) =>
            engine.process(box, nextGameInvitationIsClosed)
          case None =>
            noAnalysisEngine(
              NextGameInvitationIsClosedAnalysisEngine.name,
              NextGameInvitationIsClosedAnalysisEngine.isFromServer
            )
        }
      case Right(error: JsonError) =>
        log("JsonError")
        if (error.isFromServer) {
          log("JsonErrorFromServer")
          errorFromServerEngine match {
            case Some(engine) =>
              engine.process(box, error)
            case None =>
              noAnalysisEngine(server2client.ErrorAnalysisEngine.name, server2client.ErrorAnalysisEngine.isFromServer)
          }
        } else {
          log("JsonErrorFromClient")
          errorFromClientEngine match {
            case Some(engine) =>
              engine.process(box, error)
            case None =>
              noAnalysisEngine(client2server.ErrorAnalysisEngine.name, client2server.ErrorAnalysisEngine.isFromServer)
          }
        }
      case _ =>
        log("return nothing")
        otherwise
    }
  }
}
