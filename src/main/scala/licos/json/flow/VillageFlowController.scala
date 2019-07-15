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
        parseReceivedPlayerMessage(jsValue) >>> {
          parseReceivedSystemMessage(jsValue) >>> {
            parseReceivedFlavorTextMessage(jsValue) >>> {
              parseChatFromClient(jsValue) >-> {
                parseChatFromServer(jsValue) >-> {
                  parseBoard(jsValue) >-> {
                    parseVote(jsValue) >-> {
                      parseScroll(jsValue) >-> {
                        parsePhase(jsValue) >-> {
                          parseFlavorText(jsValue) >-> {
                            parseGameResult(jsValue) >-> {
                              parseBuildVillage(jsValue) >>> {
                                parseLeaveWaitingPage(jsValue) >>> {
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
    )
  }
}
