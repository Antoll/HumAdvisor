package uqac.dim.humadvisor;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOUserInfo {

    private DatabaseReference databaseReference;
    public DAOUserInfo(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(UserInfo.class.getName());
    }

    public Task<Void> add(UserInfo user){
        return  databaseReference.push().setValue(user);
    }
}
