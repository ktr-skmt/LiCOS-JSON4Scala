package protocol.engine.village

import java.nio.charset.StandardCharsets

import com.typesafe.scalalogging.Logger
import json.engine.VillageExample
import licos.json.parser.VillageParser
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.engine.processing.{
  SpecificProcessingEngineFactory,
  VillagePE,
  VillageProcessingEngine,
  VillageProcessingEngineFactory
}
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit
import protocol.element.VillageMessageTestProtocol

import scala.io.{Codec, Source}
import scala.util.{Failure, Success}

object VillageProcessingEngineSpec {
  @DataPoints
  def exampleSeq: Array[VillageExample] = Array[VillageExample](
    )
}

@RunWith(classOf[Theories])
class VillageProcessingEngineSpec extends AssertionsForJUnit with VillageParser {

  private final val log: Logger = Logger[VillageProcessingEngineSpec]

  private val processingEngineFactory: VillageProcessingEngineFactory = SpecificProcessingEngineFactory
    .create(VillagePE)
    .asInstanceOf[VillageProcessingEngineFactory]

  private val processingEngine: VillageProcessingEngine = processingEngineFactory.create

  @Theory
  def process(jsonExample: VillageExample): Unit = {
    val jsonType:       String = jsonExample.`type`
    val url:            String = jsonExample.path
    implicit val codec: Codec  = Codec(StandardCharsets.UTF_8)
    log.info(url)
    val source = Source.fromURL(url)
    val msg: String = source.getLines.mkString("\n")
    source.close()
    log.debug(msg)
    processingEngine.process(new VillageBox(), msg) match {
      case Success(protocol: VillageMessageProtocol) =>
        protocol match {
          case p: VillageMessageTestProtocol =>
            assert(p.text == jsonType)
          case _ =>
            fail(
              Seq[String](
                "No AuthMessageTestProtocol"
              ).mkString("\n")
            )
        }
      case Failure(error: Throwable) =>
        fail(
          Seq[String](
            "No response is generated.",
            error.getMessage,
            msg
          ).mkString("\n")
        )
    }
  }
}
