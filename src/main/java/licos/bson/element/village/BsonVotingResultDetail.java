package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.character.BsonSimpleCharacter;
import licos.json.element.village.JsonVotingResultDetail;
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
@Entity("votingResultDetails")
public class BsonVotingResultDetail extends BsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String $id;

    @Getter @Setter @Reference
    private BsonSimpleCharacter sourceCharacter;

    @Getter @Setter @Reference
    private BsonSimpleCharacter targetCharacter;

    @SuppressWarnings("unused")
    private BsonVotingResultDetail() {
        // Do nothing
    }

    public BsonVotingResultDetail(ObjectId _id,
                                  String $id,
                                  BsonSimpleCharacter sourceCharacter,
                                  BsonSimpleCharacter targetCharacter) {
        this._id = _id;
        this.$id = $id;
        this.sourceCharacter = sourceCharacter;
        this.targetCharacter = targetCharacter;
    }

    @Override
    public JsonVotingResultDetail toJson() {
        return new JsonVotingResultDetail(
                $id,
                sourceCharacter.toJson(),
                targetCharacter.toJson()
        );
    }
}
