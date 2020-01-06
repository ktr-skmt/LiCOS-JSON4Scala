package protocol.engine.lobby.analysis.server2client;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.server2client.AuthorizationRequestProtocol;
import licos.protocol.engine.analysis.lobby.server2client.AuthorizationRequestAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import protocol.engine.lobby.example.server2client.AuthorizationRequest;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JAuthorizationRequestAE implements AuthorizationRequestAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, AuthorizationRequestProtocol authorizationRequestProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(new AuthorizationRequest(null).type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
