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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {
    boolean isPageOpenMenu4=false;
    private Animation translateLeftAnim4;
    private Animation translateRightAnim4;
    private boolean allSelOn4=false;
    static int id;
    static boolean find=false;

    Button check;
    EditText input;
    int b=0;
    LinearLayout page4;
    ImageButton menuBtn;
    LinearLayout container4;
    private DatabaseReference nicknameDB;
    ValueEventListener vel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        page4 = (LinearLayout) findViewById(R.id.page4);
        menuBtn = (ImageButton) findViewById(R.id.menuBtn);

        translateLeftAnim4 = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim4 = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener4 animListener4 = new SlidingPageAnimationListener4();
        translateRightAnim4.setAnimationListener(animListener4);
        translateLeftAnim4.setAnimationListener(animListener4);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        container4 = (LinearLayout) findViewById(R.id.container4);

        page4 = (LinearLayout) findViewById(R.id.page4);

        nicknameDB= FirebaseDatabase.getInstance().getReference().child("users");

        input=(EditText) findViewById(R.id.nicknameInput);

    }

    public void onCheckClicked(View v){
        nicknameDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str=input.getText().toString();
                check=(Button) findViewById(R.id.check);
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    if(child.getValue().equals(str)){
                        find=true;
                        Toast.makeText(getApplicationContext(),"이미 있는 닉네임 입니다.",Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if(find==false){
                    Toast.makeText(getApplicationContext(),"사용 가능한 닉네임 입니다.",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public class User{

        public String username;

        public User(){

        }
        public User(String username){
            this.username=username;
        }
    }

    private  void writeNewUser(String userId, String nickname){
        User user=new User(nickname);
        nicknameDB.child(userId).setValue(nickname);
    }


    public void onmain1Clicked(View v){
        Intent intent=new Intent(getApplicationContext(),Main1_1Activity.class);
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

    public void onPastClicked(View v){
        Intent intent=new Intent(getApplicationContext(),PastActivity.class);
        startActivity(intent);
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
