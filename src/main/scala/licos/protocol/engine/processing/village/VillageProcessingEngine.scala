package licos.protocol.engine.processing.village

import com.typesafe.scalalogging.Logger
import licos.json.element.lobby.client2server.{JsonBuildVillage, JsonLeaveWaitingPage, JsonReady}
import licos.json.element.village.client2server._
import licos.json.element.village.invite.{JsonNextGameInvitation, JsonNextGameInvitationIsClosed}
import licos.json.element.village.receipt.{
  JsonReceivedChatMessage,
  JsonReceivedFlavorTextMessage,
  JsonReceivedSystemMessage
}
import licos.json.element.village.server2client.{JsonChatFromServer, JsonFlavorText, JsonGameResult, JsonPhase}
import licos.json.element.village.{JsonAnonymousAudienceChat, JsonError, JsonOnymousAudienceChat}
import licos.json.flow.{FlowController, VillageFlowController}
import licos.knowledge.{Morning, Night, Noon, PostMortemDiscussion}
import licos.protocol.element.village.VillageMessageProtocol
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
import licos.protocol.engine.analysis.village.client2server._
import licos.protocol.engine.analysis.village.server2client._
import licos.protocol.engine.processing.{JSON2ProtocolException, NoEngineException, ProcessingEngine}
import play.api.libs.json.{JsValue, Json}

import scala.util.{Failure, Try}

