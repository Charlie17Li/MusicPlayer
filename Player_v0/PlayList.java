package Player_v0;

import Player_v0.SongInfo;

public interface PlayList {
    SongInfo getCurMusic();
    SongInfo getPrevMusic();
    SongInfo getNextMusic();
    void setCurIndex(int curIndex);

}
