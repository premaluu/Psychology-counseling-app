package com.example.psychologycounselingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public class QuestionListActivity extends AppCompatActivity implements FalseAnswerThresholdCrossListener
{
    public int choice;
    private Button btnsubmit;
    public  Question[] questiondata = null;
    private Button submit;
    private QuestionListAdapter questionListAdapter;
    private int wrongCounter = 0;
    public  int backwardCounter = 0, forwardCounter = 0;
     Intent intent1;
    int TotValue;
    public int actualAgeDays = 0;
    String age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        //intent1 = new Intent(QuestionListActivity.this, Passed.class);
        Intent intent = getIntent();
        intent1=new Intent(QuestionListActivity.this,ResultActivity.class);
        choice = intent.getIntExtra("choice", 1);
        age = String.valueOf(choice);
        submit = findViewById(R.id.btnsubmit);
        createLayout(choice, backwardCounter, forwardCounter);
//        Toast.makeText(getApplicationContext(), String.valueOf(choice), Toast.LENGTH_LONG).show(); displaying age (not needed)
        //intent1 = new Intent(QuestionListActivity.this, Passed.class);
    }

    @Override
    public void onThresholdCrossed() {
        if (backwardCounter >= 1 && wrongCounter < 2) {
            Toast.makeText(getApplicationContext(), "Test passed", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Test passed succesfully")
                    .setCancelable(false)
                    .setPositiveButton("View Result", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Log.d(TAG, "onClick:bye");
                            dialog.cancel();
//                                        inten= new Intent(QuestionList.this, ResultActivity.class);
//                                        startActivity(inten);
                            equation(actualAgeDays, TotValue);

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (forwardCounter >= 1 && wrongCounter >= 2) {
            Toast.makeText(getApplicationContext(), "Test passed", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Test failed")
                    .setCancelable(false)
                    .setPositiveButton("View Result", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Log.d(TAG, "onClick:bye");
                            //inten= new Intent(QuestionList.this, ResultActivity.class);
                            //startActivity(inten);
                            equation(actualAgeDays, TotValue);

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (choice < -2) {
            Toast.makeText(this, "Test failed", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Test failed ..")
                    .setCancelable(false)
                    .setPositiveButton("View Result", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Log.d(TAG, "onClick:bye");
                            dialog.cancel();
                            //inten= new Intent(QuestionList.this, ResultActivity.class);
                            //startActivity(inten);
                            equation(actualAgeDays, TotValue);

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else if (backwardCounter >= 3 && forwardCounter <= 0) {
            //failed test
            Toast.makeText(this, "Test failed", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Test failed..")
                    .setCancelable(false)
                    .setPositiveButton("View Result", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Log.d(TAG, "onClick:bye");
                            dialog.cancel();
                            //inten= new Intent(QuestionList.this, ResultActivity.class);
                            //startActivity(inten);
                            equation(actualAgeDays, TotValue);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            // intent1.putExtra("result", false);
            //startActivity(inten);
            // equation(choice, TotValue);

        }
        else {
            for (int i = 0; i < questionListAdapter.attemptedCount(); i++) {
                if (!questiondata[i].isAnswer()) {
                    wrongCounter++;
                    TotValue+=questiondata[i].getValue();
                }
            }
            createLayout(--choice, ++backwardCounter, forwardCounter);
        }
    }

    public void createLayout(final int choice, final int backwardCounter, final int forwardCounter) {
        if (choice > 15) {
            Toast.makeText(this, "Test passed", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Test passed succesfully..")
                    .setCancelable(false)
                    .setPositiveButton("View Result", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Log.d(TAG, "onClick: hi");
                            dialog.cancel();
//                            inten= new Intent(QuestionList.this, ResultActivity.class);
//                            startActivity(inten);
                            equation(actualAgeDays, TotValue);

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (choice < -2) {
            Toast.makeText(this, "Test failed", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Test failed ..")
                    .setCancelable(false)
                    .setPositiveButton("View Result", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Log.d(TAG, "onClick:bye");
                            dialog.cancel();
//                            inten= new Intent(QuestionList.this, ResultActivity.class);
//                            startActivity(inten);
                            equation(actualAgeDays, TotValue);

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        if (backwardCounter >= 3 && forwardCounter <= 0 && choice >= 1) {
            //failed test
            Toast.makeText(this, "Test failed", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Test failed..")
                    .setCancelable(false)
                    .setPositiveButton("View Result", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int id) {
                            //Log.d(TAG, "onClick:bye");
                            dialog.cancel();
                            //inten= new Intent(QuestionList.this, ResultActivity.class);
                            // startActivity(inten);
                            equation(actualAgeDays, TotValue);

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            // intent1.putExtra("result", false);
            //startActivity(inten);
        }
        else if (forwardCounter >= 3 && backwardCounter <= 0)
        {
            Toast.makeText(this, "Test passed", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Test passed succesfully..")
                    .setCancelable(false)
                    .setPositiveButton("View Result", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //Log.d(TAG, "onClick:bye");
//                           dialog.cancel();
//                           inten= new Intent(QuestionList.this, ResultActivity.class);
//                           startActivity(inten);
                            equation(actualAgeDays, TotValue);

                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
//            intent1.putExtra("result", true);
//            startActivity(intent1);
        } else {
            switch (choice) {
                case -2:
                    questiondata = new Question[]{
                            new Question("જન્મ સમયે ૨ડયા હતા ?", false, 13, 91),
                            new Question("બંને બાજુના અંગોનો ઉપયોગ બરાબર થાય? ", false, 13, 91),
                            new Question("ઘંટડીના અવાજની સામે કોઈ પ્રતિકિયા અIપે છે? અવાજ પારખી શકે છે ?", false, 13, 91),
                            new Question("મોઢા માંથી અવાજો કાઢતા હતા ?", false, 13, 91),
                            new Question("આપણે હસીએ તો તે સામે હસે છે ?", false, 13, 91),
                            new Question("આંખ સામે ફરતી વસ્તુને જુએ છે?", false, 13, 91),
                            new Question("માથુ સ્થિર રાખી શકે છે?", false, 13, 91)
                    };
                    break;
                case -1:
                    questiondata = new Question[]{
                            new Question("વસ્તુઓને લેવા માટે હાથ ફેલાવે છે ?", false, 15, 183),
                            new Question("મોટેથી હસે છે ?", false, 15, 183),
                            new Question("મમ્મીને ઓળખે છે?", false, 15, 183),
                            new Question("લાંબા સમય સુધી અવાજ કરી રમે કે બબડે છે ?", false, 15, 183),
                            new Question("વસ્તુઓને માથા સુધી લઈ જવાની કોશીષ કરે છે ?", false, 15, 183),
                            new Question("ઉંધા ચત્તા થાય છે ?", false, 15, 183),
                    };
                    break;
                case 0:
                    questiondata = new Question[]{
                            new Question("આપણે  અવાજો કાઢીએ તેની નકલ કરે છે?", false, 23, 274),
                            new Question("આગળી અને અંગુઠાથી પકડી શકે છે ?", false, 23, 274),
                            new Question("વસ્તુઓને જોઈ જીજ્ઞાસા બતાવે છે?", false, 23, 274)
                    };
                    break;

                case 1:
                    questiondata = new Question[]{
                            new Question("ત્રણ અક્ષરના શબ્દો બોલે છે દા.ત , દાદા , મામા વગેરે", false, 23, 365),
                            new Question("એકલા વ્યવસ્થિત ઉભા રહે છે?", false, 23, 365),
                            new Question("સરળ  સૂચનાઓને અનુસરે છે?", false, 23, 365),
                            new Question("કપડા પહેરતી વખતે હાથપગ ઉંચાનીચા કરે છે? ", false, 23, 365),
                            new Question("અર્થવાળા શબ્દો બોલે છે ?", false, 45, 365),
                            new Question("બરાબર ચાલે , બોલે છે?", false, 45, 365),
                            new Question("જે વસ્તુ જોઈએ તે બતાવે છે ?", false, 45, 365),
                            new Question("લીટા પાડી શકે છે?", false, 45, 365)
                    };
                    break;
                case 2:
                    questiondata = new Question[]{
                            new Question("2/3 શબ્દના વાક્યો બોલી શકે છે ?", false, 45, 730),
                            new Question("વસ્તુઓને ચિત્રમાં ઓળખી શકે છે ?", false, 45, 730),
                            new Question("શરીરના અંગો બતાવી શકે છે?", false, 45, 730),
                            new Question("રમત રમવામાં ભાગ લઈ શકે છે?", false, 45, 730)
                    };
                    break;
                case 3:
                    questiondata = new Question[]{
                            new Question("ગોળ ( 0 ) કોપી કરી શકે છે ?", false, 60, 1095),
                            new Question("જે થયુ હો તો ( વાગ્યુ હોય વગેરે ) કહી કે બતાડી શકે છે ?", false, 60, 1095),
                            new Question("રોજિંદી વસ્તુના નામ અને ઉપયોગ જાણે છે?", false, 60, 1095),
                            new Question("કેમ ? થી સવાલ પૂછે છે?", false, 60, 1095),
                            new Question("જાતે ખાઇ શકે છે?", false,60, 1095),
                            new Question("Toilet control કરી શકે છે?", false,60, 1095)
                    };
                    break;
                case 4:
                    questiondata = new Question[]{
                            new Question("બટન લગાડી શકે છે ?", false, 72, 1460),
                            new Question("‘ ભૂખ ' , ' ઠંડી ' બતાવી શકે છે?", false, 72, 1460),
                            new Question(" બીજા બાળકો સાથે રમી શકે છે ?", false, 72, 1460),
                            new Question("૩ આકડા યાદ રાખી બોલી શકે છે?", false, 72, 1460),
                            new Question("વાર્તા કહી શકે છે ?", false, 72, 1460)
                    };
                    break;
                case 5:
                    questiondata = new Question[]{
                            new Question("શબ્દનો અર્થ કહી શકે છે ?", false, 60, 1825),
                            new Question("સાદુ ચિત્ર બનાવી શકે છે ?", false, 60, 1825),
                            new Question("કોઇની દેખરેખ વગર કપડા પહેરી  છે ?", false, 60, 1825),
                            new Question("ચિત્રની અંદર ના પાત્રો શું કરે છે તે વર્ણવી શકે છે ?", false, 60, 1825),
                            new Question("સવાલ ના જવાબ સાચા આપે છે ?", false, 60, 1825),
                            new Question("પડોશીઓને ત્યાં જઈ ને આવી શકે છે?", false, 60,1825)
                    };
                    break;
                case 6:
                    questiondata = new Question[]{
                            new Question("કલર નામ આપે છે કે નહી ( ૬ કલર ) ?", false, 72, 2190),
                            new Question("નિયમો સાથે રમી શકે છે ?", false, 72, 2190),
                            new Question("સાદા શબ્દો લખી શકે છે?", false, 72, 2190),
                            new Question("સ્કૂલ માં પ્રવેશ પોતાના બળ પર મળેલો ?", false, 72, 2190),
                            new Question("તડફોડ વગર રમતો રમી શકે છે?", false, 72, 2190)
                    };
                    break;
                case 7:
                    questiondata = new Question[]{
                            new Question("ઘર થી શાળામાં બરાબર જાય છે ?", false, 72, 2555),
                            new Question("બે વસ્તુ વચ્ચેનો તફાવત ઓળખી શકે છે ?", false,72, 2555),
                            new Question("સાદા શબ્દો લખી , વાંચી શકે છે?", false,72, 2555),
                            new Question("સાથેમળી રમત રમી શકે છે?", false, 72, 2555),
                            new Question("પૈસાની કિમંત ખબર છે ?", false, 72, 2555)
                    };
                    break;
                case 8:
                    questiondata = new Question[]{
                            new Question("જાતે વાળ ઓળી શકે છે ?", false, 90, 2920),
                            new Question("નાની વસ્તુ ખરીદી શકે  છે?", false, 90, 2920),
                            new Question("શાળાની હરિફાઈમાં ભાગ લે છે?", false, 90, 2920),
                            new Question("સમય બતાવી શકે છે?", false, 90, 2920)
                    };
                    break;
                case 9:
                    questiondata = new Question[]{
                            new Question("દિવસ , મહિનો , હર્ષ બતાવી શકે છે ?", false, 60, 3285),
                            new Question("પોતે રસ લઇ વંચી શકે છે ?", false, 60, 3285),
                            new Question("બીજાની વસ્તુ ઓળખી શકે છે અને કાળજી થી પાછી આપે છે?", false, 60, 3285),
                            new Question("ગમતી વાર્તા બોલે છે?", false, 60, 3285),
                            new Question("લખોટી રમી શકે છે?", false, 60, 3285),
                            new Question("મદદ  વગર નાહી  શકે છે ?", false, 60, 3285)
                    };
                    break;
                case 10:
                    questiondata = new Question[]{
                            new Question("બીજા ને સાથ સહકાર આપતા આવડે છે?", false, 72, 3650),
                            new Question("વસ્તુ નો સંગ્રહ કરવાનો ગમે છે?", false,72, 3650),
                            new Question("શહેરમાં ફરી શકે છે ?", false,72, 3650),
                            new Question("જાતિ પ્રમાણે જુદી રમતો રમી શકે છે?", false, 72, 3650),
                            new Question("મમ્મી પપ્પા વગર આખી રાત ઘરની બહાર રહી શકે છે?", false, 72, 3650)
                    };
                    break;
                case 11:
                    questiondata = new Question[]{
                            new Question("કોઈકવાર નાની ચિઠ્ઠી લખી શકે છે ?", false, 90, 4015),
                            new Question("સામાજીક રીતો ( સુખ , દુ : ખ) સમજી શકે છે ?", false, 90, 4015),
                            new Question("જીમ , કસરત વગેરે કરવાનું ગમે છે ?", false, 90, 4015),
                            new Question("મુશ્કેલ પરિસ્થિતિ વિષે ચર્ચા કરી શકે છે ?", false, 90, 4015)
                    };
                    break;
                case 12:
                    questiondata = new Question[]{
                            new Question("પુસ્તક , છાપા , મેગેઝીન વાંચવા માં રસ છે?", false, 120, 4380),
                            new Question("પોતે જાતે ખર્ચા કરી શકે છે?", false, 120, 4380),
                            new Question("પોતાની જાતે પોતાની ભૂલોની ટીકા કરી શકે છે?", false, 120, 4380)
                    };
                    break;
                case 13:
                    questiondata = new Question[]{
                            new Question("આગળથી પ્લાનીંગ , જજમેન્ટ કરી શકે છે?", false, 72, 4745),
                            new Question("અનુભવથી શીખી શકે છે ?", false, 72, 4745),
                            new Question("અઘરી રમતો રમી શકે છે ?", false, 72, 4745),
                            new Question("તૈયારૂ થવાનું ગમે છે?", false, 72, 4745),
                            new Question("કાલ્પનિક વિચારો કરી શકે છે ?", false, 72, 4745)
                    };
                    break;
                case 14:
                case 15:
                    questiondata = new Question[]{
                            new Question("પોતાની કેરીયર બનાવવાનું વિચારી શકે છે ?( job ) ", false, 144, 5475),
                            new Question("આજુ બાજુની જાણકારી રાખી શકે છે ?", false, 144, 5475),
                            new Question("પોતાના કપડા જાતે ખરીદી શકે છે?", false, 144, 5475),
                            new Question("પોતાનું કામ જાતે ગોઠવી શકે છે ?", false, 144, 5475),
                            new Question("બીજા માટે ખરીદી શકે છે?", false, 144, 5475)
                    };
                    break;
                default:
                    questiondata = new Question[]{};
            }


            if (questiondata.equals(null)) {
                Toast.makeText(getApplicationContext(), "Question not found", Toast.LENGTH_LONG).show();
            } else {
                if(actualAgeDays == 0) {
                    actualAgeDays = questiondata[0].getDays();
                }
                RecyclerView recyclerView = findViewById(R.id.question_recyclerview);
                questionListAdapter = new QuestionListAdapter(questiondata, this);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(questionListAdapter);
            }
            submit.setOnClickListener(new View.OnClickListener() {
                String message;
                int tempCounter = 0;
                @Override
                public void onClick(View v) {
                    wrongCounter = 0;
                    message = "";
                    for (int i = 0; i < questionListAdapter.attemptedCount(); i++) {
                        message = message + "\n" + (i + 1) + " " + questiondata[i].isAnswer();
                        if (!questiondata[i].isAnswer()) {
                            wrongCounter++;
                            TotValue+=questiondata[i].getValue();
                        }
                    }

 //                   Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show(); displaying total attempted answers (no needed)
                    if (wrongCounter >= 2 && backwardCounter == 0 && forwardCounter == 0) {
                        createLayout((choice - 1), backwardCounter + 1, forwardCounter);
                    } else if (backwardCounter >= 1 && wrongCounter < 2) {
                        Toast.makeText(getApplicationContext(), "Test passed", Toast.LENGTH_LONG).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("Test passed succesfully")
                                .setCancelable(false)
                                .setPositiveButton("View Result", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // Log.d(TAG, "onClick:bye");
                                        //inten= new Intent(QuestionList.this, ResultActivity.class);
                                        //startActivity(inten);
                                        equation(actualAgeDays, TotValue);

                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else if (forwardCounter >= 1 && wrongCounter >= 2) {
                        Toast.makeText(getApplicationContext(), "Test passed", Toast.LENGTH_LONG).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("Test failed")
                                .setCancelable(false)
                                .setPositiveButton("View Result", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // Log.d(TAG, "onClick:bye");
                                        //inten= new Intent(QuestionList.this, ResultActivity.class);
                                        //startActivity(inten);
                                        equation(actualAgeDays, TotValue);

                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else if (backwardCounter >= 1 && wrongCounter >= 2) {
                        createLayout(choice-1, backwardCounter+1, forwardCounter);
                    } else if (wrongCounter < 2 && forwardCounter <= 3) {
                        createLayout(choice + 1, backwardCounter, forwardCounter + 1);
                    } else if (forwardCounter >= 1 && wrongCounter >= 2) {


                        Toast.makeText(getApplicationContext(), "Test failed", Toast.LENGTH_LONG).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setMessage("Test failed..")
                                .setCancelable(false)
                                .setPositiveButton("View Result", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //Log.d(TAG, "onClick:bye");
                                        dialog.cancel();
                                        equation(actualAgeDays,TotValue);
//                                        inten= new Intent(QuestionList.this, ResultActivity.class);
//                                        startActivity(inten);
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }

                }
            });
        }
    }

    void equation(double actualAgeDays, double totalval){
        double result=0;
        double days = questiondata[0].getDays(); // current age days, as it's already in questiondata[0] array of object 0 is index by which we calling getDays() method and getting reach age days.
        double eq= (days - totalval) / actualAgeDays;
        result=eq*100;
        Toast.makeText(this, String.valueOf(result), Toast.LENGTH_LONG).show();
        intent1.putExtra("age", age);
        intent1.putExtra("result",result);
        startActivity(intent1);
    }
}

