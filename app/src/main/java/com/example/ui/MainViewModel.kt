package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class Screen {
    REGISTRATION,
    HOME,
    ZODIAC,
    BAHIRE_HASAB,
    LOVE_HARMONY,
    HEALING,
    ORACLE,
    ENOCH,
    PAYMENT,
    HISTORY,
    PROFILE
}

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = AppDatabase.getDatabase(application)
    private val appDao = database.appDao()
    val paymentRepo = PaymentRepository(appDao)
    val voiceManager = VoiceSpeechManager(application)

    val userProfile: StateFlow<UserProfileEntity?> = appDao.getUserProfile()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val consultations: StateFlow<List<ConsultationRecordEntity>> = appDao.getAllConsultations()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val transactions: StateFlow<List<TransactionEntity>> = paymentRepo.allTransactions
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _screenStack = MutableStateFlow(listOf(Screen.HOME))
    val currentScreen: StateFlow<Screen> = _screenStack.map { it.lastOrNull() ?: Screen.HOME }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Screen.HOME)

    private val _selectedLanguage = MutableStateFlow("am")
    val selectedLanguage: StateFlow<String> = _selectedLanguage.asStateFlow()

    // Zodiac calculation state
    private val _zodiacResult = MutableStateFlow<ZodiacCalculationResult?>(null)
    val zodiacResult: StateFlow<ZodiacCalculationResult?> = _zodiacResult.asStateFlow()

    // Bahire Hasab state
    private val _bahireHasabResult = MutableStateFlow(BahireHasabEngine.calculate(2017))
    val bahireHasabResult: StateFlow<EthiopianDateResult> = _bahireHasabResult.asStateFlow()

    // Love Harmony state
    private val _loveHarmonyResult = MutableStateFlow<LoveHarmonyResult?>(null)
    val loveHarmonyResult: StateFlow<LoveHarmonyResult?> = _loveHarmonyResult.asStateFlow()

    // Oracle response state
    private val _oracleResponse = MutableStateFlow<OracleResponse?>(null)
    val oracleResponse: StateFlow<OracleResponse?> = _oracleResponse.asStateFlow()

    // Healing search query
    private val _herbQuery = MutableStateFlow("")
    val herbQuery: StateFlow<String> = _herbQuery.asStateFlow()

    val filteredHerbs: StateFlow<List<HealingHerb>> = _herbQuery.map { query ->
        HealingEngine.searchHerbs(query)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HealingEngine.herbs)

    // Snackbar or feedback toast
    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage: SharedFlow<String> = _toastMessage.asSharedFlow()

    init {
        viewModelScope.launch {
            // Check if profile exists; if not, navigate to Registration
            val profile = appDao.getUserProfileOnce()
            if (profile == null || !profile.isRegistered) {
                _screenStack.value = listOf(Screen.REGISTRATION)
            } else {
                _selectedLanguage.value = profile.preferredLanguage
            }
        }
    }

    fun navigateTo(screen: Screen) {
        val current = _screenStack.value
        if (current.lastOrNull() != screen) {
            _screenStack.value = current + screen
        }
    }

    fun navigateBack(): Boolean {
        val current = _screenStack.value
        return if (current.size > 1) {
            _screenStack.value = current.dropLast(1)
            true
        } else {
            false
        }
    }

    fun setLanguage(lang: String) {
        _selectedLanguage.value = lang
        viewModelScope.launch {
            appDao.setLanguage(lang)
        }
    }

    fun registerUser(fullName: String, phone: String, gender: String, password: String) {
        if (fullName.isBlank() || phone.isBlank() || password.isBlank()) {
            emitToast("እባክዎ ሙሉ ስም፣ ስልክ ቁጥር እና የይለፍ ቃል ያስገቡ")
            return
        }

        viewModelScope.launch {
            val profile = UserProfileEntity(
                id = 1,
                fullName = fullName.trim(),
                phoneNumber = phone.trim(),
                gender = gender,
                passwordHash = password,
                isRegistered = true,
                isPremium = false,
                credits = 10,
                preferredLanguage = _selectedLanguage.value
            )
            appDao.saveUserProfile(profile)
            _screenStack.value = listOf(Screen.HOME)
            emitToast("እንኳን ወደ ጥንተ ጥበብ በደህና መጡ!")
        }
    }

    fun calculateZodiac(seekerName: String, motherName: String) {
        if (seekerName.isBlank() || motherName.isBlank()) {
            emitToast("የጠያቂውን ስም እና የእናት ስም ያስገቡ")
            return
        }
        val result = ZodiacEngine.calculateZodiac(seekerName, motherName)
        _zodiacResult.value = result

        // Save consultation
        viewModelScope.launch {
            appDao.deductCredit()
            appDao.insertConsultation(
                ConsultationRecordEntity(
                    category = "ኮከብ ቆጠራ",
                    title = "ኮከብ፡ ${result.sign.nameAmharic}",
                    seekerName = seekerName,
                    motherName = motherName,
                    details = "ቁጥር ቀመር፡ ${result.totalScore} | ንጥረ ነገር፡ ${result.sign.element}",
                    geezScript = result.sign.ancientPrayer,
                    translation = result.sign.personality,
                    prescription = "ቀለም፡ ${result.sign.luckyColor} | መልአክ፡ ${result.sign.angel}"
                )
            )
        }
    }

    fun calculateBahireHasab(year: Int) {
        _bahireHasabResult.value = BahireHasabEngine.calculate(year)
    }

    fun calculateLoveHarmony(input: LoveHarmonyInput) {
        if (input.seekerName.isBlank() || input.targetName.isBlank() || input.seekerMotherName.isBlank()) {
            emitToast("እባክዎ የተሟላ ስሞችን ያስገቡ")
            return
        }
        val result = LoveHarmonyEngine.generateHarmony(input)
        _loveHarmonyResult.value = result

        viewModelScope.launch {
            appDao.deductCredit()
            appDao.insertConsultation(
                ConsultationRecordEntity(
                    category = "መስተፋቅር",
                    title = "የፍቅር ጥምረት፡ ${input.seekerName} እና ${input.targetName}",
                    seekerName = input.seekerName,
                    motherName = input.seekerMotherName,
                    targetName = input.targetName,
                    details = "ስምምነት፡ ${result.compatibilityPercentage}% | ዓላማ፡ ${input.intentionType}",
                    geezScript = result.geezFormulaText,
                    translation = result.amharicMeaning,
                    prescription = result.naturalMaterialsNeeded.joinToString(", ")
                )
            )
        }
    }

    fun consultOracle(query: OracleQuery) {
        if (query.seekerName.isBlank()) {
            emitToast("እባክዎ ስምዎን ያስገቡ")
            return
        }
        val result = OracleEngine.consultOracle(query)
        _oracleResponse.value = result

        viewModelScope.launch {
            appDao.deductCredit()
            appDao.insertConsultation(
                ConsultationRecordEntity(
                    category = query.category,
                    title = query.category,
                    seekerName = query.seekerName,
                    motherName = query.motherName,
                    targetName = query.detailsOrTarget,
                    details = query.specificNeed,
                    geezScript = result.geezScripture,
                    translation = result.amharicExplanation,
                    prescription = result.sacredPrescription.joinToString(" • ")
                )
            )
        }
    }

    fun setHerbQuery(query: String) {
        _herbQuery.value = query
    }

    fun makePayment(bank: BankOption, pkg: PricingPackage, ref: String) {
        viewModelScope.launch {
            val tx = paymentRepo.processPayment(bank, pkg, ref)
            emitToast("ክፍያው ተረጋግጧል! ማመሳከሪያ ቁጥር፡ ${tx.transactionRef}")
        }
    }

    fun deleteConsultation(id: Long) {
        viewModelScope.launch {
            appDao.deleteConsultation(id)
        }
    }

    fun toggleFavorite(id: Long) {
        viewModelScope.launch {
            appDao.toggleFavorite(id)
        }
    }

    fun speak(text: String) {
        voiceManager.speak(text)
    }

    fun stopSpeaking() {
        voiceManager.stop()
    }

    private fun emitToast(msg: String) {
        viewModelScope.launch {
            _toastMessage.emit(msg)
        }
    }

    override fun onCleared() {
        super.onCleared()
        voiceManager.shutdown()
    }
}
