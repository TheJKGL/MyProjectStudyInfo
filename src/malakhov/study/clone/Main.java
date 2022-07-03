package malakhov.study.clone;

public class Main {
    public static void main(String[] args){
        DollySheet dollySheet = new DollySheet();
        DollySheet dollySheet2 =  foo(dollySheet);

        dollySheet.setName("Dolly");
        dollySheet2.setName("Sheet");

        System.out.println(dollySheet.getName());
        System.out.println(dollySheet2.getName());
    }

    public static DollySheet foo(DollySheet dollySheet) {
        DollySheet sheet = null;
        try {
            sheet =  dollySheet.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sheet;
    }
}
