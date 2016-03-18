package words3;

import java.util.*;

/**
 * Created by Xuhao Chen on 16/3/17.
 */
public class Words3 {
    public static void main(String[] args){new Words3();}

    private ArrayList<Word> words = new ArrayList<Word>();

    /**
     * Word Object which stores content and numbers of the word appears
     */
    private class Word{
        private String content;    //word's content
        private int time;     //times of this word appears

        public Word(String content){
            this.content = content;
            time=1;    //appears once when created
        }

        /**
         * increment time
         */
        public void addOne(){
            time++;
        }

        /**
         *
         * @return content in String type
         */
        public String getContent(){
            return content;

        }

        /**
         *
         * @return number of this word appears in int type
         */
        public int getTimes(){
            return time;
        }
    }

    /**
     * constructor
     */
    public Words3(){

        System.out.println("Please enter a line:");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Queue<Integer> spaceList = new LinkedList<Integer>();

        for(int i=0;i<input.length();i++){
            if(input.charAt(i)==' '){
                spaceList.offer(i);     //get all spaces' location and store into spaceList
            }
        }

        int start=0;
        String content;
        int space = spaceList.poll();
        for(int i=0;i<input.length();i++){
            content = "";

            for(int j = start;j<space;j++){
                char tmpChar = input.charAt(j);
                if(tmpChar!=' ')  content += tmpChar;
            }

            if(!content.equals("")){
                Word tmp = new Word(content);
                if(!exists(tmp)) words.add(tmp);  //if the word is the first time appears
                else{
                    Word t = getWord(content);
                    if(t!=null) t.addOne();
                    else{
                        System.out.println("Cannot found the Word from words");
                        return;
                    }
                }
            }

            start = space+1;
            i = space;
            if(!spaceList.isEmpty())  space = spaceList.poll();
            else space = input.length();  //if there is no more space which means next word is the last word in the line
        }
        printAll();
    }

    /**
     * get the Word Object of the specific content
     * @param content: the content of the word
     * @return the Word Object which stored in words
     */
    private Word getWord(String content){
        boolean found=false;
        int location=-1;
        for(int i=0;i<words.size();i++){
            if(words.get(i).getContent().equals(content)){
                found=true;
                location=i;
                break;
            }
        }
        if(found){
            return words.get(location);
        }
        return null;
    }

    /**
     * check if this word has already been created
     * @param aWord: the Word
     * @return
     */
    private boolean exists(Word aWord){
        for(int i=0;i<words.size();i++){
            if(aWord.getContent().equals(words.get(i).getContent())) return true;
        }
        return false;
    }

    /**
     * print all result from words
     */
    private void printAll(){
        for(int i=0;i<words.size();i++){
            Word tmp = words.get(i);
            System.out.print(tmp.getContent()+": ");
            System.out.println(tmp.getTimes());
        }
    }
}
