package com.example.databaseaplication.filters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public class MarksFilterDialogFragment extends DialogFragment {
    private OnMarkDialogFragmentListener markDialogFragmentListener;

    public void setMarkDialogListener(OnMarkDialogFragmentListener onMarkDialogFragmentListener) {
        this.markDialogFragmentListener = onMarkDialogFragmentListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        String[] itemSpinner = {"1", "2", "3", "4", "5"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle("Mark")
                .setNegativeButton("Cancel", (dialog, which) -> {
                    dialog.cancel();
                })
                .setSingleChoiceItems(itemSpinner, 0, (dialog, which) -> {
                    markDialogFragmentListener.onMark(itemSpinner[which]);
                    dialog.cancel();
                });

        return builder.create();
    }

    public interface OnMarkDialogFragmentListener {
        void onMark(String mark);
    }

}