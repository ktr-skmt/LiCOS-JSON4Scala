package protocol.engine.auth;

import licos.json2protocol.auth.Json2AuthMessageProtocol;
import licos.protocol.element.auth.AuthMessageProtocol;
import licos.protocol.engine.processing.auth.AuthProcessingEngine;
import licos.protocol.engine.processing.auth.AuthProcessingEngineFactory;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;
import play.api.libs.json.Json;
import protocol.element.AuthMessageTestProtocol;
import protocol.engine.AuthExample;
import protocol.engine.auth.analysis.robot2server.JAuthenticationAndAuthorizationRequestAE;
import protocol.engine.auth.analysis.server2robot.JAuthenticationRequestResponseAE;
import protocol.engine.auth.analysis.server2robot.JAuthorizationRequestResponseAE;
import protocol.engine.auth.example.robot2server.AuthenticationAndAuthorizationRequest;
import protocol.engine.auth.example.server2robot.AuthenticationRequestResponse;
import protocol.engine.auth.example.server2robot.AuthorizationRequestResponse;
import scala.Option;
import scala.util.Try;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@RunWith(Theories.class)
public class JAuthProcessingEngineSuite {

    @DataPoints
    private static AuthExample[] exampleSeq = {
            new AuthenticationAndAuthorizationRequest("authenticationAndAuthorizationRequest.json"),
            new AuthenticationRequestResponse("authenticationRequestResponse.json"),
            new AuthorizationRequestResponse("authorizationRequestResponse.json")
    };

    private AuthProcessingEngineFactory processingEngineFactory = new AuthProcessingEngineFactory()
            .set(new JAuthenticationAndAuthorizationRequestAE())
            .set(new JAuthenticationRequestResponseAE())
            .set(new JAuthorizationRequestResponseAE());

    private AuthProcessingEngine processingEngine = processingEngineFactory.create();

    @Theory
    public void process(AuthExample jsonExample) {
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

                Option<AuthMessageProtocol> protocolOpt = Json2AuthMessageProtocol.toProtocolOpt(Json.parse(msg));
                if (protocolOpt.nonEmpty()) {
                    AuthMessageProtocol protocol = protocolOpt.get();
                    Try<AuthMessageProtocol> responseTry = processingEngine.process(new JAuthBox(), protocol);
                    if (responseTry.isSuccess()) {
                        AuthMessageProtocol response = responseTry.get();
                        if (response instanceof AuthMessageTestProtocol) {
                            assert(((AuthMessageTestProtocol) response).text().equals(jsonType));
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
