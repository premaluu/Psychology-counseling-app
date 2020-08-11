package com.example.psychologycounselingapp;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DSTfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DSTfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DSTfragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DSTfragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DSTfragment.
     */
    // TODO: Rename and change types and number of parameters
    String todayDate, mBirth;
    int d, m, y;
    int years, months, days, choice = 0;
    DatePickerDialog.OnDateSetListener dateSetListener;
    private TextView inpDay, inpMonth, inpYear, txtYear, txtMonth, txtDay;
    private Button calculate, calender, clear;
    private Button btnsubmit;
    Intent intent;
    public static DSTfragment newInstance(String param1, String param2) {
        DSTfragment fragment = new DSTfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dstfragment, container, false);
        final int day = 1;
        final int month = 1;
        final int year = 2001;
        inpDay = (TextView) view.findViewById(R.id.inpday);
        inpMonth = (TextView) view.findViewById(R.id.inpmonth);
        inpYear = (TextView) view.findViewById(R.id.inpyear);
        txtYear = (TextView) view.findViewById(R.id.txtyear);
        txtMonth = (TextView) view.findViewById(R.id.txtmonth);
        txtDay = (TextView)  view.findViewById(R.id.txtday);
        calculate = view.findViewById(R.id.btncalculate);
        calender = (Button) view.findViewById(R.id.calender);
        clear = view.findViewById(R.id.btnclear);
        btnsubmit = (Button) view.findViewById(R.id.submit);
        intent = new Intent(view.getContext(), QuestionListActivity.class);
        SimpleDateFormat dateFormate = new SimpleDateFormat("dd/MM/yyyy");
        todayDate = dateFormate.format(Calendar.getInstance().getTime());
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                mBirth = dayOfMonth + "/" + month + "/" + year;
                d = dayOfMonth;
                m = month;
                y = year;
                inpDay.setText(String.valueOf(d));
                inpMonth.setText(String.valueOf(m));
                inpYear.setText(String.valueOf(y));
            }
        };
        calculate.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                if (mBirth == null) {
                    Toast.makeText(getActivity(), "Enter Date Of Birth", Toast.LENGTH_SHORT).show();
                } else {
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date date1 = simpleDateFormat1.parse(mBirth);
                        Date date2 = simpleDateFormat1.parse(todayDate);
                        long start = date1.getTime();
                        long end = date2.getTime();
                        Period period = new Period(start, end, PeriodType.yearMonthDay());
                        years = period.getYears();
                        months = period.getMonths();
                        days = period.getDays();
                        txtYear.setText(String.valueOf(years));
                        txtMonth.setText(String.valueOf(months));
                        txtDay.setText(String.valueOf(days));


                    } catch (ParseException e) {
                        e.printStackTrace();
                    } finally {
                        selectChoice(years, months, days);

                    }
                    //crash if i put select function here function
                }


            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inpYear.setText("YYYY");
                inpMonth.setText("MM");
                inpDay.setText("DD");
            }
        });


        return view;
    }

    private void selectChoice(int years, int months, int days) {
        btnsubmit.setEnabled(true);
        if (days <= 31 && years == 0 && months == 0) {
            Toast.makeText(getContext(), "this test start for one year child", Toast.LENGTH_SHORT).show();
            choice = 1;
            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });

        } else if (days <= 31 && years == 0 && months <= 11) {
            choice = 1;
            Toast.makeText(getActivity(), "this test start for one year child", Toast.LENGTH_SHORT).show();
            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });
        } else if (years == 1 && days <= 31 && months <= 11) {
            choice = 1;
            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 2 && days <= 31 && months <= 11) {
            choice = 2;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 3 && days <= 31 && months <= 11) {
            choice = 3;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 4 && days <= 31 && months <= 11) {
            choice = 4;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 5 && days <= 31 && months <= 11) {
            choice = 5;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 6 && days <= 31 && months <= 11) {
            choice = 6;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 7 && days <= 31 && months <= 11) {
            choice = 7;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });

        } else if (years == 8 && days <= 31 && months <= 11) {
            choice = 8;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 9 && days <= 31 && months <= 11) {
            choice = 9;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 10 && days <= 31 && months <= 11) {
            choice = 10;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 11 && days <= 31 && months <= 11) {
            choice = 11;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 12 && days <= 31 && months <= 11) {
            choice = 12;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 13 && days <= 31 && months <= 11) {
            choice = 13;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 14 && days <= 31 && months <= 11) {
            choice = 14;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });


        } else if (years == 15 && years <= 16 && days <= 31 && months <= 11) {
            choice = 15;

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    intent.putExtra("choice", choice);
startActivity(intent);
                }
            });
        } else {
            Toast.makeText(getContext(), "maximum age limit is 15 years", Toast.LENGTH_SHORT).show();
            btnsubmit.setEnabled(false);
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
