package protocol.engine.village;

import licos.entity.HostPlayer;
import licos.entity.VillageInfoFromLobby;
import licos.json2protocol.village.Json2VillageMessageProtocol;
import licos.knowledge.*;
import licos.protocol.element.village.VillageMessageProtocol;
import licos.protocol.engine.processing.village.VillageProcessingEngine;
import licos.protocol.engine.processing.village.VillageProcessingEngineFactory;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
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

@RunWith(Theories.class)
public class JVillageProcessingEngineSuite {

    @DataPoints
    public static VillageExample[] exampleSeq = {
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

    private VillageProcessingEngineFactory processingEngineFactory = new VillageProcessingEngineFactory()
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

    private VillageProcessingEngine processingEngine = processingEngineFactory.create();

    @Theory
    public void process(VillageExample jsonExample) {
        String jsonType = jsonExample.type();
        try {
            URLConnection connection = jsonExample.path().openConnection();

            try (BufferedReader br =  new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream(),
                            StandardCharsets.UTF_8)
            )) {
                String msg = br
                        .lines()
                        .collect(Collectors.joining("\n"));

                HostPlayer hostPlayer = new HostPlayer(
                        1L,
                        "Anonymous",
                        true,
                        null
                );
                VillageInfoFromLobby villageInfoFromLobby = new VillageInfoFromLobby(
                        null,
                        hostPlayer,
                        Composition.support().A(15).get(),
                        1,
                        null,
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
                            assert(((VillageMessageTestProtocol) response).text().equals(jsonType));
                        } else {
                            assert(false);
                        }
                    } else {
                        assert(false);
                    }
                } else {
                    assert(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
