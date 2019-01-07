package com.marcindziedzic.osiolek2.reusableFragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.marcindziedzic.osiolek2.R;
import com.marcindziedzic.osiolek2.features.startupFeature.MainActivity;

public class PasswordDialog extends DialogFragment {

    private final PasswordDialogType type;
    // Use this instance of the interface to deliver action events
    NoticeDialogListener mListener;
    ConnectToNetDialogListener connectToNetDialogListener;

    public PasswordDialog(PasswordDialogType passwordDialogType) { //todo verify error
        super();
        type = passwordDialogType;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        switch (type) {
            case NEW_NET:
                builder.setView(inflater.inflate(R.layout.dialog_password_to_net, null))
                        // Add action buttons
                        .setPositiveButton(R.string.signin, (dialog, id) -> mListener.onDialogPositiveClick(PasswordDialog.this))
                        .setNegativeButton(R.string.cancel, (dialog, id) -> mListener.onDialogNegativeClick(PasswordDialog.this));
                break;
            case CONNECT_TO_NET:
                builder.setView(inflater.inflate(R.layout.dialog_password_to_net, null))
                        // Add action buttons
                        .setPositiveButton(R.string.signin, (dialog, id) -> connectToNetDialogListener
                                .onConnectToNetDialogPositiveClick(PasswordDialog.this))
                        .setNegativeButton(R.string.cancel, (dialog, id) -> connectToNetDialogListener
                                .onConnectToNetDialogNegativeClick(PasswordDialog.this));
                break;
        }

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            switch (type) {
                case NEW_NET:
                    // Instantiate the NoticeDialogListener so we can send events to the host
                    mListener = (NoticeDialogListener) context;
                    break;
                case CONNECT_TO_NET:
                    connectToNetDialogListener = (ConnectToNetDialogListener) context;
                    break;
            }
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(MainActivity.class.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    public interface ConnectToNetDialogListener {
        void onConnectToNetDialogPositiveClick(DialogFragment dialogFragment);

        void onConnectToNetDialogNegativeClick(DialogFragment dialogFragment);
    }
}
