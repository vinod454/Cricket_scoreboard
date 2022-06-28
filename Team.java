import java.util.Scanner;

public class Team {
    private int teamScore = 0;
    private int teamWickets = 0;
    private int teamOversCount = 0;
    private int teamPlayerCount = 0;
    private int striker;
    private int nonStriker;
    private String teaName;
    private int wicketDown =0;

    public Player [] teamPlayers;
    public Over [] teamOvers;
    public Scanner sc=new Scanner(System.in); 

    public Team(int tScore , int tWicket, int tOversC, int tPlayersC, String tName)
    {
        this.teamScore = tScore;
        this.teamWickets = tWicket;
        this.teamOversCount = tOversC;
        this.teamPlayerCount = tPlayersC;
        teamPlayers = new Player[tPlayersC];
        for(int i=0;i<tPlayersC;i++)
        {
            teamPlayers[i] = new Player(0,0,0,0,tName+(i+1));
        }
        teamOvers = new Over[tOversC];
        for(int j=0;j<tOversC;j++)
        {
            teamOvers[j] = new Over();
        }
        this.striker = 0;
        this.nonStriker = 1;
        teamPlayers[striker].setOnStrike();
        teamPlayers[nonStriker].setOnNONStrike();
        this.teaName = tName;
    }

    public void updateSheet(String runs_currentBall) 
    {
        if(this.checkifillegalballbolwed(runs_currentBall))
        {
            int runs = Integer.parseInt(runs_currentBall);
            this.teamPlayers[striker].addrun(runs);
            this.teamScore += runs;
            if (runs % 2 == 1) {
                this.switchStrike();
            }
        }
        /*else
        {
            if(runs_currentBall.equals("W")) // Wicket on ball
            {
                this.teamPlayers[striker].incrementBalls();
                this.teamPlayers[striker].clearStrike();
                this.striker = this.wicketDown+2;
                this.wicketDown++;
                this.teamPlayers[striker].setOnStrike();
            }
            if (runs_currentBall.equals("Wd")) // Wide ball
            {
                this.teamScore += 1;
            }

        }*/
        
        
	}
    public boolean isNumeric(String string) {
        int intValue;
            
        //System.out.println(String.format("Parsing string: \"%s\"", string));
            
        if(string == null || string.equals("")) {
            //System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }
        
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            //System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
    public boolean checkifillegalballbolwed(String actionOnBall)
    {
        if(isNumeric(actionOnBall)) { 
            int runs = Integer.parseInt(actionOnBall);
            this.teamPlayers[striker].addrun(runs);
            this.teamScore += runs;
            if (runs % 2 == 1) {
                this.switchStrike();
            }
            return true;
        } 
        if(actionOnBall.equalsIgnoreCase("W")) // Wicket on ball
        {
            this.teamPlayers[striker].incrementBalls();
            this.teamPlayers[striker].clearStrike();
            this.striker = this.wicketDown+2;
            this.wicketDown++;
            if( this.wicketDown < (this.teamPlayerCount -1))
            {
                this.teamPlayers[striker].setOnStrike();
            }
            return true;
        }
        if (actionOnBall.equalsIgnoreCase("Wd")) // Wide ball
        {
            this.teamScore += 1;
            return false;
        }
        else
        {
            //System.out.println("String is not numeric.");
            return false;
        }


    }
    public void switchStrike ()
    {
        int temp = this.striker;
        this.striker = this.nonStriker;
        this.nonStriker = temp;
        this.teamPlayers[striker].setOnStrike();
        this.teamPlayers[nonStriker].setOnNONStrike();
    }

    public void printScoreBoard()
    {
        System.out.println("Scorecard for Team "+teaName);
        System.out.println("Name\tScore\t4s\t6s\tBalls");
        for (Player p :teamPlayers){
            p.printpPlayerScore();
        }
        System.out.println("Total:"+ this.teamScore+"/"+this.wicketDown);
        System.out.println("Over");
    }
    public void printTeam()
    {
        System.out.println("Batting order of Team "+teaName+" :");
        for(int i =0; i< teamPlayerCount; i++)
        {
            //System.out.println("A"+(i+1));
            System.out.println(this.teamPlayers[i].name);
        }
    }
    public int getScore()
    {
        return this.teamScore;
    }
    public void setOvers(Boolean decide, int AteamRuns)
    {
        for(int j =0; j< teamOversCount; j++)
        {
            System.out.println("Add action on each ball per over ");
            System.out.println("Over "+ (j+1));
            boolean stopmatch = false;
            for (int ball =0; ball <6; ball ++){
                String runs_currentBall = sc.next();
                this.teamOvers[j].balllist.add(runs_currentBall);
                if(this.checkifillegalballbolwed(runs_currentBall) == false)
                {
                    ball--;
                }
                if(this.wicketDown == this.teamPlayerCount -1 )
                {
                    this.printScoreBoard();
                    if(decide)
                    {
                        System.out.println("Result: Team A Won by "+( AteamRuns - this.teamScore)+"runs");
                    }
                    stopmatch =true;
                    break;
                }
                if(decide && this.decideMatchWinner(AteamRuns))
                {
                    this.printScoreBoard();
                    stopmatch =true;
                    break;
                }
            }
            
            if(stopmatch)
            {
                break;
            }
            else {
                this.switchStrike();
                this.printScoreBoard();
            }
        }
        if(decide && AteamRuns >this.teamScore)
        {
            System.out.println("Result: Team A Won by "+( AteamRuns - this.teamScore)+"runs");
        }
    }
    private boolean decideMatchWinner(int ateamRuns) {
        if(this.getScore() > ateamRuns)
        {
            System.out.println("Result: Team B Won by "+(teamPlayerCount -1-this.wicketDown)+"wickets");
            return true;
        }
        return false;
        
    }

    public void printCurrentOver(int o)
    {
        if(0>=teamOversCount)
        {
            System.out.println("Invalid Over number");
            return;
        }
        System.out.println("Over number"+ (o));
        System.out.println(this.teamOvers[o-1].balllist);
    }
    public void allOver()
    {
        for(int j =0; j< teamOversCount; j++)
        {
            System.out.println("Over "+ (j+1));
            System.out.println(this.teamOvers[j].balllist);
        }
    }

}
