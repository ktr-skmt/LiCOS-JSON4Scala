package licos.bson.village;

import licos.bson.village.agent.BsonSimpleAgent;
import licos.json.village.JsonVotingResultDetail;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

/**
 * <pre>
 * Created on 2018/01/11.
 * </pre>
 *
 * @author K.Sakamoto
 */
@Entity("votingResultDetails")
public class BsonVotingResultDetail extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String $id;

    @Getter @Setter @Reference
    private BsonSimpleAgent sourceAgent;

    @Getter @Setter @Reference
    private BsonSimpleAgent targetAgent;

    @SuppressWarnings("unused")
    private BsonVotingResultDetail() {
        // Do nothing
    }

    public BsonVotingResultDetail(ObjectId _id,
                                  String $id,
                                  BsonSimpleAgent sourceAgent,
                                  BsonSimpleAgent targetAgent) {
        this._id = _id;
        this.$id = $id;
        this.sourceAgent = sourceAgent;
        this.targetAgent = targetAgent;
    }

    @Override
    public JsonVotingResultDetail toJson() {
        return new JsonVotingResultDetail(
                $id,
                sourceAgent.toJson(),
                targetAgent.toJson()
        );
    }
}
