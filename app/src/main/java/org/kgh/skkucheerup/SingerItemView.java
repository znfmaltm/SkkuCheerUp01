package org.kgh.skkucheerup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class SingerItemView extends LinearLayout {
    CheckBox button1;
    TextView textView2;
    TextView textView1;

    public SingerItemView(Context context){
        super(context);
        init(context);
    }
    public SingerItemView(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context);
    }
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singer_item,this,true);

        textView1=(TextView) findViewById(R.id.titleText);
        button1=(CheckBox) findViewById(R.id.zle);
        textView2=(TextView) findViewById(R.id.dateText);

    }
    public void setDate(String date){
        textView2.setText(date);
    }
    public void setName(String name){
        textView1.setText(name);
    }
}