class VillageProcessingEngine(
    anonymousAudienceChatFromClientAnalysisEngine: Option[AnonymousAudienceChatFromClientAnalysisEngine],
    boardAnalysisEngine:                           Option[BoardAnalysisEngine],
    buildVillageAnalysisEngine:                    Option[BuildVillageAnalysisEngine],
    chatFromClientAnalysisEngine:                  Option[ChatFromClientAnalysisEngine],
    errorFromClientAnalysisEngine:                 Option[ErrorFromClientAnalysisEngine],
    leaveWaitingPageAnalysisEngine:                Option[LeaveWaitingPageAnalysisEngine],
    onymousAudienceBoardAnalysisEngine:            Option[OnymousAudienceBoardAnalysisEngine],
    onymousAudienceChatFromClientAnalysisEngine:   Option[OnymousAudienceChatFromClientAnalysisEngine],
    onymousAudienceScrollAnalysisEngine:           Option[OnymousAudienceScrollAnalysisEngine],
    readyAnalysisEngine:                           Option[ReadyAnalysisEngine],
    receivedChatMessageAnalysisEngine:             Option[ReceivedChatMessageAnalysisEngine],
    receivedFlavorTextMessageAnalysisEngine:       Option[ReceivedFlavorTextMessageAnalysisEngine],
    receivedSystemMessageAnalysisEngine:           Option[ReceivedSystemMessageAnalysisEngine],
    scrollAnalysisEngine:                          Option[ScrollAnalysisEngine],
    starAnalysisEngine:                            Option[StarAnalysisEngine],
    voteAnalysisEngine:                            Option[VoteAnalysisEngine],
    anonymousAudienceChatFromServerAnalysisEngine: Option[AnonymousAudienceChatFromServerAnalysisEngine],
    chatFromServerAnalysisEngine:                  Option[ChatFromServerAnalysisEngine],
    errorFromServerAnalysisEngine:                 Option[ErrorFromServerAnalysisEngine],
    firstMorningPhaseAnalysisEngine:               Option[FirstMorningPhaseAnalysisEngine],
    flavorTextAnalysisEngine:                      Option[FlavorTextAnalysisEngine],
    gameResultAnalysisEngine:                      Option[GameResultAnalysisEngine],
    morningPhaseAnalysisEngine:                    Option[MorningPhaseAnalysisEngine],
    nextGameInvitationAnalysisEngine:              Option[NextGameInvitationAnalysisEngine],
    nextGameInvitationIsClosedAnalysisEngine:      Option[NextGameInvitationIsClosedAnalysisEngine],
    nightPhaseAnalysisEngine:                      Option[NightPhaseAnalysisEngine],
    noonPhaseAnalysisEngine:                       Option[NoonPhaseAnalysisEngine],
    onymousAudienceChatFromServerAnalysisEngine:   Option[OnymousAudienceChatFromServerAnalysisEngine],
    postMortemDiscussionAnalysisEngine:            Option[PostMortemDiscussionAnalysisEngine]
) extends ProcessingEngine {

  override protected val flowController: FlowController = new VillageFlowController()

  private final val logger: Logger = Logger[VillageProcessingEngine]

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  def process(box: VillageBOX, msg: String): Try[VillageMessageProtocol] = {

    val jsValue: JsValue = Json.parse(msg)

    def log(label: String): Unit = {
      val format: String = "process %s"
      logger.info(format.format(label))
    }

    flowController.flow(jsValue) match {
      case Right(json: JsonAnonymousAudienceChat) =>
        if (json.isFromServer) {
          log("JsonAnonymousAudienceChatFromServer")
          anonymousAudienceChatFromServerAnalysisEngine match {
            case Some(engine: AnonymousAudienceChatFromServerAnalysisEngine) =>
              log("AnonymousAudienceChatFromServerAnalysisEngine")
              AnonymousAudienceChatFromServerProtocol.read(json, box.villageInfoFromLobby) match {
                case Some(protocol: AnonymousAudienceChatFromServerProtocol) =>
                  engine.process(box, protocol)
                case None => Failure(new JSON2ProtocolException(AnonymousAudienceChatFromServerAnalysisEngine.name))
              }
            case None => Failure(new NoEngineException(AnonymousAudienceChatFromServerAnalysisEngine.name))
          }
        } else {
          log("JsonAnonymousAudienceChatFromClient")
          anonymousAudienceChatFromClientAnalysisEngine match {
            case Some(engine: AnonymousAudienceChatFromClientAnalysisEngine) =>
              log("AnonymousAudienceChatFromClientAnalysisEngine")
              AnonymousAudienceChatFromClientProtocol.read(json, box.villageInfoFromLobby) match {
                case Some(protocol: AnonymousAudienceChatFromClientProtocol) =>
                  engine.process(box, protocol)
                case None => Failure(new JSON2ProtocolException(AnonymousAudienceChatFromClientAnalysisEngine.name))
              }
            case None => Failure(new NoEngineException(AnonymousAudienceChatFromClientAnalysisEngine.name))
          }
        }
      case Right(json: JsonBoard) =>
        log("JsonBoard")
        boardAnalysisEngine match {
          case Some(engine: BoardAnalysisEngine) =>
            log("BoardAnalysisEngine")
            BoardProtocol.read(json, box.villageInfoFromLobby) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(BoardAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(BoardAnalysisEngine.name))
        }
      case Right(json: JsonBuildVillage) =>
        log("JsonBuildVillage")
        buildVillageAnalysisEngine match {
          case Some(engine: BuildVillageAnalysisEngine) =>
            log("BuildVillageAnalysisEngine")
            BuildVillageProtocol.read(json, box.villageInfoFromLobby) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(BuildVillageAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(BuildVillageAnalysisEngine.name))
        }
      case Right(json: JsonChatFromClient) =>
        log("JsonChatFromClient")
        chatFromClientAnalysisEngine match {
          case Some(engine: ChatFromClientAnalysisEngine) =>
            log("ChatFromClientAnalysisEngine")
            ChatFromClientProtocol.read(json, box.villageInfoFromLobby) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ChatFromClientAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ChatFromClientAnalysisEngine.name))
        }
      case Right(json: JsonError) =>
        if (json.isFromServer) {
          log("JsonErrorFromServer")
          errorFromServerAnalysisEngine match {
            case Some(engine: ErrorFromServerAnalysisEngine) =>
              log("ErrorFromServerAnalysisEngine")
              ErrorFromServerProtocol.read(json, box.villageInfoFromLobby) match {
                case Some(protocol) =>
                  engine.process(box, protocol)
                case None => Failure(new JSON2ProtocolException(ErrorFromServerAnalysisEngine.name))
              }
            case None => Failure(new NoEngineException(ErrorFromServerAnalysisEngine.name))
          }
        } else {
          log("JsonErrorFromClient")
          errorFromClientAnalysisEngine match {
            case Some(engine: ErrorFromClientAnalysisEngine) =>
              log("ErrorFromClientAnalysisEngine")
              ErrorFromClientProtocol.read(json, box.villageInfoFromLobby) match {
                case Some(protocol) =>
                  engine.process(box, protocol)
                case None => Failure(new JSON2ProtocolException(ErrorFromClientAnalysisEngine.name))
              }
            case None => Failure(new NoEngineException(ErrorFromClientAnalysisEngine.name))
          }
        }
      case Right(json: JsonLeaveWaitingPage) =>
        log("JsonLeaveWaitingPage")
        leaveWaitingPageAnalysisEngine match {
          case Some(engine: LeaveWaitingPageAnalysisEngine) =>
            log("LeaveWaitingPageAnalysisEngine")
            LeaveWaitingPageProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(LeaveWaitingPageAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(LeaveWaitingPageAnalysisEngine.name))
        }
      case Right(json: JsonOnymousAudienceBoard) =>
        log("JsonOnymousAudienceBoard")
        onymousAudienceBoardAnalysisEngine match {
          case Some(engine: OnymousAudienceBoardAnalysisEngine) =>
            log("OnymousAudienceBoardAnalysisEngine")
            OnymousAudienceBoardProtocol.read(json, box.villageInfoFromLobby) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(OnymousAudienceBoardAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(OnymousAudienceBoardAnalysisEngine.name))
        }
      case Right(json: JsonOnymousAudienceChat) =>
        if (json.isFromServer) {
          log("JsonOnymousAudienceChatFromServer")
          onymousAudienceChatFromServerAnalysisEngine match {
            case Some(engine: OnymousAudienceChatFromServerAnalysisEngine) =>
              log("OnymousAudienceChatFromServerAnalysisEngine")
              OnymousAudienceChatFromServerProtocol.read(json, box.villageInfoFromLobby) match {
                case Some(protocol) =>
                  engine.process(box, protocol)
                case None => Failure(new JSON2ProtocolException(OnymousAudienceChatFromServerAnalysisEngine.name))
              }
            case None => Failure(new NoEngineException(OnymousAudienceChatFromServerAnalysisEngine.name))
          }
        } else {
          log("JsonOnymousAudienceChatFromClient")
          onymousAudienceChatFromClientAnalysisEngine match {
            case Some(engine: OnymousAudienceChatFromClientAnalysisEngine) =>
              log("OnymousAudienceChatFromClientAnalysisEngine")
              OnymousAudienceChatFromClientProtocol.read(json, box.villageInfoFromLobby) match {
                case Some(protocol) =>
                  engine.process(box, protocol)
                case None => Failure(new JSON2ProtocolException(OnymousAudienceChatFromClientAnalysisEngine.name))
              }
            case None => Failure(new NoEngineException(OnymousAudienceChatFromClientAnalysisEngine.name))
          }
        }
      case Right(json: JsonOnymousAudienceScroll) =>
        log("JsonOnymousAudienceScroll")
        onymousAudienceScrollAnalysisEngine match {
          case Some(engine: OnymousAudienceScrollAnalysisEngine) =>
            log("OnymousAudienceScrollAnalysisEngine")
            OnymousAudienceScrollProtocol.read(json, box.villageInfoFromLobby) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(OnymousAudienceScrollAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(OnymousAudienceScrollAnalysisEngine.name))
        }
      case Right(json: JsonReady) =>
        log("JsonReady")
        readyAnalysisEngine match {
          case Some(engine: ReadyAnalysisEngine) =>
            log("ReadyAnalysisEngine")
            ReadyProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ReadyAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ReadyAnalysisEngine.name))
        }
      case Right(json: JsonReceivedChatMessage) =>
        log("JsonReceivedChatMessage")
        receivedChatMessageAnalysisEngine match {
          case Some(engine: ReceivedChatMessageAnalysisEngine) =>
            log("ReceivedChatMessageAnalysisEngine")
            ReceivedChatMessageProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ReceivedChatMessageAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ReceivedChatMessageAnalysisEngine.name))
        }
      case Right(json: JsonReceivedFlavorTextMessage) =>
        log("JsonReceivedFlavorTextMessage")
        receivedFlavorTextMessageAnalysisEngine match {
          case Some(engine: ReceivedFlavorTextMessageAnalysisEngine) =>
            log("ReceivedFlavorTextMessageAnalysisEngine")
            ReceivedFlavorTextMessageProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ReceivedFlavorTextMessageAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ReceivedFlavorTextMessageAnalysisEngine.name))
        }
      case Right(json: JsonReceivedSystemMessage) =>
        log("JsonReceivedSystemMessage")
        receivedSystemMessageAnalysisEngine match {
          case Some(engine: ReceivedSystemMessageAnalysisEngine) =>
            log("ReceivedSystemMessageAnalysisEngine")
            ReceivedSystemMessageProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ReceivedSystemMessageAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ReceivedSystemMessageAnalysisEngine.name))
        }
      case Right(json: JsonScroll) =>
        log("JsonScroll")
        scrollAnalysisEngine match {
          case Some(engine: ScrollAnalysisEngine) =>
            log("ScrollAnalysisEngine")
            ScrollProtocol.read(json, box.villageInfoFromLobby) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ScrollAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ScrollAnalysisEngine.name))
        }
      case Right(json: JsonStar) =>
        log("JsonStar")
        starAnalysisEngine match {
          case Some(engine: StarAnalysisEngine) =>
            log("StarAnalysisEngine")
            StarProtocol.read(json, box.villageInfoFromLobby) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(StarAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(StarAnalysisEngine.name))
        }
      case Right(json: JsonVote) =>
        log("JsonVote")
        voteAnalysisEngine match {
          case Some(engine: VoteAnalysisEngine) =>
            log("VoteAnalysisEngine")
            VoteProtocol.read(json, box.villageInfoFromLobby) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(VoteAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(VoteAnalysisEngine.name))
        }
      case Right(json: JsonChatFromServer) =>
        log("JsonChatFromServer")
        chatFromServerAnalysisEngine match {
          case Some(engine: ChatFromServerAnalysisEngine) =>
            log("ChatFromServerAnalysisEngine")
            ChatFromServerProtocol.read(json, box.villageInfoFromLobby) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ChatFromServerAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ChatFromServerAnalysisEngine.name))
        }
      case Right(json: JsonPhase) =>
        log("JsonPhase")
        json.base.phase match {
          case Morning.label =>
            import cats.implicits._
            if (json.base.day === 1) {
              firstMorningPhaseAnalysisEngine match {
                case Some(engine: FirstMorningPhaseAnalysisEngine) =>
                  log("FirstMorningPhaseAnalysisEngine")
                  FirstMorningPhaseProtocol.read(json, box.villageInfoFromLobby) match {
                    case Some(protocol) =>
                      engine.process(box, protocol)
                    case None => Failure(new JSON2ProtocolException(FirstMorningPhaseAnalysisEngine.name))
                  }
                case None => Failure(new NoEngineException(FirstMorningPhaseAnalysisEngine.name))
              }
            } else {
              morningPhaseAnalysisEngine match {
                case Some(engine: MorningPhaseAnalysisEngine) =>
                  log("MorningPhaseAnalysisEngine")
                  MorningPhaseProtocol.read(json, box.villageInfoFromLobby) match {
                    case Some(protocol) =>
                      engine.process(box, protocol)
                    case None => Failure(new JSON2ProtocolException(MorningPhaseAnalysisEngine.name))
                  }
                case None => Failure(new NoEngineException(MorningPhaseAnalysisEngine.name))
              }
            }
          case Noon.label =>
            noonPhaseAnalysisEngine match {
              case Some(engine: NoonPhaseAnalysisEngine) =>
                log("NoonPhaseAnalysisEngine")
                NoonPhaseProtocol.read(json, box.villageInfoFromLobby) match {
                  case Some(protocol) =>
                    engine.process(box, protocol)
                  case None => Failure(new JSON2ProtocolException(NoonPhaseAnalysisEngine.name))
                }
              case None => Failure(new NoEngineException(NoonPhaseAnalysisEngine.name))
            }
          case Night.label =>
            nightPhaseAnalysisEngine match {
              case Some(engine: NightPhaseAnalysisEngine) =>
                log("NightPhaseAnalysisEngine")
                NightPhaseProtocol.read(json, box.villageInfoFromLobby) match {
                  case Some(protocol) =>
                    engine.process(box, protocol)
                  case None => Failure(new JSON2ProtocolException(NightPhaseAnalysisEngine.name))
                }
              case None => Failure(new NoEngineException(NightPhaseAnalysisEngine.name))
            }
          case PostMortemDiscussion.label =>
            postMortemDiscussionAnalysisEngine match {
              case Some(engine: PostMortemDiscussionAnalysisEngine) =>
                log("PostMortemDiscussionAnalysisEngine")
                PostMortemDiscussionProtocol.read(json, box.villageInfoFromLobby) match {
                  case Some(protocol) =>
                    engine.process(box, protocol)
                  case None =>
                    Failure(new JSON2ProtocolException(PostMortemDiscussionAnalysisEngine.name))
                }
              case None =>
                Failure(new NoEngineException(PostMortemDiscussionAnalysisEngine.name))
            }
          case _ => Failure(new NoEngineException("PhaseAnalysisEngine"))
        }
      case Right(json: JsonFlavorText) =>
        log("JsonFlavorText")
        flavorTextAnalysisEngine match {
          case Some(engine: FlavorTextAnalysisEngine) =>
            log("FlavorTextAnalysisEngine")
            FlavorTextProtocol.read(json, box.villageInfoFromLobby) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(FlavorTextAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(FlavorTextAnalysisEngine.name))
        }
      case Right(json: JsonGameResult) =>
        log("JsonGameResult")
        gameResultAnalysisEngine match {
          case Some(engine: GameResultAnalysisEngine) =>
            log("GameResultAnalysisEngine")
            GameResultProtocol.read(json, box.villageInfoFromLobby) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(GameResultAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(GameResultAnalysisEngine.name))
        }
      case Right(json: JsonNextGameInvitation) =>
        log("JsonNextGameInvitation")
        nextGameInvitationAnalysisEngine match {
          case Some(engine: NextGameInvitationAnalysisEngine) =>
            log("NextGameInvitationAnalysisEngine")
            NextGameInvitationProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(NextGameInvitationAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(NextGameInvitationAnalysisEngine.name))
        }
      case Right(json: JsonNextGameInvitationIsClosed) =>
        log("JsonNextGameInvitationIsClosed")
        nextGameInvitationIsClosedAnalysisEngine match {
          case Some(engine: NextGameInvitationIsClosedAnalysisEngine) =>
            log("NextGameInvitationIsClosedAnalysisEngine")
            NextGameInvitationIsClosedProtocol.read(json) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(NextGameInvitationIsClosedAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(NextGameInvitationIsClosedAnalysisEngine.name))
        }
      case _ =>
        Failure(new NoEngineException("AnalysisEngine"))
    }
  }
}
