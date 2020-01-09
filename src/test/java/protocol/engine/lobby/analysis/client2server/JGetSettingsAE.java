package protocol.engine.lobby.analysis.client2server;

import static protocol.engine.lobby.example.client2server.GetSettings$.MODULE$;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.client2server.GetSettingsProtocol;
import licos.protocol.engine.analysis.lobby.client2server.GetSettingsAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JGetSettingsAE implements GetSettingsAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, GetSettingsProtocol getSettingsProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(MODULE$.type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
