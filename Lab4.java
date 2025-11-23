import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Lab4 {


    static int assignees = 0, comparisons = 0, a = 0, b = 0;
    static int m = 13;

    public static void main(String[] args) {
        String[] words = {"Шануй", "батька", "й", "матір", "то", "буде", "тобі", "в","житті","добре"};

        String[] table = ClosedHash(words, 0);
        displayHashTable(table);
        search(table, 0);
        used.clear();
        System.out.println("\n");

        assignees = 0;
        comparisons = 0;
        a=0;
        b=0;


        List<List<String>> table2 = OpenHash(words, 0);
        displayHashTable(table2);
        search(table2);
        used.clear();

        System.out.println("\n\n\n");

        assignees = 0;
        comparisons = 0;
        m = 16;

        a=0;
        b=0;
        String[] table3 = ClosedHash(words, 1);
        displayHashTable(table3);
        search(table3, 1);
        used.clear();

        System.out.println("\n");

        assignees = 0;
        comparisons = 0;
        a=0;
        b=0;
        List<List<String>> table4 = OpenHash(words, 1);
        displayHashTable(table4);
        search(table4);
    }

    static Map<Character, Integer> letters = Map.ofEntries(
            Map.entry('А', 1), Map.entry('Б', 2), Map.entry('В', 3), Map.entry('Г', 4),
            Map.entry('Ґ', 5), Map.entry('Д', 6), Map.entry('Е', 7), Map.entry('Є', 8),
            Map.entry('Ж', 9), Map.entry('З', 10), Map.entry('И', 11), Map.entry('І', 12),
            Map.entry('Ї', 13), Map.entry('Й', 14), Map.entry('К', 15), Map.entry('Л', 16),
            Map.entry('М', 17), Map.entry('Н', 18), Map.entry('О', 19), Map.entry('П', 20),
            Map.entry('Р', 21), Map.entry('С', 22), Map.entry('Т', 23), Map.entry('У', 24),
            Map.entry('Ф', 25), Map.entry('Х', 26), Map.entry('Ц', 27), Map.entry('Ч', 28),
            Map.entry('Ш', 29), Map.entry('Щ', 30), Map.entry('Ь', 31),
            Map.entry('Ю', 32), Map.entry('Я', 33)
    );

    static int hashFromMapDivide(String word) {
        int sum = 0;
        for (Character ch : word.toCharArray()) {
            Integer pos = letters.get(ch);
            assignees++;
            comparisons++;
            if (pos != null) sum += pos;
        }
        if (a < 10) {
            System.out.println(word + " position in table: " + sum+" mod "+m+" = "+check(sum%m,word,sum));
            a++;
        }
        return sum % m;
    }

    static double A = 0.6180339887;

    static int hashFromMapMultiply(String word) {
        int sum = 0;
        for (Character ch : word.toCharArray()) {
            Integer pos = letters.get(ch);
            assignees++;
            comparisons++;
            if (pos != null) sum += pos;
        }
        if (b < 10) {
            System.out.println(word + " position in table: "+m+" * ("+sum+" * 0.6180339887 mod 1) = "+check(((int) Math.floor(m * ((sum * A) % 1))), word,sum));
            b++;
        }
        return (int) Math.floor(m * ((sum * A) % 1));
    }

    static ArrayList<Integer> used = new ArrayList<>();
    static int check(int a, String word, int sum) {
            while
            //if
            (used.contains(a)) {
                System.out.println(word+" position = "+m+" * ("+sum+" * 0.6180339887 mod 1) = "+a);
                //System.out.println(word+" position = "+sum + " mod " + m +" = " + a);
                System.out.printf("Sell %d is taken, placing %s into the next cell\n", a, word);
                //System.out.println("Sell "+a+" isn't empty, adding "+word);
                a++;
            }
            used.add(a);
        return a;
    }

    static String[] ClosedHash(String[] words, int a) {
        String[] hashTable = new String[m];

        for (String word : words) {
            word = word.toUpperCase();
            int startAddress = (a == 0) ? hashFromMapDivide(word) : hashFromMapMultiply(word);
            boolean inserted = false;

            for (int i = 0; i < m; i++) {
                int address = (startAddress + i) % m;
                assignees++;
                comparisons++;
                if (hashTable[address] == null) {
                    hashTable[address] = word;
                    inserted = true;
                    break;
                }
            }

            if (!inserted) {
                System.out.println("Can't add word, table already full: " + word);
            }
        }

        return hashTable;
    }

    static void search(String[] table, int a) {
        int totalComparisons = 0;
        int maxComp = 0;
        String maxWord = "";
        int wordCount = 0;

        for (String word : table) {
            if (word == null) continue;
            wordCount++;

            int startAddress = (a == 0) ? hashFromMapDivide(word) : hashFromMapMultiply(word);
            int i = 0;
            int comparisons = 0;

            while (i < m) {
                int j = (startAddress + i) % m;
                comparisons++;

                if (table[j] != null && table[j].equals(word)) {
                    break;
                }
                i++;
            }

            System.out.println("Word: " + word + " Comparisons: " + comparisons);

            totalComparisons += comparisons;

            if (comparisons > maxComp) {
                maxComp = comparisons;
                maxWord = word;
            }
        }

        double average = (wordCount == 0) ? 0 : (double) totalComparisons / wordCount;

        System.out.println("Word with most comparisons: " + maxWord + " (" + maxComp + ")");
        System.out.println("Average comparisons: " + average);
    }

    static void displayHashTable(String[] hashTable) {

        System.out.println("\nHash Table for m = " + m);

        int n = hashTable.length;

        for (int i = 0; i < n; i++) {
            System.out.printf("|%-12d|", i);
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            String val = hashTable[i] == null ? "" : hashTable[i];
            System.out.printf("|%-12s|", val);
        }
        System.out.println("\n");

        System.out.println("Statistics for table creation:");
        System.out.println("Assignees: " + assignees + "  Comparisons: " + comparisons + "\n");
    }


    static List<List<String>> OpenHash(String[] words, int a) {
        List<List<String>> hashTable = new ArrayList<>(m);
        int address = 0;
        for (int i = 0; i < m; i++) {
            hashTable.add(new ArrayList<>());
        }

        for (String word : words) {
            word = word.toUpperCase();

            assignees++;
            address = (a == 0) ? hashFromMapDivide(word) : hashFromMapMultiply(word);

            assignees++;
            hashTable.get(address).add(word);
        }

        return hashTable;
    }

    static void search(List<List<String>> table) {
        int totalComparisons = 0;
        int maxComp = 0;
        String maxWord = "";

        for (List<String> chain : table) {
            for (int i = 0; i < chain.size(); i++) {
                String word = chain.get(i);
                int compForWord = i + 1;
                totalComparisons += compForWord;
                System.out.println("Word: " + word + " Comparisons: " + compForWord);

                if (compForWord > maxComp) {
                    maxComp = compForWord;
                    maxWord = word;
                }
            }
        }

        double average = table.stream().mapToInt(List::size).sum() == 0 ? 0 :
                (double) totalComparisons / table.stream().mapToInt(List::size).sum();

        System.out.println("Word with most comparisons: " + maxWord + " (" + maxComp + ")");
        System.out.println("Average comparisons: " + average);
    }

    static void displayHashTable(List<List<String>> hashTable) {
        System.out.println("\nHash Table for m = " + m);

        int n = hashTable.size();

        int maxHeight = 0;
        for (List<String> bucket : hashTable) {
            if (bucket.size() > maxHeight) {
                maxHeight = bucket.size();
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.printf("|%-12d|", i);
        }
        System.out.println();

        for (int level = 0; level < maxHeight; level++) {
            for (int i = 0; i < n; i++) {

                List<String> bucket = hashTable.get(i);

                if (level < bucket.size()) {
                    System.out.printf("|%-12s|", bucket.get(level));
                } else {
                    System.out.printf("|%-12s|", "");
                }
            }
            System.out.println();
        }

        System.out.println("\nStatistics for table creation:");
        System.out.println("Assignees: " + assignees + "  Comparisons: " + comparisons + "\n");
    }
}
