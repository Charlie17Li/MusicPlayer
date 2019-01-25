package Player_v0;

import Player_v0.SongInfo;

public interface Player {
    void onPlay(SongInfo info);
    void stopPlay();
    void pausePlay();
    void resumePlay();      //继续播放
}
