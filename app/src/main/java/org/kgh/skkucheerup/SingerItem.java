package org.kgh.skkucheerup;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by KGH123 on 2017-06-12.
 */
@IgnoreExtraProperties
public class SingerItem {
    String date;
    String companyName;
    String campusContent;
    String content;
    String title;
    int campus;
    int count;
    public SingerItem(String date,String name2, String camCon, String cont, String title){
        this.date=date;
        this.companyName=name2;
        campusContent=camCon;
        content=cont;
        this.title=title;
    }
    public SingerItem(){

    }

    public Map<String, Object> toMap(){
        HashMap<String,Object> result =new HashMap<>();
        result.put("date",date);
        result.put("companyName",companyName);
        result.put("campusContent",campusContent);
        result.put("content",content);
        result.put("title",title);
        return result;
    }

    public void setDate(String date){
        this.date=date;
    }
    public String getDate(){
        return date;
    }
    public String getName2(){
        return companyName;
    }
    public void setName2(String name2){
        this.companyName=name2;
    }
}
