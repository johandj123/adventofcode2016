package lib;

import java.util.Objects;

public class Pos2 {
    final int x;
    final int y;

    public Pos2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pos2 pos2 = (Pos2) o;
        return x == pos2.x && y == pos2.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
