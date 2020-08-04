public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        char [] wordArr = word.toCharArray();
        LinkedListDeque<Character> wordList = new LinkedListDeque<>();
        for (char i : wordArr) {

           wordList.addLast(i);

        }
        return wordList;
    }
    public boolean isPalindrome (String word) {
        Deque<Character> wordList = wordToDeque(word);
        StringBuffer reverseWord = new StringBuffer("") ;
        int wordLength = wordList.size();

            for(int i = 0; i < wordLength; i++) {
                reverseWord.append(wordList.removeLast());
            }
        if((reverseWord.toString()).equals(word)) {
            return true;
        }


        return false;
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordList = wordToDeque(word);
        int wordLength = wordList.size();
        if (wordLength < 2 ) {
            return false;
        }
        for (int i = 0; i < wordLength / 2; i++) {
            if(!cc.equalChars(wordList.removeFirst(),wordList.removeLast())) {
                return false;
            }

        }
        return true;

    }

}
