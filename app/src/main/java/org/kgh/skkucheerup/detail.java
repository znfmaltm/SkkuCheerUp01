package org.kgh.skkucheerup;

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
        page2 = (LinearLayout) findViewById(R.id.page2);

        TextView comName=(TextView)findViewById(R.id.companyName);
        TextView title=(TextView)findViewById(R.id.title);
        TextView whichCam=(TextView)findViewById(R.id.whichCampus);
        TextView content=(TextView)findViewById(R.id.content);

        Intent intent = getIntent();
        comName.setText(intent.getStringExtra("companyName"));
        title.setText(intent.getStringExtra("title"));
        whichCam.setText(intent.getStringExtra("whichCam"));
        content.setText(intent.getStringExtra("content"));
    }

    public void onmain1Clicked(View v){
        Toast.makeText(getApplicationContext().getApplicationContext(),str,Toast.LENGTH_LONG).show();
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
