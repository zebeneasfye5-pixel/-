package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    // User Profile
    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    fun getUserProfile(): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE id = 1 LIMIT 1")
    suspend fun getUserProfileOnce(): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProfile(profile: UserProfileEntity)

    @Update
    suspend fun updateUserProfile(profile: UserProfileEntity)

    @Query("UPDATE user_profile SET credits = credits + :amount WHERE id = 1")
    suspend fun addCredits(amount: Int)

    @Query("UPDATE user_profile SET credits = MAX(0, credits - 1) WHERE id = 1")
    suspend fun deductCredit()

    @Query("UPDATE user_profile SET isPremium = 1 WHERE id = 1")
    suspend fun setPremium()

    @Query("UPDATE user_profile SET preferredLanguage = :lang WHERE id = 1")
    suspend fun setLanguage(lang: String)

    // Consultations
    @Query("SELECT * FROM consultations ORDER BY timestamp DESC")
    fun getAllConsultations(): Flow<List<ConsultationRecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConsultation(record: ConsultationRecordEntity): Long

    @Query("DELETE FROM consultations WHERE id = :id")
    suspend fun deleteConsultation(id: Long)

    @Query("UPDATE consultations SET isFavorite = NOT isFavorite WHERE id = :id")
    suspend fun toggleFavorite(id: Long)

    // Transactions
    @Query("SELECT * FROM transactions ORDER BY timestamp DESC")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity): Long
}
