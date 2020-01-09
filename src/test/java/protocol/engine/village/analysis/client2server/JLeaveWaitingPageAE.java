package protocol.engine.village.analysis.client2server;

import static protocol.engine.village.example.client2server.LeaveWaitingPage$.MODULE$;

import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.element.village.client2server.LeaveWaitingPageProtocol;
import licos.protocol.engine.analysis.village.client2server.LeaveWaitingPageAnalysisEngine;
import licos.protocol.engine.processing.village.VillageBOX;
import licos.protocol.engine.processing.village.VillageBOXNotFoundException;
import protocol.element.VillageMessageTestProtocol;
import protocol.engine.village.JVillageBox;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JLeaveWaitingPageAE implements LeaveWaitingPageAnalysisEngine {
    @Override
    public Try<VillageMessageProtocol> process(VillageBOX box, LeaveWaitingPageProtocol leaveWaitingPageProtocol) {
        if (box instanceof JVillageBox) {
            return Success.apply(VillageMessageTestProtocol.apply(MODULE$.type()));
        } else {
            return Failure.apply(new VillageBOXNotFoundException(null, null));
        }
    }
}
