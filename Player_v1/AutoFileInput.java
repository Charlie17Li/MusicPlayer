package Player_v1;

import Player_v0.FileInput;
import Player_v0.SongInfo;

import java.io.File;
import java.util.LinkedList;

public class AutoFileInput implements FileInput {       //自动搜索某个音乐文件夹
    private LinkedList<SongInfo> playList;
    private File defaultFile;   //默认路径

    public AutoFileInput(){
        this("C:\\Users\\Charlie Li\\Desktop\\Java大作业\\8002117136李强林\\DDPlayer\\music");
    }

    public AutoFileInput(String path){
        playList = new LinkedList<>();
        defaultFile = new File(path);
        autoAdd();
    }

    @Override
    public LinkedList<SongInfo> getPlayList() {
        return playList;
    }

    private void autoAdd(){ //自动添加;
        if(defaultFile != null && defaultFile.isDirectory()){
            //过滤出mp3后缀的文件
            File[] files = defaultFile.listFiles(new MusicFileFilter("wav"));

            for(File file : files){
                SongInfo info = new MSongInfo(file);
                playList.add(info);
            }
        }
    }

}
