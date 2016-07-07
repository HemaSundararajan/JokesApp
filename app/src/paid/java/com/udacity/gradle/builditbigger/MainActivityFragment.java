package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.jokesandroidlib.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Activity activity;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);
        Button tellJokeBtn = (Button) root.findViewById(R.id.jokeButton);
        tellJokeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tellJoke(root);
            }
        });
        activity = getActivity();
        return root;
    }

    public void tellJoke(View view){

        EndpointsAsyncTask.AsyncTaskResponseListener listener = new EndpointsAsyncTask.AsyncTaskResponseListener() {
            @Override
            public void onAsyncResponse(String joke) {
                Intent displayJokeIntent = new Intent(activity, com.example.jokesandroidlib.MainActivity.class);
                displayJokeIntent.putExtra("jokeString", joke);
                activity.startActivity(displayJokeIntent);
            }
        };
        new EndpointsAsyncTask(activity,listener).execute(new Pair<Context, String>(getActivity(), ""));
    }
}
