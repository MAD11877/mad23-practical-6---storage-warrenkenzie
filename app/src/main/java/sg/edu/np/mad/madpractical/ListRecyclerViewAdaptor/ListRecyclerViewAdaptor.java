package sg.edu.np.mad.madpractical.ListRecyclerViewAdaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sg.edu.np.mad.madpractical.R;
import sg.edu.np.mad.madpractical.UserModelClass;

public class ListRecyclerViewAdaptor extends RecyclerView.Adapter<ListRecyclerViewHolder> {
    private ArrayList<UserModelClass> list_users;


    public ListRecyclerViewAdaptor(ArrayList<UserModelClass> list_users) {
        this.list_users = list_users;
    }

    public ListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ListRecyclerViewHolder holder = new ListRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListRecyclerViewHolder holder, int position) {
        UserModelClass list_item = list_users.get(position);
        holder.Name.setText(String.format("Name%s",String.valueOf(list_item.getName())));
        holder.setObj(list_item);
        holder.Description.setText(String.format("Description%s",String.valueOf(list_item.getDescription())));
    }

    @Override
    public int getItemCount() {
        return list_users.size();
    }


}
