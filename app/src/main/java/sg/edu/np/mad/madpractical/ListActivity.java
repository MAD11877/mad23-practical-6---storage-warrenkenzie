package sg.edu.np.mad.madpractical;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sg.edu.np.mad.madpractical.ListRecyclerViewAdaptor.ListRecyclerViewAdaptor;
import sg.edu.np.mad.madpractical.ListRecyclerViewAdaptor.ListRecyclerViewHolder;

public class ListActivity extends AppCompatActivity {

    String TAG = "List activity";

    ArrayList<UserModelClass> List_users;

    MyDBHandler myDBHandler = new MyDBHandler(this,null,null,1);
    private int randomnum(){
        Random ran = new Random();
        int myRandomValue = ran.nextInt(999999);
        return myRandomValue;
    }

    private void queryAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Profile");
        builder.setMessage("MADness");
        builder.setCancelable(false);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG,"dASDAD");
            }
        });
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG,"view option");
                /*Intent myIntent = new Intent(ListActivity.this,MainActivity.class);
                String random_num = Integer.toString(randomnum());
                myIntent.putExtra("random_num",random_num);
                startActivity(myIntent);*/
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.v(TAG,"Create");
        /*ImageView avatar_img = findViewById(R.id.avatar_img);

       */
        // inserts 20 users data in the db
        List_users = new ArrayList<>();
        for(int i =0;i<=19;i++){
            UserModelClass user =new UserModelClass("create");
            myDBHandler.addUser(user);
        }

        // get the user data from the db and populate the recyclerview
        List_users = myDBHandler.getUsers();

        RecyclerView recyclerView = findViewById(R.id.ListRecyclerView);
        ListRecyclerViewAdaptor listRecyclerViewAdaptor = new ListRecyclerViewAdaptor(List_users);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setAdapter(listRecyclerViewAdaptor);
    }
}