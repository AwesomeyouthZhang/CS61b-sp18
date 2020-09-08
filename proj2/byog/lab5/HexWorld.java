package byog.lab5;

import org.junit.Test;

import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;


import java.util.Random;


public class HexWorld {
    /**
     * 思路为建立一个坐标系
     * 以最左下的一个点为坐标标原点
     * hexRowOffset 作用是计算一行所需的移位
     * <p>
     * Draws a world consisting of hexagonal regions.
     *
     * @param s size of the hexagon
     * @param i row num of the hexagon, where i = 0 is the bootom
     * @return
     */

    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;

    }

    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;

        }
        return s + 2 * effectiveI;
    }
    /**
     * Adds a row of the same tile.
     * @param world the world to draw on
     * @param p the leftmost position of the row
     * @param t the title to draw
     */
//    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
//        for (int xi = 0; xi < width; xi += 1) {
//            int xCoord = p.x + xi;
//            int yCoord = p.y;
//            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
//        }
//    }

    /**
     * Adds a hexagon to the world.
     *
     * @param world the world to draw on
     * @param p     the bottom left coordinate of the hexagon
     * @param t     the title to draw
     */
//    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
//
//    }
    @Test
    public void testHexRowWidth() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(5, hexRowWidth(3, 4));
        assertEquals(7, hexRowWidth(3, 3));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(5, hexRowWidth(3, 1));
        assertEquals(3, hexRowWidth(3, 0));
        assertEquals(2, hexRowWidth(2, 0));
        assertEquals(4, hexRowWidth(2, 1));
        assertEquals(4, hexRowWidth(2, 2));
        assertEquals(2, hexRowWidth(2, 3));
    }

    @Test
    public void testHexRowOffset() {
        assertEquals(0, hexRowOffset(3, 5));
        assertEquals(-1, hexRowOffset(3, 4));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-1, hexRowOffset(3, 1));
        assertEquals(0, hexRowOffset(3, 0));
        assertEquals(0, hexRowOffset(2, 0));
        assertEquals(-1, hexRowOffset(2, 1));
        assertEquals(-1, hexRowOffset(2, 2));
        assertEquals(0, hexRowOffset(2, 3));
    }


}

