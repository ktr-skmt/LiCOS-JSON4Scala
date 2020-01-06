package protocol.engine.village.analysis.server2client;

import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.element.village.server2client.FirstMorningPhaseProtocol;
import licos.protocol.engine.analysis.village.server2client.FirstMorningPhaseAnalysisEngine;
import licos.protocol.engine.processing.village.VillageBOX;
import licos.protocol.engine.processing.village.VillageBOXNotFoundException;
import protocol.element.VillageMessageTestProtocol;
import protocol.engine.village.JVillageBox;
import protocol.engine.village.example.server2client.FirstMorningPhase;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JFirstMorningPhaseAE implements FirstMorningPhaseAnalysisEngine {
    @Override
    public Try<VillageMessageProtocol> process(VillageBOX box, FirstMorningPhaseProtocol firstMorningPhase) {
        if (box instanceof JVillageBox) {
            return Success.apply(VillageMessageTestProtocol.apply(new FirstMorningPhase(null).type()));
        } else {
            return Failure.apply(new VillageBOXNotFoundException(null, null));
        }
    }
}
