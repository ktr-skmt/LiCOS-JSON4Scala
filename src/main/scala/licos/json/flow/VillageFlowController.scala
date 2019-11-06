package licos.json.flow

import licos.json.flow.utils._
import licos.json.parser.VillageParser
import play.api.libs.json.JsValue

/** This class is to control the flow of parsing play.api.libs.json.JsValue for village.
  *
  * @author Kotaro Sakamoto
  */
class VillageFlowController extends FlowController with VillageParser {

  /** Controls the flow of parsing play.api.libs.json.JsValue for village.
    *
    * @param jsValue a play.api.libs.json.JsValue for village to parse.
    * @return a parsing result.
    */
  override def flow(jsValue: JsValue): Option[Any] = {
    Option(
      parseReady(jsValue) >>> {
        parseReceivedChatMessage(jsValue) >>> {
          parseReceivedSystemMessage(jsValue) >>> {
            parseReceivedFlavorTextMessage(jsValue) >>> {
              parseChatFromClient(jsValue) >-> {
                parseChatFromServer(jsValue) >-> {
                  parseOnymousAudienceChat(jsValue) >-> {
                    parseAnonymousAudienceChat(jsValue) >-> {
                      parseBoard(jsValue) >-> {
                        parseOnymousAudienceBoard(jsValue) >-> {
                          parseVote(jsValue) >-> {
                            parseScroll(jsValue) >-> {
                              parseOnymousAudienceScroll(jsValue) >-> {
                                parseStar(jsValue) >-> {
                                  parsePhase(jsValue) >-> {
                                    parseFlavorText(jsValue) >-> {
                                      parseGameResult(jsValue) >-> {
                                        parseBuildVillage(jsValue) >>> {
                                          parseLeaveWaitingPage(jsValue) >>> {
                                            parseNextGameInvitation(jsValue) >>> {
                                              parseNextGameInvitationIsClosed(jsValue) >>> {
                                                parseError(jsValue).orNull
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
        }
      }
    )
  }
}
