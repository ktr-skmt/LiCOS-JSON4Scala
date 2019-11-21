package licos.protocol.engine.processing

class LobbyProcessingEngine(pongEngine:               Option[PongAnalysisEngine],
                            pingEngine:               Option[PingAnalysisEngine],
                            waitingPageEngine:        Option[WaitingPageAnalysisEngine],
                            lobbyEngine:              Option[LobbyAnalysisEngine],
                            enterLobbyEngine:         Option[EnterLobbyAnalysisEngine],
                            getAvatarInfoEngine:      Option[GetAvatarInfoAnalysisEngine],
                            avatarInfoEngine:         Option[AvatarInfoAnalysisEngine],
                            selectVillageEngine:      Option[SelectVillageAnalysisEngine],
                            leaveWaitingPageEngine:   Option[LeaveWaitingPageAnalysisEngine],
                            kickOutPlayerEngine:      Option[KickOutPlayerAnalysisEngine],
                            buildVillageEngine:       Option[BuildVillageAnalysisEngine],
                            advancedSearchEngine:     Option[AdvancedSearchAnalysisEngine],
                            idSearchEngine:           Option[IdSearchAnalysisEngine],
                            playEngine:               Option[PlayAnalysisEngine],
                            playedEngine:             Option[PlayedAnalysisEngine],
                            playedWithTokenEngine:    Option[PlayedWithTokenAnalysisEngine],
                            readyEngine:              Option[ReadyAnalysisEngine],
                            searchResultEngine:       Option[SearchResultAnalysisEngine],
                            changeLangEngine:         Option[ChangeLangAnalysisEngine],
                            changeUserEmailEngine:    Option[ChangeUserEmailAnalysisEngine],
                            changeUserNameEngine:     Option[ChangeUserNameAnalysisEngine],
                            changeUserPasswordEngine: Option[ChangeUserPasswordAnalysisEngine],
                            getSettingsEngine:        Option[GetSettingsAnalysisEngine],
                            settingsEngine:           Option[SettingsAnalysisEngine]
                           ) extends ProcessingEngine {

}
