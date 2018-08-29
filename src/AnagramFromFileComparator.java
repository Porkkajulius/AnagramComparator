import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
 
public class AnagramFromFileComparator {
 
            /* Read 250 000 words from dictionary.csv file and fetch into wordList */
            public static void main(String[] args) throws FileNotFoundException, IOException {
                         String csvFile = "C://tmp/englishDictionary.csv";
                         String line = "";
                         List<String> wordList = new ArrayList<String>();
 
                         /* Use BufferedReader and fetch rows while new line in dictionary file */
                         try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
 
                                     while ((line = br.readLine()) != null) {
                                                  wordList.add((line));
                                     }
                         }
 
                         /*
                         * Map with string and list. String will be a word and List collects all words
                         * which are anagrams
                         */
                         Map<String, List<String>> wordMapList = new HashMap<String, List<String>>();
 
                         /* Use listFromFile method which creates anagram mappings */
                         listFromFile(wordList, wordMapList);
 
                         /* Use compareAnagrams after all mapping has been done */
                         compareAnagrams(wordMapList);
 
            }
 
            /* Loop wordList which contains data from dictionary file */
            private static void listFromFile(List<String> wordList, Map<String, List<String>> wordMapList) {
                         for (int i = 0; i < wordList.size(); i++) {
 
                                     /* Sorting string value from wordList to lower case and alphabetic order */
                                     char sortedKey[] = wordList.get(i).toLowerCase().toCharArray();
                                     Arrays.sort(sortedKey);
                                     String sortedKeyString = String.valueOf(sortedKey);
 
                                     /* If wordMapList key = sortedKeyString then its anagram */
                                     if (wordMapList.containsKey(sortedKeyString)) {
 
                                                  /*
                                                  * Add sortedKeyString to List where key=sortedKeyString. Every word will have
                                                  * list which contains anagrams
                                                  */
                                                 wordMapList.get(sortedKeyString).add(wordList.get(i));
 
                                                  /* Process goes to else always first time, because wordMapList is empty */
                                     } else {
                                                  List<String> mapppingWordList = new ArrayList<String>();
                                                  mapppingWordList.add(wordList.get(i));
                                                  wordMapList.put(sortedKeyString, mapppingWordList);
                                     }
                         }
            }
 
            /* Use listFromFile method which creates anagram mappings */
            private static void compareAnagrams(Map<String, List<String>> wordMapList) {
 
                         /* Looping wordMapList */
                         for (Entry<String, List<String>> entry : wordMapList.entrySet()) {
                                     /*
                                     * EntryValue contains list of strings that are anagrams to key word, if list
                                     * size>1 then we know the map contains anagrams
                                     */
                                     if (entry.getValue().size() > 1) {
                                                  for (int i = 0; i < entry.getValue().size(); i++) {
 
                                                              if (!entry.getKey().equals(entry.getValue().get(i)))
                                                                          System.out.println(entry.getKey() + " is anagram with " + entry.getValue().get(i));
 
                                                              if (!entry.getValue().get(i).equals(entry.getKey())) {
                                                                          System.out.println(entry.getValue().get(i) + " is anagram with " + entry.getKey());
                                                              }
                                                  }
                                     }
 
                         }
            }
 
}