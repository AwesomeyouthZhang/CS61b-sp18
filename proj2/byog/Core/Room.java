package byog.Core;

/**
 *
 *将Map分成很多个Room
 */
public class Room extends RectangleHelper{

    private int roomHeight;
    private int roomWidth;
    private final Position pos;
/**
 * pos is in the left bottom of room
 *  . . . .
 *  . . . .
 *  p . . .
 * */
    public Room(int roomHeight, int roomWidth, Position pos) {
        this.roomHeight = roomHeight;
        this.roomWidth = roomWidth;
        this.pos = pos;
    }

    /**
     *
     *
     * */
}
