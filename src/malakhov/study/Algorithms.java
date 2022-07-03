package malakhov.study;

public class Algorithms {

    public static void main(String[] args) {
        //System.out.println(isPolindrom("tenet"));
        //int[] array = {1,2,3,5,6,9,10};
        //System.out.println(binarySearch(array,3));
        //int[] array = new int[]{64, 42, 73, 41, 31, 53, 16, 24, 57, 42, 74, 55, 36};
        //System.out.println(isPrimeNUmber(4));
        System.out.println(isPrime(23));
        //System.out.println(factorial(0));
        //System.out.println(Arrays.toString(bubbleSort(array)));
    }

    public static boolean isPrimeNUmber(int number) {
        if (number < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPrime(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPolindrom(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static int[] bubbleSort(int[] array) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    int temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    isSorted = false;
                }
            }
        }
        return array;
    }

    public static int binarySearch(int[] array, int key) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (key == array[mid]) {
                return mid;
            }

            if (key > array[mid]) {
                left = mid + 1;
            } else if (key < array[mid]) {
                right = mid - 1;
            }
        }
        return -1;
    }

    //Для небольших размеров.
    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i;
            while (j > 0 && array[j - 1] > current) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = current;
        }
        return array;
    }

    public static int factorial(int x) {
        if (x == 1 || x == 0) {
            return 1;
        }
        return x * factorial(x - 1);
    }

    //Сортировка подсчетом подразумевает создание корзин (buckets), в каждой из которых хранится количество элементов исходного массива,
    // значение которых совпадает с индексом корзины. Соответственно, нужно иметь корзины, индексы которых будут от минимального значения
    // массива до максимального.
    //При использовании массива для хранения корзин индексы будут от 0 до X. Чтобы при этом можно было работать с отрицательными числами,
    // а также чтобы не хранить ненужные значения от 0 до минимального значения из массива, имеет смысл перед сортировкой
    // (или непосредственно перед добавлением в корзину) вычесть из всех элементов массива минимум, а после сортировки
    // (при извлечении из корзины) - добавить его обратно.
    public static void countSort1(int[] array) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int element : array) {
            if (element < min) {
                min = element;
            }
            if (element > max) {
                max = element;
            }
        }
        int[] buckets = new int[max - min + 1];
        for (int element : array) {
            buckets[element - min]++;
        }
        int arrayIndex = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = buckets[i]; j > 0; j--) {
                array[arrayIndex++] = i + min;
            }
        }
    }

    public static int[] countSort2(int[] array) {
        int[] temp = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            int k = 0;
            for (int j = 0; j < array.length; j++) {
                if (array[i] > array[j]) {
                    k++;
                }
            }
            temp[k] = array[i];
        }
        return temp;
    }
}
