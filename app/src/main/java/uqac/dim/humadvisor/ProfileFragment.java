package uqac.dim.humadvisor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ProfileFragment extends Fragment {

    TextView textViewGlobalNote, textViewConfortable, textViewSympa,
            textViewBeau, textViewIntelligent, textViewSociable,
            textViewPseudo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        textViewPseudo = getActivity().findViewById(R.id.username_textedit);
        textViewGlobalNote = getActivity().findViewById(R.id.global_textedit);
        textViewConfortable = getActivity().findViewById(R.id.confort_textedit);
        textViewSympa = getActivity().findViewById(R.id.sympa_textedit);
        textViewBeau = getActivity().findViewById(R.id.beaute_textedit);
        textViewIntelligent = getActivity().findViewById(R.id.intel_textedit);
        textViewSociable = getActivity().findViewById(R.id.sociable_textedit);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentID = user.getUid();
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(currentID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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


    }
}