package protocol.engine.lobby.analysis.server2client;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.server2client.PingProtocol;
import licos.protocol.engine.analysis.lobby.server2client.PingAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import protocol.engine.lobby.example.server2client.Ping;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JPingAE implements PingAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, PingProtocol pingProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(new Ping(null).type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
