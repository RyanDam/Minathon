package com.rstudio.minathon.minathon.firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

/**
 * Created by Ryan on 1/7/17.
 */

public class FireBase {

    private static final String TAG = "Firebase";

    public static void connectGroup(String id, final OnConnectGroupListener ls) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("group");
        final DatabaseReference group = myRef.child(id);
        group.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    Group g = dataSnapshot.getValue(Group.class);
                    ls.onSuccessful(g);
                    group.removeEventListener(this);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                ls.onFailure(databaseError.toException());
                group.removeEventListener(this);
            }
        });
    }

    public static void listenGroup(String id, final OnListenToGroup ls) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("group");
        final DatabaseReference group = myRef.child(id);
        group.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    Group g = dataSnapshot.getValue(Group.class);
                    if (g.alarm) {
                        ls.onGroupAlarm(g);
                    }
                    Calendar cal = Calendar.getInstance();
                    if (cal.getTimeInMillis() > g.startTime + g.duration) {
                        ls.onGroupEnd(g);
                        group.removeEventListener(this);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                ls.onError(databaseError.toException());
            }
        });
    }

    public static void alarm(final Group g, String name, final OnConnectGroupListener ls) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("group");
        final DatabaseReference group = myRef.child(g.id);
        g.alarm = true;
        g.alarmName = name;
        group.setValue(g, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    ls.onFailure(databaseError.toException());
                } else {
                    ls.onSuccessful(g);
                }
            }
        });
    }

    public static void createGroup(final Group g, final OnConnectGroupListener ls) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("group");
        final DatabaseReference group = myRef.child(g.id);
        group.setValue(g, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError != null) {
                    ls.onFailure(databaseError.toException());
                } else {
                    ls.onSuccessful(g);
                }
            }
        });
    }

}
