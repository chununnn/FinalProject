public class Score {
    private int score;
    Score(int init_score){
        this.score = init_score;
    }
    public void setPoint(int score){
        this.score = score;
    }

    public int returnScore(){
        return score;
    }
}
