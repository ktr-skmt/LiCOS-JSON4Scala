package protocol.engine.village.analysis.server2client;

import static protocol.engine.village.example.server2client.NextGameInvitation$.MODULE$;

import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.element.village.server2client.NextGameInvitationProtocol;
import licos.protocol.engine.analysis.village.server2client.NextGameInvitationAnalysisEngine;
import licos.protocol.engine.processing.village.VillageBOX;
import licos.protocol.engine.processing.village.VillageBOXNotFoundException;
import protocol.element.VillageMessageTestProtocol;
import protocol.engine.village.JVillageBox;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JNextGameInvitationAE implements NextGameInvitationAnalysisEngine {
    @Override
    public Try<VillageMessageProtocol> process(VillageBOX box, NextGameInvitationProtocol nextGameInvitation) {
        if (box instanceof JVillageBox) {
            return Success.apply(VillageMessageTestProtocol.apply(MODULE$.type()));
        } else {
            return Failure.apply(new VillageBOXNotFoundException(null, null));
        }
    }
}
