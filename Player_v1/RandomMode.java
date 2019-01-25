package Player_v1;

import java.util.Random;

public class RandomMode implements PlayMode {
    private int curIndex;
    private int songNums;
    @Override
    public void setCurIndex(int curIndex) {
        this.curIndex = curIndex;
    }

    @Override
    public int getPrevIndex() {
        int tmpIndex;
        Random random = new Random();

        do {
            tmpIndex = random.nextInt(songNums);
        }while (curIndex == tmpIndex);
        return tmpIndex;
    }

    @Override
    public int getNextIndex() {
        return getPrevIndex();
    }
}
