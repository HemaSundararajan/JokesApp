package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;

import com.example.hema.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

    private MyApi myApiService = null;
    private AsyncTaskResponseListener listener;
    private Context context;
    public String mResult;

    public String getResult() {
        return mResult;
    }

    public EndpointsAsyncTask(Context activity, AsyncTaskResponseListener listener) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }
        context = params[0].first;
        String name = params[0].second;
        try {
            String joke = myApiService.sayHi().execute().getData();
            listener.
                    onAsyncResponse(joke);
            return joke;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public interface AsyncTaskResponseListener {
        void onAsyncResponse(String joke) throws IOException;
    }

    @Override
    protected void onPostExecute(String result) {
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        /*mResult = result;
        Intent intent = new Intent(context, com.example.jokesandroidlib.MainActivity.class);
        intent.putExtra("jokeString", result);
        context.startActivity(intent);*/
    }
}
