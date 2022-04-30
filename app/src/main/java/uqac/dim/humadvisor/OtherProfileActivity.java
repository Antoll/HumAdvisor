package uqac.dim.humadvisor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OtherProfileActivity extends Activity implements View.OnClickListener {

    TextView textViewGlobalNote, textViewConfortable, textViewSympa,
            textViewBeau, textViewIntelligent, textViewSociable,
            textViewPseudo;

    Button rateUserButton;
    String clickedUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
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


        textViewPseudo = findViewById(R.id.username_textedit);
        textViewGlobalNote = findViewById(R.id.global_textedit);
        textViewConfortable = findViewById(R.id.confort_textedit);
        textViewSympa = findViewById(R.id.sympa_textedit);
        textViewBeau = findViewById(R.id.beaute_textedit);
        textViewIntelligent = findViewById(R.id.intel_textedit);
        textViewSociable = findViewById(R.id.sociable_textedit);


        //UserClicked

//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            clickedUID = bundle.getString("ClickedUID", currentID);
//        }

        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(clickedUID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.getResult().exists()){
                    DataSnapshot dataSnapshot = task.getResult();
                    String pseudoResult = dataSnapshot.child("pseudo").getValue().toString();
                    textViewPseudo.setText(pseudoResult);

                    int nbrOfVoteResult = Integer.parseInt(dataSnapshot.child("nbrOfVote").getValue().toString());
                    if (nbrOfVoteResult == 0){
                        textViewGlobalNote.setText("No global score yet");
                        textViewConfortable.setText("No comfy score yet");
                        textViewSympa.setText("No friendly score yet");
                        textViewBeau.setText("No beauty score yet");
                        textViewIntelligent.setText("No intelligence score yet");
                        textViewSociable.setText("No sociability score yet");
                    }else{
                        String globalNoteResult = dataSnapshot.child("globalNote").getValue().toString();
                        textViewGlobalNote.setText("Average: " + globalNoteResult + "/5");

                        String confortableResult = dataSnapshot.child("confortable").getValue().toString();
                        textViewConfortable.setText("Comfy: " + confortableResult + "/5");

                        String sympaResult = dataSnapshot.child("sympa").getValue().toString();
                        textViewSympa.setText("Friendly: " + sympaResult + "/5");

                        String beauResult = dataSnapshot.child("beau").getValue().toString();
                        textViewBeau.setText("Beauty: " + beauResult + "/5");

                        String intelResult = dataSnapshot.child("intelligence").getValue().toString();
                        textViewIntelligent.setText("Intelligence: " + intelResult + "/5");

                        String sociableResult = dataSnapshot.child("sociable").getValue().toString();
                        textViewSociable.setText("Sociability: " + sociableResult + "/5");
                    }
                }
            }
        });

        rateUserButton = (Button) findViewById(R.id.rate_user_button);
        rateUserButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rate_user_button:
                Intent intent = new Intent(this, RateActivity.class);
                intent.putExtra("ClickedUID", clickedUID);
                startActivity(intent);
                finish();
        }
    }
}
