package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey val id: Int = 1,
    val fullName: String,
    val phoneNumber: String,
    val gender: String,
    val passwordHash: String,
    val isRegistered: Boolean = true,
    val isPremium: Boolean = false,
    val credits: Int = 10,
    val preferredLanguage: String = "am", // "am", "ge", "en", "om", "ti"
    val registrationDate: Long = System.currentTimeMillis()
)

@Entity(tableName = "consultations")
data class ConsultationRecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val category: String, // ኮከብ ቆጠራ, መስተፋቅር, ባህረ ሐሳብ, ፈውስ, መፍትሔ ሥራይ, ሄኖክ
    val title: String,
    val seekerName: String,
    val motherName: String,
    val targetName: String = "",
    val details: String,
    val geezScript: String,
    val translation: String,
    val prescription: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isFavorite: Boolean = false
)

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val bankName: String,
    val accountNumber: String,
    val amountBirr: Double,
    val transactionRef: String,
    val packageType: String,
    val status: String, // "COMPLETED", "VERIFIED"
    val timestamp: Long = System.currentTimeMillis()
)
