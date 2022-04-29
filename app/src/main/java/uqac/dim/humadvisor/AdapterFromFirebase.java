package uqac.dim.humadvisor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterFromFirebase extends RecyclerView.Adapter<AdapterFromFirebase.MyViewHolder> {

    android.content.Context context;
    ArrayList<User> list;
    private OnNoteListener mOnNoteListener;

    public Context getContext() {
        return context;
    }

    public AdapterFromFirebase(android.content.Context context, ArrayList<User> list, OnNoteListener onNoteListener) {
        this.context = context;
        this.list = list;
        this.mOnNoteListener = onNoteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_home,parent,false);
        return new MyViewHolder(v, mOnNoteListener);
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

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name,like;
        ImageView profilePicture,eventPicture;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            name = itemView.findViewById(R.id.itemHomeName);
            profilePicture = itemView.findViewById(R.id.itemHomeProfilePicture);
            eventPicture = itemView.findViewById(R.id.itemHomeEventPicture);
            this.onNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);

    }
}
