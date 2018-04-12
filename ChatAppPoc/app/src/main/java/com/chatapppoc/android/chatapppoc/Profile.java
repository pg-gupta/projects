package com.chatapppoc.android.chatapppoc;

import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
/*import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;*/

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

    // variables
    Firebase reference;
    Button addSkill;
    TextView skill;
    TextView skillList;
    TextView name;
    String skills = "";
    Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // get reference of the views
        addSkill = (Button) findViewById(R.id.addSkillbtn);
        skill = (TextView) findViewById(R.id.addSkillText);
        skillList = (TextView) findViewById(R.id.skillsList);
        name = (TextView) findViewById(R.id.nametxt);

        // get reference of the firebase database
        reference = new Firebase("https://chatapppoc-b9a57.firebaseio.com/users/" + UserDetails.username + "/skills");
        // set username on profile
        name.setText(UserDetails.username);
        // add skill on click of button if skill not already present
        addSkill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (skill != null && skill.getText() != "") {
                    map = new HashMap<String, String>();
                    // add skill with its proficiency
                    map.put("name", skill.getText().toString());
                    map.put("proficiency", "1");

                    // check if the skill already added
                    Query query = reference.orderByChild("name").equalTo(skill.getText().toString());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                Toast.makeText(getApplicationContext(), "Skill exist already", Toast.LENGTH_SHORT).show();

                            } else // add skill if not added
                                reference.push().setValue(map);
                        }

                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });

                } else
                    Toast.makeText(getApplicationContext(), "Please enter a skill", Toast.LENGTH_SHORT).show();
            }
        });


        // get all the skills from the database and display on the profile
        reference.orderByChild("name").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                skills += map.get("name").toString() + ",";
                skillList.setText(skills);
                skill.setText("");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }

        });


        /*PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.i("GoogleAPi", "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i("GoogleAPi", "An error occurred: " + status);
            }
        });
*/
    }

}
