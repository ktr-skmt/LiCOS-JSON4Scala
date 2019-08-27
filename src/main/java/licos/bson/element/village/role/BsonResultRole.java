package licos.bson.element.village.role;

import licos.bson.element.village.BsonName;
import licos.bson.element.village.character.BsonSimpleCharacter;
import licos.json.element.village.character.JsonSimpleCharacter;
import licos.json.element.village.role.JsonResultRole;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <pre>
 * Created on 2018/01/11.
 * </pre>
 *
 * @author K.Sakamoto
 */
@Entity("resultRoles")
public class BsonResultRole extends BsonAbstractRole {
    @Getter @Setter
    private boolean isMine;

    @Getter @Setter
    private int numberOfCharacters;

    @Getter @Setter @Reference
    private List<BsonSimpleCharacter> characterId;

    @SuppressWarnings("unused")
    private BsonResultRole() {
        // Do nothing
    }

    public BsonResultRole(ObjectId _id,
                          String $context,
                          String $id,
                          boolean isMine,
                          BsonName name,
                          String image,
                          int numberOfCharacters,
                          List<BsonSimpleCharacter> characterId) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.isMine = isMine;
        this.name = name;
        this.image = image;
        this.numberOfCharacters = numberOfCharacters;
        this.characterId = characterId;
    }

    public JsonResultRole toJson() {
        Iterator<BsonSimpleCharacter> characterIterator = characterId.iterator();
        List<JsonSimpleCharacter> characterList = new LinkedList<>();
        while (characterIterator.hasNext()) {
            characterList.add(characterIterator.next().toJson());
        }
        return new JsonResultRole(
                $context,
                $id,
                isMine,
                name.toJson(),
                image,
                numberOfCharacters,
                characterList
        );
    }
}
