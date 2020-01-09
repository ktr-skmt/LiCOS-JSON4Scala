package protocol.engine.lobby.analysis.server2client;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.server2client.PlayedProtocol;
import licos.protocol.engine.analysis.lobby.server2client.PlayedAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import protocol.engine.lobby.example.server2client.Played$;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JPlayedAE implements PlayedAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, PlayedProtocol playedProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(Played$.MODULE$.type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
