public class TestUnionFind {
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
}
