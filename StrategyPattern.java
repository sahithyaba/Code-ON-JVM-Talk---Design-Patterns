// StrategyPattern.java
// Demonstrates swapping algorithms/behaviors at runtime.

interface SortingStrategy {
    int[] sort(int[] array);
}

class BubbleSort implements SortingStrategy {
    public int[] sort(int[] array) {
        int n = array.length;
        int[] a = array.clone();
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-1-i; j++) {
                if (a[j] > a[j+1]) {
                    int t = a[j]; a[j] = a[j+1]; a[j+1] = t;
                }
            }
        }
        return a;
    }
}

class InsertionSort implements SortingStrategy {
    public int[] sort(int[] array) {
        int[] a = array.clone();
        for (int i = 1; i < a.length; i++) {
            int key = a[i], j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = key;
        }
        return a;
    }
}

class Sorter {
    private SortingStrategy strategy;
    public Sorter(SortingStrategy strategy) { this.strategy = strategy; }
    public void setStrategy(SortingStrategy strategy) { this.strategy = strategy; }
    public int[] sort(int[] array) { return strategy.sort(array); }
}

public class StrategyPattern {
    public static void main(String[] args) {
        int[] data = {5, 2, 9, 1, 5, 6};

        Sorter sorter = new Sorter(new BubbleSort());
        int[] s1 = sorter.sort(data);
        System.out.println("BubbleSort -> " + java.util.Arrays.toString(s1));

        sorter.setStrategy(new InsertionSort());
        int[] s2 = sorter.sort(data);
        System.out.println("InsertionSort -> " + java.util.Arrays.toString(s2));
    }
}
