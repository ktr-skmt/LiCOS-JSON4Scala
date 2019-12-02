package licos.protocol.engine.processing.village.server2logger

import licos.protocol.engine.analysis.village.client2server.server2logger._
import licos.protocol.engine.analysis.village.server2client.server2logger._
import licos.protocol.engine.processing.ProcessingEngineFactory

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading", "org.wartremover.warts.Var"))
class VillageProcessingEngineFactory4Logger extends ProcessingEngineFactory {

  private var anonymousAudienceChatFromClientAnalysisEngine: Option[AnonymousAudienceChatFromClientAnalysisEngine] =
    None
  private var boardAnalysisEngine:                         Option[BoardAnalysisEngine]                         = None
  private var chatFromClientAnalysisEngine:                Option[ChatFromClientAnalysisEngine]                = None
  private var errorFromClientAnalysisEngine:               Option[ErrorFromClientAnalysisEngine]               = None
  private var onymousAudienceBoardAnalysisEngine:          Option[OnymousAudienceBoardAnalysisEngine]          = None
  private var onymousAudienceChatFromClientAnalysisEngine: Option[OnymousAudienceChatFromClientAnalysisEngine] = None
  private var onymousAudienceScrollAnalysisEngine:         Option[OnymousAudienceScrollAnalysisEngine]         = None
  private var scrollAnalysisEngine:                        Option[ScrollAnalysisEngine]                        = None
  private var starAnalysisEngine:                          Option[StarAnalysisEngine]                          = None
  private var voteAnalysisEngine:                          Option[VoteAnalysisEngine]                          = None
  private var anonymousAudienceChatFromServerAnalysisEngine: Option[AnonymousAudienceChatFromServerAnalysisEngine] =
    None
  private var chatFromServerAnalysisEngine:                Option[ChatFromServerAnalysisEngine]                = None
  private var errorFromServerAnalysisEngine:               Option[ErrorFromServerAnalysisEngine]               = None
  private var firstMorningPhaseAnalysisEngine:             Option[FirstMorningPhaseAnalysisEngine]             = None
  private var flavorTextAnalysisEngine:                    Option[FlavorTextAnalysisEngine]                    = None
  private var gameResultAnalysisEngine:                    Option[GameResultAnalysisEngine]                    = None
  private var morningPhaseAnalysisEngine:                  Option[MorningPhaseAnalysisEngine]                  = None
  private var nightPhaseAnalysisEngine:                    Option[NightPhaseAnalysisEngine]                    = None
  private var noonPhaseAnalysisEngine:                     Option[NoonPhaseAnalysisEngine]                     = None
  private var onymousAudienceChatFromServerAnalysisEngine: Option[OnymousAudienceChatFromServerAnalysisEngine] = None
  private var postMortemDiscussionAnalysisEngine:          Option[PostMortemDiscussionAnalysisEngine]          = None

  override def create: VillageProcessingEngine4Logger = {
    new VillageProcessingEngine4Logger(
      anonymousAudienceChatFromClientAnalysisEngine,
      boardAnalysisEngine,
      chatFromClientAnalysisEngine,
      errorFromClientAnalysisEngine,
      onymousAudienceBoardAnalysisEngine,
      onymousAudienceChatFromClientAnalysisEngine,
      onymousAudienceScrollAnalysisEngine,
      scrollAnalysisEngine,
      starAnalysisEngine,
      voteAnalysisEngine,
      anonymousAudienceChatFromServerAnalysisEngine,
      chatFromServerAnalysisEngine,
      errorFromServerAnalysisEngine,
      firstMorningPhaseAnalysisEngine,
      flavorTextAnalysisEngine,
      gameResultAnalysisEngine,
      morningPhaseAnalysisEngine,
      nightPhaseAnalysisEngine,
      noonPhaseAnalysisEngine,
      onymousAudienceChatFromServerAnalysisEngine,
      postMortemDiscussionAnalysisEngine
    )
  }

  def set(
      anonymousAudienceChatFromClientAnalysisEngine: AnonymousAudienceChatFromClientAnalysisEngine
  ): VillageProcessingEngineFactory4Logger = {
    this.anonymousAudienceChatFromClientAnalysisEngine = Option(anonymousAudienceChatFromClientAnalysisEngine)
    this
  }

