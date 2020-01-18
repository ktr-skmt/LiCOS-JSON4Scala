package sample;

import licos.entity.HostPlayer;
import licos.entity.VillageInfoFromLobby;
import licos.json2protocol.village.Json2VillageMessageProtocol;
import licos.knowledge.Composition;
import licos.knowledge.HumanArchitecture$;
import licos.knowledge.HumanPlayerLobby$;
import licos.knowledge.RandomAvatarSetting$;
import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.engine.processing.SpecificProcessingEngineFactory$;
import licos.protocol.engine.processing.VillagePE$;
import licos.protocol.engine.processing.village.VillageBOX;
import licos.protocol.engine.processing.village.VillageProcessingEngine;
import licos.protocol.engine.processing.village.VillageProcessingEngineFactory;
import play.api.libs.json.JsValue;
import play.api.libs.json.Json;
import protocol.engine.village.JVillageBox;
import protocol.engine.village.analysis.server2client.JFirstMorningPhaseAE;
import scala.Option;
import scala.util.Try;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class JProtocolVillageMessageRunner {

    public static void main(String[] args) {

        VillageProcessingEngineFactory processingEngineFactory = (
                (VillageProcessingEngineFactory) SpecificProcessingEngineFactory$.MODULE$
                        .create(VillagePE$.MODULE$))
                        .set(new JFirstMorningPhaseAE());

        VillageProcessingEngine processingEngine = processingEngineFactory.create();

        try {
            URL url = new URL("https://raw.githubusercontent.com/ktr-skmt/werewolfworld/gh-pages/village/example/0.3/server2client/firstMorning.jsonld");
            URLConnection connection = url.openConnection();

            String msg;
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream(),
                            StandardCharsets.UTF_8)
            )) {
                msg = br
                        .lines()
                        .collect(Collectors.joining("\n"));
            }
            JsValue aJSONExampleOfTheLiCOSProtocol = Json.parse(msg);

            HostPlayer hostPlayer = new HostPlayer(
                    1L,
                    "Anonymous",
                    true,
                    HumanArchitecture$.MODULE$
            );

            VillageInfoFromLobby villageInfoFromLobby = new VillageInfoFromLobby(
                    HumanPlayerLobby$.MODULE$,
                    hostPlayer,
                    Composition.support().A(15).get(),
                    1,
                    RandomAvatarSetting$.MODULE$,
                    15,
                    Option.empty(),
                    "Christopher",
                    new URL("https://werewolf.world/image/0.3/character_icons/50x50/a_50x50.png")
            );

            VillageBOX anExampleOfBOX = new JVillageBox(villageInfoFromLobby);

            Option<VillageMessageProtocol> protocolOpt = Json2VillageMessageProtocol.toProtocolOpt(aJSONExampleOfTheLiCOSProtocol, villageInfoFromLobby);
            if (protocolOpt.nonEmpty()) {
                VillageMessageProtocol protocol = protocolOpt.get();
                Try<VillageMessageProtocol> responseTry = processingEngine.process(anExampleOfBOX, protocol);
                if (responseTry.isSuccess()) {
                    VillageMessageProtocol response = responseTry.get();
                    Option<JsValue> jsonOpt = response.toJsonOpt();
                    if (jsonOpt.nonEmpty()) {
                        System.err.println(Json.prettyPrint(jsonOpt.get()));
                    }
                }
            }
        } catch (IOException | NullPointerException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
