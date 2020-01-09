package protocol.engine.village.analysis.client2server;

import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.element.village.client2server.OnymousAudienceChatFromClientProtocol;
import licos.protocol.engine.analysis.village.client2server.OnymousAudienceChatFromClientAnalysisEngine;
import licos.protocol.engine.processing.village.VillageBOX;
import licos.protocol.engine.processing.village.VillageBOXNotFoundException;
import protocol.element.VillageMessageTestProtocol;
import protocol.engine.village.JVillageBox;
import protocol.engine.village.example.client2server.OnymousAudienceChatFromClient$;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JOnymousAudienceChatFromClientAE implements OnymousAudienceChatFromClientAnalysisEngine {

    @Override
    public Try<VillageMessageProtocol> process(VillageBOX box, OnymousAudienceChatFromClientProtocol onymousAudienceChatFromClient) {
        if (box instanceof JVillageBox) {
            return Success.apply(VillageMessageTestProtocol.apply(OnymousAudienceChatFromClient$.MODULE$.type()));
        } else {
            return Failure.apply(new VillageBOXNotFoundException(null, null));
        }
    }
}
