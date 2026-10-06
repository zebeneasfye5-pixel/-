package com.example.data

import kotlinx.coroutines.flow.Flow
import java.util.UUID

data class BankOption(
    val id: String,
    val name: String,
    val code: String,
    val accountNumber: String,
    val accountHolder: String,
    val ussdCode: String,
    val iconColorHex: Long
)

data class PricingPackage(
    val id: String,
    val title: String,
    val subtitle: String,
    val priceBirr: Double,
    val credits: Int,
    val isLifetime: Boolean
)

class PaymentRepository(private val appDao: AppDao) {

    val allTransactions: Flow<List<TransactionEntity>> = appDao.getAllTransactions()

    val banks = listOf(
        BankOption(
            id = "telebirr",
            name = "ቴሌብር (Telebirr)",
            code = "TB",
            accountNumber = "0911223344",
            accountHolder = "ጥንተ ጥበብ (Tinte Tibeb)",
            ussdCode = "*127#",
            iconColorHex = 0xFF0072CE
        ),
        BankOption(
            id = "cbe",
            name = "የኢትዮጵያ ንግድ ባንክ (CBE Birr)",
            code = "CBE",
            accountNumber = "1000234567891",
            accountHolder = "ጥንተ ጥበብ ማዕከል",
            ussdCode = "*847#",
            iconColorHex = 0xFF800080
        ),
        BankOption(
            id = "awash",
            name = "አዋሽ ባንክ (Awash Bank)",
            code = "AWASH",
            accountNumber = "01320876543200",
            accountHolder = "ጥንተ ጥበብ የአባቶች እውቀት",
            ussdCode = "*901#",
            iconColorHex = 0xFF0056B3
        ),
        BankOption(
            id = "abyssinia",
            name = "አቢሲኒያ ባንክ (Bank of Abyssinia)",
            code = "BOA",
            accountNumber = "87654321",
            accountHolder = "ጥንተ ጥበብ",
            ussdCode = "*815#",
            iconColorHex = 0xFFDAA520
        ),
        BankOption(
            id = "chapa",
            name = "ቻፓ (Chapa Payment Gateway)",
            code = "CHAPA",
            accountNumber = "chapa.link/pay/tinte-tibeb",
            accountHolder = "Tinte Tibeb Digital",
            ussdCode = "https://chapa.co",
            iconColorHex = 0xFF10B981
        )
    )

    val packages = listOf(
        PricingPackage(
            id = "pkg_basic",
            title = "የጥበብ ጅማሮ",
            subtitle = "፲ (10) የጥበብ ጠያቂና የኮከብ ቆጠራ ፈቃዶች",
            priceBirr = 50.0,
            credits = 10,
            isLifetime = false
        ),
        PricingPackage(
            id = "pkg_monthly",
            title = "የወር የጥበብ ማህደር",
            subtitle = "ለ ፩ ወር ያልተገደበ ምርምርና የፈውስ ዕፀዋት ማውጫ",
            priceBirr = 150.0,
            credits = 50,
            isLifetime = false
        ),
        PricingPackage(
            id = "pkg_vip",
            title = "ጠቅላላ የጥንተ ጥበብ ዘለቄታ (VIP)",
            subtitle = "የዕድሜ ልክ ያልተገደበ የመጻሕፍት መክፈቻና የድምፅ ንባብ",
            priceBirr = 350.0,
            credits = 999,
            isLifetime = true
        ),
        PricingPackage(
            id = "pkg_donate",
            title = "ለአዘጋጁ የበረከት ድጋፍ",
            subtitle = "የአባቶችን ጥበብ ወደ ዲጂታል ላመጣው ሰሪ ማበረታቻ",
            priceBirr = 100.0,
            credits = 20,
            isLifetime = false
        )
    )

    suspend fun processPayment(
        bank: BankOption,
        pkg: PricingPackage,
        userProvidedRef: String
    ): TransactionEntity {
        val ref = if (userProvidedRef.isNotBlank()) {
            userProvidedRef.trim().uppercase()
        } else {
            "${bank.code}-${(10000000..99999999).random()}"
        }

        val tx = TransactionEntity(
            bankName = bank.name,
            accountNumber = bank.accountNumber,
            amountBirr = pkg.priceBirr,
            transactionRef = ref,
            packageType = pkg.title,
            status = "COMPLETED"
        )

        appDao.insertTransaction(tx)

        if (pkg.isLifetime) {
            appDao.setPremium()
            appDao.addCredits(999)
        } else {
            appDao.addCredits(pkg.credits)
        }

        return tx
    }
}
