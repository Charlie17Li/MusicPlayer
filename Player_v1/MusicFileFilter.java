package Player_v1;

import java.io.File;
import java.io.FileFilter;

public class MusicFileFilter implements FileFilter {
    private String filterName;
    public MusicFileFilter(String filterName){
        this.filterName = filterName;
    }

    @Override
    public boolean accept(File pathname) {  //过滤出mp3格式的文件
        return pathname.getName().endsWith("." + filterName);
    }
}
