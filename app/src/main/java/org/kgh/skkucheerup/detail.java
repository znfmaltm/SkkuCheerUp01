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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static org.kgh.skkucheerup.R.layout.activity_detail;
import static org.kgh.skkucheerup.R.layout.activity_search_result;

public class detail extends AppCompatActivity {
    boolean isPageOpenMenu2=false;
    private Animation translateLeftAnim2;
    private Animation translateRightAnim2;

    int b=0;
    LinearLayout container2;
    private boolean allSelOn=false;
    String str;
    LinearLayout page2;
    ImageButton menuBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            page2 = (LinearLayout) findViewById(R.id.page2);
            menuBtn = (ImageButton) findViewById(R.id.menuBtn);

            translateLeftAnim2 = AnimationUtils.loadAnimation(this, R.anim.translate_left);
            translateRightAnim2 = AnimationUtils.loadAnimation(this, R.anim.translate_right);

            SlidingPageAnimationListener2 animListener = new SlidingPageAnimationListener2();
            translateRightAnim2.setAnimationListener(animListener);
            translateLeftAnim2.setAnimationListener(animListener);

            super.onCreate(savedInstanceState);
            setContentView(activity_detail);

            container2 = (LinearLayout) findViewById(R.id.container2);

            page2 = (LinearLayout) findViewById(R.id.page2);

            TextView comName = (TextView) findViewById(R.id.companyName);
            TextView title = (TextView) findViewById(R.id.title);
            TextView whichCam = (TextView) findViewById(R.id.whichCampus);
            TextView content = (TextView) findViewById(R.id.content);

            Intent intent = getIntent();
            comName.setText(intent.getStringExtra("companyName"));
            title.setText(intent.getStringExtra("title"));
            whichCam.setText(intent.getStringExtra("whichCam"));
            content.setText(intent.getStringExtra("content"));

        }

    public void onPastClicked(View v){
        Intent intent=new Intent(getApplicationContext(),PastActivity.class);
        startActivity(intent);
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

    public void onSearchButton(View v){
        if(b==0) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.search_sub, container2, true);
            b++;
        }
        else{
            LinearLayout con=(LinearLayout) findViewById(R.id.container2);
            con.setVisibility(View.VISIBLE);
        }
    }

    public void onFavoriteClicked(View v){
        Intent intent=new Intent(getApplicationContext(),BookmarkActivity.class);
        startActivity(intent);
    }

    public void onSearchInvi(View v){
        LinearLayout search=(LinearLayout) findViewById(R.id.container2);
        search.setVisibility(View.INVISIBLE);
    }

    public void onSettingClicked(View v){
        Intent intent=new Intent(getApplicationContext(),Settings.class);
        startActivity(intent);
    }

    public void onmain1Clicked(View v){
        Intent intent=new Intent(detail.this,Main1_1Activity.class);
        startActivity(intent);

    }
    public void menuTab(View v){
        try {
            if (isPageOpenMenu2) {
                page2.startAnimation(translateLeftAnim2);
            } else {
                page2.setVisibility(View.VISIBLE);
                page2.startAnimation(translateRightAnim2);
            }
        } catch (Exception e) {
            Toast.makeText(detail.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    class SlidingPageAnimationListener2 implements Animation.AnimationListener {
        public void onAnimationEnd(Animation animation) {
            if (isPageOpenMenu2) {
                page2.setVisibility(View.INVISIBLE);
                isPageOpenMenu2 = false;
            } else {
                isPageOpenMenu2 = true;
            }
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
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
