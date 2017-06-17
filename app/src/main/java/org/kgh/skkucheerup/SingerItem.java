package org.kgh.skkucheerup;

/**
 * Created by KGH123 on 2017-06-12.
 */

public class SingerItem {
    String date;
    String companyName;
    String campusContent;
    String content;
    String title;
    int campus;
    int count;
    public SingerItem(String date,String name2, String c, String d, String e, int f, int g){
        this.date=date;
        this.companyName=name2;
        campusContent=c;
        content=d;
        title=e;
        campus=f;
        count=g;
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
