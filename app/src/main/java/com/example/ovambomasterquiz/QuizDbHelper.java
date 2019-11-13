package com.example.ovambomasterquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ovambomasterquiz.Question;
import com.example.ovambomasterquiz.QuizContract.*;
import com.example.ovambomasterquiz.QuizContract;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME= "OvamboMasterQuiz.db"; //Database name
    public static final int DATABASE_VERSION= 1;

    private SQLiteDatabase db;


    public QuizDbHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //onCreate method is used to create SQLiteDatabase with specified fields and data types

    @Override
    public void onCreate(SQLiteDatabase db) {
      this.db= db;

      final String SQL_CREATE_QUESTIONS_TABLE= "CREATE TABLE " +
              QuestionsTable.TABLE_NAME + " ( " +
              QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
              QuestionsTable.COLUMN_QUESTION + " TEXT, " +
              QuestionsTable.COLUMN_OPTION1 + " TEXT," +
              QuestionsTable.COLUMN_OPTION2 + " TEXT," +
              QuestionsTable.COLUMN_OPTION3 + " TEXT," +
              QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
              QuestionsTable.COLUMN_DIFFICULTY + " TEXT" +
              ")";
       db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
       fillQuestionsTable();
    }
//onUpGrade method is used to update the database with changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

//fillQuestionsTable used to populate the database with information
    private void fillQuestionsTable() {
        Question q1 = new Question("How was a Ovambo father told the gender of their child when it was girl?",
                "They were told a frog catcher was born",
                "They were told someone who would grind the meal had arrived",
                "They were told they have a daughter", 2, Question.DIFFICULTY_EASY);
        addQuestion(q1);

        Question q2 = new Question("How was a Ovambo father told the gender of their child when it was boy?",
                "They were told they have a son",
                "They were told someone who would grind the meal had arrived",
                "They were told a frog catcher was born", 3, Question.DIFFICULTY_EASY);
        addQuestion(q2);
        Question q3 = new Question("When a new born boy was born in what order were the following steps taken ?",
                "The baby was washed, anointed with almond oil, lips of the child and breasts of the mother were greased",
                "The baby was anointed with almond oil, the baby was washed, lips of the child and breasts of the mother were greased",
                "The lips of the child and breasts of the mother were greased, the baby was anointed with almond oil and then baby was washed", 1, Question.DIFFICULTY_HARD);
        addQuestion(q3);

        Question q4 = new Question("What is the name of the ceremony that takes place when a child is a few days old?",
                "Epiitho Ceremony",
                "Edhina Iyopomboga",
                "Omboga", 1, Question.DIFFICULTY_HARD);
        addQuestion(q4);

        Question q5 = new Question("Why were the lips of a mother and child greased after child birth?",
                "To prevent the child from having dry lips",
                "To prevent the child from liking its lips which was considered bad manners",
                "To prevent the child from having an infection", 2, Question.DIFFICULTY_HARD);
        addQuestion(q5);

        Question q6 = new Question("In Ondonga what was the first name given to a child refered to as?",
                "Epiitho",
                "Omboga",
                "Edhina lyopomboga", 3, Question.DIFFICULTY_HARD);
        addQuestion(q6);

        Question q7 = new Question("What was the term used to refer to people who shared the same name?",
                "Mbushoye",
                "Mbushandje",
                "Uumbushe", 3, Question.DIFFICULTY_HARD);
        addQuestion(q7);

        Question q8 = new Question("What was it believed that sharing the same name signified?",
                "Sharing the same personality",
                "Sharing the same parents",
                "It signified nothing", 1, Question.DIFFICULTY_EASY);
        addQuestion(q8);

        Question q9 = new Question("What was it believed that sharing the same name signified?",
                "Sharing the same personality",
                "Sharing the same parents",
                "It signified nothing", 1, Question.DIFFICULTY_MEDIUM);
        addQuestion(q9);

        Question q10 = new Question("The Ovambo concept of a man is divided the following 3 spheres?",
                "Omweuo, Ombepo,and the third part consisting of: Omuzizimba, Olutu, Edjina and Eha",
                "The spirit, The breathing and the third part consisting of: the shadow, the body, the name and the place the name was given",
                "All of the above", 3, Question.DIFFICULTY_MEDIUM);
        addQuestion(q10);

        Question q11 = new Question("When is the Eluko Ceremony done?",
                "Immediately after child birth",
                "A month after child birth",
                "A week after child birth", 2, Question.DIFFICULTY_MEDIUM);
        addQuestion(q11);

        Question q12 = new Question("What was is the Eluko Ceremony?",
                "It is a ceremony that takes place after child birth",
                "Option 1 and 2",
                "A month after child birth", 2, Question.DIFFICULTY_MEDIUM);
        addQuestion(q12);


    }
//used to retrieve the necessary questions information
    private void addQuestion(Question question){
        ContentValues cv= new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);

    }

    //method retreives all the messages from the database

public ArrayList<Question> getAllQuestions(){
    ArrayList<Question>  questionList= new ArrayList<>();
    db = getReadableDatabase();
    Cursor c= db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

    if(c.moveToFirst()) {
        do{
            Question question= new Question();
            question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
            question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
            question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
            question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
            question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
            question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
            questionList.add(question);
        }while (c.moveToNext());
    }

        c.close();
    return questionList;
}
    public ArrayList<Question> getQuestions(String difficulty){
        ArrayList<com.example.ovambomasterquiz.Question>  questionList= new ArrayList<>();
        db = getReadableDatabase();
        String[] selectionArgs= new String[]{difficulty};
        Cursor c= db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME +
                " WHERE " + QuestionsTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);

        if(c.moveToFirst()) {
            do{
                com.example.ovambomasterquiz.Question question= new com.example.ovambomasterquiz.Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                questionList.add(question);
            }while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
