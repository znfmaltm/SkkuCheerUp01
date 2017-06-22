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

import java.util.ArrayList;

public class BookmarkActivity extends AppCompatActivity {
    boolean isPageOpenMenu4=false;
    private Animation translateLeftAnim4;
    private Animation translateRightAnim4;
    private static final String TAG= "BookmarkActivity";
    private boolean allSelOn4=false;

    int b=0;
    ListView listView34;
    ListView listView234;
    static SingerAdapter4 adapter234;
    LinearLayout page4;
    ImageButton menuBtn;
    LinearLayout container4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        page4=(LinearLayout) findViewById(R.id.page4);
        menuBtn=(ImageButton)findViewById(R.id.menuBtn);

        translateLeftAnim4 = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim4= AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener4 animListener4 = new SlidingPageAnimationListener4();
        translateRightAnim4.setAnimationListener(animListener4);
        translateLeftAnim4.setAnimationListener(animListener4);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        container4 =(LinearLayout) findViewById(R.id.container4);

        page4=(LinearLayout) findViewById(R.id.page4);
        listView234=(ListView) findViewById(R.id.listView234);

        adapter234=new SingerAdapter4();

        for(int i=0;i<PastActivity.toBook2.size();i+=0){
            boolean find=false;
            SingerItem temp=PastActivity.toBook2.get(i);
            try {
                for (int j = 0; j < adapter234.items.size(); j++) {
                    if (temp.companyName.equals(adapter234.items.get(j).companyName)) {
                        find = true;
                        break;
                    }
                }
                if (find == false) {
                    adapter234.addItem(temp);
                    PastActivity.toBook2.remove(i);
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
        }

        for(int i=0;i<Main1_1Activity.toBook.size();i+=0){
            boolean find=false;
            SingerItem temp=Main1_1Activity.toBook.get(i);
            try {
                for (int j = 0; j < adapter234.items.size(); j++) {
                    if (temp.companyName.equals(adapter234.items.get(j).companyName)) {
                        find = true;
                        break;
                    }
                }
                if (find == false) {
                    adapter234.addItem(temp);
                    Main1_1Activity.toBook.remove(i);
                }
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
            }
        }

        listView234.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getApplicationContext(),
                        detail.class);
                intent.putExtra("companyName",adapter234.items.get(position).companyName);
                intent.putExtra("title",adapter234.items.get(position).title);
                intent.putExtra("whichCam",adapter234.items.get(position).campusContent);
                intent.putExtra("content",adapter234.items.get(position).content);

                startActivity(intent);
            }
        });

        listView234.setAdapter(adapter234);
    }

    public void onmain1Clicked(View v){
        Intent intent=new Intent(getApplicationContext(),Main1_1Activity.class);
        startActivity(intent);

    }


    public void onPastClicked(View v){
        Intent intent=new Intent(getApplicationContext(),PastActivity.class);
        startActivity(intent);
    }

    public void onSearchButton(View v){
        if(b==0) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.search_sub, container4, true);
            b++;
        }
        else{
            LinearLayout con=(LinearLayout) findViewById(R.id.container4);
            con.setVisibility(View.VISIBLE);
        }
    }
    public void onSearchInvi(View v){
        LinearLayout search=(LinearLayout) findViewById(R.id.container4);
        search.setVisibility(View.INVISIBLE);
    }
    class SlidingPageAnimationListener4 implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
            if (isPageOpenMenu4) {
                page4.setVisibility(View.INVISIBLE);
                isPageOpenMenu4 = false;
            } else {
                isPageOpenMenu4 = true;
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

        if (allSelOn4 == false) {
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
            allSelOn4=true;
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
            allSelOn4=false;
        }
    }

    public void on123ButtonClicked(View v){
        Intent intent = new Intent(getApplicationContext(),detail.class);
        startActivity(intent);
    }
    public void menuTab(View v){
        try {
            if (isPageOpenMenu4) {
                page4.startAnimation(translateLeftAnim4);
            } else {
                page4.setVisibility(View.VISIBLE);
                page4.startAnimation(translateRightAnim4);
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    class SingerAdapter4 extends BaseAdapter {
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
                        Toast.makeText(getApplicationContext(),"즐겨찾기에서 제거되었습니다.",Toast.LENGTH_SHORT).show();
                        adapter234.items.remove(a);
                        adapter234.notifyDataSetChanged();
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
        assert actionBar != null;
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
