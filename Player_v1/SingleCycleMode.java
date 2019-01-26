package Player_v1;

public class SingleCycleMode implements PlayMode {
    private int curIndex;


    @Override
    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    @Override
    public void setSongNums(int songnums) {

    }

    @Override
    public int getPrevIndex() {
        return curIndex;
    }

    @Override
    public int getNextIndex() {
        return curIndex;
    }


}
