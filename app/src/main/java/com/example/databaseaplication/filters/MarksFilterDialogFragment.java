package com.example.databaseaplication.filters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.databaseaplication.R;

import java.util.concurrent.atomic.AtomicReference;


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
                });

        return builder.create();
    }

    public interface OnMarkDialogFragmentListener {
        void onMark(String mark);
    }

}