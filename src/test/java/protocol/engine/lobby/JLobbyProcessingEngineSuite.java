package protocol.engine.lobby;

import licos.json2protocol.lobby.Json2LobbyMessageProtocol;
import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.engine.processing.LobbyPE$;
import licos.protocol.engine.processing.SpecificProcessingEngineFactory$;
import licos.protocol.engine.processing.lobby.LobbyProcessingEngine;
import licos.protocol.engine.processing.lobby.LobbyProcessingEngineFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class JLobbyProcessingEngineSuite {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Parameters
    public static LobbyExample[] data() {
        return new LobbyExample[] {
            new AdvancedSearch("advancedSearch.json"),
            new BuildVillage("buildVillage.json"),
            new ChangeLanguage("changeLanguage.json"),
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
            new SelectVillage("selectVillage.json"),
            new AuthorizationRequest("authorizationRequest.json"),
            new AuthorizationRequestAcceptedResponse("authorizationRequestAcceptedResponse.json"),
            new AvatarInfo("avatar.json"),
            new Lobby("lobbyForAnonymousAudience.json"),
            new Lobby("lobbyForHumanPlayer.json"),
            new Lobby("lobbyForOnymousAudience.json"),
            new Lobby("lobbyForRobotPlayer.json"),
            new Ping("ping.json"),
            new Played("played.json"),
            new SearchResult("searchResult.json"),
            new Settings("settings.json"),
            new WaitingPage("waitingPage.json"),
            new PlayedWithToken("playedWithToken.json"),
            new AuthorizationRequestAccepted("authorizationRequestAccepted.json"),
            new RenewAvatarToken("renewAvatarToken.json"),
            new CreateHumanPlayer("createHumanPlayer.json"),
            new CreateOnymousAudience("createOnymousAudience.json"),
            new CreateRobotPlayer("createRobotPlayer.json"),
            new DeleteAvatar("deleteAvatar.json"),
            new RunRobotPlayerInTheBackground("runRobotPlayerInTheBackground.json"),
            new StopRobotPlayer("stopRobotPlayer.json"),
            new HumanPlayerSelectionPage("humanPlayerSelectionPage.json"),
            new OnymousAudienceSelectionPage("onymousAudienceSelectionPage.json"),
            new RobotPlayerSelectionPage("robotPlayerSelectionPage.json"),
            new ChangeAvatar("updateAvatarName.json"),
            new ChangeAvatar("updateAvatarImage.json"),
            new ChangeAvatar("updateAvatarLanguage.json"),
            new EnterAvatarSelectionPage("enterAvatarSelectionPage.json")
        };
    }

    @Parameter
    public LobbyExample jsonExample;

    private final LobbyProcessingEngineFactory processingEngineFactory = (
            (LobbyProcessingEngineFactory) SpecificProcessingEngineFactory$.MODULE$
            .create(LobbyPE$.MODULE$))
            .set(new JAdvancedSearchAE())
            .set(new JAvatarInfoAE())
            .set(new JBuildVillageAE())
            .set(new JChangeLanguageAE())
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
            .set(new JCreateHumanPlayerAE())
            .set(new JCreateOnymousAudienceAE())
            .set(new JCreateRobotPlayerAE())
            .set(new JDeleteAvatarAE())
            .set(new JRunRobotPlayerInTheBackgroundAE())
            .set(new JStopRobotPlayerAE())
            .set(new JHumanPlayerSelectionPageAE())
            .set(new JOnymousAudienceSelectionPageAE())
            .set(new JRobotPlayerSelectionPageAE())
            .set(new JChangeAvatarAE())
            .set(new JEnterAvatarSelectionPageAE());

    private final LobbyProcessingEngine processingEngine = processingEngineFactory.create();

    @Test
    public void test() {
        String jsonType = jsonExample.type();
        URL url = jsonExample.path();
        log.info(url.toString());
        try {
            URLConnection connection = url.openConnection();

            try (BufferedReader br =  new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream(),
                            StandardCharsets.UTF_8)
            )) {
                String msg = br
                        .lines()
                        .collect(Collectors.joining("\n"));
                log.debug(msg);
                Option<LobbyMessageProtocol> protocolOpt = Json2LobbyMessageProtocol.toProtocolOpt(Json.parse(msg));
                if (protocolOpt.nonEmpty()) {
                    LobbyMessageProtocol protocol = protocolOpt.get();
                    Try<LobbyMessageProtocol> responseTry = processingEngine.process(new JLobbyBox(), protocol);
                    if (responseTry.isSuccess()) {
                        LobbyMessageProtocol response = responseTry.get();
                        if (response instanceof LobbyMessageTestProtocol) {
                            assertEquals(((LobbyMessageTestProtocol) response).text(), jsonType);
                        } else {
                            fail("No LobbyMessageTestProtocol");
                        }
                    } else {
                        fail(
                                String.join(
                                        "No response is generated.",
                                        responseTry
                                                .failed()
                                                .get()
                                                .getMessage(),
                                        msg
                                )
                        );
                    }
                } else {
                    fail("No protocol");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
