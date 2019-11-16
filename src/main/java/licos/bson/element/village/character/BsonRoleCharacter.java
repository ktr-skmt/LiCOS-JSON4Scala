package licos.bson.element.village.character;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.BsonName;
import licos.bson.element.village.role.BsonSimpleRole;
import licos.json.element.village.character.JsonRoleCharacter;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("roleCharacters")
public class BsonRoleCharacter extends BsonAbstractCharacter {

    @Getter @Setter @Reference
    private BsonSimpleRole role;

    @SuppressWarnings("unused")
    private BsonRoleCharacter() {
        // Do nothing
    }

    public BsonRoleCharacter(ObjectId _id,
                             String $context,
                             String $id,
                             int id,
                             BsonName name,
                             String image,
                             BsonSimpleRole role) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.id = id;
        this.name = name;
        this.image = image;
        this.role = role;
    }

    @Override
    public JsonRoleCharacter toJson() {
        return new JsonRoleCharacter(
                $context,
                $id,
                id,
                name.toJson(),
                image,
                role.toJson()
        );
    }
}
