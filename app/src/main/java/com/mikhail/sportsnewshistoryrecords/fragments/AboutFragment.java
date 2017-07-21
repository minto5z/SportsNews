package com.mikhail.sportsnewshistoryrecords.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhail.sportsnewshistoryrecords.R;
import com.mikhail.sportsnewshistoryrecords.interfaces.ControlToolbar;


/**
 * About page Fragment
 */
public class AboutFragment extends Fragment {

    protected ImageView appImage;
    protected TextView appName, appVersion;
    protected Button feedback;
    protected View v;
    public ControlToolbar controlToolbar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.about_layout, container, false);
        appImage = (ImageView) v.findViewById(R.id.imageAbout);
        appName = (TextView) v.findViewById(R.id.app_name);
        appVersion = (TextView) v.findViewById(R.id.app_version);
        feedback = (Button) v.findViewById(R.id.button_feedback);

        /**
         * set screen orientation for portrait mode only
         * activity that will be launched after is set to portrait mode
         */
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        controlToolbar.showSpinner(false);
        controlToolbar.setTitle("About");

        hasOptionsMenu();
        setClickListener();

        return v;
    }

    /**
     * fragment attaches to activity from which opened
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            controlToolbar = (ControlToolbar) getActivity();
        } catch (ClassCastException ex) {
            throw new ClassCastException();
        }
    }

    private void setClickListener() {
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendFeedback();
            }
        });
    }
    /**
     * Open email clients to send feedback email
     */
    private void sendFeedback() {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.mail_feedback_email)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_feedback_subject));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.mail_feedback_message));
        startActivity(Intent.createChooser(intent, getString(R.string.title_send_feedback)));
    }

}
