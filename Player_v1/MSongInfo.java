package Player_v1;

import Player_v0.SongInfo;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MSongInfo implements SongInfo {

    private String mSongName;
    private String mSongPath;
    private String mPlayer;     //歌手
    private AudioInputStream inputStream;

    public MSongInfo(File file){
        this.initMember(file);

        try {
            System.out.println("文件位置：" + file.getAbsolutePath());
            inputStream = AudioSystem.getAudioInputStream(file);

        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getSongName() {
        return mSongName;
    }

    @Override
    public String getSongPath() {
        return mSongPath;
    }

    @Override
    public String getPlayer() {
        return mPlayer;
    }

    @Override
    public AudioInputStream getAudioStream() {
        return inputStream;
    }


    private void initMember(File file){
        //歌曲路径，歌曲名，歌手
        String fileName = file.getName();
        mSongPath = file.getAbsolutePath();
        String pattern = "[\\u4e00-\\u9fa5A-Za-z0-9\\s]+";     //匹配歌曲名和歌手
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(fileName);

        if(m.find()){
            mSongName = m.group(0);
            System.out.println(mSongName);
            //mPlayer = m.group(1);
            if(mSongName == null || mPlayer == null)
                System.out.println("正则表达式：可能有问题");
        }else{
            System.out.println("匹配出错");
        }


    }
}
