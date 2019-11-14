package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.character.BsonSimpleCharacter;
import licos.json.element.village.JsonVotingResultSummary;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

/**
 * <pre>
 * Created on 2018/01/11.
 * </pre>
 *
 * @author K.Sakamoto
 */
@Entity("votingResultSummaries")
public class BsonVotingResultSummary extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String $id;

    @Getter @Setter @Reference
    private BsonSimpleCharacter characterToLynch;

    @Getter @Setter
    private int numberOfVotes;

    @Getter @Setter
    private int rankOfVotes;

    @SuppressWarnings("unused")
    private BsonVotingResultSummary() {
        // Do nothing
    }

    public BsonVotingResultSummary(ObjectId _id,
                                   String $id,
                                   BsonSimpleCharacter characterToLynch,
                                   int numberOfVotes,
                                   int rankOfVotes) {
        this._id = _id;
        this.$id = $id;
        this.characterToLynch = characterToLynch;
        this.numberOfVotes = numberOfVotes;
        this.rankOfVotes = rankOfVotes;
    }

    @Override
    public JsonVotingResultSummary toJson() {
        return new JsonVotingResultSummary(
                $id,
                characterToLynch.toJson(),
                numberOfVotes,
                rankOfVotes
        );
    }
}
