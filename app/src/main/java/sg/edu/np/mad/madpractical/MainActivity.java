package sg.edu.np.mad.madpractical;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends AppCompatActivity {
    List<User> userList = new ArrayList<User>();
    String TAG = "Main activity";
    MyDBHandler myDBHandler = new MyDBHandler(this,null,null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG,"OnCreate");
        User user = new User(21,false);
        userList.add(user);


    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TAG, "on Start!!");
        Log.v(TAG, String.valueOf(userList.get(0).followed));

        TextView title = findViewById(R.id.random_num_placeholder);
        TextView description = findViewById(R.id.description);

        Intent recvmyIntent = getIntent();
        UserModelClass myObject = (UserModelClass) recvmyIntent.getSerializableExtra("myObject");

        title.setText(String.format("Name%s",myObject.Name.toString()));
        description.setText(String.format("Description%s",myObject.Description.toString()));

        Boolean UserFollowValue = myDBHandler.findUserFollowValue(myObject.Name.toString());
        Log.d("UserFollowValue",UserFollowValue.toString());

        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        Log.d("UserFollowValue",UserFollowValue.toString());
        if (UserFollowValue) {
            toggleButton.setChecked(true);
        } else {
            toggleButton.setChecked(false);
        }

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("ischecked", String.valueOf(isChecked));
                myDBHandler.updateUser(myObject.Name.toString(),isChecked);

                /*String title_name = "";
                if(myObject.Followed){
                    title_name="Followed";
                }
                else{
                    title_name = "Unfollowed";
                }
                Toast.makeText(getApplicationContext(),title_name, Toast.LENGTH_SHORT).show();*/
            }
        });

    }


    @Override
    protected void onResume(){
        super.onResume();
        Log.v(TAG,"On Resume!!");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TAG, "on Pause!!");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TAG, "on Stop!!");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v(TAG, "Destroy!!");
    }
}