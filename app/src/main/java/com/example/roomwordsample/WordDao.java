package com.example.roomwordsample;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/** Accès aux données : requêtes SQL associées à des méthodes Java. */
@Dao
public interface WordDao {

    // Ignore l'insertion si le mot existe déjà (clé primaire)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    // LiveData : l'UI est notifiée automatiquement à chaque changement
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAlphabetizedWords();
}
