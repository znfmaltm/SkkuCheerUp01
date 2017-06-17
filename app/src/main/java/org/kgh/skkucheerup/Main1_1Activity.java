package org.kgh.skkucheerup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static org.kgh.skkucheerup.R.layout.singer_item;

public class Main1_1Activity extends AppCompatActivity {
    boolean isPageOpenMenu=false;
    private Animation translateLeftAnim;
    private Animation translateRightAnim;

    int b=0;
    ListView listView;
    ListView listView2;
    SingerAdapter adapter;
    SingerAdapter adapter2;
    LinearLayout page;
    ImageButton menuBtn;
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        page=(LinearLayout) findViewById(R.id.page1);
        menuBtn=(ImageButton)findViewById(R.id.menuBtn);

        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener(this);
        translateRightAnim.setAnimationListener(animListener);
        translateLeftAnim.setAnimationListener(animListener);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1_1);

        container =(LinearLayout) findViewById(R.id.container);

        page=(LinearLayout) findViewById(R.id.page1);
        listView2=(ListView) findViewById(R.id.listView2);
        listView=(ListView) findViewById(R.id.listView);

        adapter=new SingerAdapter();
        adapter2=new SingerAdapter();
        adapter.addItem(new SingerItem("2017-06-24","엘쥐 전자","고형주","추추추","고추",1,2));
        adapter.addItem(new SingerItem("2016-06-19","LG생활건강","캠컨","정보","제목목",1,3));

        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getApplicationContext(),detail.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getApplicationContext(),
                        detail.class);
                intent.putExtra("companyName",adapter.items.get(position).companyName);
                intent.putExtra("title",adapter.items.get(position).title);
                intent.putExtra("whichCam",adapter.items.get(position).campusContent);
                intent.putExtra("content",adapter.items.get(position).content);

                startActivity(intent);
            }
        });
    }

    public void onSearchButton(View v){
        if(b==0) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.search_sub, container, true);
            b++;
        }
        else{
            LinearLayout con=(LinearLayout) findViewById(R.id.container);
            con.setVisibility(View.VISIBLE);
        }
    }
    public void onInsaButtonClicked(View v){
        LinearLayout insa=(LinearLayout) findViewById(R.id.insaL);
        insa.setVisibility(View.VISIBLE);
        LinearLayout zagwa=(LinearLayout) findViewById(R.id.zagwaL);
        zagwa.setVisibility(View.INVISIBLE);
    }
    public void onzagwaButtonClicked(View v){
        LinearLayout insa=(LinearLayout) findViewById(R.id.insaL);
        insa.setVisibility(View.INVISIBLE);
        LinearLayout zagwa=(LinearLayout) findViewById(R.id.zagwaL);
        zagwa.setVisibility(View.VISIBLE);
    }

    public void onSearchInvi(View v){
        LinearLayout search=(LinearLayout) findViewById(R.id.container);
        search.setVisibility(View.INVISIBLE);
    }
    class SlidingPageAnimationListener implements Animation.AnimationListener {

        private Main1_1Activity main1_1Activity;

        public SlidingPageAnimationListener(Main1_1Activity main1_1Activity) {
            this.main1_1Activity = main1_1Activity;
        }

        public void onAnimationEnd(Animation animation) {
            if (main1_1Activity.isPageOpenMenu) {
                main1_1Activity.page.setVisibility(View.INVISIBLE);
                main1_1Activity.isPageOpenMenu = false;
            } else {
                main1_1Activity.isPageOpenMenu = true;
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }
    public void on123ButtonClicked(View v){
        Intent intent = new Intent(Main1_1Activity.this,detail.class);
        startActivity(intent);
    }
    public void menuTab(View v){
        try {
            if (isPageOpenMenu) {
                page.startAnimation(translateLeftAnim);
            } else {
                page.setVisibility(View.VISIBLE);
                page.startAnimation(translateRightAnim);
            }
            } catch (Exception e) {
            Toast.makeText(Main1_1Activity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    class SingerAdapter extends BaseAdapter{
        ArrayList<SingerItem> items=new ArrayList<SingerItem>();
        @Override
        public int getCount(){
            return items.size();
        }
        public void addItem(SingerItem item){
            items.add(item);
        }
        @Override
        public Object getItem(int position){
            return items.get(position);
        }
        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup){
            SingerItemView view=new SingerItemView(getApplicationContext());
            SingerItem item=items.get(position);
            view.setName(item.getName2());
            view.setDate(item.getDate());
            return view;
        }
    }
    @Override
        public boolean onCreateOptionsMenu(Menu menu){
            ActionBar actionBar=getSupportActionBar();
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);

            LayoutInflater inflater =(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View actionbar = inflater.inflate(R.layout.custom_action,null);

            actionBar.setCustomView(actionbar);
            Toolbar parent = (Toolbar)actionbar.getParent();
            parent.setContentInsetsAbsolute(0,0);

            return true;
        }
    }