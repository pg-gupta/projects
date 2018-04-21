package com.chatapppoc.android.chatapppoc;

import android.app.Activity;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchFriend extends AppCompatActivity {

    // variables declared
    ListView friendList;
    Activity activity;
    String name;
    Firebase reference;
    List<String> users;
    List<String> friends;
    List<String> requestList;
    TextView searchSkillText;
    ImageButton searchBtn;
    String searchTxt;
    public static final GeoLocation CURRENT_LOCATION = UserDetails.getUserLocation();//new GeoLocation(37.7789, -122.4056973);
    private DatabaseReference database;
    private GeoFire geofire;
    Boolean usersFetchedByDistance = false;
    private List<String> peopleNearby = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend);

        // variables defined
        activity = this;
        friendList = (ListView) findViewById(R.id.friendList);
        searchSkillText = (TextView) findViewById(R.id.searchTxt);
        searchBtn = (ImageButton) findViewById(R.id.searchBtn);
        reference = new Firebase(getString(R.string.firebase_database));

        setupFirebase();

        fetchUsers();

        // Search button click event
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchTxt = searchSkillText.getText().toString();
                if (!searchTxt.isEmpty()) {
                    searchPeopleWithSkill();
                }
            }
        });


    }

    private void setupFirebase() {
        database = FirebaseDatabase.getInstance().getReference();
        geofire = new GeoFire(database.child(getString(R.string.geolocation)));
    }

    private void fetchUsers() {
        GeoQuery geoQuery = geofire.queryAtLocation(CURRENT_LOCATION, 0.4);

        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                Location to = new Location("to");
                to.setLatitude(location.latitude);
                to.setLongitude(location.longitude);
                if (!usersFetchedByDistance) {
                    peopleNearby.add(key);
                }
            }

            @Override
            public void onKeyExited(String key) {
                Log.d("Map", "onKeyExited: ");

            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                Log.d("Map", "onKeyMoved: ");
            }

            @Override
            public void onGeoQueryReady() {
                Log.d("Map", "onGeoQueryReady: ");
                usersFetchedByDistance = true;
            }


            @Override
            public void onGeoQueryError(DatabaseError error) {
                Log.e("Map", "onGeoQueryError: ", error.toException());
            }
        });

    }

    private void searchPeopleWithSkill() {
        getFriendsInRequestList();
    }

    private void getFriendsInRequestList() {
        requestList = new ArrayList<>();
        reference.child(getString(R.string.users)).child(UserDetails.username).child(getString(R.string.request_list)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    Map<String, Boolean> req = (Map<String, Boolean>) dataSnapshot.getValue();
                    for (Map.Entry<String, Boolean> entry : req.entrySet()) {
                        if (!entry.getValue()) {
                            requestList.add(entry.getKey());
                        }
                    }
                }
                getFriends();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void getFriends() {
        friends = new ArrayList<>();

        reference.child(getString(R.string.users)).child(UserDetails.username).child(getString(R.string.friends_list)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Boolean> map = (Map<String, Boolean>) dataSnapshot.getValue();
                if (dataSnapshot.getValue() != null) {
                    for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                        friends.add(entry.getKey());
                    }
                }
                getPeopleWithSkill();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void getPeopleWithSkill() {
        users = new ArrayList<>();
        reference.child(getString(R.string.users)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    try {
                        if (!ds.getKey().equals(UserDetails.username) && !friends.contains(ds.getKey()) && !requestList.contains(ds.getKey())
                                && peopleNearby.contains(ds.getKey())) {
                            name = ds.getKey();
                            users.add(name);
                        }
                    } catch (Exception e) {
                        Log.i("Error", e.getMessage());
                    }

                }

                try {
                    filterUserHavingSkill(searchTxt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void filterUserHavingSkill(final String skill) throws InterruptedException {
        final List<String> filteredUser = new ArrayList<>();
        final AddFriendList[] addFriendCustomAdapter = new AddFriendList[1];
        for (final String user : users) {
            reference.child(getString(R.string.users)).child(user).child(getString(R.string.skills)).orderByChild(getString(R.string.skill_name)).equalTo(skill).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists())
                        filteredUser.add(user);
                    addFriendCustomAdapter[0] = new AddFriendList(activity, filteredUser, reference, getString(R.string.users), getString(R.string.request_list));
                    friendList.setAdapter(addFriendCustomAdapter[0]);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });

        }


    }
}
