package licos.protocol.engine.processing.server2logger

import com.typesafe.scalalogging.Logger
import licos.json.element.village.{
  JsonAnonymousAudienceChat,
  JsonBoard,
  JsonChatFromClient,
  JsonChatFromServer,
  JsonError,
  JsonFlavorText,
  JsonGameResult,
  JsonOnymousAudienceBoard,
  JsonOnymousAudienceChat,
  JsonOnymousAudienceScroll,
  JsonPhase,
  JsonScroll,
  JsonStar,
  JsonVote
}
import licos.json.flow.{FlowController, VillageFlowController}
import licos.knowledge.{Morning, Night, Noon}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.element.village.client2server.server2logger.{
  AnonymousAudienceChatFromClientProtocol,
  BoardProtocol,
  ChatFromClientProtocol,
  ErrorFromClientProtocol,
  OnymousAudienceBoardProtocol,
  OnymousAudienceChatFromClientProtocol,
  OnymousAudienceScrollProtocol,
  ScrollProtocol,
  StarProtocol,
  VoteProtocol
}
import licos.protocol.element.village.server2client.server2logger.{
  AnonymousAudienceChatFromServerProtocol,
  ChatFromServerProtocol,
  ErrorFromServerProtocol,
  FirstMorningPhaseProtocol,
  FlavorTextProtocol,
  GameResultProtocol,
  MorningPhaseProtocol,
  NightPhaseProtocol,
  NoonPhaseProtocol,
  OnymousAudienceChatFromServerProtocol
}
import licos.protocol.engine.analysis.village.client2server.server2logger.{
  AnonymousAudienceChatFromClientAnalysisEngine,
  BoardAnalysisEngine,
  ChatFromClientAnalysisEngine,
  ErrorFromClientAnalysisEngine,
  OnymousAudienceBoardAnalysisEngine,
  OnymousAudienceChatFromClientAnalysisEngine,
  OnymousAudienceScrollAnalysisEngine,
  ScrollAnalysisEngine,
  StarAnalysisEngine,
  VoteAnalysisEngine
}
import licos.protocol.engine.analysis.village.server2client.server2logger.{
  AnonymousAudienceChatFromServerAnalysisEngine,
  ChatFromServerAnalysisEngine,
  ErrorFromServerAnalysisEngine,
  FirstMorningPhaseAnalysisEngine,
  FlavorTextAnalysisEngine,
  GameResultAnalysisEngine,
  MorningPhaseAnalysisEngine,
  NightPhaseAnalysisEngine,
  NoonPhaseAnalysisEngine,
  OnymousAudienceChatFromServerAnalysisEngine
}
import licos.protocol.engine.processing.{JSON2ProtocolException, NoEngineException, ProcessingEngine, VillageBOX}
import play.api.libs.json.{JsValue, Json}

import scala.util.{Failure, Try}

