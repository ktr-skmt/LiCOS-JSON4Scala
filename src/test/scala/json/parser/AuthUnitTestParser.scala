package json.parser

import licos.json.element.auth.robot2server.{JsonProgrammingLanguage, JsonSourceCode}
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.json.{JsError, JsResult, JsSuccess, JsValue}

import scala.util.{Failure, Success, Try}

trait AuthUnitTestParser {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[AuthUnitTestParser])

  def parseSourceCode(jsValue: JsValue): Option[JsonSourceCode] = {
    Try(jsValue.validate[JsonSourceCode]) match {
      case Success(json: JsResult[JsonSourceCode]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            Option.empty[JsonSourceCode]
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        Option.empty[JsonSourceCode]
    }
  }

  def parseProgrammingLanguage(jsValue: JsValue): Option[JsonProgrammingLanguage] = {
    Try(jsValue.validate[JsonProgrammingLanguage]) match {
      case Success(json: JsResult[JsonProgrammingLanguage]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            Option.empty[JsonProgrammingLanguage]
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        Option.empty[JsonProgrammingLanguage]
    }
  }
}
