package licos.bson.element.village.character;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Reference;
import licos.bson.element.village.BsonAvatar;
import licos.bson.element.village.BsonName;
import licos.bson.element.village.role.BsonSimpleRole;
import licos.json.element.village.character.JsonResultCharacter;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Entity("resultCharacters")
public class BsonResultCharacter extends BsonAbstractCharacter {

    @Getter @Setter
    private boolean isMine;

    @Getter @Setter @Reference
    private BsonSimpleRole role;

    @Getter @Setter
    private String status;

    @Getter @Setter
    private String result;

    @Getter @Setter
    private BsonAvatar avatar;

    @SuppressWarnings("unused")
    private BsonResultCharacter() {
        // Do nothing
    }

    public BsonResultCharacter(ObjectId _id,
                               String $context,
                               String $id,
                               int id,
                               BsonName name,
                               String image,
                               boolean isMine,
                               BsonSimpleRole role,
                               String status,
                               String result,
                               BsonAvatar avatar) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.id = id;
        this.name = name;
        this.image = image;
        this.isMine = isMine;
        this.role = role;
        this.status = status;
        this.result = result;
        this.avatar = avatar;
    }

    @Override
    public JsonResultCharacter toJson() {
        return new JsonResultCharacter(
                $context,
                $id,
                id,
                name.toJson(),
                image,
                isMine,
                role.toJson(),
                status,
                result,
                avatar.toJson()
        );
    }
}
