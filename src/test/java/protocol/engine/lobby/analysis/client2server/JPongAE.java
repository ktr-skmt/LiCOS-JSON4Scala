package protocol.engine.lobby.analysis.client2server;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.client2server.PongProtocol;
import licos.protocol.engine.analysis.lobby.client2server.PongAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import protocol.engine.lobby.example.client2server.Pong;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JPongAE implements PongAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, PongProtocol pongProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(new Pong("").type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
