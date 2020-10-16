package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import org.junit.Test;

public class RectangleHelper {
    /**
     * @param width  width of Room
     * @param height height of Room
     * @return position[] the four corner of the room
     * 3 . . . 2
     * .       .
     * .       .
     * .       .
     * 0 . . . 1
     */
    public Position[] cornerPositions(Position p, int width, int height) {
        Position[] cornerPos = new Position[4];
        cornerPos[0] = new Position(p.x, p.y);
        cornerPos[1] = new Position(p.x + width - 1, p.y);
        cornerPos[2] = new Position(p.x + width - 1, p.y + height - 1);
        cornerPos[3] = new Position(p.x, p.y + height - 1);
        return cornerPos;

    }

    /**
     * return four orthogonally-adjacent sites of given position p
     * . 3 .
     * 0 p 2
     * . 1 .
     */
    public static Position[] orthogonallyPosition(Position p) {
        Position[] orthogonallyPos = new Position[4];
        orthogonallyPos[0] = new Position(p.x - 1, p.y);
        orthogonallyPos[1] = new Position(p.x, p.y - 1);
        orthogonallyPos[2] = new Position(p.x + 1, p.y);
        orthogonallyPos[3] = new Position(p.x, p.y + 1);

         return orthogonallyPos;


    }


    /**
     * @return one site's four corner
     * 3 . 2
     * . p .
     * 0 . 1
     */

    public static Position[] aroundCornerPositions(Position p) {
        Position[] cornerPos = new Position[4];
        cornerPos[0] = new Position(p.x - 1, p.y - 1);
        cornerPos[1] = new Position(p.x + 1, p.y - 1);
        cornerPos[2] = new Position(p.x + 1, p.y + 1);
        cornerPos[3] = new Position(p.x - 1, p.y + 1);
        return cornerPos;

    }


    /**
     * return if the position p in edge
     */
    public static boolean isOnEdge(Position p, TETile[][] world) {
        return p.x == 0 || p.y == 0 || p.x == world.length - 1 || p.y == world[0].length - 1;

    }

    /**
     * return if the the position is wrapped with WALL
     * using the orthogonallyPosition method
     */

    public static boolean isInDeadEnd(Position p, TETile[][] world) {
        int exitsWallSum = 0;
        Position[] positionsArray = orthogonallyPosition(p);
        for (int i = 0; i < 4; i++) {
            if (world[positionsArray[i].x][positionsArray[i].y] == Tileset.FLOOR) {
                exitsWallSum ++;
            }


        }
        return exitsWallSum == 1;
    }

    /**
     *return if the position p is inner the WALL
     *using aroundPosition
     */
    public static  boolean isWrapped(Position p, TETile[][] world){
        Position[] PositionArray = aroundCornerPositions(p);
        for (int i = 0; i < 4; i++) {
            if (world[PositionArray[i].x][PositionArray[i].y] == Tileset.FLOOR) {
                return false;
            }
        }
        return true;
    }




}
