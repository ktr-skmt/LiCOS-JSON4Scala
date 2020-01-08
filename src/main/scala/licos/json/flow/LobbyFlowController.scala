package licos.json.flow

import licos.json.element.Element
import licos.json.flow.utils._
import licos.json.parser.LobbyParser
import play.api.libs.json.JsValue

/** This class is to control the flow of parsing play.api.libs.json.JsValue for lobby.
  *
  * @author Kotaro Sakamoto
  */
final class LobbyFlowController() extends FlowController with LobbyParser {

  /** Controls the flow of parsing play.api.libs.json.JsValue for lobby.
    *
    * @param jsValue a play.api.libs.json.JsValue for lobby to parse.
    * @return a parsing result.
    */
  @SuppressWarnings(Array[String]("org.wartremover.warts.Nothing"))
  override def flow(jsValue: JsValue): Either[JsValue, Element] = {
    parsePong(jsValue) >>> {
      parsePing(jsValue) >>> {
        parseWaitingPage(jsValue) >>> {
          parseLobby(jsValue) >>> {
            parseEnterLobby(jsValue) >>> {
              parseGetAvatarInfo(jsValue) >>> {
                parseAvatarInfo(jsValue) >>> {
                  parseSelectVillage(jsValue) >>> {
                    parseLeaveWaitingPage(jsValue) >>> {
                      parseKickOutPlayer(jsValue) >>> {
                        parseBuildVillage(jsValue) >>> {
                          parseAdvancedSearch(jsValue) >>> {
                            parseIdSearch(jsValue) >>> {
                              parsePlay(jsValue) >>> {
                                parsePlayed(jsValue) >>> {
                                  parseReady(jsValue) >>> {
                                    parseSearchResult(jsValue) >>> {
                                      parseChangeLanguage(jsValue) >>> {
                                        parseChangeUserEmail(jsValue) >>> {
                                          parseChangeUserName(jsValue) >>> {
                                            parseChangeUserPassword(jsValue) >>> {
                                              parseGetSettings(jsValue) >>> {
                                                parseSettings(jsValue) >>> {
                                                  parseAuthorizationRequest(jsValue) >>> {
                                                    parseAuthorizationRequestAccepted(jsValue) >>> {
                                                      parseAuthorizationRequestAcceptedResponse(jsValue) >>> {
                                                        parseRenewAvatarToken(jsValue) >>> {
                                                          parseNewAvatarToken(jsValue) >>> {
                                                            parseCreateHumanPlayer(jsValue) >>> {
                                                              parseCreateOnymousAudience(jsValue) >>> {
                                                                parseCreateRobotPlayer(jsValue) >>> {
                                                                  parseDeleteAvatar(jsValue) >>> {
                                                                    parseRunRobotPlayerInTheBackground(jsValue) >>> {
                                                                      parseStopRobotPlayer(jsValue) >>> {
                                                                        parseHumanPlayerSelectionPage(jsValue) >>> {
                                                                          parseOnymousAudienceSelectionPage(
                                                                            jsValue
                                                                          ) >>> {
                                                                            parseRobotPlayerSelectionPage(jsValue) >>> {
                                                                              parseUpdateAvatar(jsValue) >>> {
                                                                                parseEnterAvatarSelectionPage(jsValue) >>> {
                                                                                  parsePlayedWithToken(jsValue)
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
