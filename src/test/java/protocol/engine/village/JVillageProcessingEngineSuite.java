package protocol.engine.village;

import licos.entity.HostPlayer;
import licos.entity.VillageInfoFromLobby;
import licos.json2protocol.village.Json2VillageMessageProtocol;
import licos.knowledge.*;
import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.engine.processing.SpecificProcessingEngineFactory$;
import licos.protocol.engine.processing.VillagePE$;
import licos.protocol.engine.processing.village.VillageProcessingEngine;
import licos.protocol.engine.processing.village.VillageProcessingEngineFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.api.libs.json.Json;
import protocol.element.VillageMessageTestProtocol;
import protocol.engine.VillageExample;
import protocol.engine.village.analysis.client2server.*;
import protocol.engine.village.analysis.server2client.*;
import protocol.engine.village.example.client2server.*;
import protocol.engine.village.example.server2client.*;
import protocol.engine.village.example.server2client.FlavorText;
import protocol.engine.village.example.server2client.PostMortemDiscussion;
import scala.Option;
import scala.util.Try;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class JVillageProcessingEngineSuite {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Parameters
    public static VillageExample[] data() {
        return new VillageExample[] {
                new ReceivedChatMessage("receipt/receivedChatMessage.json"),
                new ReceivedFlavorTextMessage("receipt/receivedFlavorTextMessage.json"),
                new ReceivedSystemMessage("receipt/receivedSystemMessage.json"),
                new AnonymousAudienceChatFromClient("anonymousAudienceChat.jsonld"),
                new Board("board.jsonld"),
                new ChatFromClient("chat.jsonld"),
                new ErrorFromClient("error.jsonld"),
                new Vote("nightVote.jsonld"),
                new Vote("noonVote.jsonld"),
                new OnymousAudienceBoard("onymousAudienceBoard.jsonld"),
                new OnymousAudienceChatFromClient("onymousAudienceChat.jsonld"),
                new OnymousAudienceScroll("onymousAudienceScroll.jsonld"),
                new Scroll("scroll.jsonld"),
                new Star("star.jsonld"),
                new NextGameInvitation("invitation/nextGameInvitation.json"),
                new NextGameInvitationIsClosed("invitation/nextGameInvitationIsClosed.json"),
                new AnonymousAudienceChatFromServer("anonymousAudienceChat.jsonld"),
                new ErrorFromServer("error.jsonld"),
                new FirstMorningPhase("firstMorning.jsonld"),
                new FlavorText("flavorText.jsonld"),
                new MorningPhase("morning.jsonld"),
                new ChatFromServer("myMessageOnChat.jsonld"),
                new NightPhase("night.jsonld"),
                new NoonPhase("noon.jsonld"),
                new OnymousAudienceChatFromServer("onymousAudienceChat.jsonld"),
                new PostMortemDiscussion("postMortemDiscussion.jsonld"),
                new GameResult("result.jsonld"),
                new ChatFromServer("theirMessageOnChat.jsonld")
        };
    }

    @Parameter
    public VillageExample jsonExample;

    private final VillageProcessingEngineFactory processingEngineFactory = (
            (VillageProcessingEngineFactory) SpecificProcessingEngineFactory$.MODULE$
            .create(VillagePE$.MODULE$))
            .set(new JAnonymousAudienceChatFromClientAE())
            .set(new JBoardAE())
            .set(new JBuildVillageAE())
            .set(new JChatFromClientAE())
            .set(new JErrorFromClientAE())
            .set(new JLeaveWaitingPageAE())
            .set(new JOnymousAudienceBoardAE())
            .set(new JOnymousAudienceChatFromClientAE())
            .set(new JOnymousAudienceScrollAE())
            .set(new JReadyAE())
            .set(new JReceivedChatMessageAE())
            .set(new JReceivedFlavorTextMessageAE())
            .set(new JReceivedSystemMessageAE())
            .set(new JScrollAE())
            .set(new JStarAE())
            .set(new JVoteAE())
            .set(new JNextGameInvitationAE())
            .set(new JNextGameInvitationIsClosedAE())
            .set(new JAnonymousAudienceChatFromServerAE())
            .set(new JChatFromServerAE())
            .set(new JErrorFromServerAE())
            .set(new JFirstMorningPhaseAE())
            .set(new JFlavorTextAE())
            .set(new JGameResultAE())
            .set(new JMorningPhaseAE())
            .set(new JNightPhaseAE())
            .set(new JNoonPhaseAE())
            .set(new JOnymousAudienceChatFromServerAE())
            .set(new JPostMortemDiscussionAE());

    private final VillageProcessingEngine processingEngine = processingEngineFactory.create();

    @Test
    public void test() {
        String jsonType = jsonExample.type();
        URL url = jsonExample.path();
        log.info(url.toString());
        try {
            URLConnection connection = url.openConnection();

            try (BufferedReader br =  new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream(),
                            StandardCharsets.UTF_8)
            )) {
                String msg = br
                        .lines()
                        .collect(Collectors.joining("\n"));
                log.debug(msg);
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

                Option<VillageMessageProtocol> protocolOpt = Json2VillageMessageProtocol.toProtocolOpt(Json.parse(msg), villageInfoFromLobby);
                if (protocolOpt.nonEmpty()) {
                    VillageMessageProtocol protocol = protocolOpt.get();
                    Try<VillageMessageProtocol> responseTry = processingEngine.process(new JVillageBox(villageInfoFromLobby), protocol);
                    if (responseTry.isSuccess()) {
                        VillageMessageProtocol response = responseTry.get();
                        if (response instanceof VillageMessageTestProtocol) {
                            assertEquals(((VillageMessageTestProtocol) response).text(), jsonType);
                        } else {
                            fail("No VillageMessageTestProtocol");
                        }
                    } else {
                        fail(
                                String.join(
                                        "No response is generated.",
                                        responseTry
                                                .failed()
                                                .get()
                                                .getMessage(),
                                        msg
                                )
                        );
                    }
                } else {
                    fail("No protocol");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
