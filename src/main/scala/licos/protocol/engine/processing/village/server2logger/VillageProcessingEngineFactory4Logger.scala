package licos.protocol.engine.processing.village.server2logger

import licos.protocol.engine.analysis.village.client2server.server2logger._
import licos.protocol.engine.analysis.village.server2client.server2logger._
import licos.protocol.engine.processing.ProcessingEngineFactory

@SuppressWarnings(Array[String]("org.wartremover.warts.Overloading", "org.wartremover.warts.Var"))
final class VillageProcessingEngineFactory4Logger extends ProcessingEngineFactory {

  private var anonymousAudienceChatFromClientAnalysisEngine: Option[AnonymousAudienceChatFromClientAnalysisEngine] =
    Option.empty[AnonymousAudienceChatFromClientAnalysisEngine]
  private var boardAnalysisEngine: Option[BoardAnalysisEngine] = Option.empty[BoardAnalysisEngine]
  private var chatFromClientAnalysisEngine: Option[ChatFromClientAnalysisEngine] =
    Option.empty[ChatFromClientAnalysisEngine]
  private var errorFromClientAnalysisEngine: Option[ErrorFromClientAnalysisEngine] =
    Option.empty[ErrorFromClientAnalysisEngine]
  private var onymousAudienceBoardAnalysisEngine: Option[OnymousAudienceBoardAnalysisEngine] =
    Option.empty[OnymousAudienceBoardAnalysisEngine]
  private var onymousAudienceChatFromClientAnalysisEngine: Option[OnymousAudienceChatFromClientAnalysisEngine] =
    Option.empty[OnymousAudienceChatFromClientAnalysisEngine]
  private var onymousAudienceScrollAnalysisEngine: Option[OnymousAudienceScrollAnalysisEngine] =
    Option.empty[OnymousAudienceScrollAnalysisEngine]
  private var scrollAnalysisEngine: Option[ScrollAnalysisEngine] = Option.empty[ScrollAnalysisEngine]
  private var starAnalysisEngine:   Option[StarAnalysisEngine]   = Option.empty[StarAnalysisEngine]
  private var voteAnalysisEngine:   Option[VoteAnalysisEngine]   = Option.empty[VoteAnalysisEngine]
  private var anonymousAudienceChatFromServerAnalysisEngine: Option[AnonymousAudienceChatFromServerAnalysisEngine] =
    Option.empty[AnonymousAudienceChatFromServerAnalysisEngine]
  private var chatFromServerAnalysisEngine: Option[ChatFromServerAnalysisEngine] =
    Option.empty[ChatFromServerAnalysisEngine]
  private var errorFromServerAnalysisEngine: Option[ErrorFromServerAnalysisEngine] =
    Option.empty[ErrorFromServerAnalysisEngine]
  private var firstMorningPhaseAnalysisEngine: Option[FirstMorningPhaseAnalysisEngine] =
    Option.empty[FirstMorningPhaseAnalysisEngine]
  private var flavorTextAnalysisEngine:   Option[FlavorTextAnalysisEngine]   = Option.empty[FlavorTextAnalysisEngine]
  private var gameResultAnalysisEngine:   Option[GameResultAnalysisEngine]   = Option.empty[GameResultAnalysisEngine]
  private var morningPhaseAnalysisEngine: Option[MorningPhaseAnalysisEngine] = Option.empty[MorningPhaseAnalysisEngine]
  private var nightPhaseAnalysisEngine:   Option[NightPhaseAnalysisEngine]   = Option.empty[NightPhaseAnalysisEngine]
  private var noonPhaseAnalysisEngine:    Option[NoonPhaseAnalysisEngine]    = Option.empty[NoonPhaseAnalysisEngine]
  private var onymousAudienceChatFromServerAnalysisEngine: Option[OnymousAudienceChatFromServerAnalysisEngine] =
    Option.empty[OnymousAudienceChatFromServerAnalysisEngine]
  private var postMortemDiscussionAnalysisEngine: Option[PostMortemDiscussionAnalysisEngine] =
    Option.empty[PostMortemDiscussionAnalysisEngine]

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
