package protocol.engine.lobby.analysis.client2server;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.client2server.ChangeLangProtocol;
import licos.protocol.engine.analysis.lobby.client2server.ChangeLangAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import protocol.engine.lobby.example.client2server.ChangeLang;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JChangeLangAE implements ChangeLangAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, ChangeLangProtocol changeLangProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(new ChangeLang(null).type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
