package com.marcindziedzic.osiolek2.features.startupFeature;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.marcindziedzic.osiolek2.R;

public class AddIpDialog extends DialogFragment {
    // Use this instance of the interface to deliver action events
    AddIpDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.dialog_add_ip, null))
                // Add action buttons
                .setPositiveButton(R.string.signin, (dialog, id) -> mListener
                        .onIpDialogPositiveClick(AddIpDialog.this))
                .setNegativeButton(R.string.cancel, (dialog, id) -> mListener
                        .onIpDialogNegativeClick(AddIpDialog.this));
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (AddIpDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(MainActivity.class.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface AddIpDialogListener {
        void onIpDialogPositiveClick(DialogFragment dialog);

        void onIpDialogNegativeClick(DialogFragment dialog);
    }
}
