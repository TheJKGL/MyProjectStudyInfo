package malakhov.study.hash_code_and_equals.instanceOf_getClass;


import java.util.Objects;

public class ColorPoint extends Point {
    private Color color;
    private double aDouble;

    public ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ColorPoint that)) return false;
//        if (!super.equals(o)) return false;
//
//        return color == that.color;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ColorPoint that = (ColorPoint) o;
        return Objects.equals(aDouble, that.aDouble) && color == that.color;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
