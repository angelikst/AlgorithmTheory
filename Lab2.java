public class Lab2 {
    public static void main(String[] args) {

        int[] A = {80, 27, 37, 36, 91, 53, 86, 66, 98};
        int[] B = {80, 27, 37, 36, 91, 53, 86, 66, 98};
        
        print(A);
        System.out.print("\n\n");
        mergeSort(A);

        assignees=0;
        comparisons=0;
        recursion=0;


        System.out.print("\n\n");
        print(B);
        System.out.print("\n\n");
        System.out.println(quickSort(B, 0, B.length - 1));
    }

    public static void print (int[] arr){
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    static int assignees =0, comparisons =0, recursion =0;

    public static void mergeSort(int[] A) {

        int n =  A.length;

        if (n < 2) {
            return;
        }

        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        for (int i = 0; i < mid; i++) {
            assignees++;
            left[i] = A[i];
        }

        for (int i = mid; i < n; i++) {
            assignees++;
            right[i - mid] = A[i];
        }

        recursion++;
        mergeSort(left);

        recursion++;
        mergeSort(right);

        System.out.println("left part:");
        print(left);
        System.out.println("\nright part:");
        print(right);

        merge(A, left, right, left.length, right.length);

        System.out.println("\nAssignees: "+ assignees +" Comparisons: "+ comparisons +" Recursion calls:"+ recursion);
        System.out.println("\n");
    }

    public static void merge(int[] A, int[] left, int[] right, int l, int r) {
        int i = 0, j = 0, k = 0;

        while (i < l && j < r) {

            comparisons++;
            if (left[i] <= right[j]) {
                assignees++;
                A[k++] = left[i++];
            }

            else {
                assignees++;
                A[k++] = right[j++];
            }
        }

        while (i < l) {
            assignees++;
            A[k++] = left[i++];
        }

        while (j < r) {
            assignees++;
            A[k++] = right[j++];
        }
    }

    public static String quickSort(int[] arr, int low, int high){

        print(arr);
        System.out.println("\nAssignees: " + assignees + ", Comparisons: " + comparisons +" Recursion calls:"+ recursion);

        comparisons++;
        if(low<high){
            recursion++;

            assignees++;
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);

            quickSort(arr, pivotIndex + 1, high);
        }

        return "In total\nAssignees: " + assignees + ", Comparisons: " + comparisons +" Recursion calls:"+ recursion;
    }



    private static int partition(int[] arr, int low, int high){
        assignees +=3;
        int pivot = arr[low];
        int left = low +1;
        int right = high;


        while(true){

            comparisons +=2;
            while(left <= right && arr[left] <= pivot){
                assignees++;
                left++;
            }

            comparisons +=2;
            while(right >= left && arr[right] >= pivot){
                assignees++;
                right--;
            }

            comparisons++;
            if(right < left){
                break;
            }
            else{
                assignees +=3;
                int tmp =  arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }
        }
        assignees +=3;
        int tmp = arr[low];
        arr[low] = arr[right];
        arr[right] = tmp;

        return right;
    }
}
