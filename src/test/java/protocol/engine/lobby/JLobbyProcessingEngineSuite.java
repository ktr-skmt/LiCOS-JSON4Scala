package protocol.engine.lobby;

import licos.json2protocol.lobby.Json2LobbyMessageProtocol;
import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.engine.processing.lobby.LobbyProcessingEngine;
import licos.protocol.engine.processing.lobby.LobbyProcessingEngineFactory;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import play.api.libs.json.Json;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.LobbyExample;

import protocol.engine.lobby.analysis.client2server.*;
import protocol.engine.lobby.analysis.server2client.*;
import protocol.engine.lobby.analysis.server2server.JPlayedWithTokenAE;
import protocol.engine.lobby.example.client2server.*;
import protocol.engine.lobby.example.server2client.*;
import protocol.engine.lobby.example.server2server.PlayedWithToken;
import scala.Option;
import scala.util.Try;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@RunWith(Theories.class)
public class JLobbyProcessingEngineSuite {

    @DataPoints
    private static LobbyExample[] exampleSeq = {
            new AdvancedSearch("advancedSearch.json"),
            new BuildVillage("buildVillage.json"),
            new ChangeLang("changeLang.json"),
            new ChangeUserEmail("changeUserEmail.json"),
            new ChangeUserName("changeUserName.json"),
            new ChangeUserPassword("changeUserPassword.json"),
            new EnterLobby("enterLobbyForAnonymousAudience.json"),
            new EnterLobby("enterLobbyForHumanPlayer.json"),
            new EnterLobby("enterLobbyForOnymousAudience.json"),
            new EnterLobby("enterLobbyForRobotPlayer.json"),
            new GetAvatarInfo("getAvatar.json"),
            new GetSettings("getSettings.json"),
            new IdSearch("idSearch.json"),
            new KickOutPlayer("kickOutPlayer.json"),
            new LeaveWaitingPage("leaveWaitingPage.json"),
            new Play("play.json"),
            new Pong("pong.json"),
            new Ready("ready.json"),
            new SelectVillage("selectVillageForHumanPlayer.json"),
            new AuthorizationRequest("authorizationRequest.json"),
            new AuthorizationRequestAcceptedResponse("authorizationRequestAcceptedResponse.json"),
            new AvatarInfo("avatar.json"),
            new Lobby("lobbyForHumanPlayer.json"),
            new Ping("ping.json"),
            new Played("played.json"),
            new SearchResult("searchResult.json"),
            new Settings("settings.json"),
            new WaitingPage("waitingPageForHumanPlayer.json"),
            new PlayedWithToken("playedWithToken.json"),
            new AuthorizationRequestAccepted("authorizationRequestAccepted.json"),
            new RenewAvatarToken("renewAvatarToken.json"),
            new NewAvatarToken("newAvatarToken.json"),
            new CreateHumanPlayer("createHumanPlayer.json"),
            new CreateOnymousAudience("createOnymousAudience.json"),
            new CreateRobotPlayer("createRobotPlayer.json"),
            new DeleteAvatar("deleteAvatar.json"),
            new RunRobotPlayerInTheBackground("runRobotPlayerInTheBackground.json"),
            new RunRobotPlayerInTheForeground("runRobotPlayerInTheForeground.json"),
            new SelectHumanPlayer("selectHumanPlayer.json"),
            new SelectOnymousAudience("selectOnymousAudience.json"),
            new StopRobotPlayer("stopRobotPlayer.json"),
            new HumanPlayerSelectionPage("humanPlayerSelectionPage.json"),
            new OnymousAudienceSelectionPage("onymousAudienceSelectionPage.json"),
            new RobotPlayerSelectionPage("robotPlayerSelectionPage.json")
    };

    private LobbyProcessingEngineFactory processingEngineFactory = new LobbyProcessingEngineFactory()
            .set(new JAdvancedSearchAE())
            .set(new JAvatarInfoAE())
            .set(new JBuildVillageAE())
            .set(new JChangeLangAE())
            .set(new JChangeUserEmailAE())
            .set(new JChangeUserNameAE())
            .set(new JChangeUserPasswordAE())
            .set(new JEnterLobbyAE())
            .set(new JGetAvatarInfoAE())
            .set(new JGetSettingsAE())
            .set(new JIdSearchAE())
            .set(new JKickOutPlayerAE())
            .set(new JLeaveWaitingPageAE())
            .set(new JLobbyAE())
            .set(new JPingAE())
            .set(new JPlayAE())
            .set(new JPlayedAE())
            .set(new JPlayedWithTokenAE())
            .set(new JPongAE())
            .set(new JReadyAE())
            .set(new JSearchResultAE())
            .set(new JSelectVillageAE())
            .set(new JSettingsAE())
            .set(new JWaitingPageAE())
            .set(new JAuthorizationRequestAE())
            .set(new JAuthorizationRequestAcceptedResponseAE())
            .set(new JAuthorizationRequestAcceptedAE())
            .set(new JRenewAvatarTokenAE())
            .set(new JNewAvatarTokenAE())
            .set(new JCreateHumanPlayerAE())
            .set(new JCreateOnymousAudienceAE())
            .set(new JCreateRobotPlayerAE())
            .set(new JDeleteAvatarAE())
            .set(new JRunRobotPlayerInTheBackgroundAE())
            .set(new JRunRobotPlayerInTheForegroundAE())
            .set(new JSelectHumanPlayerAE())
            .set(new JSelectOnymousAudienceAE())
            .set(new JStopRobotPlayerAE())
            .set(new JHumanPlayerSelectionPageAE())
            .set(new JOnymousAudienceSelectionPageAE())
            .set(new JRobotPlayerSelectionPageAE());

    private LobbyProcessingEngine processingEngine = processingEngineFactory.create();

    @Theory
    public void process(LobbyExample jsonExample) {
        String jsonType = jsonExample.type();
        try {
            URLConnection connection = jsonExample.path().openConnection();

            try (BufferedReader br =  new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream(),
                            StandardCharsets.UTF_8)
            )) {
                String msg = br
                        .lines()
                        .collect(Collectors.joining("\n"));

                Option<LobbyMessageProtocol> protocolOpt = Json2LobbyMessageProtocol.toProtocolOpt(Json.parse(msg));
                if (protocolOpt.nonEmpty()) {
                    LobbyMessageProtocol protocol = protocolOpt.get();
                    Try<LobbyMessageProtocol> responseTry = processingEngine.process(new JLobbyBox(), protocol);
                    if (responseTry.isSuccess()) {
                        LobbyMessageProtocol response = responseTry.get();
                        if (response instanceof LobbyMessageTestProtocol) {
                            assert(((LobbyMessageTestProtocol) response).text().equals(jsonType));
                        } else {
                            assert(false);
                        }
                    } else {
                        assert(false);
                    }
                } else {
                    assert(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
