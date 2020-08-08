import java.util.Scanner;

public class HangMan {

    public void LoadLevel(int level,String challengeWord,String Description)
    {
        System.out.printf("\n\n%50s\n","Level "+level);

        HangMan.wait(1000);

        System.out.println("Your Challenge :- "+Description+"\n");

        int totalChances=challengeWord.length();

        System.out.println("Total chances :- "+totalChances+"\n");

        StringBuffer guessWord=new StringBuffer();

        for (int i = 0; i <challengeWord.length() ; i++) {
            guessWord.append("_");
        }

        Display(guessWord);

        System.out.println("\n\nNow, Start Guessing ... \n");
        Play(challengeWord,guessWord,totalChances);

    }

    public  void Play(String word,StringBuffer guessWord,int chances)
    {
        Scanner scObj=new Scanner(System.in);
        while(chances>0)
        {
            System.out.print("Enter guessed character :- ");
            String letter = scObj.next();
            int[] matchedIndex=CheckLetter(word,letter.charAt(0));
            if(matchedIndex.length>0)
            {
                for (int i = 0; i <matchedIndex.length ; i++)
                {
                    if(matchedIndex[i]==0)
                        guessWord.replace(matchedIndex[i],matchedIndex[i]+1, String.valueOf(letter.charAt(0)).toUpperCase());
                    else
                        guessWord.replace(matchedIndex[i],matchedIndex[i]+1, String.valueOf(letter.charAt(0)).toLowerCase());
                }
            }
            System.out.println();
            Display(guessWord);
            if(CheckGameOverStatus(word,guessWord)){
                System.out.println("\n\nCongratulation ! You won ");
                break;
            }
            chances--;
            System.out.println("\t\tChances Left :- "+chances+"\n");
        }

        if(chances==0 && !CheckGameOverStatus(word,guessWord))
        {
            System.out.println("Better Luck Next Time ! ");
            System.exit(0);
        }
    }

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    public boolean CheckGameOverStatus(String word,StringBuffer guessWord)
    {
        return word.contentEquals(guessWord);
    }

    public int[] CheckLetter(String word,char letter)
    {
        int count=0;
        for (char c:
             word.toCharArray()) {
            if(String.valueOf(c).equalsIgnoreCase(String.valueOf(letter)))
                count++;
        }
        int[] matchedIndex=new int[count];
        int index=0;
        for (int j = 0; j <word.length() ; j++) {
                if (String.valueOf(word.charAt(j)).equalsIgnoreCase(String.valueOf(letter))) {
                    matchedIndex[index]=j;
                    index++;
                }
            }
        return  matchedIndex;
    }

    public  void Display(StringBuffer word)
    {
        int size=word.length();

        for (int i = 0; i < size ; i++) {
            System.out.print(word.charAt(i));
            HangMan.wait(500);
            if(i!=size-1){
                System.out.print("\t");
            }
        }
    }

    public static  void main(String[] args)
    {
        System.out.printf("\n%50s\n","HangMan");

        //Storing Data
        String[] data =new String[5];
        data[0]="Emojis";
        data[1]="Bank";
        data[2]="Stairs";
        data[3]="Rain";
        data[4]="Stamp";

        // Description
        String[] desc =new String[5];
        desc[0]="I have many faces, expressions and emotions and am only one tap away. What am I? ";
        desc[1]="I have branches yet I have no leaves, no trunk and no fruit. What am I?";
        desc[2]="I go up and I go down, sometimes I am curvy and sometimes I am straight. What am I? ";
        desc[3]="What do you purposefully put lots of in and on your body, but run away from when you encounter it outside? ";
        desc[4]="I go to and from India and Burma, Alaska and Nebraska and travel all over the world but I always stay in the corner. What am I?";
        System.out.print("Enter your Name ( Player 1 ):- ");

        //Declaring Scanner
        Scanner scannerObj = new Scanner(System.in);
        String Player1=scannerObj.nextLine();
        System.out.printf("\n%s\n","Starting Game ... ");

        wait(1000);
        System.out.printf("\n%s\n","Welcome "+Player1);

        //Declaring Class Object

        HangMan hg=new HangMan();
        for (int i = 0; i < 5; i++) {
            wait(1000);
            hg.LoadLevel(i+1,data[i],desc[i]);
        }

        scannerObj.close();
    }
}