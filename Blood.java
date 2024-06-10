public class Blood {
    private int blood;
    Blood(int init_blood){  //初始化血量
        this.blood = init_blood;
    }

    public void bloodMinusOne(){
        this.blood = this.blood-1;
    }
    public int getBlood(){
        return this.blood;
    }
}
