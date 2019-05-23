package databindingtest.com.driving;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Service;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListPopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import databindingtest.com.driving.databinding.ActivityRegistraionBinding;

public class Registration extends BaseActivity {


    private ActivityRegistraionBinding binding;
    SoftKeyboard softKeyboard;
//    private RelativeLayout footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_registraion);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_registraion);

//        ((ArrayAdapter) binding.nationality.getAdapter()).setDropDownViewResource(android.R.layout.simple_list_item_1);
//
//        binding.nationality.setOnItemSelectedListener(new ItemSelected() {
//            @Override
//            void itemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//
//        binding.vehicletype.setOnItemSelectedListener(new ItemSelected() {
//            @Override
//            void itemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//
//        binding.emirates.setOnItemSelectedListener(new ItemSelected() {
//            @Override
//            void itemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//
//        binding.country.setOnItemSelectedListener(new ItemSelected() {
//            @Override
//            void itemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
//        binding.dob.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                if (hasFocus) {
//                    new DatePickerDialog(v.getContext(), date, myCalendar
//                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//                }
//            }
//        });


        binding.sendMsgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                softKeyboard.closeSoftKeyboard();
            }
        });

//        binding.name.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                if (hasFocus) {
//
//                    binding.footer.setVisibility(View.VISIBLE);
//                } else binding.footer.setVisibility(View.GONE);
//            }
//        });


        binding.dob.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        binding.registraion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Registration.this, "You have successfully registered...", Toast.LENGTH_SHORT).show();

            }
        });


//        binding.popup.getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//
//                if (hasFocus) {
//
//
//                    showPopUp(binding.popup.getEditText());
//                }
//
//            }
//        });

        binding.nationality.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] list = getResources().getStringArray(R.array.nationality);
                showPopUp(binding.nationality.getEditText(), list);
            }
        });

        binding.vehicletype.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] list = getResources().getStringArray(R.array.vehicletype);
                showPopUp(binding.vehicletype.getEditText(), list);
            }
        });
        binding.emirates.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] list = getResources().getStringArray(R.array.emirates);
                showPopUp(binding.emirates.getEditText(), list);
            }
        });
        binding.country.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] list = getResources().getStringArray(R.array.country);
                showPopUp(binding.country.getEditText(), list);
            }
        });

        binding.sex.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] list = getResources().getStringArray(R.array.sex);
                showPopUp(binding.sex.getEditText(), list);
            }
        });

//        attachKeyboardListeners();
    }


    @Override
    protected void onStart() {
        super.onStart();
        InputMethodManager im = (InputMethodManager) getSystemService(Service.INPUT_METHOD_SERVICE);

/*
Instantiate and pass a callback
*/

        softKeyboard = new SoftKeyboard(binding.rootLayout, im);
        softKeyboard.setSoftKeyboardCallback(new SoftKeyboard.SoftKeyboardChanged() {

            @Override
            public void onSoftKeyboardHide() {
                // Code here

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.footer.setVisibility(View.GONE);
                    }
                });


            }

            @Override
            public void onSoftKeyboardShow() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.footer.setVisibility(View.VISIBLE);
                    }
                });
            }


        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        softKeyboard.closeSoftKeyboard();
        softKeyboard.unRegisterSoftKeyboardCallback();
    }

    private void showPopUp(final EditText v, final String[] list) {


        final ListPopupWindow listPopupWindow = new ListPopupWindow(Registration.this);

//        String[] list = getResources().getStringArray(R.array.nationality);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);

        listPopupWindow.setAdapter(arrayAdapter);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listPopupWindow.dismiss();
                v.setText(arrayAdapter.getItem(position).toString());

            }


        });

        listPopupWindow.setAnchorView(v);
        listPopupWindow.setWidth(250);
        listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);

        listPopupWindow.setModal(true);

        listPopupWindow.show();


//        String[] list = getResources().getStringArray(R.array.nationality);
//
//        final PopWindow popWindow = new PopWindow(this, list, new PopWindow.ItemSelectedListener() {
//            @Override
//            public void onItemSelected(String s) {
//
//                binding.popup.getEditText().setText(s);
//            }
//        });
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            popWindow.showAsDropDown(v, -5, 0, Gravity.CENTER);
//        } else {
//            popWindow.showAsDropDown(v);
//        }
//
//        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

    }

//    @Override
//    protected void onShowKeyboard(int keyboardHeight) {
//        // do things when keyboard is shown
//        binding.footer.setVisibility(View.GONE);
//    }
//
//    @Override
//    protected void onHideKeyboard() {
//        // do things when keyboard is hidden
//        binding.footer.setVisibility(View.VISIBLE);
//    }

    final Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        binding.dob.getEditText().setText(sdf.format(myCalendar.getTime()));
    }

    private abstract class ItemSelected implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                ((TextView) parent.getChildAt(0)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.monsoon));
            } else {
                ((TextView) parent.getChildAt(0)).setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }

            itemSelected(parent, view, position, id);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        abstract void itemSelected(AdapterView<?> parent, View view, int position, long id);
    }
}
