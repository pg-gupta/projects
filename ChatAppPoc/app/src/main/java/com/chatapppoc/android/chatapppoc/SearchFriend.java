package com.chatapppoc.android.chatapppoc;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.chatapppoc.android.chatapppoc.AddFriendList;
import com.chatapppoc.android.chatapppoc.R;
import com.chatapppoc.android.chatapppoc.UserDetails;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.auth.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class SearchFriend extends AppCompatActivity {

    ListView friendList;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);
        activity = this;
        friendList = (ListView) findViewById(R.id.friendList);
        final List<String> users = new ArrayList<>();


        final Firebase reference = new Firebase(getString(R.string.firebase_database));
        reference.child(getString(R.string.users)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    try {
                        if (!ds.getKey().equals(UserDetails.username))
                            users.add(ds.getKey().toString());
                    } catch (Exception e) {
                        Log.i("Error", e.getMessage());
                    }
                }
                try {
                    AddFriendList addFriendCustomAdapter = new AddFriendList(activity, users, reference, getString(R.string.users), getString(R.string.request_list));
                    friendList.setAdapter(addFriendCustomAdapter);
                } catch (Exception e) {
                    Log.i("Erroradapter", e.getMessage());
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
