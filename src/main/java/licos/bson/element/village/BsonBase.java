package licos.bson.element.village;

import licos.bson.element.village.agent.BsonStatusAgent;
import licos.json.element.village.JsonBase;
import licos.json.element.village.JsonVotingResultDetail;
import licos.json.element.village.JsonVotingResultSummary;
import licos.json.element.village.agent.JsonStatusAgent;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * Created on 2018/01/11.
 * </pre>
 *
 * @author K.Sakamoto
 */
@Entity("bases")
public class BsonBase extends BsonElementToJsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private List<String> $context;

    @Getter @Setter
    private String $id;

    @Getter @Setter @Reference
    private BsonVillage village;

    @Getter @Setter
    private String token;

    @Getter @Setter
    private String phase;

    @Getter @Setter
    private int date;

    @Getter @Setter
    private int phaseTimeLimit;

    @Getter @Setter
    private String phaseStartTime;

    @Getter @Setter
    private String serverTimestamp;

    @Getter @Setter
    private String clientTimestamp;

    @Getter @Setter
    private String directionality;

    @Getter @Setter
    private String intensionalDisclosureRange;

    @Getter @Setter @Reference
    private List<BsonStatusAgent> extensionalDisclosureRange;

    @Getter @Setter @Reference
    private List<BsonVotingResultSummary> votingResultSummary;

    @Getter @Setter @Reference
    private List<BsonVotingResultDetail> votingResultDetail;

    @SuppressWarnings("unused")
    private BsonBase() {
        // Do nothing
    }

    public BsonBase(ObjectId _id,
                    List<String> $context,
                    String $id,
                    BsonVillage village,
                    String token,
                    String phase,
                    int date,
                    int phaseTimeLimit,
                    String phaseStartTime,
                    String serverTimestamp,
                    String clientTimestamp,
                    String directionality,
                    String intensionalDisclosureRange,
                    List<BsonStatusAgent> extensionalDisclosureRange,
                    List<BsonVotingResultSummary> votingResultSummary,
                    List<BsonVotingResultDetail> votingResultDetail) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.village = village;
        this.token = token;
        this.phase = phase;
        this.date = date;
        this.phaseTimeLimit = phaseTimeLimit;
        this.phaseStartTime = phaseStartTime;
        this.serverTimestamp = serverTimestamp;
        this.clientTimestamp = clientTimestamp;
        this.directionality = directionality;
        this.intensionalDisclosureRange = intensionalDisclosureRange;
        this.extensionalDisclosureRange = extensionalDisclosureRange;
        this.votingResultSummary = votingResultSummary;
        this.votingResultDetail = votingResultDetail;
    }

    private List<JsonStatusAgent> extensionalDisclosureRangeToJson() {
        return  elementToJson(extensionalDisclosureRange).
                map(element -> (JsonStatusAgent) element).
                collect(Collectors.toCollection(LinkedList::new));
    }

    private List<JsonVotingResultSummary> votingResultSummaryToJson() {
        return elementToJson(votingResultSummary).
                map(element -> (JsonVotingResultSummary) element).
                collect(Collectors.toCollection(LinkedList::new));
    }

    private List<JsonVotingResultDetail> votingResultDetailToJson() {
        return elementToJson(votingResultDetail).
                map(element -> (JsonVotingResultDetail) element).
                collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public JsonBase toJson() {
        return new JsonBase(
                $context,
                $id,
                village.toJson(),
                token,
                phase,
                date,
                phaseTimeLimit,
                phaseStartTime,
                serverTimestamp,
                clientTimestamp,
                directionality,
                intensionalDisclosureRange,
                extensionalDisclosureRangeToJson(),
                votingResultSummaryToJson(),
                votingResultDetailToJson()
        );
    }
}
