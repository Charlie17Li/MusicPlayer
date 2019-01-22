public interface Player {
    void onPlay(SongInfo info);
    void stopPlay();
    void pausePlay();
    void resumePlay();      //继续播放
}
