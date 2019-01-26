package Player_v0;

public interface Player {
    void onPlay(SongInfo info);
    void stopPlay();
    void pausePlay();
    void resumePlay();      //继续播放
    PlayStatus getStatus();
}
