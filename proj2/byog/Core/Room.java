/*
package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class Room extends RectangleHelper {
    private int height;
    private int width;
    private final Position position;

    */
/**
 * Property of a rectangle room. p is at the bottom left  Overlap checked method.
 * First check if a point in the room.
 * Then calculate four corner points.
 * Finally check if these four points in the room.
 * Print room method.
 * When meet the edge, break.
 * Create a random room with position, width and height.  Remove overlapped rooms by check every room.
 * I've tried to sort rooms by x and check by order
 * but it has problems in vector y.
 * Generate rooms and return rooms. Create four random positions on room's edges.
 * Overlap checked method.
 * First check if a point in the room.
 * Then calculate four corner points.
 * Finally check if these four points in the room.
 * Print room method.
 * When meet the edge, break.
 * Create a random room with position, width and height.  Remove overlapped rooms by check every room.
 * I've tried to sort rooms by x and check by order
 * but it has problems in vector y.
 * Generate rooms and return rooms. Create four random positions on room's edges.
 * Overlap checked method.
 * First check if a point in the room.
 * Then calculate four corner points.
 * Finally check if these four points in the room.
 * Print room method.
 * When meet the edge, break.
 * Create a random room with position, width and height.  Remove overlapped rooms by check every room.
 * I've tried to sort rooms by x and check by order
 * but it has problems in vector y.
 * Generate rooms and return rooms. Create four random positions on room's edges.
 *//*

    public Room(int height, int width, Position position) {
        this.height = height;
        this.width = width;
        this.position = position;
    }

    */
/** Overlap checked method.
 * First check if a point in the room.
 * Then calculate four corner points.
 * Finally check if these four points in the room.
 *//*

    private boolean containPosition(Position p) {
        return (p.x <= position.x + width - 1 && p.x >= position.x)
                && (p.y <= position.y + height - 1 && p.y >= position.y);
    }

    public boolean isOverlap(Room r) {
        Position[] p = cornerPositions(r.position, r.width, r.height);
        for (int i = 0; i < 4; i++) {
            if (containPosition(p[i])) {
                return true;
            }
        }
        return false;
    }

    */
/** Print room method.
 *  When meet the edge, break.
 *//*

    public void printRoom(TETile[][] world) {

        // Print vertical wall. Don't crash the edge.
        for (int i = 0; i < height; i++) {
            // If crash the edges, update height and width.
            if (position.y + i == world[0].length - 1) {
                height = i + 1;
                break;
            }
            world[position.x][position.y + i] = Tileset.WALL;
            if (position.x + width - 1 < world.length - 1) {
                world[position.x + width - 1][position.y + i] = Tileset.WALL;
            } else {
                width = world.length - position.x;
            }
        }
        // Print horizontal wall.
        for (int i = 0; i < width; i++) {
            world[position.x + i][position.y] = Tileset.WALL;
            world[position.x + i][position.y + height - 1] = Tileset.WALL;
        }

        // Print floor.
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                world[position.x + j][position.y + i] = Tileset.FLOOR;
            }
        }
    }

    */
/** Create a random room with position, width and height. *//*

    private static Room randomRoom(Random RANDOM, TETile[][] world) {
        // The distance between edge and position will over 3,
        // in case of one TETile side length.
        int xP = RANDOM.nextInt((int) (0.5 * (world.length - 2))) * 2;
        int yP = RANDOM.nextInt((int) (0.5 * (world[0].length - 2))) * 2;
        Position p = new Position(xP, yP);

        // Height and width are among {5, 7, 9}
        int height = (3 + RANDOM.nextInt(2)) * 2 - 1;
        int width = (3 + RANDOM.nextInt(2)) * 2 - 1;
        return new Room(height, width, p);
    }

    */
/** Remove overlapped rooms by check every room.
 * I've tried to sort rooms by x and check by order
 * but it has problems in vector y.
 *//*

    private static void removeOverlaps(List<Room> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            for (int j = i + 1; j < rooms.size(); j++) {
                if (rooms.get(i).isOverlap(rooms.get(j))
                        || rooms.get(j).isOverlap(rooms.get(i))) { // To get rid of one specific condition.
                    rooms.remove(j);
                    j--;
                }
            }
        }
    }

    */
