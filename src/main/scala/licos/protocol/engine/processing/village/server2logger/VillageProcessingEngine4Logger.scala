package licos.protocol.engine.processing.village.server2logger

import com.typesafe.scalalogging.Logger
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
  OnymousAudienceChatFromServerProtocol,
  PostMortemDiscussionProtocol
}
import licos.protocol.engine.analysis.village.client2server.server2logger._
import licos.protocol.engine.analysis.village.server2client.server2logger._
import licos.protocol.engine.processing.village.VillageBOX
import licos.protocol.engine.processing.{JSON2ProtocolException, NoEngineException, ProcessingEngine}

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
    onymousAudienceChatFromServerAnalysisEngine:   Option[OnymousAudienceChatFromServerAnalysisEngine],
    postMortemDiscussionAnalysisEngine:            Option[PostMortemDiscussionAnalysisEngine]
) extends ProcessingEngine {

  private final val logger: Logger = Logger[VillageProcessingEngine4Logger]

  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing", "org.wartremover.warts.Overloading"))
  def process(box: VillageBOX, msg: VillageMessageProtocol): Try[VillageMessageProtocol] = {

    def log(label: String): Unit = {
      val format: String = "process %s"
      logger.info(format.format(label))
    }

    msg match {
      case protocol: AnonymousAudienceChatFromServerProtocol =>
        anonymousAudienceChatFromServerAnalysisEngine match {
          case Some(engine: AnonymousAudienceChatFromServerAnalysisEngine) =>
            log(AnonymousAudienceChatFromServerAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(AnonymousAudienceChatFromServerAnalysisEngine.name))

        }
      case protocol: AnonymousAudienceChatFromClientProtocol =>
        anonymousAudienceChatFromClientAnalysisEngine match {
          case Some(engine: AnonymousAudienceChatFromClientAnalysisEngine) =>
            log(AnonymousAudienceChatFromClientAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(AnonymousAudienceChatFromClientAnalysisEngine.name))
        }
      case protocol: BoardProtocol =>
        boardAnalysisEngine match {
          case Some(engine: BoardAnalysisEngine) =>
            log(BoardAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(BoardAnalysisEngine.name))
        }
      case protocol: ChatFromClientProtocol =>
        chatFromClientAnalysisEngine match {
          case Some(engine: ChatFromClientAnalysisEngine) =>
            log(ChatFromClientAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ChatFromClientAnalysisEngine.name))
        }
      case protocol: ErrorFromServerProtocol =>
        errorFromServerAnalysisEngine match {
          case Some(engine: ErrorFromServerAnalysisEngine) =>
            log(ErrorFromServerAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ErrorFromServerAnalysisEngine.name))
        }
      case protocol: ErrorFromClientProtocol =>
        errorFromClientAnalysisEngine match {
          case Some(engine: ErrorFromClientAnalysisEngine) =>
            log(ErrorFromClientAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ErrorFromClientAnalysisEngine.name))
        }
      case protocol: OnymousAudienceBoardProtocol =>
        onymousAudienceBoardAnalysisEngine match {
          case Some(engine: OnymousAudienceBoardAnalysisEngine) =>
            log(OnymousAudienceBoardAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(OnymousAudienceBoardAnalysisEngine.name))
        }
      case protocol: OnymousAudienceChatFromServerProtocol =>
        onymousAudienceChatFromServerAnalysisEngine match {
          case Some(engine: OnymousAudienceChatFromServerAnalysisEngine) =>
            log(OnymousAudienceChatFromServerAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(OnymousAudienceChatFromServerAnalysisEngine.name))
        }
      case protocol: OnymousAudienceChatFromClientProtocol =>
        onymousAudienceChatFromClientAnalysisEngine match {
          case Some(engine: OnymousAudienceChatFromClientAnalysisEngine) =>
            log(OnymousAudienceChatFromClientAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(OnymousAudienceChatFromClientAnalysisEngine.name))
        }
      case protocol: OnymousAudienceScrollProtocol =>
        onymousAudienceScrollAnalysisEngine match {
          case Some(engine: OnymousAudienceScrollAnalysisEngine) =>
            log(OnymousAudienceScrollAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(OnymousAudienceScrollAnalysisEngine.name))
        }
      case protocol: ScrollProtocol =>
        scrollAnalysisEngine match {
          case Some(engine: ScrollAnalysisEngine) =>
            log(ScrollAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ScrollAnalysisEngine.name))
        }
      case protocol: StarProtocol =>
        starAnalysisEngine match {
          case Some(engine: StarAnalysisEngine) =>
            log(StarAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(StarAnalysisEngine.name))
        }
      case protocol: VoteProtocol =>
        voteAnalysisEngine match {
          case Some(engine: VoteAnalysisEngine) =>
            log(VoteAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(VoteAnalysisEngine.name))
        }
      case protocol: ChatFromServerProtocol =>
        chatFromServerAnalysisEngine match {
          case Some(engine: ChatFromServerAnalysisEngine) =>
            log(ChatFromServerAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(ChatFromServerAnalysisEngine.name))
        }
      case protocol: FirstMorningPhaseProtocol =>
        firstMorningPhaseAnalysisEngine match {
          case Some(engine: FirstMorningPhaseAnalysisEngine) =>
            log(FirstMorningPhaseAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(FirstMorningPhaseAnalysisEngine.name))
        }
      case protocol: MorningPhaseProtocol =>
        morningPhaseAnalysisEngine match {
          case Some(engine: MorningPhaseAnalysisEngine) =>
            log(MorningPhaseAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(MorningPhaseAnalysisEngine.name))
        }
      case protocol: NoonPhaseProtocol =>
        noonPhaseAnalysisEngine match {
          case Some(engine: NoonPhaseAnalysisEngine) =>
            log(NoonPhaseAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(NoonPhaseAnalysisEngine.name))
        }
      case protocol: NightPhaseProtocol =>
        nightPhaseAnalysisEngine match {
          case Some(engine: NightPhaseAnalysisEngine) =>
            log(NightPhaseAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(NightPhaseAnalysisEngine.name))
        }
      case protocol: PostMortemDiscussionProtocol =>
        postMortemDiscussionAnalysisEngine match {
          case Some(engine: PostMortemDiscussionAnalysisEngine) =>
            log(PostMortemDiscussionAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(PostMortemDiscussionAnalysisEngine.name))
        }
      case protocol: FlavorTextProtocol =>
        flavorTextAnalysisEngine match {
          case Some(engine: FlavorTextAnalysisEngine) =>
            log(FlavorTextAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(FlavorTextAnalysisEngine.name))
        }
      case protocol: GameResultProtocol =>
        gameResultAnalysisEngine match {
          case Some(engine: GameResultAnalysisEngine) =>
            log(GameResultAnalysisEngine.name)
            engine.process(box, protocol)
          case None => Failure(new NoEngineException(GameResultAnalysisEngine.name))
        }
      case _ =>
        Failure(new JSON2ProtocolException("No protocol"))
    }
  }
}
