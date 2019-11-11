package licos.bson.element.village.role;

import licos.bson.element.village.BsonBoardResult;
import licos.bson.element.village.BsonName;
import licos.json.element.village.JsonBoardResult;
import licos.json.element.village.role.JsonRole;
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
@Entity("roles")
public class BsonRole extends BsonAbstractRole {
    @Getter @Setter
    private boolean isMine;

    @Getter @Setter
    private int numberOfCharacters;

    @Getter @ Setter @Reference
    private List<BsonBoardResult> board;

    @SuppressWarnings("unused")
    private BsonRole() {
        // Do nothing
    }

    public BsonRole(ObjectId _id,
                    String $context,
                    String $id,
                    BsonName name,
                    String image,
                    boolean isMine,
                    int numberOfCharacters,
                    List<BsonBoardResult> board) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.name = name;
        this.image = image;
        this.isMine = isMine;
        this.numberOfCharacters = numberOfCharacters;
        this.board = board;
    }

    public JsonRole toJson() {
        Iterator<BsonBoardResult> boardIterator = board.iterator();
        List<JsonBoardResult> boardList = new LinkedList<>();
        while (boardIterator.hasNext()) {
            boardList.add(boardIterator.next().toJson());
        }
        return new JsonRole(
            $context,
            $id,
            name.toJson(),
            image,
            isMine,
            numberOfCharacters,
            boardList
        );
    }
}
