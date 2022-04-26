package com.SyndicG5.ui.ContainerHome.transaction;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.SyndicG5.R;
import com.SyndicG5.databinding.DialogDetailsTransactionBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.greenrobot.eventbus.EventBus;

public class AddTransactionDetailsFragment extends BottomSheetDialogFragment {

    private DialogDetailsTransactionBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogDetailsTransactionBinding.inflate(
                inflater,
                container,
                false
        );
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.transactionMoreInfoInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                handleTextChanges(s);
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                handleTextChanges(s);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        binding.transactionDetailsSaveBtn.setOnClickListener(view1 -> {
                    dismiss();
                    EventBus.getDefault().postSticky(new DescriptionEvenBus(binding.transactionMoreInfoInput.getText().toString()));
        });
    }

    private void handleTextChanges(CharSequence s) {
        if (s.toString().isEmpty()) {
            binding.transactionDetailsSaveBtn.setEnabled(false);
            binding.transactionDetailsSaveBtn.setTextColor(
                    ContextCompat.getColor(
                            requireContext(), R.color.primary_pinkish_grey_two
                    )
            );
        } else {
            if (s.length() >= 1) {
                binding.transactionDetailsSaveBtn.setEnabled(true);
                binding.transactionDetailsSaveBtn.setTextColor(ContextCompat.getColor(requireContext(), R.color.primary));
            }
        }
    }
}
