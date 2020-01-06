package protocol.engine.lobby.analysis.server2client;

import licos.protocol.element.lobby.LobbyMessageProtocol;
import licos.protocol.element.lobby.server2client.SettingsProtocol;
import licos.protocol.engine.analysis.lobby.server2client.SettingsAnalysisEngine;
import licos.protocol.engine.processing.lobby.LobbyBOX;
import licos.protocol.engine.processing.lobby.LobbyBOXNotFoundException;
import protocol.element.LobbyMessageTestProtocol;
import protocol.engine.lobby.JLobbyBox;
import protocol.engine.lobby.example.server2client.Settings;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JSettingsAE implements SettingsAnalysisEngine {
    @Override
    public Try<LobbyMessageProtocol> process(LobbyBOX box, SettingsProtocol settingsProtocol) {
        if (box instanceof JLobbyBox) {
            return Success.apply(LobbyMessageTestProtocol.apply(new Settings(null).type()));
        } else {
            return Failure.apply(new LobbyBOXNotFoundException(null, null));
        }
    }
}
