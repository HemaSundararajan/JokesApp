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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


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

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
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
