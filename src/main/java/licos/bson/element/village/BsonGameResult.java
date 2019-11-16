package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.character.BsonResultCharacter;
import licos.bson.element.village.role.BsonResultRole;
import licos.json.element.village.JsonGameResult;
import licos.json.element.village.character.JsonResultCharacter;
import licos.json.element.village.role.JsonResultRole;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Entity("gameResults")
public class BsonGameResult extends BsonElementToJsonElement {

    @Id
    @SuppressWarnings("unused")
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
