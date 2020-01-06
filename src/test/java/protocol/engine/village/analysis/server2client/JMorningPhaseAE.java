package protocol.engine.village.analysis.server2client;

import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.element.village.server2client.MorningPhaseProtocol;
import licos.protocol.engine.analysis.village.server2client.MorningPhaseAnalysisEngine;
import licos.protocol.engine.processing.village.VillageBOX;
import licos.protocol.engine.processing.village.VillageBOXNotFoundException;
import protocol.element.VillageMessageTestProtocol;
import protocol.engine.village.JVillageBox;
import protocol.engine.village.example.server2client.MorningPhase;
import scala.util.Failure;
import scala.util.Success;
import scala.util.Try;

public class JMorningPhaseAE implements MorningPhaseAnalysisEngine {
    @Override
    public Try<VillageMessageProtocol> process(VillageBOX box, MorningPhaseProtocol morningPhase) {
        if (box instanceof JVillageBox) {
            return Success.apply(VillageMessageTestProtocol.apply(new MorningPhase(null).type()));
        } else {
            return Failure.apply(new VillageBOXNotFoundException(null, null));
        }
    }
}
