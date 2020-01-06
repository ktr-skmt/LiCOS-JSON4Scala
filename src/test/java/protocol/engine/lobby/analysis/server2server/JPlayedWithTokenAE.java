package protocol.engine.lobby.analysis.server2server;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.server2server.PlayedWithTokenProtocol;
import licos.protocol.engine.analysis.lobby.server2server.PlayedWithTokenAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import protocol.engine.lobby.example.server2server.PlayedWithToken;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JPlayedWithTokenAE implements PlayedWithTokenAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, PlayedWithTokenProtocol playedWithTokenProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(new PlayedWithToken(null).type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
