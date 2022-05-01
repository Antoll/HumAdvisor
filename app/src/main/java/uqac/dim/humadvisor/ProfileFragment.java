package uqac.dim.humadvisor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    TextView textViewGlobalNote, textViewConfortable, textViewSympa,
            textViewBeau, textViewIntelligent, textViewSociable,
            textViewPseudo;

    private ImageView profilePic;
    private Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        profilePic = getActivity().findViewById(R.id.noprofilepicture);
        profilePic.setOnClickListener(this);

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
    private void choosePicture()
    {
        Log.i("DIM","AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == getActivity().RESULT_OK && data != null && data.getData()!=null)
        {
            imageUri = data.getData();
            profilePic.setImageURI(imageUri );
            uploadPicture();
        }
    }

    private void uploadPicture()
    {
        final ProgressDialog pd = new ProgressDialog(getActivity());
        pd.setTitle("Uploading image...");
        pd.show();

        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        pd.dismiss();
                        Snackbar.make(getActivity().findViewById(android.R.id.content), "Image Uploaded.", Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getActivity().getApplicationContext(), "Failed to Upload", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progressPercent = (100.00 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        pd.setMessage("Progress: " + (int)progressPercent + "%");
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.noprofilepicture:
                storage = FirebaseStorage.getInstance();
                storageReference = storage.getReference();
                choosePicture();
                break;
        }
    }
}