import java.util.Scanner;

public class HangMan {

    public void LoadLevel(int level,String challengeWord)
    {
        System.out.printf("\n\n%50s\n","Level "+level);

        HangMan.wait(1200);

        System.out.println("Your Challenge :- \n");

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
                    guessWord.insert(matchedIndex[i],letter.charAt(0));
                }
            }
            Display(guessWord);
            if(CheckGameOverStatus(word,guessWord)){
                System.out.println("Congratulation ! You won ");
                break;
            }
            chances--;
            System.out.println("Chances Left :- "+chances+"\n");
        }
        scObj.close();
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
            if(c==letter)
                count++;
        }
        int[] matchedIndex=new int[count];
        int index=0;
        for (int j = 0; j <word.length() ; j++) {
                if (word.charAt(j)==letter) {
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
            HangMan.wait(700);
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
        data[0]="River";
        data[1]="Sky";
        data[2]="Water";
        data[3]="Fish";
        data[4]="Chicken";

        System.out.print("Enter your Name ( Player 1 ):- ");

        //Declaring Scanner
        Scanner scannerObj = new Scanner(System.in);
        String Player1=scannerObj.nextLine();
        System.out.printf("\n%s\n","Starting Game ... ");

        wait(1200);
        System.out.printf("\n%s\n","Welcome "+Player1);

        scannerObj.close();

        //Declaring Class Object

        HangMan hg=new HangMan();
        for (int i = 0; i < 5; i++) {
            wait(1200);
            hg.LoadLevel(i+1,data[i]);
        }
    }
}