package sample;

public class SortArray {
    private int[] array;

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        if (array != null) {
            this.array = array;
        } else {
            System.err.println("Empty Array! Please enter valid array!");
            System.exit(1);
        }
    }

    public SortArray(int[] array) {
        setArray(array);
    }

    public void sort(boolean ascending) {
        if (ascending) {
            // using the shell - insertion sorting method ( faster than regular insertion )
            // it uses gradually reducing gaps and checks if the element before the current element is smaller
            // and if it is it swaps the
            for (int gap = array.length / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < array.length; i++) {
                    int temp = array[i];
                    int j;
                    for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                        array[j] = array[j - gap];
                    }
                    array[j] = temp;
                }
            }
        }
        else {
            for (int gap = array.length / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < array.length; i++) {
                    int temp = array[i];
                    int j;
                    for (j = i; j >= gap && array[j - gap] < temp; j -= gap) {
                        array[j] = array[j - gap];
                    }
                    array[j] = temp;
                }
            }
        }
    }
}

