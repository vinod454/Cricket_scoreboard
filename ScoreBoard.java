import java.util.Scanner;

public class ScoreBoard{
    public static void main(String args[])
    {
        Team teamA;
        //Team teamB = new Team();
        Scanner sc=new Scanner(System.in); 
        System.out.println("Number of Players of each team:");
        int players = sc.nextInt(); 
        System.out.println("Number of Overs:");
        int overs = sc.nextInt(); 

        teamA = new Team(0,0, overs, players, "A");
        teamA.printTeam();
        teamA.setOvers(false, teamA.getScore());

        Team teamB = new Team(0,0, overs, players, "B");
        teamB.printTeam();
        teamB.setOvers(true, teamA.getScore());

    }
    
}