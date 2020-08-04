/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    public static void main(String[] args) {
        int minLength = 4;
       // In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();

//        while (!in.isEmpty()) {
//            String word = in.readString();
//            if (word.length() >= minLength && palindrome.isPalindrome(word)) {
//                System.out.println(word);
//            }
//        }

        for(int i = 0; i < 5 ; i++) {
            int sum = 0;
            int longest = 0;
            String longestWord = "";
            In in = new In("../library-sp18/data/words.txt");
            while (!in.isEmpty()) {

                String word = in.readString();
                if (palindrome.isPalindrome(word, new OffByN(i))){
                    sum ++;
                   // System.out.println(word);
                    if(word.length()>longest) {
                        longest = word.length();
                        longestWord = word;
                    }


                }

            }
            System.out.println(i+ "sum = " + sum);

            System.out.println(longestWord+"                 is"+longest);



        }

    }
}
