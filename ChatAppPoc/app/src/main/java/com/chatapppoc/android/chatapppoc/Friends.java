package com.chatapppoc.android.chatapppoc;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Friends extends AppCompatActivity {
    ListView usersList;
    TextView noUsersText;
    ArrayList<String> al = new ArrayList<>();
    int totalUsers = 0;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        usersList = (ListView) findViewById(R.id.usersList);
        noUsersText = (TextView) findViewById(R.id.noUsersText);
        pd = new ProgressDialog(Friends.this);
        pd.setMessage("Loading...");
        pd.show();


        Firebase reference = new Firebase(getString(R.string.firebase_database) +"/"+ getString(R.string.users) +"/"+ UserDetails.username +"/"+ getString(R.string.friends_list));

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Boolean> map = (Map<String, Boolean>) dataSnapshot.getValue();
                if(dataSnapshot.getValue()!=null) {
                    for (Map.Entry<String, Boolean> entry : map.entrySet()) {
                        al.add(entry.getKey());
                        totalUsers++;
                    }
                }

                if (totalUsers < 1) {
                    noUsersText.setVisibility(View.VISIBLE);
                    usersList.setVisibility(View.GONE);
                } else {
                    noUsersText.setVisibility(View.GONE);
                    usersList.setVisibility(View.VISIBLE);
                    usersList.setAdapter(new ArrayAdapter<String>(Friends.this, android.R.layout.simple_list_item_1, al));
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDetails.chatWith = al.get(position);
                startActivity(new Intent(Friends.this, Chat.class));
            }
        });

        pd.dismiss();

        /*String url = getString(R.string.firebase_database) + getString(R.string.users) + UserDetails.username + getString(R.string.friends_list) + ".json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Users.this);
        rQueue.add(request);

        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserDetails.chatWith = al.get(position);
                startActivity(new Intent(Users.this, Chat.class));
            }
        });
    }

    public void doOnSuccess(String s) {
        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            while (i.hasNext()) {
                key = i.next().toString();

                if (!key.equals(UserDetails.username)) {
                    al.add(key);
                }

                totalUsers++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (totalUsers < 1) {
            noUsersText.setVisibility(View.VISIBLE);
            usersList.setVisibility(View.GONE);
        } else {
            noUsersText.setVisibility(View.GONE);
            usersList.setVisibility(View.VISIBLE);
            usersList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
        }

        pd.dismiss();*/
    }
}

