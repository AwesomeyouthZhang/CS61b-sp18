/*public class TestUnionFind {
    public static void main(String[] args) {
        UnionFind u1 = new UnionFind(10);
        u1.union(0,1);
        System.out.println(u1.connected(0, 1));
        u1.union(2,3);
        System.out.println(u1.connected(2, 3));
        u1.union(10,8);
        u1.union(4,5);
        System.out.println(u1.connected(4, 5));
        u1.union(0,3);
        u1.union(0,5);
        System.out.println(u1.sizeOf(5));


    }
}*/
import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {

    UnionFind uf = new UnionFind(6);

    @Test
    public void testBasic() {
        uf.union(5, 1);
        uf.union(2, 3);
        uf.union(1, 2);


        assertTrue(uf.connected(5, 2));
        uf.union(0, 2);
       assertEquals(5, uf.parent(0)); // Path-compression

        assertEquals(5, uf.sizeOf(0));
        assertEquals(5, uf.sizeOf(1));
        assertEquals(5, uf.sizeOf(2));
        assertEquals(5, uf.sizeOf(3));
        assertTrue(uf.connected(1, 3));

     assertEquals(5, uf.find(0));
         assertEquals( 5,uf.parent(2));
    }

}
