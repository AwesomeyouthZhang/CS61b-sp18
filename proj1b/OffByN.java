public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int n) {
        this.N = n;
    }

    @Override
    public boolean equalChars(char x, char y) {

        if ((x - y == N) || (y - x == N)) {
            return true;
        }
        return false;
    }

}
