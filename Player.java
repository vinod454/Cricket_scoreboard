public class Player {
    private int score =0;
    private int four =0;
    private int six =0;
    private int balls =0;
    private boolean onStrike = false;
    private boolean onNonStrike = false;
    public String name;

    public Player(int score, int four, int six, int balls, String name){
        this.score = score;
        this.four = four;
        this.six = six;
        this.balls = balls;
        this.name = name;
    }
    public void incrementBalls()
    {
        this.balls++;
    }

    public void addrun(int run) {
        if(run == 4)
        {
            this.four +=1; 
        }
        else if(run == 6)
        {
            this.six +=1; 
        }
        this.score+=run;
        this.incrementBalls();
    }

    public void printpPlayerScore() {
        String addStar  = this.onStrike ? "**" : this.onNonStrike ?"*":"";
        System.out.println(this.name+addStar+"\t"+this.score+"\t"+this.four+"\t"+this.six+"\t"+this.balls);
    }

    public void setOnStrike() {
        this.onStrike = true;
        this.onNonStrike = false;
    }

    public void setOnNONStrike() {
        this.onNonStrike = true;
        this.onStrike = false;
    }
    public void clearStrike() 
    {
        this.onNonStrike = false;
        this.onStrike = false;
    }
}
