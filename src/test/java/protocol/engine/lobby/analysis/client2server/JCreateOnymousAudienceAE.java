package protocol.engine.lobby.analysis.client2server;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.client2server.CreateOnymousAudienceProtocol;
import licos.protocol.engine.analysis.lobby.client2server.CreateOnymousAudienceAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import protocol.engine.lobby.example.client2server.CreateOnymousAudience;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JCreateOnymousAudienceAE implements CreateOnymousAudienceAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, CreateOnymousAudienceProtocol createOnymousAudienceProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(new CreateOnymousAudience(null).type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
