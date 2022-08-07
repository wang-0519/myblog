package data;

import common.SQLHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2022/7/24.
 */
public class NavigationData {

    List<BlogFile> blogFiles;
    private static NavigationData mInstance;

    public static NavigationData getInstance(){
        if (null == mInstance) {
            synchronized (NavigationData.class) {
                if (null == mInstance) {
                    mInstance = new NavigationData();
                }
            }
        }
        return mInstance;
    }

    private NavigationData(){
        blogFiles = new ArrayList<>();
        SQLHelper sql = SQLHelper.getInstance();
        parseBlogFile(sql);
        sql.closeDatabase();
    }

    private void parseBlogFile(SQLHelper sql){
        try{
            ResultSet directorySet = sql.getData("select * from t_directory;");
            if (null != directorySet) {
                while (directorySet.next()) {
                    int id = directorySet.getInt(1);
                    String fileName = directorySet.getString(2);
                    String filePath = directorySet.getString(3);
                    blogFiles.add(new BlogFile(id, fileName, filePath));
                }
                directorySet.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<BlogFile> getBlogFiles(){
        return blogFiles;
    }
}
