package org.kgh.skkucheerup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static org.kgh.skkucheerup.R.layout.activity_past;
import static org.kgh.skkucheerup.R.layout.activity_settings;
import static org.kgh.skkucheerup.R.layout.singer_item;

public class Main1_1Activity extends AppCompatActivity {
    boolean isPageOpenMenu=false;
    private Animation translateLeftAnim;
    private Animation translateRightAnim;
    private static final String TAG= "Main1_1Activity";
    private DatabaseReference InsaDatabase;
    private DatabaseReference ZagwaDatabase;
    private boolean allSelOn=false;
    static ArrayList<SingerItem> curInsaItems=new ArrayList<SingerItem>();
    static ArrayList<SingerItem> curZagwaItems=new ArrayList<SingerItem>();
    static ArrayList<Integer> curInsaArr=new ArrayList<Integer>();
    static ArrayList<Integer> curZagwaArr=new ArrayList<Integer>();

    int b=0;
    ListView listView;
    ListView listView2;
    static public SingerAdapter adapter;
    static public SingerAdapter adapter2;
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
        try {
            adapter = new SingerAdapter(curZagwaItems,1);
            adapter2 = new SingerAdapter(curInsaItems,0);
        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        InsaDatabase =database.getReference().child("insa");
        ZagwaDatabase = database.getReference().child("zagwa");

        ValueEventListener vel=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                        SingerItem insa=child.getValue(SingerItem.class);
                        adapter2.addItem(new SingerItem(insa.date,insa.companyName,insa.campusContent,insa.content,insa.title));
                    curInsaArr.add(0);
                    adapter2.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ValueEventListener velZagwa=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    SingerItem zagwa=child.getValue(SingerItem.class);
                    adapter.addItem(new SingerItem(zagwa.date,zagwa.companyName,zagwa.campusContent,zagwa.content,zagwa.title));
                    curZagwaArr.add(0);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        ZagwaDatabase.addValueEventListener(velZagwa);
        InsaDatabase.addValueEventListener(vel);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getApplicationContext(),
                        detail.class);
                intent.putExtra("companyName",adapter2.items.get(position).companyName);
                intent.putExtra("title",adapter2.items.get(position).title);
                intent.putExtra("whichCam",adapter2.items.get(position).campusContent);
                intent.putExtra("content",adapter2.items.get(position).content);

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

        listView.setAdapter(adapter);
        listView2.setAdapter(adapter2);
    }

    public void onFavoriteClicked(View v){
        Intent intent=new Intent(getApplicationContext(),BookmarkActivity.class);
        startActivity(intent);
    }

    public void onPastClicked(View v){
        Intent intent=new Intent(getApplicationContext(),PastActivity.class);
        startActivity(intent);
    }

    public void onSettingClicked(View v){
        Intent intent=new Intent(getApplicationContext(),Settings.class);
        startActivity(intent);
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

    public void onAllSelClicked(View v){
        CheckBox b1 = (CheckBox) findViewById(R.id.bank);
        CheckBox b2 = (CheckBox) findViewById(R.id.itt );
        CheckBox b3 = (CheckBox) findViewById(R.id.media);
        CheckBox b4 = (CheckBox) findViewById(R.id.mu);
        CheckBox b5 = (CheckBox) findViewById(R.id.produce);
        CheckBox b6 = (CheckBox) findViewById(R.id.service);
        CheckBox b7 = (CheckBox) findViewById(R.id.you);
        CheckBox b8 = (CheckBox) findViewById(R.id.bo);
        CheckBox b9 = (CheckBox) findViewById(R.id.cons);
        CheckBox b10= (CheckBox) findViewById(R.id.tong);
        CheckBox b11= (CheckBox) findViewById(R.id.fore);
        CheckBox b12= (CheckBox) findViewById(R.id.anso);

        if (allSelOn == false) {
            b1.setChecked(true);
            b2.setChecked(true);
            b3.setChecked(true);
            b4.setChecked(true);
            b5.setChecked(true);
            b6.setChecked(true);
            b7.setChecked(true);
            b8.setChecked(true);
            b9.setChecked(true);
            b10.setChecked(true);
            b11.setChecked(true);
            b12.setChecked(true);
            allSelOn=true;
        }else{
            b1.setChecked(false);
            b2.setChecked(false);
            b3.setChecked(false);
            b4.setChecked(false);
            b5.setChecked(false);
            b6.setChecked(false);
            b7.setChecked(false);
            b8.setChecked(false);
            b9.setChecked(false);
            b10.setChecked(false);
            b11.setChecked(false);
            b12.setChecked(false);
            allSelOn=false;
        }
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
        ArrayList<SingerItem> items;
        int campus;

        SingerAdapter(ArrayList<SingerItem> list, int cam){
            items=list;
            campus=cam;
        }
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
            SingerItemView view = new SingerItemView(getApplicationContext());
            SingerItem item = items.get(position);
            view.setName(item.title);
            view.setDate(item.getDate());
            CheckBox button1 = (CheckBox) view.findViewById(R.id.zle);


            button1.setTag(position + "");

            button1.setOnClickListener(new Button.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),(String)v.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });

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