/** Generate rooms and return rooms.*//*

    public static List<Room> roomGenerator(Random RANDOM, TETile[][] world) {
        int numOfRooms = 100 + RANDOM.nextInt(50);
        List<Room> rooms = new LinkedList<>();
        for (int i = 0; i < numOfRooms; i++) {
            rooms.add(randomRoom(RANDOM, world));
        }
        removeOverlaps(rooms);
        for (Room r: rooms) {
            r.printRoom(world);
        }
        return rooms;
    }

    */
/** Create four random positions on room's edges. *//*

    private Position[] randomRoomPositions(Random RANDOM) {
        int widthRandom1 = 1 + RANDOM.nextInt(width - 3);
        int heightRandom1 = 1 + RANDOM.nextInt(height - 3);
        int widthRandom2 = -(1 + RANDOM.nextInt(width - 3));
        int heightRandom2 = -(1 + RANDOM.nextInt(height - 3));
        Position[] pArray = new Position[4];

        pArray[0] = new Position(position.x + widthRandom1, position.y);
        pArray[1] = new Position(position.x, position.y + heightRandom1);
        pArray[2] = new Position(position.x + width - 1 + widthRandom2, position.y + height - 1);
        pArray[3] = new Position(position.x + width - 1, position.y + height - 1 + heightRandom2);
        return pArray;
    }

    public void randomRemoveWalls(Random RANDOM, TETile[][] world) {
        Position[] randomPositions = randomRoomPositions(RANDOM);
        for (int i = 0; i < 4; i++) {
            int whichEdge = RANDOM.nextInt(4);
            if (!isOnEdge(randomPositions[whichEdge], world)
                    && !isInDeadEnd(randomPositions[whichEdge], world)) {
                world[randomPositions[whichEdge].x][randomPositions[whichEdge].y] = Tileset.FLOOR;
            }
        }
    }
}*/


package byog.Core;

import byog.SaveDemo.World;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

/**
 * 将Map分成很多个Room
 */
public class Room extends RectangleHelper {

    private int roomHeight;
    private int roomWidth;
    private final Position pos;

    /**
     * pos is in the left bottom of room
     * . . . .
     * . . . .
     * p . . .
     */
    public Room(int roomHeight, int roomWidth, Position pos) {
        this.roomHeight = roomHeight;
        this.roomWidth = roomWidth;
        this.pos = pos;
    }


    /**
     * @return a random room
     */
    private static Room randomRoom(Random RANDOM, TETile[][] world) {
        /**xP and yP nearest each edge is 3*/

        int xP = RANDOM.nextInt((int) (world.length - 2));
        int yP = RANDOM.nextInt((int) world[0].length - 2);
        Position roomPosition = new Position(xP, yP);


        /**  the bound of height and width is {5,7,9} */
        int height = 2 * (3 + RANDOM.nextInt(2)) - 1;
        int width = 2 * (3 + RANDOM.nextInt(2)) - 1;

        return new Room(height, width, roomPosition);

    }

    /**
     * a helper method of is isOverlap
     * just judge if the room four corner position is in the other room
     */
    private boolean isCornerInner(Position p) {
        return (p.x >= pos.x && (p.x <= pos.x + roomWidth - 1)) && (p.y >= pos.y) && (p.y <= pos.y - roomHeight - 1);

    }

    /**
     * @return if two room is overlapped
     */
    public boolean isOverlapped(Room r) {
        Position[] p = cornerPositions(r.pos, r.roomWidth, r.roomHeight);
        for (int i = 0; i < 4; i++) {
            if (isCornerInner(p[i])) {
                return true;
            }
        }
        return false;

    }

