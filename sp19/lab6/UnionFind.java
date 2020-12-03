public class UnionFind {

    // TODO - Add instance variables?

    private int[] parent;
    private int[] size;


    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {

        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            size[i] = 1;

            parent[i] = i;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >=parent.length) {

            throw new ArrayIndexOutOfBoundsException("v1 is not a valid index");


        }


    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {

        return size[find(v1)];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {

        /*if (v1 == parent(v1)) {
            return - size[v1];
        }*/
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {

        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
       try {
           validate(v1);
           validate(v2);

       }catch (Exception e) {
           System.out.println("v1 is not a valid index");
           return ;
       }

        int i = find(v1);
        int j = find(v2);

        if (i == j ) {
            return;
        }
        if (size[i] < size[j]) {

            size[find(j)] += size[find(i)];
            parent[i] = j;
        }else {

            size[find(i)] +=size[find(j)];
            parent [j] = i;
        }



    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
    /*    int currentParent ;
        int root = vertex;
        while (parent(root) > - 1) {

            root = parent(vertex);



        }
        //path - compression
       while (vertex != parent[vertex]) {
           currentParent = parent(vertex);
           parent[vertex] = currentParent
       }*/
        int root =  vertex;
        while (root !=parent[root] ) {
            root = parent[root];
        }
        int currentParent;
        while (vertex != root) {
            currentParent = parent(vertex);
            parent[vertex] = root;
            vertex =currentParent;
        }




        return  root;
    }

}
