package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.v4.util.Pair;
import android.test.ActivityUnitTestCase;
import android.test.AndroidTestCase;
import android.test.InstrumentationTestCase;
import android.text.TextUtils;
import android.widget.Button;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by hema on 26/6/16.
 */

public class JokeTaskTest extends InstrumentationTestCase implements EndpointsAsyncTask.AsyncTaskResponseListener {
    EndpointsAsyncTask task;
    Context context;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        context = getInstrumentation().getTargetContext();
        task = new EndpointsAsyncTask(context, JokeTaskTest.this) {
            @Override
            protected void onPostExecute(String joke) {
            /* All-clear! */
            }
        };
    }

    public void testAsynkTaskResponse() throws Throwable {
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                String result = null;
                try {
                    result = task.execute(new Pair<Context, String>(context, "Manfred")).get(30, TimeUnit.SECONDS);
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    e.printStackTrace();
                }
                assertFalse(TextUtils.isEmpty(result));
            }
        });

    }

    @Override
    public void onAsyncResponse(String joke) throws IOException {

    }
}
