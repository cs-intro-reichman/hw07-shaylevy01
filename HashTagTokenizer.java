

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In in = new In(fileName);
		// This function read a local file of words, and store them in an array
		for (int i=0 ; i<dictionary.length ; i++){
			dictionary[i] = in.readLine();
		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		// This function take a string as an input and determine its presence in the dictionary
		boolean result = false;
		for (int i=0 ; i<dictionary.length ; i++){
			if (dictionary[i].equals(word)){
				result = true;
			}
		}
		return result;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
		hashtag = hashtag.toLowerCase();
		int N = hashtag.length();
        for (int i = 1; i <= N; i++) {
			String prifix = hashtag.substring(0,i);
			boolean isInDirectory = existInDictionary(prifix, dictionary);
			if (isInDirectory == true){
				System.out.println(prifix);
				breakHashTag(hashtag.substring(i, N), dictionary);
				return;
			}
        }
    }
}
