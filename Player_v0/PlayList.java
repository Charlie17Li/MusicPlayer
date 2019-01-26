package Player_v0;

import Player_v1.PlayMode;

import java.util.LinkedList;

public interface PlayList {
    SongInfo getCurMusic();
    SongInfo getPrevMusic();
    SongInfo getNextMusic();

    void setCurIndex(int curIndex);     //使用前，必须设置
    void setPlayMode(PlayMode playMode);    //使用前，必须设置
    void setList(LinkedList<SongInfo> list);


}
