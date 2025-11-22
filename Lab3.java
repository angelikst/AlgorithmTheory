public class Lab3 {
    public static void main(String[] args) {

        int[] A = {80,27,37,36,91,53,86,66,98};
        heapSort(A);

        print(A);
    }

    public static void print (int[] arr){
        System.out.print("\n ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    private static int assignees =0, comparisons =0, recursion =1;

    public static void heapSort(int[] arr) {
        int n = arr.length;

        System.out.println("BUILDING HEAP");
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
            System.out.println("Assignees:"+ assignees + " Comparissons:"+ comparisons +" Recursive calls:"+ recursion);
        }

        System.out.println("ARRAY AFTER HEAP BUILD");
        print(arr);

        System.out.print("\nSORTING HEAP\n");
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            assignees +=3;

            heapify(arr, i, 0);
            print(arr);
            System.out.println("Assignees:"+ assignees + " Comparissons:"+ comparisons +" Recursive calls:"+ recursion);
        }
    }

    static void heapify(int[] arr, int n, int i) {
        assignees +=3;
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n){
            if(arr[l] > arr[largest]){
                assignees++;
                largest = l;
            }
            comparisons++;
        }
        comparisons++;

        if (r < n){
            if(arr[r] > arr[largest]){

                largest = r;
                assignees++;
            }
            comparisons++;
        }
        comparisons++;

        System.out.println("Largest:" + arr[largest]+" index"+largest+" i:"+i);
        comparisons++;
        if (largest != i) {

            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            assignees +=3;
            recursion++;
            heapify(arr, n, largest);
        }
    }
}
