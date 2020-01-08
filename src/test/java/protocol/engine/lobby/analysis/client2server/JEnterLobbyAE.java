package protocol.engine.lobby.analysis.client2server;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.client2server.EnterLobbyProtocol;
import licos.protocol.engine.analysis.lobby.client2server.EnterLobbyAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import protocol.engine.lobby.example.client2server.EnterLobby;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JEnterLobbyAE implements EnterLobbyAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, EnterLobbyProtocol enterLobbyProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(new EnterLobby("").type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