    /**
     * remove overlapped room
     *
     * @param rooms sorted room list
     */
    private static void removeOverlaps(List<Room> rooms) {
        for (int i = 0; i < rooms.size() - 1; i++) {
            if (rooms.get(i).isOverlapped(rooms.get(i + 1))) {
                rooms.remove(i + 1);
            }
        }
    }

    /**
     * @param RANDOM
     * @param world
     * @return a priorityQueue of room, sorted by room size
     */

    public static List<Room> roomGenerator(Random RANDOM, TETile[][] world) {
        int numberOfRooms = 10 + RANDOM.nextInt(50);
        List<Room> rooms = new ArrayList<Room>();
        for (int i = 0; i < numberOfRooms; i++) {
            rooms.add(randomRoom(RANDOM, world));
        }
        rooms.sort(new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                if (o1.roomHeight * o1.roomWidth < o2.roomWidth * o2.roomHeight) {
                    return -1;
                }
                if (o1.roomHeight * o1.roomWidth > o2.roomWidth * o2.roomHeight) {
                    return 1;
                }

                return 0;
            }
        });


        /**
         * 去除重复
         */
        removeOverlaps(rooms);


        /**
         * print room
         */

        for (Room r : rooms) {
            r.printRoom(world);
        }
        return rooms;

    }


    /**
     * print a room of vertical Walls
     * if the room is crash the edge ,then break
     */
    private void drawVerticalHallway(TETile[][] world) {
        for (int i = 1; i < roomHeight - 1; i++) {
            if (pos.y + i == world[0].length - 1) {
                roomHeight = i + 1;
                break;
            }
            world[pos.x][pos.y + i] = Tileset.WALL;
            /** 宽度也可能溢出
             *
             */
            if (pos.x + roomWidth - 1 < world.length - 2) {
                world[pos.x + roomWidth - 1][pos.y + i] = Tileset.WALL;
            } else {
                roomWidth = world.length - pos.x;
                world[pos.x + roomWidth - 1][pos.y + i] = Tileset.WALL;
            }

        }


    }


    /*    private void drawVerticalHallway(TETile[][] world) {
     *//* for (int i = 0; i < roomHeight - 2; i++) {
            if (pos.x + i == world.length - 1) {
                roomHeight = i + 1;
                break;

            }
            try {
                world[pos.x][pos.y + i + 1] = Tileset.WALL;
                world[pos.x + roomWidth - 1][pos.y + i + 1] = Tileset.WALL;
            } catch (Exception e) {

            }

        }*//*

        for (int i = 0; i < roomHeight; i++) {
            // If crash the edges, update height and width.
            if (pos.y + i == world[0].length - 1) {
                roomHeight = i + 1;
                break;
            }
            world[pos.x][pos.y + i] = Tileset.WALL;
            if (pos.x + roomWidth - 1 < world.length - 1) {
                world[pos.x + roomWidth - 1][pos.y + i] = Tileset.WALL;
            } else {
                roomWidth = world.length - pos.x;
            }


        }
    }*/

    /**
     * print a room of horizontal Walls
     * if the room is crash the edge, then break
     */
    private void drawHorizontalHallway(TETile[][] world) {
        for (int i = 1; i < roomWidth - 1; i++) {
            if (pos.x + i == world.length - 1) {
                roomWidth = i + 1;
                break;
            }
            world[pos.x + i][pos.y] = Tileset.WALL;
            if (roomHeight + pos.y - 1 < world[0].length - 2) {
                world[pos.x + i][pos.y + roomHeight - 1] = Tileset.WALL;
            } else {
                roomHeight = world[0].length - pos.y;
                world[pos.x + i][pos.y + roomHeight - 1] = Tileset.WALL;
            }
        }


    }


/*

  private void drawHorizontalHallway(TETile[][] world) {
      // Print horizontal wall.
      for (int i = 0; i < roomWidth; i++) {
          world[pos.x + i][pos.y] = Tileset.WALL;
          world[pos.x + i][pos.y + roomHeight - 1] = Tileset.WALL;
      }
  }

*/

    /**
     * 更新房间宽度
     *
     *//*
     */
