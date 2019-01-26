package Player_v1;

public class ListCycleMode implements PlayMode {
    private int curIndex;
    private int songNums;
    @Override
    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    @Override
    public void setSongNums(int songnums) {
        this.songNums = songnums;
    }

    @Override
    public int getPrevIndex() {
        return (--curIndex) % songNums;
    }

    @Override
    public int getNextIndex() {
        return (++curIndex) % songNums;
    }
}
