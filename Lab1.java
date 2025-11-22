public class Lab1 {
    public static void main(String[] args) {

        int[] arr = {80, 27, 37, 36, 91, 53, 86, 66, 98};

        int[] arr2 = {80, 27, 37, 36, 91, 53, 86, 66, 98};

        selectionSort(arr);

        System.out.println("\n\n");

        assignees=0;
        comparisons=0;

        insertSort(arr2);
    }

    static int assignees = 0, comparisons = 0;

    public static void print (int[] arr){
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            assignees++;
            int key = arr[i];

            assignees++;
            int j = i - 1;

            while (j >= 0 && key < arr[j]) {
                comparisons ++;

                assignees ++;
                arr[j + 1] = arr[j];

                assignees++;
                j--;
            }
            assignees += 1;
            arr[j + 1] = key;

            print(arr);
            System.out.println("\nassignees:" + assignees + " comparisons:" + comparisons);
        }
    }

    public static void selectionSort(int[] arr) {
        for(int i = 0; i < arr.length-1; i++) {
            assignees ++;
            int min = i;

            for(int j = i + 1; j < (arr.length); j++) {

                comparisons++;
                if (arr[j]<arr[min]){
                    assignees ++;
                    min = j;
                }
            }

            comparisons++;
            if(min !=i) {
                assignees +=3;
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
            print(arr);
            System.out.println("\nassignees:"+ assignees +" comparisons:"+comparisons);
        }
    }
}
