package licos.bson.element.village;

import licos.bson.element.village.character.BsonResultCharacter;
import licos.bson.element.village.role.BsonResultRole;
import licos.json.element.village.JsonGameResult;
import licos.json.element.village.character.JsonResultCharacter;
import licos.json.element.village.role.JsonResultRole;
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
@Entity("gameResults")
public class BsonGameResult extends BsonElementToJsonElement {
    @Id @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private List<BsonResultCharacter> character;

    @Getter @Setter @Reference
    private List<BsonResultRole> role;

    @SuppressWarnings("unused")
    private BsonGameResult() {
        // Do nothing
    }

    public BsonGameResult(ObjectId _id,
                          BsonBase base,
                          List<BsonResultCharacter> character,
                          List<BsonResultRole> role) {
        this._id = _id;
        this.base = base;
        this.character = character;
        this.role = role;
    }

    private List<JsonResultCharacter> characterToJson() {
        return elementToJson(character).
                map(element -> (JsonResultCharacter) element).
                collect(Collectors.toCollection(LinkedList::new));
    }

    private List<JsonResultRole> roleToJson() {
        return elementToJson(role).
                map(element -> (JsonResultRole) element).
                collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public JsonGameResult toJson() {
        return new JsonGameResult(
                base.toJson(),
                characterToJson(),
                roleToJson()
        );
    }
}
