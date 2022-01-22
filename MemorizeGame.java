import java.util.*;
class Game
{
    static int attempts,successfull_counts;
    int min=1,max=10;
    String playername;
    int digits,sec,value;
    int autopilotsucess,autopilotfailure;
    boolean autopilotstate=false;
    Scanner sc=new Scanner(System.in);
    Game(String name,int digits,int sec)
    {
        playername=name;
        this.digits=digits;
        if(sec==0)
        this.sec=3;
        else
        this.sec=sec;
        int temp=digits;
        while(temp>1)
        {
            min=min*10;
            max=max*10;
            temp--;
        }
    }
    public void showNumber()throws Exception
    {
        
        if(autopilotstate)
        {
            if(autopilotsucess==3)
            {
            min=min*10;
            max=max*10;
            autopilotsucess=0;
            autopilotfailure=0;
            }
            else if(autopilotfailure==3)
            {
             if(digits>1)
             {
               min=min/10;
               max=max/10;
               digits--;
             }
             autopilotsucess=0; 
             autopilotfailure=0;
            }
        }
        value=(int)(Math.random()*(max-min+1)+min); 
        attempts++;
       	System.out.print("\nHere the value you remember :"+""+value);
       	Thread.sleep(sec*1000);
       	System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
    }
    public void enableAutoPilotMode()
    {
        autopilotstate=true;
        autopilotsucess=0;
        autopilotfailure=0;
        System.out.println("AutoPilot Mode Activated.....");
    }
    public void disableAutoPilotMode()
    {
        autopilotstate=false;
        autopilotsucess=0;
        autopilotfailure=0;
        System.out.println("AutoPilot Mode Deactivated.....");
    }
    public void validateNumber()
    {
        System.out.println("________________________________________");
        System.out.print("Enter the number you remember :");
        int playervalue=sc.nextInt();
        if(playervalue==value)
        {
            successfull_counts++;
             if(autopilotstate)
             {
             autopilotsucess++;
             autopilotfailure=0;
             }
            System.out.println("\nKudous!! "+playername +" you have made it right\n");
        }
        else
        {
            if(autopilotstate)
            {
            autopilotfailure++;
            autopilotsucess=0;
            }
            System.out.println("\nOOPS!! "+ playername +" bad luck this time you made it wrong\n");
        }
    }
    public void displayScoreCard()
    {
        System.out.println("\n\n_______SCORECARD_______");
        System.out.println("Player Name :"+playername);
        System.out.println("Number of times the game played :"+attempts);
        System.out.println("Number of successfull Attempts :"+successfull_counts);
        int percentage=(int)((successfull_counts*1.0/attempts*1.0)*100.00);
        System.out.println("Success Rate :"+(int)((successfull_counts*1.0/attempts*1.0)*100.00)+"%");
    }
}
public class MemorizeGame
{
	public static void main(String[] args) {
	    int option;
	    Scanner sc=new Scanner(System.in);
	   	System.out.println("_________WELCOME TO MEMORIZE GAMES_________\n\n");
	   	System.out.print("Enter your Name : ");
	   	String name=sc.next();
	   	System.out.print("Enter number of digits you are going to remember :");
	   	int digits=sc.nextInt();
	   	System.out.print("Enter your required time to memorize (in secs) [if not interseted means give 0]:");
	   	int secs=sc.nextInt();
	    boolean aoption=false;
	    boolean your_option;
	   	Game player1=new Game(name,digits,secs);
	   try {
	     do
	      {
	   	    if(aoption)
	   	    {
    	   	   System.out.print("Do you want to disable the AutoPilot Mode (YES:true | NO:false) :");
    	   	   your_option=sc.nextBoolean();
    	   	   if(your_option)
    	   	   player1.disableAutoPilotMode();
	   	    }  
    	   	else
    	   	{
    	   	    System.out.print("Do you want to enable the AutoPilot Mode (YES:true | NO:false)");
    	   	    your_option=sc.nextBoolean();
    	   	    if(your_option)
    	   	    {
    	   	    player1.enableAutoPilotMode();
    	   	    aoption=true;
    	   	    }
    	   	}
	   	    player1.showNumber();
	   	    player1.validateNumber();
	   	    System.out.print("\nAre you continue the game(YES =1 | NO=0)");
	   	    option=sc.nextInt();
	   	}while(option==1);
	   } catch(Exception e) {
	       System.out.println("Exception occurs enter your input in correct format :" +e);
	   } 
	   	player1.displayScoreCard();
	}
}




