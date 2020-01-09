package protocol.engine.lobby.analysis.client2server;

import static protocol.engine.lobby.example.client2server.ChangeUserName$.MODULE$;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.client2server.ChangeUserNameProtocol;
import licos.protocol.engine.analysis.lobby.client2server.ChangeUserNameAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JChangeUserNameAE implements ChangeUserNameAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, ChangeUserNameProtocol changeUserNameProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(MODULE$.type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