  def set(boardAnalysisEngine: BoardAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.boardAnalysisEngine = Option(boardAnalysisEngine)
    this
  }

  def set(chatFromClientAnalysisEngine: ChatFromClientAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.chatFromClientAnalysisEngine = Option(chatFromClientAnalysisEngine)
    this
  }

  def set(errorFromClientAnalysisEngine: ErrorFromClientAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.errorFromClientAnalysisEngine = Option(errorFromClientAnalysisEngine)
    this
  }

  def set(
      onymousAudienceBoardAnalysisEngine: OnymousAudienceBoardAnalysisEngine
  ): VillageProcessingEngineFactory4Logger = {
    this.onymousAudienceBoardAnalysisEngine = Option(onymousAudienceBoardAnalysisEngine)
    this
  }

  def set(
      onymousAudienceChatFromClientAnalysisEngine: OnymousAudienceChatFromClientAnalysisEngine
  ): VillageProcessingEngineFactory4Logger = {
    this.onymousAudienceChatFromClientAnalysisEngine = Option(onymousAudienceChatFromClientAnalysisEngine)
    this
  }

  def set(
      onymousAudienceScrollAnalysisEngine: OnymousAudienceScrollAnalysisEngine
  ): VillageProcessingEngineFactory4Logger = {
    this.onymousAudienceScrollAnalysisEngine = Option(onymousAudienceScrollAnalysisEngine)
    this
  }

  def set(scrollAnalysisEngine: ScrollAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.scrollAnalysisEngine = Option(scrollAnalysisEngine)
    this
  }

  def set(starAnalysisEngine: StarAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.starAnalysisEngine = Option(starAnalysisEngine)
    this
  }

  def set(voteAnalysisEngine: VoteAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.voteAnalysisEngine = Option(voteAnalysisEngine)
    this
  }

  def set(
      anonymousAudienceChatFromServerAnalysisEngine: AnonymousAudienceChatFromServerAnalysisEngine
  ): VillageProcessingEngineFactory4Logger = {
    this.anonymousAudienceChatFromServerAnalysisEngine = Option(anonymousAudienceChatFromServerAnalysisEngine)
    this
  }

  def set(chatFromServerAnalysisEngine: ChatFromServerAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.chatFromServerAnalysisEngine = Option(chatFromServerAnalysisEngine)
    this
  }

  def set(errorFromServerAnalysisEngine: ErrorFromServerAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.errorFromServerAnalysisEngine = Option(errorFromServerAnalysisEngine)
    this
  }

  def set(firstMorningPhaseAnalysisEngine: FirstMorningPhaseAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.firstMorningPhaseAnalysisEngine = Option(firstMorningPhaseAnalysisEngine)
    this
  }

  def set(flavorTextAnalysisEngine: FlavorTextAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.flavorTextAnalysisEngine = Option(flavorTextAnalysisEngine)
    this
  }

  def set(gameResultAnalysisEngine: GameResultAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.gameResultAnalysisEngine = Option(gameResultAnalysisEngine)
    this
  }

  def set(morningPhaseAnalysisEngine: MorningPhaseAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.morningPhaseAnalysisEngine = Option(morningPhaseAnalysisEngine)
    this
  }

  def set(nightPhaseAnalysisEngine: NightPhaseAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.nightPhaseAnalysisEngine = Option(nightPhaseAnalysisEngine)
    this
  }

  def set(noonPhaseAnalysisEngine: NoonPhaseAnalysisEngine): VillageProcessingEngineFactory4Logger = {
    this.noonPhaseAnalysisEngine = Option(noonPhaseAnalysisEngine)
    this
  }

  def set(
      onymousAudienceChatFromServerAnalysisEngine: OnymousAudienceChatFromServerAnalysisEngine
  ): VillageProcessingEngineFactory4Logger = {
    this.onymousAudienceChatFromServerAnalysisEngine = Option(onymousAudienceChatFromServerAnalysisEngine)
    this
  }

  def set(
      postMortemDiscussionAnalysisEngine: PostMortemDiscussionAnalysisEngine
  ): VillageProcessingEngineFactory4Logger = {
    this.postMortemDiscussionAnalysisEngine = Option(postMortemDiscussionAnalysisEngine)
    this
  }
}
