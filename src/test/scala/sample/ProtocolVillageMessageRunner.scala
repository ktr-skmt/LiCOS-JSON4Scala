package sample

import licos.entity.{Village, VillageFactory}
import licos.protocol.element.village.VillageMessageProtocol
import licos.protocol.engine.processing.{SpecificProcessingEngineFactory, VillageBOX, VillagePE, VillageProcessingEngine, VillageProcessingEngineFactory}
import play.api.libs.json.{JsValue, Json}
import protocol.engine.village.VillageBox

import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success}

object ProtocolVillageMessageRunner extends App {
  private val processingEngineFactory: VillageProcessingEngineFactory = SpecificProcessingEngineFactory.
    create(VillagePE).
    asInstanceOf[VillageProcessingEngineFactory]

  private val processingEngine: VillageProcessingEngine = processingEngineFactory.create
  // set analysis engines
  private val aJSONExampleOfTheLiCOSProtocol: String = {
    val source: BufferedSource = Source.fromURL(
      "https://raw.githubusercontent.com/ktr-skmt/werewolfworld/gh-pages/village/example/0.3/server2client/firstMorning.jsonld"
    )
    val json: String = source.getLines().mkString("\n")
    source.close()
    json
  }

  private val villageFactory: VillageFactory = new VillageFactory()
  // set parameters
  villageFactory.create match {
    case Success(village: Village) =>
      val anExampleOfBOX: VillageBOX = new VillageBox(village)

      processingEngine.process(anExampleOfBOX, aJSONExampleOfTheLiCOSProtocol) match {
        case Success(protocol: VillageMessageProtocol) =>
          protocol.toJsonOpt foreach { json: JsValue =>
            System.err.println(Json.prettyPrint(json))
          }
        case Failure(exception: Throwable) =>
          System.err.println(exception.getMessage)
      }
    case Failure(exception: Throwable) =>
      System.err.println(exception.getMessage)
  }
}