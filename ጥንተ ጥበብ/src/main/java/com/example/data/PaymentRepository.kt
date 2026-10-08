package com.example.data

import kotlinx.coroutines.flow.Flow

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
            accountHolder = "ጥንተ ጥበብ",
            ussdCode = "*127#",
            iconColorHex = 0xFF0072CE
        ),
        BankOption(
            id = "cbe",
            name = "የኢትዮጵያ ንግድ ባንክ (CBE)",
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
            accountHolder = "ጥንተ ጥበብ",
            ussdCode = "*901#",
            iconColorHex = 0xFF0056B3
        )
    )

    val packages = listOf(
        PricingPackage("pkg_basic", "የጥበብ ጅማሮ", "10 ፈቃዶች", 50.0, 10, false),
        PricingPackage("pkg_monthly", "የወር የጥበብ ማህደር", "1 ወር ያልተገደበ", 150.0, 50, false),
        PricingPackage("pkg_vip", "የጥንተ ጥበብ ዘለቄታ (VIP)", "የዕድሜ ልክ ሙሉ ፍቃድ", 350.0, 999, true)
    )

    suspend fun processPayment(bank: BankOption, pkg: PricingPackage, refInput: String): TransactionEntity {
        val ref = refInput.ifBlank { "${bank.code}-${(10000000..99999999).random()}" }
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
