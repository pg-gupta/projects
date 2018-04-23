package com.chatapppoc.android.chatapppoc;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.chatapppoc.android.chatapppoc.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Requests extends AppCompatActivity {

    // variables
    ListView requestsList;
    Firebase reference;
    TextView requestPageTxt;
    AcceptFriendRequestCustomList customListAdapter;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        // get reference of the views
        requestsList = (ListView) findViewById(R.id.friendRequestList);
        requestPageTxt = (TextView) findViewById(R.id.requestPageTxt);
        reference = new Firebase(getString(R.string.firebase_database));
        activity = this;
        getAllRequests();


    }

    /**
     * Method to get all pending requests
     */
    private void getAllRequests() {

        final List<String> requests = new ArrayList<>();
        reference.child(getString(R.string.users)).child(UserDetails.username).child(getString(R.string.request_list)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Thread.sleep(2000);
                if (dataSnapshot.getValue() != null) {
                    Map<String, Boolean> req = (Map<String, Boolean>) dataSnapshot.getValue();
                    for (Map.Entry<String, Boolean> entry : req.entrySet()) {
                        if (!entry.getValue()) {
                            requests.add(entry.getKey());
                        }
                    }
                    customListAdapter = new AcceptFriendRequestCustomList(activity, requests, reference,
                            getString(R.string.users), getString(R.string.request_list), getString(R.string.friends_list));
                    requestsList.setAdapter(customListAdapter);
                } else
                    requestPageTxt.setText("No Pending Requests");

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