class VillageProcessingEngine4Logger(
    anonymousAudienceChatFromClientAnalysisEngine: Option[AnonymousAudienceChatFromClientAnalysisEngine],
    boardAnalysisEngine:                           Option[BoardAnalysisEngine],
    chatFromClientAnalysisEngine:                  Option[ChatFromClientAnalysisEngine],
    errorFromClientAnalysisEngine:                 Option[ErrorFromClientAnalysisEngine],
    onymousAudienceBoardAnalysisEngine:            Option[OnymousAudienceBoardAnalysisEngine],
    onymousAudienceChatFromClientAnalysisEngine:   Option[OnymousAudienceChatFromClientAnalysisEngine],
    onymousAudienceScrollAnalysisEngine:           Option[OnymousAudienceScrollAnalysisEngine],
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
    nightPhaseAnalysisEngine:                      Option[NightPhaseAnalysisEngine],
    noonPhaseAnalysisEngine:                       Option[NoonPhaseAnalysisEngine],
    onymousAudienceChatFromServerAnalysisEngine:   Option[OnymousAudienceChatFromServerAnalysisEngine]
) extends ProcessingEngine {

  override protected val flowController: FlowController = new VillageFlowController()

  private final val logger: Logger = Logger[VillageProcessingEngine4Logger]

  def process(box: VillageBOX, msg: String): Try[VillageMessageProtocol] = {

    val jsValue: JsValue = Json.parse(msg)

    def log(label: String): Unit = {
      val format: String = "process %s"
      logger.info(format.format(label))
    }

    flowController.flow(jsValue) match {
      case Right(json: JsonAnonymousAudienceChat) =>
        if (json.isFromServer) {
          anonymousAudienceChatFromServerAnalysisEngine match {
            case Some(engine: AnonymousAudienceChatFromServerAnalysisEngine) =>
              AnonymousAudienceChatFromServerProtocol.read(json, box.village) match {
                case Some(protocol: AnonymousAudienceChatFromServerProtocol) =>
                  engine.process(box, protocol)
                case None => Failure(new JSON2ProtocolException(AnonymousAudienceChatFromServerAnalysisEngine.name))
              }
            case None => Failure(new NoEngineException(AnonymousAudienceChatFromServerAnalysisEngine.name))

          }
        } else {
          anonymousAudienceChatFromClientAnalysisEngine match {
            case Some(engine: AnonymousAudienceChatFromClientAnalysisEngine) =>
              AnonymousAudienceChatFromClientProtocol.read(json, box.village) match {
                case Some(protocol) =>
                  engine.process(box, protocol)
                case None => Failure(new JSON2ProtocolException(AnonymousAudienceChatFromClientAnalysisEngine.name))
              }
            case None => Failure(new NoEngineException(AnonymousAudienceChatFromClientAnalysisEngine.name))
          }
        }
      case Right(json: JsonBoard) =>
        boardAnalysisEngine match {
          case Some(engine: BoardAnalysisEngine) =>
            BoardProtocol.read(json, box.village) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(BoardAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(BoardAnalysisEngine.name))
        }
      case Right(json: JsonChatFromClient) =>
        chatFromClientAnalysisEngine match {
          case Some(engine: ChatFromClientAnalysisEngine) =>
            ChatFromClientProtocol.read(json, box.village) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ChatFromClientAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ChatFromClientAnalysisEngine.name))
        }
      case Right(json: JsonError) =>
        if (json.isFromServer) {
          errorFromServerAnalysisEngine match {
            case Some(engine: ErrorFromServerAnalysisEngine) =>
              ErrorFromServerProtocol.read(json, box.village) match {
                case Some(protocol) =>
                  engine.process(box, protocol)
                case None => Failure(new JSON2ProtocolException(ErrorFromServerAnalysisEngine.name))
              }
            case None => Failure(new NoEngineException(ErrorFromServerAnalysisEngine.name))
          }
        } else {
          errorFromClientAnalysisEngine match {
            case Some(engine: ErrorFromClientAnalysisEngine) =>
              ErrorFromClientProtocol.read(json, box.village) match {
                case Some(protocol) =>
                  engine.process(box, protocol)
                case None => Failure(new JSON2ProtocolException(ErrorFromClientAnalysisEngine.name))
              }
            case None => Failure(new NoEngineException(ErrorFromClientAnalysisEngine.name))
          }
        }
      case Right(json: JsonOnymousAudienceBoard) =>
        onymousAudienceBoardAnalysisEngine match {
          case Some(engine: OnymousAudienceBoardAnalysisEngine) =>
            OnymousAudienceBoardProtocol.read(json, box.village) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(OnymousAudienceBoardAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(OnymousAudienceBoardAnalysisEngine.name))
        }
      case Right(json: JsonOnymousAudienceChat) =>
        if (json.isFromServer) {
          onymousAudienceChatFromServerAnalysisEngine match {
            case Some(engine: OnymousAudienceChatFromServerAnalysisEngine) =>
              OnymousAudienceChatFromServerProtocol.read(json, box.village) match {
                case Some(protocol) =>
                  engine.process(box, protocol)
                case None => Failure(new JSON2ProtocolException(OnymousAudienceChatFromServerAnalysisEngine.name))
              }
            case None => Failure(new NoEngineException(OnymousAudienceChatFromServerAnalysisEngine.name))
          }
        } else {
          onymousAudienceChatFromClientAnalysisEngine match {
            case Some(engine: OnymousAudienceChatFromClientAnalysisEngine) =>
              OnymousAudienceChatFromClientProtocol.read(json, box.village) match {
                case Some(protocol) =>
                  engine.process(box, protocol)
                case None => Failure(new JSON2ProtocolException(OnymousAudienceChatFromClientAnalysisEngine.name))
              }
            case None => Failure(new NoEngineException(OnymousAudienceChatFromClientAnalysisEngine.name))
          }
        }
      case Right(json: JsonOnymousAudienceScroll) =>
        onymousAudienceScrollAnalysisEngine match {
          case Some(engine: OnymousAudienceScrollAnalysisEngine) =>
            OnymousAudienceScrollProtocol.read(json, box.village) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(OnymousAudienceScrollAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(OnymousAudienceScrollAnalysisEngine.name))
        }
      case Right(json: JsonScroll) =>
        scrollAnalysisEngine match {
          case Some(engine: ScrollAnalysisEngine) =>
            ScrollProtocol.read(json, box.village) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ScrollAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ScrollAnalysisEngine.name))
        }
      case Right(json: JsonStar) =>
        starAnalysisEngine match {
          case Some(engine: StarAnalysisEngine) =>
            StarProtocol.read(json, box.village) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(StarAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(StarAnalysisEngine.name))
        }
      case Right(json: JsonVote) =>
        voteAnalysisEngine match {
          case Some(engine: VoteAnalysisEngine) =>
            VoteProtocol.read(json, box.village) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(VoteAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(VoteAnalysisEngine.name))
        }
      case Right(json: JsonChatFromServer) =>
        chatFromServerAnalysisEngine match {
          case Some(engine: ChatFromServerAnalysisEngine) =>
            ChatFromServerProtocol.read(json, box.village) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(ChatFromServerAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(ChatFromServerAnalysisEngine.name))
        }
      case Right(json: JsonPhase) =>
        json.base.phase match {
          case Morning.label =>
            if (json.base.day == 1) {
              firstMorningPhaseAnalysisEngine match {
                case Some(engine: FirstMorningPhaseAnalysisEngine) =>
                  FirstMorningPhaseProtocol.read(json, box.village) match {
                    case Some(protocol) =>
                      engine.process(box, protocol)
                    case None => Failure(new JSON2ProtocolException(FirstMorningPhaseAnalysisEngine.name))
                  }
                case None => Failure(new NoEngineException(FirstMorningPhaseAnalysisEngine.name))
              }
            } else {
              morningPhaseAnalysisEngine match {
                case Some(engine: MorningPhaseAnalysisEngine) =>
                  MorningPhaseProtocol.read(json, box.village) match {
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
                NoonPhaseProtocol.read(json, box.village) match {
                  case Some(protocol) =>
                    engine.process(box, protocol)
                  case None => Failure(new JSON2ProtocolException(NoonPhaseAnalysisEngine.name))
                }
              case None => Failure(new NoEngineException(NoonPhaseAnalysisEngine.name))
            }
          case Night.label =>
            nightPhaseAnalysisEngine match {
              case Some(engine: NightPhaseAnalysisEngine) =>
                NightPhaseProtocol.read(json, box.village) match {
                  case Some(protocol) =>
                    engine.process(box, protocol)
                  case None => Failure(new JSON2ProtocolException(NightPhaseAnalysisEngine.name))
                }
              case None => Failure(new NoEngineException(NightPhaseAnalysisEngine.name))
            }
          case _ => Failure(new NoEngineException("PhaseAnalysisEngine"))
        }
      case Right(json: JsonFlavorText) =>
        flavorTextAnalysisEngine match {
          case Some(engine: FlavorTextAnalysisEngine) =>
            FlavorTextProtocol.read(json, box.village) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(FlavorTextAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(FlavorTextAnalysisEngine.name))
        }
      case Right(json: JsonGameResult) =>
        gameResultAnalysisEngine match {
          case Some(engine: GameResultAnalysisEngine) =>
            GameResultProtocol.read(json, box.village) match {
              case Some(protocol) =>
                engine.process(box, protocol)
              case None => Failure(new JSON2ProtocolException(GameResultAnalysisEngine.name))
            }
          case None => Failure(new NoEngineException(GameResultAnalysisEngine.name))
        }
      case _ =>
        Failure(new NoEngineException("AnalysisEngine"))
    }
  }
}
