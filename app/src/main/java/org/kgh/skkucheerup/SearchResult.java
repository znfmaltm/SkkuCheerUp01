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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static org.kgh.skkucheerup.R.layout.activity_search_result;

public class SearchResult extends AppCompatActivity {
    boolean isPageOpenMenu5=false;
    private Animation translateLeftAnim5;
    private Animation translateRightAnim5;
    private static final String TAG= "SearchResult";
    private DatabaseReference InsaDatabase5;
    private DatabaseReference ZagwaDatabase5;
    private boolean allSelOn5=false;

    int b=0;
    ListView listView35;
    ListView listView235;
    static SingerAdapter5 adapter35;
    static SingerAdapter5 adapter235;
    LinearLayout page5;
    ImageButton menuBtn;
    LinearLayout container5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        page5=(LinearLayout) findViewById(R.id.page5);
        menuBtn=(ImageButton)findViewById(R.id.menuBtn);

        translateLeftAnim5 = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim5= AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener5 animListener5 = new SlidingPageAnimationListener5();
        translateRightAnim5.setAnimationListener(animListener5);
        translateLeftAnim5.setAnimationListener(animListener5);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        TextView searchWhat=(TextView)findViewById(R.id.searchWhat);
        Intent intent = getIntent();
        searchWhat.setText(intent.getStringExtra("search"));

        container5 =(LinearLayout) findViewById(R.id.container5);

        page5=(LinearLayout) findViewById(R.id.page5);
        listView235=(ListView) findViewById(R.id.listView235);
        listView35=(ListView) findViewById(R.id.listView35);

        adapter35=new SingerAdapter5();
        adapter235=new SingerAdapter5();

        FirebaseDatabase database5 = FirebaseDatabase.getInstance();
        InsaDatabase5 =database5.getReference().child("past").child("insa");
        ZagwaDatabase5 = database5.getReference().child("past").child("zagwa");

        ValueEventListener vel5=new ValueEventListener() {
            TextView temp=(TextView) findViewById(R.id.searchWhat);
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    SingerItem insa=child.getValue(SingerItem.class);
                    if(insa.title.contains(temp.getText()) || insa.content.contains(temp.getText()) || insa.companyName.contains(temp.getText())){
                        adapter235.addItem(new SingerItem(insa.date,insa.companyName,insa.campusContent,insa.content,insa.title));
                        adapter235.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ValueEventListener velZagwa5=new ValueEventListener() {
            TextView temp=(TextView) findViewById(R.id.searchWhat);
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    SingerItem zagwa=child.getValue(SingerItem.class);
                    if(zagwa.title.contains(temp.getText()) || zagwa.content.contains(temp.getText()) || zagwa.companyName.contains(temp.getText())){
                        adapter35.addItem(new SingerItem(zagwa.date, zagwa.companyName, zagwa.campusContent, zagwa.content, zagwa.title));
                        adapter35.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ZagwaDatabase5.addValueEventListener(velZagwa5);
        InsaDatabase5.addValueEventListener(vel5);

        listView235.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getApplicationContext(),
                        detail.class);
                intent.putExtra("companyName",adapter235.items.get(position).companyName);
                intent.putExtra("title",adapter235.items.get(position).title);
                intent.putExtra("whichCam",adapter235.items.get(position).campusContent);
                intent.putExtra("content",adapter235.items.get(position).content);

                startActivity(intent);
            }
        });

        listView35.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getApplicationContext(),
                        detail.class);
                intent.putExtra("companyName",adapter35.items.get(position).companyName);
                intent.putExtra("title",adapter35.items.get(position).title);
                intent.putExtra("whichCam",adapter35.items.get(position).campusContent);
                intent.putExtra("content",adapter35.items.get(position).content);

                startActivity(intent);
            }
        });

        listView35.setAdapter(adapter35);
        listView235.setAdapter(adapter235);

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
            inflater.inflate(R.layout.search_sub, container5, true);
            b++;
        }
        else{
            LinearLayout con=(LinearLayout) findViewById(R.id.container5);
            con.setVisibility(View.VISIBLE);
        }
    }

    public void onPastClicked(View v){
        Intent intent=new Intent(getApplicationContext(),PastActivity.class);
        startActivity(intent);
    }

    public void onGoSearchClicked(View v){
        try {
            Intent intent = new Intent(getApplicationContext(), SearchResult.class);
            EditText et = (EditText) findViewById(R.id.searchBar);
            intent.putExtra("search", et.getText().toString());
            startActivity(intent);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public void onInsaButtonClicked(View v){
        LinearLayout insa=(LinearLayout) findViewById(R.id.insaL5);
        insa.setVisibility(View.VISIBLE);
        LinearLayout zagwa=(LinearLayout) findViewById(R.id.zagwaL5);
        zagwa.setVisibility(View.INVISIBLE);
    }
    public void onzagwaButtonClicked(View v){
        LinearLayout insa=(LinearLayout) findViewById(R.id.insaL5);
        insa.setVisibility(View.INVISIBLE);
        LinearLayout zagwa=(LinearLayout) findViewById(R.id.zagwaL5);
        zagwa.setVisibility(View.VISIBLE);
    }

    public void onSearchInvi(View v){
        LinearLayout search=(LinearLayout) findViewById(R.id.container5);
        search.setVisibility(View.INVISIBLE);
    }
    class SlidingPageAnimationListener5 implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
            if (isPageOpenMenu5) {
                page5.setVisibility(View.INVISIBLE);
                isPageOpenMenu5 = false;
            } else {
                isPageOpenMenu5 = true;
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

        if (allSelOn5 == false) {
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
            allSelOn5=true;
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
            allSelOn5=false;
        }
    }

    public void on123ButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(),detail.class);
        startActivity(intent);
    }
    public void menuTab(View v){
        try {
            if (isPageOpenMenu5) {
                page5.startAnimation(translateLeftAnim5);
            } else {
                page5.setVisibility(View.VISIBLE);
                page5.startAnimation(translateRightAnim5);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    class SingerAdapter5 extends BaseAdapter {
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
