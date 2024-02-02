
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// This function gets a string and return its tail
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		// This function implements the Levenshtein distance function
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word2.isEmpty()) {
			return word1.length();
		}
		if (word1.isEmpty()) {
			return word2.length();
		}
		if (word1.charAt(0) == word2.charAt(0)){
			return levenshtein(tail(word1), tail(word2));
		} else {
			int delete = levenshtein(tail(word1), word2) + 1;
			int insert = levenshtein(word1, tail(word2)) + 1;
			int replace = levenshtein(tail(word1), tail(word2)) + 1;	
			return Math.min(delete, Math.min(insert, replace));
		}
	}

	public static String[] readDictionary(String fileName) {
		// This function read a local file of words, and store them in an array
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		for (int i=0 ; i<dictionary.length ; i++){
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// This funnction receives a word, a threshold value for distance, and a dictionary as inputs. 
		// It returns the word from the dictionary that most closely resembles the given word.
		String result = word;
		int minDistance = 300;
		for (int i=0 ; i<dictionary.length ; i++){
			int distance = levenshtein(word, dictionary[i]);
			if (distance <= threshold){
				if (distance < minDistance){
					minDistance = Math.min(minDistance, distance);
					result = dictionary[i];
				}
			}
		} 
		return result;
	}
}
