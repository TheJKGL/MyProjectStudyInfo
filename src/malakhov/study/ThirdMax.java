package malakhov.study;

import java.util.Optional;
import java.util.TreeSet;


public class ThirdMax {
    public static void main(String[] args) {
        /*ThirdMax object = new ThirdMax();
        int[] array = {1,2,3,4,5};
        System.out.println(object.thirdMax(array,1));*/

        int mas [][] = new int [3][4];
        int i,j;

        for (i = 0; i < 3; i++)
            for (j = 0; j < 4; j++)
                mas[i][j] = i * j;

        for (i = 0; i < 3; i++){
            for (j = 0; j < 4; j++){
                System.out.print (mas[i][j]);
            }
            System.out.println();
        }
    }
    public Optional<Integer> thirdMax(int[] nums, int k) {
        var tree = new TreeSet<Integer>();
        for(var i : nums){
            tree.add(i);
            if(tree.size() > k){
                tree.pollFirst();
            }
        }

        return Optional.ofNullable(tree.size() == k ? tree.first() : tree.last());
    }
}
