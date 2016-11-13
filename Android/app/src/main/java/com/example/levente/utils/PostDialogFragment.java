package com.example.levente.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.Button;

import com.example.levente.models.Person;
import com.example.levente.securiapp_version20.MainActivity;
import com.example.levente.securiapp_version20.R;

/**
 * Created by root on 11/1/16.
 */

public class PostDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("")
                .setItems(R.array.posts, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity ma = (MainActivity)getActivity();
                        String posts[] = getResources().getStringArray(R.array.posts);
                        Person.getInstance().setPost(posts[which]);
                        ma.setPostButtonText(posts[which]);

                    }
                });
        return builder.create();
    }

}
