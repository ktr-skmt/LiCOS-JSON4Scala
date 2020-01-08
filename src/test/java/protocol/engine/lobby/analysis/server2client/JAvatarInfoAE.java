package protocol.engine.lobby.analysis.server2client;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.server2client.AvatarInfoProtocol;
import licos.protocol.engine.analysis.lobby.server2client.AvatarInfoAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import protocol.engine.lobby.example.server2client.AvatarInfo;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JAvatarInfoAE implements AvatarInfoAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, AvatarInfoProtocol avatarInfoProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(new AvatarInfo("").type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
