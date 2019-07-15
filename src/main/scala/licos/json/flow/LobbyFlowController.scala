package licos.json.flow

import licos.json.flow.utils._
import licos.json.parser.LobbyParser
import play.api.libs.json.JsValue

/** This class is to control the flow of parsing play.api.libs.json.JsValue for lobby.
  *
  * @author Kotaro Sakamoto
  */
class LobbyFlowController() extends FlowController with LobbyParser {

  /** Controls the flow of parsing play.api.libs.json.JsValue for lobby.
    *
    * @param jsValue a play.api.libs.json.JsValue for lobby to parse.
    * @return a parsing result.
    */
  override def flow(jsValue: JsValue): Option[Any] = {
    Option(
      parsePong(jsValue) >>> {
        parsePing(jsValue) >>> {
          parseWaitingPage(jsValue) >>> {
            parseLobby(jsValue) >>> {
              parseEnterLobby(jsValue) >>> {
                parseGetAvatarInfo(jsValue) >>> {
                  parseSelectVillage(jsValue) >>> {
                    parseLeaveWaitingPage(jsValue) >>> {
                      parseKickOutPlayer(jsValue) >>> {
                        parseBuildVillage(jsValue) >>> {
                          parseAdvancedSearch(jsValue) >>> {
                            parseIdSearch(jsValue) >>> {
                              parsePlay(jsValue) >>> {
                                parsePlayedWithToken(jsValue) >-> {
                                  parseReady(jsValue) >>> {
                                    parseSearchResult(jsValue) >>> {
                                      parseChangeLang(jsValue) >>> {
                                        parseChangeUserEmail(jsValue) >>> {
                                          parseChangeUserName(jsValue) >>> {
                                            parseChangeUserPassword(jsValue) >>>
                                              parseGetSettings(jsValue).orNull
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    )
  }
}
