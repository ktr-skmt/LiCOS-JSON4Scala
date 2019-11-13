package licos.bson.element.village;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.character.BsonCharacter;
import licos.bson.element.village.role.BsonRole;
import licos.json.element.village.JsonPhase;
import licos.json.element.village.character.JsonCharacter;
import licos.json.element.village.role.JsonRole;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

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
@Entity("phases")
public class BsonPhase extends BsonElementToJsonElement {

    @Id
    @SuppressWarnings("unused")
    private ObjectId _id;

    @Getter @Setter @Reference
    private BsonBase base;

    @Getter @Setter @Reference
    private List<BsonCharacter> character;

    @Getter @Setter @Reference
    private List<BsonRole> role;

    @SuppressWarnings("unused")
    private BsonPhase() {
        // Do nothing
    }

    public BsonPhase(ObjectId _id,
                     BsonBase base,
                     List<BsonCharacter> character,
                     List<BsonRole> role) {
        this._id = _id;
        this.base = base;
        this.character = character;
        this.role = role;
    }

    private List<JsonCharacter> characterToJson() {
        return elementToJson(character).
                map(element -> (JsonCharacter) element).
                collect(Collectors.toCollection(LinkedList::new));
    }

    private List<JsonRole> roleToJson() {
        return elementToJson(role).
                map(element -> (JsonRole) element).
                collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public JsonPhase toJson() {
        return new JsonPhase(
                base.toJson(),
                characterToJson(),
                roleToJson()
        );
    }
}
