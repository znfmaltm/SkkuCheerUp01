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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PastActivity extends AppCompatActivity {
    boolean isPageOpenMenu3=false;
    private Animation translateLeftAnim3;
    private Animation translateRightAnim3;
    private static final String TAG= "PastActivity";
    private DatabaseReference InsaDatabase3;
    private DatabaseReference ZagwaDatabase3;
    private boolean allSelOn3=false;
    static ArrayList<SingerItem> toBook2=new ArrayList<SingerItem>();

    int b=0;
    ListView listView3;
    ListView listView23;
    static SingerAdapter3 adapter3;
    static SingerAdapter3 adapter23;
    LinearLayout page3;
    ImageButton menuBtn;
    LinearLayout container3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        page3=(LinearLayout) findViewById(R.id.page3);
        menuBtn=(ImageButton)findViewById(R.id.menuBtn);

        translateLeftAnim3 = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim3= AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener3 animListener3 = new SlidingPageAnimationListener3();
        translateRightAnim3.setAnimationListener(animListener3);
        translateLeftAnim3.setAnimationListener(animListener3);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past);

        container3 =(LinearLayout) findViewById(R.id.container3);

        page3=(LinearLayout) findViewById(R.id.page3);
        listView23=(ListView) findViewById(R.id.listView23);
        listView3=(ListView) findViewById(R.id.listView3);

        adapter3=new SingerAdapter3();
        adapter23=new SingerAdapter3();

        FirebaseDatabase database3 = FirebaseDatabase.getInstance();
        InsaDatabase3 =database3.getReference().child("past").child("insa");
        ZagwaDatabase3 = database3.getReference().child("past").child("zagwa");

        ValueEventListener vel3=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    SingerItem insa=child.getValue(SingerItem.class);
                    adapter23.addItem(new SingerItem(insa.date,insa.companyName,insa.campusContent,insa.content,insa.title));
                    adapter23.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ValueEventListener velZagwa3=new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    SingerItem zagwa=child.getValue(SingerItem.class);
                    adapter3.addItem(new SingerItem(zagwa.date,zagwa.companyName,zagwa.campusContent,zagwa.content,zagwa.title));
                    adapter3.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ZagwaDatabase3.addValueEventListener(velZagwa3);
        InsaDatabase3.addValueEventListener(vel3);

        listView23.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getApplicationContext(),
                        detail.class);
                intent.putExtra("companyName",adapter23.items.get(position).companyName);
                intent.putExtra("title",adapter23.items.get(position).title);
                intent.putExtra("whichCam",adapter23.items.get(position).campusContent);
                intent.putExtra("content",adapter23.items.get(position).content);

                startActivity(intent);
            }
        });

        listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getApplicationContext(),
                        detail.class);
                intent.putExtra("companyName",adapter3.items.get(position).companyName);
                intent.putExtra("title",adapter3.items.get(position).title);
                intent.putExtra("whichCam",adapter3.items.get(position).campusContent);
                intent.putExtra("content",adapter3.items.get(position).content);

                startActivity(intent);
            }
        });

        listView3.setAdapter(adapter3);
        listView23.setAdapter(adapter23);

    }

    public void onmain1Clicked(View v){
        Intent intent=new Intent(getApplicationContext(),Main1_1Activity.class);
        startActivity(intent);

    }

    public void onFavoriteClicked(View v){
        Intent intent=new Intent(getApplicationContext(),BookmarkActivity.class);
        startActivity(intent);
    }

    public void onSearchButton(View v){
        if(b==0) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.search_sub, container3, true);
            b++;
        }
        else{
            LinearLayout con=(LinearLayout) findViewById(R.id.container);
            con.setVisibility(View.VISIBLE);
        }
    }

    public void onPastClicked(View v){
        Intent intent=new Intent(getApplicationContext(),PastActivity.class);
        startActivity(intent);
    }

    public void onInsaButtonClicked(View v){
        LinearLayout insa=(LinearLayout) findViewById(R.id.insaL3);
        insa.setVisibility(View.VISIBLE);
        LinearLayout zagwa=(LinearLayout) findViewById(R.id.zagwaL3);
        zagwa.setVisibility(View.INVISIBLE);
    }
    public void onzagwaButtonClicked(View v){
        LinearLayout insa=(LinearLayout) findViewById(R.id.insaL3);
        insa.setVisibility(View.INVISIBLE);
        LinearLayout zagwa=(LinearLayout) findViewById(R.id.zagwaL3);
        zagwa.setVisibility(View.VISIBLE);
    }

    public void onSearchInvi(View v){
        LinearLayout search=(LinearLayout) findViewById(R.id.container3);
        search.setVisibility(View.INVISIBLE);
    }
    class SlidingPageAnimationListener3 implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
            if (isPageOpenMenu3) {
                page3.setVisibility(View.INVISIBLE);
                isPageOpenMenu3 = false;
            } else {
                isPageOpenMenu3 = true;
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

        if (allSelOn3 == false) {
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
            allSelOn3=true;
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
            allSelOn3=false;
        }
    }

    public void on123ButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(),detail.class);
        startActivity(intent);
    }
    public void menuTab(View v){
        try {
            if (isPageOpenMenu3) {
                page3.startAnimation(translateLeftAnim3);
            } else {
                page3.setVisibility(View.VISIBLE);
                page3.startAnimation(translateRightAnim3);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    class SingerAdapter3 extends BaseAdapter {
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
            view.setName(item.title);
            view.setDate(item.getDate());

            CheckBox button1=(CheckBox) view.findViewById(R.id.zle);
            button1.setTag(position);

            button1.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    try {
                        int a=(Integer)v.getTag();
                        toBook2.add(items.get(a));
                        Toast.makeText(getApplicationContext(),"즐겨찾기에 추가되었습니다.",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });

            return view;
        }
    }

    public void onSettingClicked(View v){
        Intent intent=new Intent(getApplicationContext(),Settings.class);
        startActivity(intent);
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