/*

                roomWidth = i + 1;
                break;
            }

            try {
                world[pos.x + i][pos.y] = Tileset.WALL;
                world[pos.x + i][pos.y + roomHeight - 1] = Tileset.WALL;
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
*//*



        /**
         * print a room of four corner
         * if the room corner is crash or outside the edge, then continue
         */
    private void drawCorner(TETile[][] world) {

        Position[] cornerPos = cornerPositions(pos, roomWidth, roomHeight);
        for (int i = 0; i < 4; i++) {

            world[cornerPos[i].x][cornerPos[i].y] = Tileset.WALL;
        }
    }

    /**
     * print a room of FLOOR
     */
    private void drawFloor(TETile[][] world) {


        for (int i = 1; i < roomHeight - 1; i++) {
            for (int j = 1; j < roomWidth - 1; j++) {
                world[pos.x + j][pos.y + i] = Tileset.FLOOR;
            }
        }

    }


    /**
     * print the room
     * inside is FLOOR
     * outside is WALL
     * <p>
     * ########
     * #......#
     * #......#
     * #......#
     * ########
     */
    public void printRoom(TETile[][] world) {
        drawVerticalHallway(world);
        drawHorizontalHallway(world);

         drawCorner(world);
             drawFloor(world);

    }
  /*  public void printRoom(TETile[][] world) {

        // Print vertical wall. Don't crash the edge.
        for (int i = 0; i < roomHeight; i++) {
            // If crash the edges, update height and width.
            if (pos.y + i == world[0].length - 1) {
                roomHeight = i + 1;
                break;
            }
            world[pos.x][pos.y + i] = Tileset.WALL;
            if (pos.x + roomWidth - 1 < world.length - 1) {
                world[pos.x + roomWidth - 1][pos.y + i] = Tileset.WALL;
            } else {
                roomWidth = world.length - pos.x;
            }
        }
        // Print horizontal wall.
        for (int i = 0; i < roomWidth; i++) {
            world[pos.x + i][pos.y] = Tileset.WALL;
            world[pos.x + i][pos.y + roomHeight - 1] = Tileset.WALL;
        }

        // Print floor.
        for (int i = 1; i < roomHeight - 1; i++) {
            for (int j = 1; j < roomWidth - 1; j++) {
                world[pos.x + j][pos.y + i] = Tileset.FLOOR;
            }
        }
    }*/

    /**
     * @param RANDOM
     * @return a array of four random site of the room each four edge
     */

    private Position[] randomRoomPositions(Random RANDOM) {
        int downRandomPositionXOffset = 1 + RANDOM.nextInt(roomWidth - 3);
        int upRandomPositionXOffset = -(1 + RANDOM.nextInt(roomWidth - 3));
        int rightRandomPositionYOffset = 1 + RANDOM.nextInt(roomHeight - 3);
        int leftRandomPositionYOffset = -(1 + RANDOM.nextInt(roomHeight - 3));

        Position[] cornersPositions = new Position[4];

        cornersPositions[0] = new Position(pos.x + downRandomPositionXOffset, pos.y);
        cornersPositions[1] = new Position(pos.x + roomWidth - 1, pos.y + rightRandomPositionYOffset);
        cornersPositions[2] = new Position(pos.x + roomWidth - 1 + upRandomPositionXOffset, pos.y + roomHeight - 1);
        cornersPositions[3] = new Position(pos.x, pos.y + roomHeight - 1 + leftRandomPositionYOffset);
        return cornersPositions;
    }


    public void randomRemovelWalls(Random RANDOM, TETile[][] world) {
        Position[] randomPositions = randomRoomPositions(RANDOM);
        for (int i = 0; i < 4; i++) {
            int whichEdge = RANDOM.nextInt(4);
            if (!isOnEdge(randomPositions[whichEdge], world) && !isInDeadEnd(randomPositions[whichEdge], world)) {
                world[randomPositions[whichEdge].x][randomPositions[whichEdge].y] = Tileset.FLOOR;

            }
        }
    }


}

