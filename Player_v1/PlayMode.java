package Player_v1;

public interface PlayMode {     //音乐播放模式
    void setCurIndex(int curIndex);
    void setSongNums(int songnums);
    int getPrevIndex();
    int getNextIndex();
}
