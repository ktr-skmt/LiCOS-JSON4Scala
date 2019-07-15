package engine.lobby

import java.nio.charset.StandardCharsets

import engine.Example
import engine.lobby.analysis.{AdvancedSearchAE, BuildVillageAE, ChangeLangAE, ChangeUserEmailAE, ChangeUserNameAE, ChangeUserPasswordAE, EnterLobbyAE, GetAvatarInfoAE, GetSettingsAE, IdSearchAE, KickOutPlayerAE, LeaveWaitingPageAE, LobbyAE, PingAE, PlayAE, PlayedWithTokenAE, PongAE, ReadyAE, SearchResultAE, SelectVillageAE, WaitingPageAE}
import entity.JsonTest
import licos.json.engine.processing.LobbyProcessingEngine
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import play.api.libs.json.{JsResult, JsValue}

import scala.io.{Codec, Source}
import scala.util.{Failure, Success, Try}

object LobbyProcessingEngineSpec {
  @DataPoints
  def jsonExampleSeq: Array[Example] = Array[Example](

  )
}


@RunWith(classOf[Theories])
class LobbyProcessingEngineSpec extends AssertionsForJUnit {
  private val processingEngine = new LobbyProcessingEngine(
    new PongAE(),
    new PingAE(),
    new WaitingPageAE(),
    new LobbyAE(),
    new EnterLobbyAE(),
    new GetAvatarInfoAE(),
    new SelectVillageAE(),
    new LeaveWaitingPageAE(),
    new KickOutPlayerAE(),
    new BuildVillageAE(),
    new AdvancedSearchAE(),
    new IdSearchAE(),
    new PlayAE(),
    new PlayedWithTokenAE(),
    new ReadyAE(),
    new SearchResultAE(),
    new ChangeLangAE(),
    new ChangeUserEmailAE(),
    new ChangeUserNameAE(),
    new ChangeUserPasswordAE(),
    new GetSettingsAE()
  )

  @Theory
  def process(jsonExample: Example): Unit = {
    val jsonType: String = jsonExample.`type`
    val url: String = jsonExample.path
    implicit val codec: Codec = Codec(StandardCharsets.UTF_8)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()
    processingEngine.process(new LobbyBox(jsonType), msg) match {
      case Some(jsValue: JsValue) =>
        parseJsonTest(jsValue) match {
          case Some(json: JsonTest) =>
            assert(json.text == jsonType)
          case None => fail(s"Something is wrong right after parsing.\n$msg")
        }

      case None => fail(s"No response is generated.\n$msg")
    }
  }

  private def parseJsonTest(jsValue: JsValue): Option[JsonTest] = {
    Try(jsValue.validate[JsonTest]) match {
      case Success(json: JsResult[JsonTest]) => json.asOpt
      case Failure(err: Throwable) =>
        fail(s"Parsing failed.\n${err.getMessage}\n${jsValue.toString}")
        None
    }
  }
}
