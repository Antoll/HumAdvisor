package uqac.dim.humadvisor;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements AdapterFromFirebase.OnNoteListener {


    Button buttonLike;
    RecyclerView recyclerView;
    DatabaseReference database;
    AdapterFromFirebase adapterFromFirebase;
    ArrayList<User> list;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,
                container, false);

        recyclerView = view.findViewById(R.id.recyclerviewHome);
        database = FirebaseDatabase.getInstance().getReference("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false)); //iciciicicic

        list = new ArrayList<>();
        adapterFromFirebase = new AdapterFromFirebase(getActivity(), list, this);  //icicicicicc
        recyclerView.setAdapter(adapterFromFirebase);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int range = 0;

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    if (range < 8) {
                        list.add(user);
                    }else {
                        break;
                    }
                    range++;
                }
                adapterFromFirebase.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int itemPosition = recyclerView.getChildLayoutPosition(view);
//                User userClickedOn = list.get(itemPosition);
//                String userClickedOnFirebaseUID= userClickedOn.getFirebaseUID();
//                Toast.makeText(getActivity(), "clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
        /*
        buttonLike = (Button) view.findViewById(R.id.itemHomeLike);
        buttonLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(buttonLike.getText()=="❤")
                    buttonLike.setText("♡");
                else
                    buttonLike.setText("❤");
            }
        });
        */
        return view;
    }

    @Override
    public void onNoteClick(int position) {
        User userClickedOn = list.get(position);
        String userClickedOnFirebaseUID= userClickedOn.getFirebaseUID();
        Intent intent = new Intent(getActivity(), OtherProfileActivity.class);
        intent.putExtra("ClickedUID", userClickedOnFirebaseUID);
        startActivity(intent);
        /// PASSER A L'AUTRE ACTIVITE
    }
}