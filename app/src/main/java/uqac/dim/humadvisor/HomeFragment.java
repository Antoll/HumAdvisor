package uqac.dim.humadvisor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment {


    Button buttonLike1;
    Button buttonLike2;
    Button buttonLike3;
    Button buttonLike4;
    Button buttonLike5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home,
                container, false);
        buttonLike1 = (Button) view.findViewById(R.id.buttonLike1);
        buttonLike2 = (Button) view.findViewById(R.id.buttonLike2);
        buttonLike3 = (Button) view.findViewById(R.id.buttonLike3);
        buttonLike4 = (Button) view.findViewById(R.id.buttonLike4);
        buttonLike5 = (Button) view.findViewById(R.id.buttonLike5);


        buttonLike1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(buttonLike1.getText()=="❤")
                    buttonLike1.setText("♡");
                else
                    buttonLike1.setText("❤");
            }
        });
        buttonLike2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonLike2.getText()=="❤")
                    buttonLike2.setText("♡");
                else
                    buttonLike2.setText("❤");
            }
        });
        buttonLike3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonLike3.getText()=="❤")
                    buttonLike3.setText("♡");
                else
                    buttonLike3.setText("❤");
            }
        });
        buttonLike4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonLike4.getText()=="❤")
                    buttonLike4.setText("♡");
                else
                    buttonLike4.setText("❤");
            }
        });
        buttonLike5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buttonLike5.getText()=="❤")
                    buttonLike5.setText("♡");
                else
                    buttonLike5.setText("❤");
            }
        });
        return view;
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_home, container, false);
    }
}