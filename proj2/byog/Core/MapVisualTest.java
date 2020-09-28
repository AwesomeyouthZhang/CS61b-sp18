package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.List;
import java.util.Random;

public class MapVisualTest {


    public static TETile[][] worldGenerator(Random RANDOM, TETile[][] world) {

        List<Room> rooms = Room.roomGenerator(RANDOM, world);




        // Add a door at right edge.
        for (int i = world[0].length - 1; i > 0; i--) {
            if (world[world.length - 2][i].equals(Tileset.FLOOR)) {
                world[world.length - 2][i] = Tileset.LOCKED_DOOR;
                break;
            }
        }
        return world;
    }

    public static void main(String[] args) {
        int WIDTH = 81, HEIGHT = 31;
        int seed = 23456758;
        Random RANDOM = new Random(seed);
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        worldGenerator(RANDOM, world);

        ter.renderFrame(world);
    }
}
