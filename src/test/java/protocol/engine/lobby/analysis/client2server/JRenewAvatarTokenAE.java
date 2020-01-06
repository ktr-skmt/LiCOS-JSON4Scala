package protocol.engine.lobby.analysis.client2server;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.client2server.RenewAvatarTokenProtocol;
import licos.protocol.engine.analysis.lobby.client2server.RenewAvatarTokenAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import protocol.engine.lobby.example.client2server.RenewAvatarToken;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JRenewAvatarTokenAE implements RenewAvatarTokenAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, RenewAvatarTokenProtocol renewAvatarTokenProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(new RenewAvatarToken(null).type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
