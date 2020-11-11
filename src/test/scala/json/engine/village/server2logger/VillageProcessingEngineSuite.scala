package json.engine.village.server2logger

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import json.element.JsonTest
import json.engine.VillageExample
import json.engine.village.VillageBox
import json.engine.village.analysis.client2server.VoteAE
import json.engine.village.analysis.server2client.{ChatFromServerAE, PhaseAE}
import json.engine.village.example.client2server.server2logger.Vote
import json.engine.village.example.server2client.server2logger.{Chat, Phase}
import licos.json.engine.processing.{
  SpecificProcessingEngineFactory,
  VillagePE,
  VillageProcessingEngine,
  VillageProcessingEngineFactory
}
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor1}
import play.api.libs.json.{JsResult, JsValue}

import scala.io.{Codec, Source}
import scala.util.{Failure, Success, Try}

final class VillageProcessingEngineSuite extends AnyFunSuite with Matchers with TableDrivenPropertyChecks {

  private val fractions: TableFor1[VillageExample] =
    Table(
      "jsonExample",
      Vote("nightVoteForLog.jsonld"),
      Chat("myMessageOnChatForLog.jsonld"),
      Phase("morningForLog.jsonld")
    )

  private val log: Logger = Logger[VillageProcessingEngineSuite]

  private val processingEngineFactory: VillageProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(VillagePE)
    .asInstanceOf[VillageProcessingEngineFactory]
    .set(new VoteAE())
    .set(new ChatFromServerAE())
    .set(new PhaseAE())

  private val processingEngine: VillageProcessingEngine = processingEngineFactory.create

  test("json.server2logger.villageProcessingEngineSuite") {
    forEvery(fractions) { jsonExample: VillageExample =>
      val jsonType:       String = jsonExample.`type`
      val url:            String = jsonExample.path
      implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
      log.info(url)
      val source = Source.fromURL(url)
      val msg: String = source.getLines().mkString("\n")
      source.close()
      log.debug(msg)
      processingEngine.process(new VillageBox(jsonType), msg) match {
        case Right(jsValue: JsValue) =>
          parseJsonTest(jsValue) match {
            case Some(json: JsonTest) =>
              assert(json.text == jsonType)
            case None =>
              fail(
                List[String](
                  "Something is wrong right after parsing.",
                  msg
                ).mkString("\n")
              )
          }

        case Left(jsValue: JsValue) =>
          fail(
            List[String](
              "No response is generated.",
              msg,
              jsValue.toString
            ).mkString("\n")
          )
      }
    }
  }

  private def parseJsonTest(jsValue: JsValue): Option[JsonTest] = {
    Try(jsValue.validate[JsonTest]) match {
      case Success(json: JsResult[JsonTest]) => json.asOpt
      case Failure(err: Throwable) =>
        fail(
          List[String](
            "Parsing failed.",
            err.getMessage,
            jsValue.toString
          ).mkString("\n")
        )
        Option.empty[JsonTest]
    }
  }

}
