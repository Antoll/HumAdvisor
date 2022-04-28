package uqac.dim.humadvisor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFromFirebase extends RecyclerView.Adapter<AdapterFromFirebase.MyViewHolder> {

    android.content.Context context;
    ArrayList<User> list;

    public Context getContext() {
        return context;
    }

    public AdapterFromFirebase(android.content.Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_home,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);
        holder.name.setText(user.getPseudo());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,like;
        ImageView profilePicture,eventPicture;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.itemHomeName);
            profilePicture = itemView.findViewById(R.id.itemHomeProfilePicture);
            eventPicture = itemView.findViewById(R.id.itemHomeEventPicture);
        }
    }
}
