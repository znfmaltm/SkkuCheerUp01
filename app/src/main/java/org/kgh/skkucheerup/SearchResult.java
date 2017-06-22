package org.kgh.skkucheerup;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import static org.kgh.skkucheerup.R.layout.activity_search_result;

public class SearchResult extends AppCompatActivity {
    boolean isPageOpenMenu=false;
    public Animation translateLeftAnim;
    public Animation translateRightAnim;

    LinearLayout page;
    ImageButton menuBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_search_result);
        page=(LinearLayout) findViewById(R.id.page1);
        menuBtn=(ImageButton)findViewById(R.id.menuBtn);

        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateRightAnim.setAnimationListener(animListener);
        translateLeftAnim.setAnimationListener(animListener);
        page=(LinearLayout) findViewById(R.id.page1);
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
            Toast.makeText(SearchResult.this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    class SlidingPageAnimationListener implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation){
            if(isPageOpenMenu){
                page.setVisibility(View.INVISIBLE);
                isPageOpenMenu=false;
            }else{
                isPageOpenMenu=true;
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
