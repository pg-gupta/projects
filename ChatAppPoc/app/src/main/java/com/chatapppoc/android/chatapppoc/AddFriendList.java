package com.chatapppoc.android.chatapppoc;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddFriendList extends ArrayAdapter {

    List<String> users;
    Activity context;
    Firebase reference;
    AlertDialog.Builder builder;
    String usersDBKey;
    String requestListDBKey;

    /**
     * Constructor
     *
     * @param context The current context.
     * @param users   The objects to represent in the ListView
     */
    public AddFriendList(Activity context, List<String> users, Firebase reference, String usersDBKey,
                         String requestListDBKey) {
        super(context, R.layout.activity_add_friend_list, users);

        this.users = users;
        this.context = context;
        this.reference = reference;
        builder = new AlertDialog.Builder(context);
        this.usersDBKey = usersDBKey;
        this.requestListDBKey = requestListDBKey;
    }


    private void addFriend(final String requestFrom, final String requestTo) {
        reference.child(usersDBKey).child(requestFrom).child(requestListDBKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // store the values in map structure
                Map<String, Boolean> requests = (Map<String, Boolean>) dataSnapshot.getValue();
                if (requests == null) {
                    Map<String, Boolean> requestName = new HashMap<String, Boolean>();
                    requestName.put(requestFrom, false);
                    reference.child(usersDBKey).child(requestTo).child(requestListDBKey).setValue(requestName);
                } else {
                    requests.put(requestTo, false);
                    reference.child(usersDBKey).child(requestTo).child(requestListDBKey).setValue(requests);
                }

                // show alert
                AlertDialog alert = builder.setMessage("Friend Request Sent to " + requestTo).create();
                alert.show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    // method populating values in the view of add_friend_list
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_add_friend_list, null, true);

        // getting reference of the views
        TextView nameTxt = (TextView) rowView.findViewById(R.id.nametxtView);
        Button addBtn = (Button) rowView.findViewById(R.id.addbtn);


        nameTxt.setText(users.get(position));
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFriend(UserDetails.username, users.get(position));
                // remove the user from the list when add request is sent and reload the list
                users.remove(users.get(position));
                notifyDataSetChanged();
            }
        });

        return rowView;
    }
}