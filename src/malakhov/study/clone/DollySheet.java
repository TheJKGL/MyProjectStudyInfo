package malakhov.study.clone;

public class DollySheet implements Cloneable{
    private String name;
    private Head head;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public DollySheet clone() throws CloneNotSupportedException {
        return (DollySheet) super.clone();

    }
}
