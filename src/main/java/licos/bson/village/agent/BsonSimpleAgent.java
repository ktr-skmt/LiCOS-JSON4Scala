package licos.bson.village.agent;

import licos.bson.village.BsonName;
import licos.json.village.agent.JsonSimpleAgent;
import org.bson.types.ObjectId;

/**
 * <pre>
 * Created on 2018/01/10.
 * </pre>
 *
 * @author K.Sakamoto
 */
public class BsonSimpleAgent extends BsonAbstractAgent {
    @SuppressWarnings("unused")
    private BsonSimpleAgent() {
        // Do nothing
    }

    public BsonSimpleAgent(ObjectId _id,
                           String $context,
                           String $id,
                           long id,
                           BsonName name,
                           String image) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.id = id;
        this.name = name;
        this.image = image;
    }

    @Override
    public JsonSimpleAgent toJson() {
        return new JsonSimpleAgent(
                $context,
                $id,
                id,
                name.toJson(),
                image
        );
    }
}
