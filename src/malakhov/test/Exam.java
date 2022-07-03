package malakhov.test;

public class Exam {

    private final double DEGREES = Math.cos(Math.toRadians(30));//Constant for degrees

    public static void main(String[] args) {
        double[] vector = {2.2, 3.5, 7.1, 8.3, 9.4, 9.1, 8.1, 7.3, 4.2, 1.8};
        Exam test = new Exam();
        double module = test.findModule(vector);
        double[] vectorR = test.createVectorR(vector, module);
        test.printArray(vectorR);
    }

    private double findModule(double[] array){
        double sum = 0;
        for(int i = 0; i < array.length; i++){
            sum += Math.pow(array[i], 2.0);
        }
        return Math.sqrt(sum);
    }

    private double[] createVectorR(double[] array, double module){
        double[] result = new double[array.length];
        for(int i = 0; i < array.length; i++){
            double temp = (array[i]/(1 + module)) * DEGREES;
            result[i] = temp;
        }
        return result;
    }

    private void printArray(double[] array){
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }
}
