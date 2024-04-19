import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class Hashing {

  // constants
  private static final int C = 123;
  private static String[] hashTable;

  public static void main(String[] args) {
    // read user input
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter file name: ");
    String file = scanner.nextLine();
    System.out.println("Enter size of hash table: ");
    int tableSize = scanner.nextInt();
    scanner.nextLine();

    // initialize hash table
    hashTable = new String[tableSize];

    String[] lines = null;

    try {
      // read lines from file
      lines = Files.readAllLines(Path.of(file)).toArray(new String[0]);
    } catch (Exception e) {
      // handle file reading error
      System.err.println("Error reading file: " + e.getMessage());
      scanner.close();
      return;
    }

    if (lines != null) {
      // process each line in the file
      for (String line : lines) {
        // split line into words
        String[] words = line.split("\\s+");

        // insert each word into hash table
        for (String word : words) {
          // calculate hash value
          int hashedVal = hashLevitin(word);
          // insert word into hash table
          insert(hashedVal, word);
        }
      }

      // display hash table
      displayHashTable();

      // 3a: number of non-empty addresses & load factor
      double loadFactor = findLoadFactor();
      System.out.println("Load Factor: " + loadFactor);

      // 3b: longest empty area
      int[] longestEmptyArea = findLongestEmptyArea();
      System.out.println("Longest Empty Area: " + longestEmptyArea[1] + ", Starting Index: " + longestEmptyArea[0]);

      // 3c: longest cluster
      int[] longestCluster = findLongestCluster();
      System.out.println("Longest Cluster: " + longestCluster[1] + ", Starting Index: " + longestCluster[0]);

      // 3d: hash value from greatest number of distinct words
      int[] mostDistinctWords = findMostDistinctWords();
      System.out.println("Hash Value with Most Distinct Words: " + mostDistinctWords[0] + " with "
          + mostDistinctWords[1] + " distinct words");

      // 3e: word farthest from its actual hash value
      String farthestWord = findFarthestWord();
      System.out.println("Farthest Word: " + farthestWord);
    }

    scanner.close();
  }

  /**
   * This method is a hash function based on the textbook.
   * 
   * @param word word to be hashed
   * @return teh calculated hash value
   */
  public static int hashLevitin(String word) {
    int h = 0;

    // calculate hash value for given word
    for (int i = 0; i < word.length(); i++) {
      h = (h * C + word.charAt(i)) % hashTable.length;
    }

    return h;
  }

  /**
   * This method inserts a word into the hash table.
   * 
   * @param hashVal the hash value of the word
   * @param word    word to be inserted
   */
  public static void insert(int hashVal, String word) {
    int i = hashVal % hashTable.length;

    // handle errors
    while (hashTable[i] != null) {
      i = (i + 1) % hashTable.length;
    }

    // insert word into hash table
    hashTable[i] = word;
  }

  /**
   * This method displays the hash table.
   */
  public static void displayHashTable() {
    for (int i = 0; i < hashTable.length; i++) {
      if (hashTable[i] != null) {
        System.out
            .println("Hash Address: " + i + ", " + "Hashed Word: " + hashTable[i] + ", " + "Hash Value of Word: "
                + hashLevitin(hashTable[i]));
      }
    }
  }

  /**
   * This method calculates the load factor of the hash table.
   * 
   * @return load factor
   */
  public static double findLoadFactor() {
    int count = 0;
    for (String value : hashTable) {
      if (value != null) {
        count++;
      }
    }

    return (double) count / hashTable.length;
  }

  /**
   * This method calculates the longest empty area in the hash table.
   * 
   * @return array containing the starting index and the length of the longest
   *         empty area
   */
  public static int[] findLongestEmptyArea() {
    int maxLength = 0;
    int start = -1;
    int currentLength = 0;
    int i = 0;

    while (i < hashTable.length) {
      if (hashTable[i] == null) {
        currentLength++;
        if (currentLength > maxLength) {
          maxLength = currentLength;
          start = i - maxLength + 1;
        }
      } else {
        currentLength = 0;
      }
      i++;
    }

    // store start index and max length of empty area
    int[] a = new int[] { start, maxLength };

    return a;
  }

  /**
   * This method finds the longest cluster in the hash table.
   * 
   * @return array containing the starting index and the length of the longest
   *         cluster
   */
  public static int[] findLongestCluster() {
    int maxLength = 0;
    int start = -1;
    int currentLength = 0;
    int i = 0;

    while (i < hashTable.length) {
      if (hashTable[i] != null) {
        currentLength++;
        if (currentLength > maxLength) {
          maxLength = currentLength;
          start = i - maxLength + 1;
        }
      } else {
        currentLength = 0;
      }
      i++;
    }

    // store start index and max length of cluster
    int[] a = new int[] { start, maxLength };

    return a;
  }

  /**
   * This method finds the hash value with the most distinct words.
   * 
   * @return array containing the hash value and the number of distinct words
   */
  public static int[] findMostDistinctWords() {
    HashMap<Integer, Integer> wordCount = new HashMap<>();
    for (String word : hashTable) {
      if (word != null) {
        int hashVal = hashLevitin(word);
        wordCount.put(hashVal, wordCount.getOrDefault(hashVal, 0) + 1);
      }
    }

    int mostDistinctWords = 0;
    int maxHashVal = -1;
    for (int hashVal : wordCount.keySet()) {
      int distinctWords = wordCount.get(hashVal);
      if (distinctWords > mostDistinctWords) {
        mostDistinctWords = distinctWords;
        maxHashVal = hashVal;
      }
    }

    // store hash value and distinct words count
    int[] a = new int[] { maxHashVal, mostDistinctWords };

    return a;
  }

  /**
   * This method finds the word farthest from its actual hash value.
   * 
   * @return the farthest word and its distance from its hash value
   */
  public static String findFarthestWord() {
    String farthestWord = null;
    int maxLength = 0;

    for (int i = 0; i < hashTable.length; i++) {
      String word = hashTable[i];
      if (word != null) {
        int hashVal = hashLevitin(word);
        int length = Math.abs(i - hashVal);
        if (length > maxLength) {
          maxLength = length;
          farthestWord = word;
        }
      }
    }

    return farthestWord + " (" + maxLength + " slots)";
  }
}
