package licos.bson.village.role;

import licos.bson.village.BsonBoardPolarity;
import licos.bson.village.BsonName;
import licos.json.village.JsonBoardPolarity;
import licos.json.village.role.JsonRole;
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
    private int numberOfAgents;

    @Getter @ Setter @Reference
    private List<BsonBoardPolarity> board;

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
                    int numberOfAgents,
                    List<BsonBoardPolarity> board) {
        this._id = _id;
        this.$context = $context;
        this.$id = $id;
        this.name = name;
        this.image = image;
        this.isMine = isMine;
        this.numberOfAgents = numberOfAgents;
        this.board = board;
    }

    public JsonRole toJson() {
        Iterator<BsonBoardPolarity> boardIterator = board.iterator();
        List<JsonBoardPolarity> boardList = new LinkedList<>();
        while (boardIterator.hasNext()) {
            boardList.add(boardIterator.next().toJson());
        }
        return new JsonRole(
            $context,
            $id,
            name.toJson(),
            image,
            isMine,
            numberOfAgents,
            boardList
        );
    }
}
