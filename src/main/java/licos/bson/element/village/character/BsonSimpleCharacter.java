package licos.bson.element.village.character;

import licos.bson.element.village.BsonName;
import licos.json.element.village.character.JsonSimpleCharacter;
import org.bson.types.ObjectId;

/**
 * <pre>
 * Created on 2018/01/10.
 * </pre>
 *
 * @author K.Sakamoto
 */
public class BsonSimpleCharacter extends BsonAbstractCharacter {
    @SuppressWarnings("unused")
    private BsonSimpleCharacter() {
        // Do nothing
    }

    public BsonSimpleCharacter(ObjectId _id,
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
    public JsonSimpleCharacter toJson() {
        return new JsonSimpleCharacter(
                $context,
                $id,
                id,
                name.toJson(),
                image
        );
    }
}
