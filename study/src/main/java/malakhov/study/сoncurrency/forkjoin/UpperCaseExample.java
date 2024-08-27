package malakhov.study.сoncurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

public class UpperCaseExample {
    public static void main(String[] args) {
        String[] strs = {
            "Salut", "¿Qué tal?", "Privet", "Nǐ hǎo", "Ciao", "Hallo", "Oi",
            "Anyoung", "Ahlan", "Yassou", "Halo", "Hei", "Selam", "Hey"
        };
        
        UpperCaseRecursiveTask task = new UpperCaseRecursiveTask(strs, 0, strs.length);
        String[] res = task.compute();
        for (String str : res) {
            System.out.printf("%s :: %s <= received \"%s\"\n",
                System.currentTimeMillis(), Thread.currentThread().getName(), str);
        }
    }
}

class UpperCaseRecursiveTask extends RecursiveTask<String[]> {
    
    private static final int THRESHOLD = 2;
    
    private final String[] strs;
    private final int start;
    private final int end;
    
    public UpperCaseRecursiveTask(String[] strs, int start, int end) {
        this.strs = strs;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected String[] compute() {
        if (end - start <= THRESHOLD) {
            return convertToUpperCase();
        } else {
            return forkTasksAndMergeResults();
        }
    }
    
    private String[] convertToUpperCase() {
        String[] arr = new String[end - start];
        for (int i = start, j = 0; i < end; i++, j++) {
            System.out.printf("%s :: %s => is processing \"%s\" (%s-%s)\n",
                System.currentTimeMillis(), Thread.currentThread().getName(), strs[i], start, end);
            arr[j] = strs[i].toUpperCase();
        }
        return arr;
    }
    
    private String[] forkTasksAndMergeResults() {
        int mid = start + (end - start) / 2;
        UpperCaseRecursiveTask leftTask = new UpperCaseRecursiveTask(strs, start, mid);
        UpperCaseRecursiveTask rightTask = new UpperCaseRecursiveTask(strs, mid, end);
        leftTask.fork();
        String[] rightResult = rightTask.compute();
        String[] leftResult = leftTask.join();
        return mergeResults(leftResult, rightResult);
    }
    
    private String[] mergeResults(String[] leftResult, String[] rightResult) {
        String[] finalResult = new String[leftResult.length + rightResult.length];
        int i = 0;
        for (String str : leftResult) {
            finalResult[i++] = str; 
        }
        for (String str : rightResult) {
            finalResult[i++] = str; 
        }
        return finalResult;
    }
}