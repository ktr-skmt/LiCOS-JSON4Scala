package parser

import licos.json.element.lobby.{
  JsonHostPlayer,
  JsonHuman,
  JsonPingResult,
  JsonPlayerInWaitingPage,
  JsonPlayerSetting,
  JsonPlayerTokenInKickOutPlayer,
  JsonRobot,
  JsonRoleSetting
}
import org.slf4j.{Logger, LoggerFactory}
import play.api.libs.json.{JsError, JsResult, JsSuccess, JsValue}

import scala.util.{Failure, Success, Try}

trait LobbyUnitTestParser {

  private final val logger: Logger = LoggerFactory.getLogger(classOf[LobbyUnitTestParser])

  def parseHostPlayer(jsValue: JsValue): Option[JsonHostPlayer] = {
    Try(jsValue.validate[JsonHostPlayer]) match {
      case Success(json: JsResult[JsonHostPlayer]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseHuman(jsValue: JsValue): Option[JsonHuman] = {
    Try(jsValue.validate[JsonHuman]) match {
      case Success(json: JsResult[JsonHuman]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parsePingResult(jsValue: JsValue): Option[JsonPingResult] = {
    Try(jsValue.validate[JsonPingResult]) match {
      case Success(json: JsResult[JsonPingResult]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parsePlayerInWaitingPage(jsValue: JsValue): Option[JsonPlayerInWaitingPage] = {
    Try(jsValue.validate[JsonPlayerInWaitingPage]) match {
      case Success(json: JsResult[JsonPlayerInWaitingPage]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parsePlayerSetting(jsValue: JsValue): Option[JsonPlayerSetting] = {
    Try(jsValue.validate[JsonPlayerSetting]) match {
      case Success(json: JsResult[JsonPlayerSetting]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parsePlayerTokenInKickOutPlayer(jsValue: JsValue): Option[JsonPlayerTokenInKickOutPlayer] = {
    Try(jsValue.validate[JsonPlayerTokenInKickOutPlayer]) match {
      case Success(json: JsResult[JsonPlayerTokenInKickOutPlayer]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseRobot(jsValue: JsValue): Option[JsonRobot] = {
    Try(jsValue.validate[JsonRobot]) match {
      case Success(json: JsResult[JsonRobot]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }

  def parseRoleSetting(jsValue: JsValue): Option[JsonRoleSetting] = {
    Try(jsValue.validate[JsonRoleSetting]) match {
      case Success(json: JsResult[JsonRoleSetting]) =>
        json match {
          case JsSuccess(j, _) => Option(j)
          case e: JsError =>
            logger.debug(e.toString)
            None
        }
      case Failure(err: Throwable) =>
        logger.error(err.getMessage)
        None
    }
  }
}
