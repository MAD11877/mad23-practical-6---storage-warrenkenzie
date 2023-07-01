package sg.edu.np.mad.madpractical.ListRecyclerViewAdaptor;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;

import sg.edu.np.mad.madpractical.ListActivity;
import sg.edu.np.mad.madpractical.MainActivity;
import sg.edu.np.mad.madpractical.R;
import sg.edu.np.mad.madpractical.UserModelClass;

public class ListRecyclerViewHolder extends RecyclerView.ViewHolder implements Serializable {

    TextView Description;
    TextView Name;
    ImageView img;

    Integer name;
    Integer description;

    TextView followed;

    UserModelClass obj;


    public void setObj(UserModelClass obj) {
        this.obj = obj;
    }

    public ListRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        Description = itemView.findViewById(R.id.description_view);
        Name = itemView.findViewById(R.id.name_view);
        img = itemView.findViewById(R.id.img);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numberStr = String.valueOf(obj.getName());
                char lastDigitChar = numberStr.charAt(numberStr.length() - 1);
                int lastDigit = Character.getNumericValue(lastDigitChar);
                if(lastDigit == 7){
                    View huge_image_view = itemView.findViewById(R.id.huge_image_view);
                    huge_image_view.setVisibility(View.VISIBLE);
                }else {
                    queryAlert(itemView.getContext());
                }
            }

        });
    }

    private void queryAlert(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Profile");
        builder.setMessage(String.format("Name%s",obj.getName().toString()));
        builder.setCancelable(false);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myIntent = new Intent(itemView.getContext(),MainActivity.class);
                myIntent.putExtra("myObject", obj);
                context.startActivity(myIntent);
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

}
