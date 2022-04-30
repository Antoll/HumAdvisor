package uqac.dim.humadvisor;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RateActivity extends AppCompatActivity implements View.OnClickListener {

    Button rateNowButton;
    RatingBar rBarConfort, rBarSympa, rBarSociable, rBarIntel, rBarBeaute;
    double noteConfort, noteSympa, noteSociable, noteIntel, noteBeaute, noteGlobal;
    String clickedUID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                clickedUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            } else {
                clickedUID = extras.getString("ClickedUID");
            }
        } else {
            clickedUID = (String) savedInstanceState.getSerializable("ClickedUID");
        }
        Toast.makeText(this, clickedUID, Toast.LENGTH_SHORT).show();

        rateNowButton = findViewById(R.id.ratenow_button);
        rateNowButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ratenow_button:
                //Take ratings
                rBarConfort = findViewById(R.id.ratingBar_confort);
                rBarSympa = findViewById(R.id.ratingBar_sympa);
                rBarSociable = findViewById(R.id.ratingBar_sociable);
                rBarIntel = findViewById(R.id.ratingBar_intel);
                rBarBeaute = findViewById(R.id.ratingBar_beaute);

                noteConfort = rBarConfort.getRating();
                noteSympa = rBarSympa.getRating();
                noteSociable = rBarSociable.getRating();
                noteIntel = rBarBeaute.getRating();
                noteBeaute = rBarBeaute.getRating();
                noteGlobal = (noteConfort+noteSympa+noteSociable+noteIntel+noteBeaute)/5;

                DatabaseReference ref;
                ref = FirebaseDatabase.getInstance().getReference("Users");
                ref.child(clickedUID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.getResult().exists()) {
                            DataSnapshot dataSnapshot = task.getResult();
                            long oldNbrOfVote = (long) dataSnapshot.child("nbrOfVote").getValue();
                            Toast.makeText(RateActivity.this, String.valueOf(noteConfort), Toast.LENGTH_SHORT).show();

//                            double oldConfort = (double) dataSnapshot.child("confortable").getValue();
//                            double newConfort = (oldConfort * oldNbrOfVote + noteConfort)/(oldNbrOfVote + 1);
//                            dataSnapshot.child("confortable").getRef().setValue(newConfort);
//
//                            double oldSympa = (double) dataSnapshot.child("sympa").getValue();
//                            Toast.makeText(RateActivity.this, String.valueOf(oldSympa), Toast.LENGTH_SHORT).show();
//                            double newSympa = (oldSympa * oldNbrOfVote + noteSympa)/(oldNbrOfVote + 1);
//                            dataSnapshot.child("sympa").getRef().setValue(newSympa);
//
//                            double oldSociable = (double) dataSnapshot.child("sociable").getValue();
//                            double newSociable = (oldSociable * oldNbrOfVote + noteSociable)/(oldNbrOfVote + 1);
//                            dataSnapshot.child("sociable").getRef().setValue(newSociable);
//
//                            double oldIntel = (double) dataSnapshot.child("intelligence").getValue();
//                            double newIntel = (oldIntel * oldNbrOfVote + noteIntel)/(oldNbrOfVote + 1);
//                            dataSnapshot.child("intelligence").getRef().setValue(newIntel);
//
//                            double oldBeaute = (double) dataSnapshot.child("beau").getValue();
//                            double newBeaute = (oldBeaute * oldNbrOfVote + noteBeaute)/(oldNbrOfVote + 1);
//                            dataSnapshot.child("sociable").getRef().setValue(newBeaute);
//
//                            double oldGlobal = (double) dataSnapshot.child("globalNote").getValue();
//                            double newGlobal = (newConfort + newSympa + newSociable
//                                    + newIntel + newBeaute)/5;
//                            dataSnapshot.child("globalNote").getRef().setValue(newGlobal);


                            dataSnapshot.child("nbrOfVote").getRef().setValue(oldNbrOfVote + 1);



                            //dataSnapshot.child("nbrOfVote").getRef().setValue(42);

                        }
                    }
                });

        }
    }
}
