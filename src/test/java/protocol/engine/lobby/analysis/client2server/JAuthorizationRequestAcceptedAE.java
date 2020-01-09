package protocol.engine.lobby.analysis.client2server;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.client2server.AuthorizationRequestAcceptedProtocol;
import licos.protocol.engine.analysis.lobby.client2server.AuthorizationRequestAcceptedAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import protocol.engine.lobby.example.client2server.AuthorizationRequestAccepted$;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JAuthorizationRequestAcceptedAE implements AuthorizationRequestAcceptedAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, AuthorizationRequestAcceptedProtocol authorizationRequestAcceptedProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(AuthorizationRequestAccepted$.MODULE$.type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
