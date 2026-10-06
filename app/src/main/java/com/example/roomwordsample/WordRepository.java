package com.example.roomwordsample;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/** Dépôt : unique point d'accès aux données pour le reste de l'application. */
class WordRepository {

    private final WordDao mWordDao;
    private final LiveData<List<Word>> mAllWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAlphabetizedWords();
    }

    // Room exécute les requêtes LiveData sur un thread secondaire
    LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    // L'insertion doit se faire hors du thread principal
    void insert(Word word) {
        WordRoomDatabase.databaseWriteExecutor.execute(() -> mWordDao.insert(word));
    }
}
