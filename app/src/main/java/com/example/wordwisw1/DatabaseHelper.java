package com.example.wordwisw1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper instance;
    public static final String DATABASE_NAME = "word1wise.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "word_entries";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ENGLISH_WORD = "english_word";
    public static final String COLUMN_WORD_TYPE = "word_type";
    public static final String COLUMN_TURKISH_TRANSLATION = "turkish_translation";
    public static final String COLUMN_EXAMPLE_SENTENCE = "example_sentence";

    public static final String COLUMN_DEFINATION = "defination";

    public static final String TABLE_USER = "users";

    public static final String ID_USER = "ID";
    public static final String USERNAME_USER = "username";
    public static final String PASSWORD_USER = "password";

    public static final String EMAIL_USER = "email";

    public static final String TABLE_IPUCU = "ipucu";
    public static final String ID_IPUCU = "id";

    public static final String WORD_IPUCU = "kelime";
    public static final String ILK_IPUCU = "ilkipucu";
    public static final String IKINCI_IPUCU = "ikiciipucu";

    public static final String UCUNCU_IPUCU = "ucuncuipucu";

    public static final String TURKCE_IPUCU = "turkcekelime";

    public DatabaseHelper(MainActivity  context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper((MainActivity) context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ENGLISH_WORD + " TEXT, " +
                COLUMN_WORD_TYPE + " TEXT, " +
                COLUMN_TURKISH_TRANSLATION + " TEXT, " +
                COLUMN_DEFINATION + " TEXT, " +
                COLUMN_EXAMPLE_SENTENCE + " TEXT)";
        db.execSQL(createTableQuery);

        // Örnek veri ekleme
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_ENGLISH_WORD + ", " +
                COLUMN_WORD_TYPE + ", " +
                COLUMN_TURKISH_TRANSLATION + ", " +
                COLUMN_DEFINATION + ", " +
                COLUMN_EXAMPLE_SENTENCE + ") VALUES ('example', 'noun', 'örnek','Representative instance.', 'This is an example sentence.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_ENGLISH_WORD + ", " +
                COLUMN_WORD_TYPE + ", " +
                COLUMN_TURKISH_TRANSLATION + ", " +
                COLUMN_DEFINATION + ", " +
                COLUMN_EXAMPLE_SENTENCE + ") VALUES ('computer', 'noun', 'bilgisayar', 'Electronic device for processing data.','I use a computer for work.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" +
                COLUMN_ENGLISH_WORD + ", " +
                COLUMN_WORD_TYPE + ", " +
                COLUMN_TURKISH_TRANSLATION + ", " +
                COLUMN_DEFINATION + ", " +
                COLUMN_EXAMPLE_SENTENCE + ") VALUES ('explore', 'verb', 'keşfetmek', 'Examine or investigate in detail.','We will explore the new area tomorrow.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('book', 'noun', 'kitap', 'Printed or written literary work.', 'I love reading a good book before bedtime.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('car', 'noun', 'araba', 'Automobile for transportation.', 'I drive my car to work every day.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('pen', 'noun', 'kalem', 'Writing instrument.', 'I always carry a pen in my pocket.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('house', 'noun', 'ev', 'A place of residence.', 'I enjoy spending time in my cozy house.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('phone', 'noun', 'telefon', 'Communication device for making calls and sending messages.', 'My phone is always with me wherever I go.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('chair', 'noun', 'sandalye', 'Furniture for sitting.', 'I like to sit on a comfortable chair while reading.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('dog', 'noun', 'köpek', 'Domesticated mammal.', 'I take my dog for a walk every evening.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('table', 'noun', 'masa', 'Furniture for placing items.', 'We gather around the table for family dinners.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('watch', 'noun', 'saat', 'Timekeeping device worn on the wrist.', 'I always wear a watch to be punctual.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('bag', 'noun', 'çanta', 'Container for carrying items.', 'I carry my laptop in my bag to work.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('guitar', 'noun', 'gitar', 'Musical instrument with strings.', 'I enjoy playing the guitar in my free time.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('cat', 'noun', 'kedi', 'Domesticated feline.', 'My cat sleeps a lot during the day.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('key', 'noun', 'anahtar', 'Tool for locking and unlocking.', 'I always keep my keys in my pocket.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('camera', 'noun', 'kamera', 'Device for capturing images.', 'I love taking photos with my camera.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('shoes', 'noun', 'ayakkabı', 'Footwear for protecting feet.', 'I bought new shoes for running.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('bookshelf', 'noun', 'kitaplık', 'Furniture for storing books.', 'I organize my books on the bookshelf.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('headphones', 'noun', 'kulaklık', 'Audio device for listening privately.', 'I use headphones to listen to music.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('desk', 'noun', 'masa', 'Furniture for studying or working.', 'I have a cluttered desk in my room.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('window', 'noun', 'pencere', 'Opening in a wall for light and air.', 'I open the window to let fresh air in.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('plant', 'noun', 'bitki', 'Living organism for decoration.', 'I water my plants every morning.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('hat', 'noun', 'şapka', 'Headwear for protection or fashion.', 'I wear a hat in the summer to shield from the sun.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('clock', 'noun', 'saat', 'Device for telling time.', 'I have a wall clock in my living room.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('backpack', 'noun', 'sırt çantası', 'Bag carried on the back.', 'I pack my backpack for hiking trips.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('wallet', 'noun', 'cüzdan', 'Storage for money and cards.', 'I keep my wallet in my pocket.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('bed', 'noun', 'yatak', 'Furniture for sleeping.', 'I love my comfortable bed.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('cup', 'noun', 'bardak', 'Container for drinking liquids.', 'I drink tea from a cup every morning.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('mirror', 'noun', 'ayna', 'Surface for reflection.', 'I check myself in the mirror before leaving the house.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('umbrella', 'noun', 'şemsiye', 'Device for protection from rain.', 'I always carry an umbrella in my bag.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('stove', 'noun', 'ocak', 'Appliance for cooking.', 'I cook dinner on the stove every evening.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('newspaper', 'noun', 'gazete', 'Printed publication for news.', 'I read the newspaper with breakfast.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_ENGLISH_WORD + ", " + COLUMN_WORD_TYPE + ", " + COLUMN_TURKISH_TRANSLATION + ", " + COLUMN_DEFINATION + ", " + COLUMN_EXAMPLE_SENTENCE + ") VALUES ('glasses', 'noun', 'gözlük', 'Optical devices for vision.', 'I wear glasses for reading.')");

        String createTableQueryUser = "CREATE TABLE " + TABLE_USER + " (" +
                ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USERNAME_USER + " TEXT, " +
                PASSWORD_USER + " TEXT, " +
                EMAIL_USER + " TEXT)";
        db.execSQL(createTableQueryUser);


        db.execSQL("INSERT INTO " + TABLE_USER + " (" +
                ID_USER + ", " +
                USERNAME_USER + ", " +
                PASSWORD_USER + ", " +
                EMAIL_USER + ") VALUES ('1', '**********', '************', '*************@yokmail.com')");




        String createTableQueryipucu = "CREATE TABLE " + TABLE_IPUCU + " (" +
                ID_IPUCU + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WORD_IPUCU + " TEXT, " +
                ILK_IPUCU + " TEXT, " +
                IKINCI_IPUCU + " TEXT, " +
                UCUNCU_IPUCU + " TEXT, " +
                TURKCE_IPUCU + " TEXT)";
        db.execSQL(createTableQueryipucu);

        db.execSQL("INSERT INTO " + TABLE_IPUCU + " (" +
                ID_IPUCU + ", " +
                WORD_IPUCU + ", " +
                ILK_IPUCU + " ," +
                IKINCI_IPUCU + " , " +
                UCUNCU_IPUCU + " , " +
                TURKCE_IPUCU+ ") VALUES ('1', 'flying', 'u','m','k','ucmak' )");

        db.execSQL("INSERT INTO " + TABLE_IPUCU + " (" +
                ID_IPUCU + ", " +
                WORD_IPUCU + ", " +
                ILK_IPUCU + " ," +
                IKINCI_IPUCU + " , " +
                UCUNCU_IPUCU + " , " +
                TURKCE_IPUCU + ") VALUES ('2', 'test', 's','n','k','sinamak' )");

        db.execSQL("INSERT INTO " + TABLE_IPUCU + " (" +
                ID_IPUCU + ", " +
                WORD_IPUCU + ", " +
                ILK_IPUCU + " ," +
                IKINCI_IPUCU + " , " +
                UCUNCU_IPUCU + " , " +
                TURKCE_IPUCU + ") VALUES ('3', 'example', 'r','n','k','ornek' )");

        db.execSQL("INSERT INTO " + TABLE_IPUCU + " (" +
                ID_IPUCU + ", " +
                WORD_IPUCU + ", " +
                ILK_IPUCU + " ," +
                IKINCI_IPUCU + " , " +
                UCUNCU_IPUCU + " , " +
                TURKCE_IPUCU + ") VALUES ('4', 'english', 'i','l','c','ingilizce' )");

        db.execSQL("INSERT INTO " + TABLE_IPUCU + " (" +
                ID_IPUCU + ", " +
                WORD_IPUCU + ", " +
                ILK_IPUCU + " ," +
                IKINCI_IPUCU + " , " +
                UCUNCU_IPUCU + " , " +
                TURKCE_IPUCU + ") VALUES ('5', 'mirror', 'a','n','a','ayna' )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }



    public boolean addUser(String username, String password, String mail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME_USER, username);
        values.put(PASSWORD_USER, password);
        values.put(EMAIL_USER, mail);

        long result = db.insert(TABLE_USER, null, values);
        db.close();


        return result != -1;
    }



    public boolean Login(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { DatabaseHelper.USERNAME_USER, DatabaseHelper.PASSWORD_USER, DatabaseHelper.EMAIL_USER };
        String selection = DatabaseHelper.USERNAME_USER + " = ?" + " AND " + DatabaseHelper.PASSWORD_USER + " = ?";
        String[] selectionArgs = { username, password };

        Cursor cursor = db.query(DatabaseHelper.TABLE_USER, columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();
        cursor.close();
        db.close();

        return count > 0;
    }



    public boolean updateUsername(long currentUserID, String newUsername) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME_USER, newUsername);

        int rowsAffected = db.update(TABLE_USER, values, ID_USER + " = ?", new String[]{String.valueOf(1)});
        db.close();

        return rowsAffected > 0;
    }

    public boolean updateEmail(long currentUserID, String newEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL_USER, newEmail);

        int rowsAffected = db.update(TABLE_USER, values, ID_USER + " = ?", new String[]{String.valueOf(1)});
        db.close();

        return rowsAffected > 0;
    }

    public boolean updatePassword(long currentUserID, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PASSWORD_USER, newPassword);

        int rowsAffected = db.update(TABLE_USER, values, ID_USER + " = ?", new String[]{String.valueOf(1)});
        db.close();

        return rowsAffected > 0;
    }

    public long getCurrentUserID() {

        return 1234;
    }

}


