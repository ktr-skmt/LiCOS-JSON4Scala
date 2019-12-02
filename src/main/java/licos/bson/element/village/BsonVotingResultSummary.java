package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.character.BsonSimpleCharacter;
import licos.json.element.village.JsonVotingResultSummary;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("votingResultSummaries")
public class BsonVotingResultSummary extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String $id;

    @Getter @Setter @Reference
    private BsonSimpleCharacter characterToPutToDeath;

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
                                   BsonSimpleCharacter characterToPutToDeath,
                                   int numberOfVotes,
                                   int rankOfVotes) {
        this._id = _id;
        this.$id = $id;
        this.characterToPutToDeath = characterToPutToDeath;
        this.numberOfVotes = numberOfVotes;
        this.rankOfVotes = rankOfVotes;
    }

    @Override
    public JsonVotingResultSummary toJson() {
        return new JsonVotingResultSummary(
                $id,
                characterToPutToDeath.toJson(),
                numberOfVotes,
                rankOfVotes
        );
    }
}
