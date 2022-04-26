package com.SyndicG5.ui.ContainerHome.transaction;


import static com.syndicg5.networking.utils.AppUtils.hideKeyboard;

import android.app.DatePickerDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.SyndicG5.SyndicActivity;
import com.SyndicG5.databinding.ActivityCalculatorBinding;
import com.SyndicG5.ui.ContainerHome.fragments.home.HomefragementViewModel;
import com.syndicg5.networking.models.Balance;
import com.syndicg5.networking.models.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class calculatorActivity extends SyndicActivity {

    private ActivityCalculatorBinding binding;
    private HomefragementViewModel homeViewModel;
    private Double transactionAmount = 0.0;
    private boolean editingAmount = false;
    private static Boolean balanceType = true;
    private Calendar selectedDate = Calendar.getInstance();

    private String input, output, newOutput;
    private double income, outcome = 0.0;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalculatorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        setupCalculatorView();
        homeViewModel = new ViewModelProvider(this).get(HomefragementViewModel.class);
        subscribe();

        binding.transactionMoneyBtn.setOnClickListener(view1 -> {
            balanceType = !balanceType;
            binding.transactionMoneyBtn.setText(balanceType ? "Income" : "Expense");
           // binding.transactionMoneyBtn.setIconTint(balanceType ? ColorStateList.valueOf(Color.GREEN) : ColorStateList.valueOf(Color.RED));
        });

        binding.transactionDateBtn.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date().getTime()));
        binding.transactionDateBtn.setOnClickListener(view1 -> {

            int year = selectedDate.get(Calendar.YEAR);
            int month = selectedDate.get(Calendar.MONTH);
            int day = selectedDate.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), (datePicker, year2, month2, day2) -> {
                selectedDate.set(year2, month2, day2);
                binding.transactionDateBtn.setText(new SimpleDateFormat("dd/MM/yyyy").format(selectedDate.getTime()));
            }, year, month, day);
            datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
            datePickerDialog.show();
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.GREEN);
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
        });


        binding.transactionMoreInfoInput.setOnClickListener(view1 -> {
            AddTransactionDetailsFragment transactionDetailsDialog = new AddTransactionDetailsFragment();
            transactionDetailsDialog.show(getSupportFragmentManager(), transactionDetailsDialog.getTag());
        });

        binding.saveTransactionBtn.setOnClickListener(view1 -> {
            if (balanceType) {
                income = Double.parseDouble(newOutput);
                outcome = 0.0;
            } else {
                outcome = Double.parseDouble(newOutput);
                income = 0.0;
            }
            Balance f = new Balance(outcome, income, new Date().getTime(), user, binding.transactionMoreInfoInput.getText().toString());
            //homeViewModel.saveBalance(f);
            super.onBackPressed();
        });
    }

    private void subscribe() {
        //homeViewModel.getUserInfo();
      //  homeViewModel.getUserMutableLiveData().observe(this, user1 -> this.user = user1);
    }


    private void setupCalculatorView() {
        binding.amountView.etCalculations.setShowSoftInputOnFocus(false);
        binding.amountView.etCalculations.setOnFocusChangeListener((view, b) -> {
            if (!b) hideKeyboard(getParent());
        });
        setCalculatorListeners();
    }

    private void setCalculatorListeners() {
        binding.calculator.button0.setOnClickListener(view -> setWorkings('0'));
        binding.calculator.button1.setOnClickListener(view -> setWorkings('1'));
        binding.calculator.button2.setOnClickListener(view -> setWorkings('2'));
        binding.calculator.button3.setOnClickListener(view -> setWorkings('3'));
        binding.calculator.button4.setOnClickListener(view -> setWorkings('4'));
        binding.calculator.button5.setOnClickListener(view -> setWorkings('5'));
        binding.calculator.button6.setOnClickListener(view -> setWorkings('6'));
        binding.calculator.button7.setOnClickListener(view -> setWorkings('7'));
        binding.calculator.button8.setOnClickListener(view -> setWorkings('8'));
        binding.calculator.button9.setOnClickListener(view -> setWorkings('9'));
        binding.calculator.buttonDot.setOnClickListener(view -> setWorkings('.'));
        binding.calculator.multiply.setOnClickListener(view -> setWorkings('x'));
        binding.calculator.divide.setOnClickListener(view -> setWorkings('÷'));
        binding.calculator.add.setOnClickListener(view -> setWorkings('+'));
        binding.calculator.subtract.setOnClickListener(view -> setWorkings('-'));
        binding.calculator.equals.setOnClickListener(view -> setWorkings('='));
        binding.calculator.buttonClear.setOnClickListener(view -> calculatorClear());
    }

    private void setWorkings(Character data) {
        switch (data) {

            case 'x':
                solve();
                input += "x";
                break;

            case '+':
                solve();
                input += "+";
                break;

            case '-':
                solve();
                input += "-";
                break;

            case '÷':
                solve();
                input += "÷";
                break;

            case '=':
                solve();
                newOutput = cutDecimal(input);
                binding.transactionAmount.setText(newOutput + " Dh");
                binding.saveTransactionBtn.setEnabled(true);
                break;

            case '%':
                input += "%";
                double d = Double.parseDouble(binding.amountView.etCalculations.getText().toString()) / 100;
                binding.transactionAmount.setText(String.valueOf(d));
                break;

            default:
                if (input == null) {
                    input = "";
                }
                input += data;
        }
        binding.amountView.etCalculations.setText(input);
    }

    private void solve() {
        if (input.split("\\+").length == 2) {
            String numbers[] = input.split("\\+");
            try {
                double d = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                binding.transactionAmount.setText(newOutput + " Dh");
                input = d + "";
            } catch (Exception e) {
                binding.transactionAmount.setText(e.getMessage().toString());
            }
        }
        if (input.split("x").length == 2) {
            String numbers[] = input.split("x");
            try {
                double d = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                binding.transactionAmount.setText(newOutput + " Dh");
                input = d + "";
            } catch (Exception e) {
                binding.transactionAmount.setText(e.getMessage().toString());
            }
        }
        if (input.split("÷").length == 2) {
            String numbers[] = input.split("÷");
            try {
                double d = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                binding.transactionAmount.setText(newOutput + " Dh");
                input = d + "";
            } catch (Exception e) {
                binding.transactionAmount.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\^").length == 2) {
            String numbers[] = input.split("\\^");
            try {
                double d = Math.pow(Double.parseDouble(numbers[0]), Double.parseDouble(numbers[1]));
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                binding.transactionAmount.setText(newOutput + " Dh");
                input = d + "";
            } catch (Exception e) {
                binding.transactionAmount.setText(e.getMessage().toString());
            }
        }
        if (input.split("\\-").length == 2) {
            String numbers[] = input.split("\\-");
            try {
                if (Double.parseDouble(numbers[0]) < Double.parseDouble(numbers[1])) {
                    double d = Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[0]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    binding.transactionAmount.setText("-" + newOutput + " Dh");
                    input = d + "";
                } else {
                    double d = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    binding.transactionAmount.setText(newOutput + " Dh");
                    input = d + "";
                }
            } catch (Exception e) {
                binding.transactionAmount.setText(e.getMessage().toString());
            }
        }
        binding.saveTransactionBtn.setEnabled(true);
    }

    private String cutDecimal(String number) {
        String n[] = number.split("\\.");
        if (n.length > 1) {
            if (n[1].equals("0")) {
                number = n[0];
            }
        }
        return number;
    }

    private void calculatorClear() {
        transactionAmount = 0.0;
        input = output = newOutput = "";
        binding.transactionAmount.setText("");
        binding.amountView.etCalculations.setText("0");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (EventBus.getDefault().hasSubscriberForEvent(DescriptionEvenBus.class))
            EventBus.getDefault().removeStickyEvent(DescriptionEvenBus.class);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void OnMyLocationUpdateEventBus(DescriptionEvenBus descriptionEvenBus) {
        binding.transactionMoreInfoInput.setText(descriptionEvenBus.getText());
        binding.saveTransactionBtn.setEnabled(true);
    }

}
