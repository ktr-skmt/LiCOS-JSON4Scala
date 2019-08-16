package licos.bson.element.village;

import licos.bson.element.village.agent.BsonSimpleAgent;
import licos.json.element.village.JsonBoardPolarity;
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
@Entity("boardPolarities")
public class BsonBoardPolarity extends BsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter
    private String $context;

    @Getter @Setter
    private String $id;

    @Getter @Setter @Reference
    private BsonSimpleAgent agent;

    @Getter @Setter
    private String polarity;

    @Getter @Setter
    private String phase;

    @Getter @Setter
    private int date;

    @SuppressWarnings("unused")
    private BsonBoardPolarity() {
        // Do nothing
    }

    public BsonBoardPolarity(ObjectId _id,
                             String $context,
                             String $id,
                             BsonSimpleAgent agent,
                             String polarity,
                             String phase,
                             int date) {
        this.$context = $context;
        this.$id = $id;
        this._id = _id;
        this.agent = agent;
        this.polarity = polarity;
        this.phase = phase;
        this.date = date;
    }

    @Override
    public JsonBoardPolarity toJson() {
        return new JsonBoardPolarity(
                $context,
                $id,
                agent.toJson(),
                polarity,
                phase,
                date
        );
    }
}
