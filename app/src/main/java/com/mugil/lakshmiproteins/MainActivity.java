package com.mugil.lakshmiproteins;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mugil.lakshmiproteins.model.RateModel;
import com.mugil.lakshmiproteins.model.UserModel;


public class MainActivity extends BaseActivity {

    TextView rates;
    EditText rate_et;
    Button rate_sub;
    DatabaseReference root_ref,child_ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rates = (TextView) findViewById(R.id.rate);
        rate_et = (EditText) findViewById(R.id.uptd_et);
        rate_sub = (Button) findViewById(R.id.uptd);


        showProgress();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
        database = database.child(user.getUid());

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hideProgress();
                UserModel userModel = dataSnapshot.getValue(UserModel.class);
                ((TextView) findViewById(R.id.textView)).setText("Hello " + userModel.getFirst_name() + " " + userModel.getLast_name());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideProgress();
                Log.d("==>", "==>" + databaseError);
            }
        });

        root_ref = FirebaseDatabase.getInstance().getReference();
        child_ref = root_ref.child("rate");

        child_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.d("==>", "==>" + dataSnapshot.child("chicken").getValue());
                String chk = dataSnapshot.child("chicken").getValue().toString();
                String eg = dataSnapshot.child("egg").getValue().toString();

                RateModel rate_model = new RateModel();
                rate_model.setChicken_rate(chk);
                rate_model.setEgg_rate(eg);

                rates.setText(rate_model.getChicken_rate());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        rate_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                child_ref.child("chicken").setValue(rate_et.getText().toString().trim());

            }
        });



    }
}
