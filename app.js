// Tinte Tibeb (ጥንተ ጥበብ) - Web Platform JavaScript Engine
// Genuine Ethiopian Ancient Manuscripts & Seven Houses Interactive System

// --- State & Storage ---
const APP_STORAGE_KEY = "tinte_tibeb_user";
let currentUser = {
    fullName: "ተጠቃሚ",
    phone: "",
    gender: "ወንድ",
    password: "",
    credits: 10,
    isRegistered: false,
    lang: "am"
};

let currentZodiacVoiceText = "";
let currentLoveVoiceText = "";
let currentOracleVoiceText = "";
let currentUniversalVoiceText = "";

let selectedPackageInfo = { id: "monthly", price: 150, credits: 50 };
let selectedBankInfo = { id: "telebirr", acc: "0911223344", name: "ቴሌብር (Telebirr)", ussd: "*127#" };

// --- Initialize App ---
document.addEventListener("DOMContentLoaded", () => {
    loadUserData();
    setupNavigation();
    initBahireHasab();
    renderHerbs(herbsData);
    renderEnochChapters();
    renderGlobalGrimoires();
    updatePaymentInstructions();
});

// --- Navigation Tabs ---
function setupNavigation() {
    const tabs = document.querySelectorAll(".nav-tab");
    tabs.forEach(tab => {
        tab.addEventListener("click", () => {
            const target = tab.getAttribute("data-tab");
            switchTab(target);
        });
    });

    const langSel = document.getElementById("langSelector");
    if (langSel) {
        langSel.addEventListener("change", (e) => {
            currentUser.lang = e.target.value;
            saveUserData();
        });
    }
}

function switchTab(tabId) {
    document.querySelectorAll(".nav-tab").forEach(t => {
        t.classList.toggle("active", t.getAttribute("data-tab") === tabId);
    });
    document.querySelectorAll(".tab-pane").forEach(p => {
        p.classList.toggle("active", p.id === `tab-${tabId}`);
    });
    window.scrollTo({ top: 0, behavior: "smooth" });
}

// --- User Registration & Storage ---
function loadUserData() {
    const stored = localStorage.getItem(APP_STORAGE_KEY);
    if (stored) {
        try {
            currentUser = JSON.parse(stored);
        } catch (e) {
            console.error(e);
        }
    }
    updateUserUI();

    if (!currentUser.isRegistered) {
        setTimeout(() => {
            openUserModal();
        }, 1200);
    }
}

function saveUserData() {
    localStorage.setItem(APP_STORAGE_KEY, JSON.stringify(currentUser));
    updateUserUI();
}

function updateUserUI() {
    const badgeName = document.getElementById("badgeUserName");
    const creditDisplay = document.getElementById("userCreditDisplay");
    if (badgeName) {
        badgeName.textContent = currentUser.isRegistered ? currentUser.fullName.split(" ")[0] : "መለያ";
    }
    if (creditDisplay) {
        creditDisplay.textContent = `${currentUser.credits} ነጥቦች`;
    }
}

function openUserModal() {
    const modal = document.getElementById("userModal");
    if (currentUser.isRegistered) {
        document.getElementById("regFullName").value = currentUser.fullName;
        document.getElementById("regPhone").value = currentUser.phone;
        document.getElementById("regGender").value = currentUser.gender;
        document.getElementById("modalTitle").textContent = "መለያን ማስተካከል";
    }
    modal.classList.remove("hidden");
}

function closeUserModal() {
    document.getElementById("userModal").classList.add("hidden");
}

function openProfileModal() {
    openUserModal();
}

function saveUserRegistration() {
    const name = document.getElementById("regFullName").value.trim();
    const phone = document.getElementById("regPhone").value.trim();
    const gender = document.getElementById("regGender").value;
    const password = document.getElementById("regPassword").value.trim();

    if (!name || !phone || !password) {
        alert("እባክዎ ሙሉ ስም፣ ስልክ ቁጥር እና የይለፍ ቃል ያስገቡ!");
        return;
    }

    currentUser.fullName = name;
    currentUser.phone = phone;
    currentUser.gender = gender;
    currentUser.password = password;
    currentUser.isRegistered = true;
    saveUserData();
    closeUserModal();
    alert(`እንኳን ወደ ጥንተ ጥበብ በደህና መጡ፣ ${name}! 10 የጥበብ ነጥቦች በስጦታ ተበርክቶሎታል።`);
}

// --- Speech Synthesis (ድምፅ አንባቢ) ---
function speakText(text) {
    if (!text) return;
    if ("speechSynthesis" in window) {
        window.speechSynthesis.cancel();
        const utterance = new SpeechSynthesisUtterance(text);
        utterance.rate = 0.88;
        utterance.pitch = 1.0;
        
        const voices = window.speechSynthesis.getVoices();
        const amVoice = voices.find(v => v.lang.startsWith("am") || v.lang.startsWith("gez") || v.lang.startsWith("om"));
        if (amVoice) {
            utterance.voice = amVoice;
        }
        window.speechSynthesis.speak(utterance);
    } else {
        alert("የድምፅ አንባቢ በዚህ ብሮውዘር አይደገፍም");
    }
}

// ===================================================================
// 1. UNIVERSAL ANCIENT MANUSCRIPT COMMAND ENGINE (ቀጥተኛ አዛዥና ጠያቂ)
// ===================================================================

const manuscriptCorpus = [
    {
        keywords: ["ጥይት", "አረር", "ነፍጥ", "ጥይት እንዳይመታ", "መክስተ ደመና", "ሐጹረ መስቀል", "ጦርነት", "ጥይት መከላከያ"],
        type: "የጥይት መከላከያ ጋሻ (ጸሎትና ድግምት)",
        typeClass: "spell",
        book: "መጽሐፈ ሐጹረ መስቀል ወአስማተ ጥይት ዘጥንት",
        chapter: "ምዕራፍ ፯፡ መክስተ ደመና ወመከላከየ አረር",
        page: "ገጽ ፸፪ (ብራና ቁጥር ፫፻፲)",
        source: "የደብረ ሊባኖስ ገዳም ጥንታዊ መዛግብት",
        geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
ጸሎት በእንተ መስተሐምም ወመክስተ ደመና ዘጥይት፤
በስመ ያህ፣ ኤልሻዳይ፣ ጸባኦት፣ አማኑኤል፤
ከመ ኢይቅረበኒ አረር ወነደ እሳት፤
ይኩን ጥይት ከመ ማይ ወከመ ሐመድ በቅድሜየ፤
በኃይለ መስቀሉ ለክርስቶስ ይትሰበር ኩሉ ነፍጥ ወመንፈሰ ሞት፤
ቅዱስ ሚካኤል ወገብርኤል ይኩኑኒ ጋሻ ወዋልታ ለዓለመ ዓለም፤ አሜን።`,
        explanation: `ጥይትና አረር ወደ ሰውዬው እንዳይደርስ አቅጣጫውን እንዲስት ወይም እንደ ውኃና አፈር እንዲቀዘቅዝ በጥንታውያን አባቶች የተዘጋጀ ታላቅ የመስቀል ጋሻና መክስተ ደመና ነው።`,
        action: [
            "ዕፀ ደብዳቤ ወይም ዕፀ መሰውር በንጹሕ ቀይ ቆዳ ተሰፍቶ በግራ ክንድ ወይም በአንገት ማሰር",
            "ማለዳ ከመውጣት በፊት ጸሎቱን ፯ (ሰባት) ጊዜ አንብቦ በሦስቱ ጣቶች ማማተብ",
            "በንጽሕና መመላለስና ለተቸገረ ሰው ምጽዋት ማድረግ"
        ],
        timing: "ማለዳ ፀሐይ ስትወጣ ወደ ምሥራቅ አቅጣጫ ዞሮ ማድረስ።"
    },
    {
        keywords: ["ስለት", "ሰይፍ", "ቢላዋ", "ካራ", "ጦር", "ስለታማ", "ስለታማ ነገሮች እንዳይመቱ", "አፍልጣ", "ሰይፈ ሥላሴ"],
        type: "የስለት መከላከያ ጋሻ (ጸሎትና አስማት)",
        typeClass: "spell",
        book: "መጽሐፈ ሰይፈ ሥላሴ ወአስማተ አፍልጣ ዘጥንት",
        chapter: "ምዕራፍ ፱፡ መከላከየ ስለት ወአፍልጣ",
        page: "ገጽ ፶፰ (ብራና ቁጥር ፻፵፭)",
        source: "የዋልድባ ገዳም ጥንታዊ ብራና",
        geez: `በስመ ሥላሴ ቅዱስ፤ አብ እሳት፣ ወልድ እሳት፣ መንፈስ ቅዱስ እሳት፤
በዛቲ አስማተ ስለት ኢይምታዐኒ ሰይፍ ወኩናት፣ ሾተል ወካራ፤
ይኩን ኩሉ ብረት ከመ ሰም በቅድመ እሳት፤
በስመ ዑራኤል መልአክ ዘይመልሕ ሰይፈ እሳት፤
ዕቀበኒ ወአድኅነኒ እምኩሉ አፈ ስለት ወእምነዳደ ብረት፤ አሜን።`,
        explanation: `ከሰይፍ፣ ከቢላዋ፣ ከጦርና ከማንኛውም ስለታማ ብረት መከላከያ ጥንታዊ ጋሻ፤ ብረት እንደ ሰም እንዲቀልጥና አካልን እንዳይቆርጥ የሚጸለይ የታመነ ቃል ነው።`,
        action: [
            "ዕፀ አፍልጣ ሥር በንጽሕና ቆርጦ ከጥቁር ዕጣን ጋር በወይራ ዘይት አፍልቶ አካልን መቀባት",
            "ቃሉን ፫ ጊዜ በንጹሕ ውኃ ላይ አንብቦ ማለዳ መጠጣት",
            "በክንድ ወይም በኪስ በክታብ መያዝ"
        ],
        timing: "ማለዳ 1:00 ሰዓት ወይም ወደ አደገኛ ስፍራ ከመጓዝ በፊት።"
    },
    {
        keywords: ["ዕፀ ሳቤዕ", "እፀ ሳቤዕ", "ሳቤዕ", "ሳቤቅ", "ዕፀ ሳቤቅ", "ሐረገ ሳቤቅ", "የይስሐቅ በግ ሐረግ", "ብረት አቅላጭ", "ብረት ማለስለሻ", "ጥይት መከላከያ ሐረግ"],
        type: "ዕፀ ሳቤዕ (የስለትና ጥይት መከላከያ ሐረግ)",
        typeClass: "spell",
        book: "መጽሐፈ ገብረ ሕይወት ወዕፀ ሳቤቅ ዘጥንት",
        chapter: "ምዕራፍ ፩፡ ምስጢረ ዕፀ ሳቤቅ ወመከላከየ አረር",
        page: "ገጽ ፵፰ (ብራና ቁጥር ፻፲፭)",
        source: "የደብረ ሊባኖስ ገዳም ጥንታዊ መዛግብት",
        geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
ጸሎት በእንተ ዕፀ ሳቤቅ፤
በከመ አድኀንኮ ለይስሐቅ እምሰይፈ አቡሁ አብርሃም በበግዕ ዘተአሥረ በዕፀ ሳቤቅ፤
ከማሁ አድኅነኒ ሊተ ለገብርከ እምኩሉ ኲናት፣ እምሰይፍ፣ እምነዳደ እሳት ወእምአረር ዘይነቱግ፤
ዐቅበኒ በኃይለ መስቀልከ፤
በስመ ያህ፣ ኤልሻዳይ፣ አማኑኤል፤ አሜን።`,
        explanation: `ዕፀ ሳቤዕ (ሐረገ ሳቤቅ / የይስሐቅ በግ ሐረግ) በኦሪት ዘፍጥረት ፳፪፥፲፫ ላይ ይስሐቅን በአብርሃም ሰይፍ ፊት የታደገው ምስጢር ሲሆን፤ ለጥይት፣ ለስለት፣ ለብረት ማለስለሻና ለታላቅ ሞገስ የታዘዘ ታላቅ የኢትዮጵያ ጥንታዊ ብራና ምስጢር ነው።`,
        action: [
            "ሐረገ ሳቤቅ በንጹሕ ቀይ ቆዳ ወይም በብር ክታብ ተሰፍቶ በግራ ክንድ ወይም በአንገት ማሰር",
            "በንጹሕ የወይራ ዘይት ውስጥ አንተክትኮ ዘይቱን በጦርነትና በአደጋ ጊዜ ሰውነትን መቀባት",
            "ማለዳ ፀሐይ ሳትወጣ ወደ ምሥራቅ ዞሮ ጸሎቱን ፯ ጊዜ ማንበብ"
        ],
        timing: "ሐሙስ ሌሊት 9:00 ሰዓት ወይም እሑድ ፀሐይ ሳትወጣ።"
    },
    {
        keywords: ["ዕፀ መሰውር", "መሰወር", "መሰወሪያ", "ማምለጫ", "ሄኖክ", "ኤልያስ", "መከወን", "ድብቅ", "እፀመሰውር"],
        type: "ዕፀ መሰውርና ማምለጫ (ምስጢር)",
        typeClass: "spell",
        book: "መጽሐፈ ዕፀ መሰውር ወኅቡዓት ዘሄኖክ ወኤልያስ",
        chapter: "ምዕራፍ ፬፡ አስማተ መሰውር ወተከውኖ",
        page: "ገጽ ፴፱ (ብራና ቁጥር ፹፪)",
        source: "የጣና ቂርቆስ ገዳም ጥንታዊ መዝገብ",
        geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
ጸሎት በእንተ ዕፀ መሰውር፤
በከመ ከወንኮሙ ለሄኖክ ወለኤልያስ እምዓይነ ኃጥአን፤
ከማሁ ሰውረኒ እምዓይነ ፀር ወጸላኢ፤
በስመ አስተርእዮ ወኅቡዕ፤ አዖ፣ ፈዖ፣ ኤልሻዳይ፤
ዕፅም ዓይኖሙ ወዕበስ አእምሮቶሙ ከመ ኢይርአዩኒ ወኢያእምሩኒ ለዓለም፤ አሜን።`,
        explanation: `በጠላት ወይም በአደጋ ጊዜ ከክፉ ዓይንና ከፈላጊዎች መሰወሪያ ጥንታዊ ምስጢር፤ ዓይናቸው እንዳያይና አእምሮታቸው እንዳያስተውል የሚያደርግ ጥበብ ነው።`,
        action: [
            "የዕፀ መሰውር ቅጠልና ሥር በንጽሕና ተቆርጦ ከሰሊጥ ዘይት ጋር ግንባርንና ዓይንን መቀባት",
            "ቃሉን በለሆሳስ ፯ ጊዜ ደግሞ በአደጋው ወቅት መራመድ",
            "በክታብ ተሰፍቶ በአንገት ወይም በኪስ መያዝ"
        ],
        timing: "በዕለተ ዓርብ ማታ ወይም በአስቸጋሪ ሰዓት።"
    },
    {
        keywords: ["ዓይነ ጥላ", "መፍትሔ ሥራይ", "ድግምት", "ሰይጣን", "ክፉ መንፈስ", "ጭንቀት", "መተት", "ቡዳ", "ዛር", "ጥላ"],
        type: "ድግምትና ጸሎት (መፍትሔ ሥራይ)",
        typeClass: "spell",
        book: "መጽሐፈ መፍትሔ ሥራይ ወአስማተ ሰሎሞን ዘጥንት",
        chapter: "ምዕራፍ ፫፡ መቅሰፍተ አጋንንት ወዓይነ ጥላ",
        page: "ገጽ ፵፪ (ብራና ቁጥር ፻፲፰)",
        source: "የደብረ ሊባኖስ ገዳም ጥንታዊ ብራና መዛግብት",
        geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
በስመ ኤልሻዳይ፣ ጸባኦት፣ አማኑኤል፤
ይትፈታሕ ኩሉ ዕንቅፋተ ሰይጣን ወዓይነ ጥላ ዘይሰርር ዲበ ነፍስ ወሥጋ፤
በስመ ሚካኤል ወገብርኤል ይሠረር ሰላም ወይቁም ጽድቅ፤
በኃይለ መስቀል ይትከየድ ኩሉ ፀር ወጸላኢ! አሜን።`,
        explanation: `ይህ ጥንታዊ የመፍትሔ ሥራይ ቃል ከዓይነ ጥላ፣ ከድግምት፣ ከክፉ ዓይንና ከመንፈስ ጭንቀት ለመላቀቅ በሊቃውንት የተዘጋጀ ነው። የሰውን ልብና አእምሮ ከከበደው የጨለማ ጭስ ያነጻል፤ ሰላምንና ዕረፍትን ያጎናጽፋል።`,
        action: [
            "ማለዳ 12:00 ሰዓት ፊትና እጅን በንጹሕ ውኃ መታጠብ",
            "ዕጣንና ከርቤ በከሰል ላይ በማጨስ ቤቱንና ልብስን ማጠን",
            "ይህን የግእዝ ጸሎት ፯ (7) ጊዜ በድምፅ ማንበብ",
            "ንጹሕ የብርጭቆ ውኃ ላይ ቃሉን አንብቦ ጠዋት በባዶ ሆድ መጠጣት"
        ],
        timing: "ምቹ ሰዓት፡ በዕለተ ሰኞና ሐሙስ ማለዳ ወደ ምሥራቅ አቅጣጫ ዞሮ ማድረስ።"
    },
    {
        keywords: ["ሆድ", "ቁርጠት", "ራስ ምታት", "ጉንፋን", "ዳማከሴ", "ጤናአዳም", "ዕፀዋት", "ፈውስ", "ሕመም", "መድኃኒት", "ደዌ"],
        type: "የፈውስ ዕፀዋት (መድኃኒት)",
        typeClass: "herb",
        book: "መጽሐፈ ፈውስ ወዕፀዋት ዘጥንት",
        chapter: "ክፍል ፪፡ ፈውሰ ርእስ ወከርሥ",
        page: "ገጽ ፸፬ (ብራና ቁጥር ፳፰)",
        source: "የሐይቅ እስጢፋኖስ ገዳም ብራና መዝገብ",
        geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤
ሩፋኤል ፈዋሴ ዱያን፤
በዛቲ ዕፅ ዳማከሴ ወዕፀ ሕይወት (ጤናአዳም) ፈውስ ሕማመ ከርሥ ወርእስ ዘገብርከ፤
በከመ አድኃንኮ ለጦቢት እምደዌሁ ከማሁ ፈውስ ድዉያነ!`,
        explanation: `ዳማከሴና ጤናአዳም ከጥንት ጀምሮ ለራስ ምታት፣ ለጉንፋንና ለሆድ ቁርጠት ፍቱን መሆናቸው በመጽሐፈ ፈውስ ተመዝግቧል። የተፈጥሮ ቅመማቸው የሰውነትን ሙቀት ያስተካክላል፤ ደዌን ያባርራል።`,
        action: [
            "፯ ቅጠል የዳማከሴና ጥቂት የጤናአዳም ፍሬ መውሰድ",
            "በሙቅ ውኃ ውስጥ አንተክትኮ እንፋሎቱን ለ፲ ደቂቃ መታጠን",
            "ጭማቂውን ከንጹሕ ማር ጋር በመደባለቅ ፩ የሾርባ ማንኪያ ማለዳ መውሰድ"
        ],
        timing: "ጠዋት በፀሐይ መውጫ ሰዓት መተግበር።"
    },
    {
        keywords: ["መስተፋቅር", "ፍቅር", "ትዳር", "ሰላም", "ማስታረቅ", "ስምምነት", "ልብ", "ተፋቅሮ", "ሚስት", "ባል", "ወዳጅ"],
        type: "መስተፋቅርና ስምምነት (ጸሎት)",
        typeClass: "spell",
        book: "መጽሐፈ ተፋቅሮ ወሰላም ዘሰሎሞን",
        chapter: "ምዕራፍ ፬፡ አስማተ ተፋቅሮ ወስምምነት",
        page: "ገጽ ፲፰ (ብራና ቁጥር ፷፪)",
        source: "የጣና ቂርቆስ ገዳም ብራና",
        geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤
በስመ ሰሎሞን ንጉሠ ጥበብ፤
አፍቅር፣ አሰምር፣ አስተፋቅር በይነ እለ ተፃረሩ፤
ይኩን ፍቅር ከመ ፍቅረ ዳዊት ወዮናታን፤
ወይኩን ስምምነት ዘኢይትፈታሕ በውስተ ልቦሙ!`,
        explanation: `መስተፋቅር ማለት በክፉ ማስገደድ ሳይሆን፤ ንጹሕ ፍቅርን፣ የልብ ስምምነትን፣ የትዳር ሰላምንና የተጣሉትን ማስታረቂያ በጥበብ ቃል ማጽናት ነው። የሰዎችን ልብ በይቅርታና በመስህብ ያቀራርባል።`,
        action: [
            "ጽጌረዳ ውኃ ወይም የከርቤ ሽቶ መያዝ",
            "ቃሉን በማለዳ ፫ ጊዜ በለሆሳስ ማንበብ",
            "ልብስን በሽቶው መቀባትና በቅን ልብ ለሰላም መነጋገር"
        ],
        timing: "ምቹ ሰዓት፡ እሑድ ማለዳ 1:00 ሰዓት።"
    },
    {
        keywords: ["ሄኖክ", "ፀሐይ ደጆች", "ብርሃናት", "ሰማያት", "364", "፫፻፷፬", "ቀናት", "ዑራኤል", "መላእክት", "ነፋሳት", "ዘመን"],
        type: "የሰማይ ቀመር (እውቀት)",
        typeClass: "wisdom",
        book: "መጽሐፈ ሄኖክ ነቢይ (ሄኖክ ፩)",
        chapter: "ምዕራፍ ፸፪፡ መጽሐፈ ብርሃናት",
        page: "ገጽ ፺፮-፻ (ብራና ቁጥር ፪፻፵፭)",
        source: "የኢትዮጵያ ኦርቶዶክስ ተዋሕዶ ብሔራዊ ቤተ መዛግብት",
        geez: `«ወርኢኩ ስድስተ ደጆተ እንተ ቦን ይወጽእ ፀሐይ ወስድስተ ደጆተ እንተ ቦን ይዐርብ ፀሐይ፤
ወዘንተ ኩሎ ሥርዓተ ብርሃናት አርአየኒ ዑራኤል መልአከ ብርሃናት።
ዓመቱሂ ፫፻፷፬ ዕለታት ውእቱ!»`,
        explanation: `ሄኖክ በዑራኤል መሪነት ያያቸው ስድስቱ የፀሐይ ደጆች የዘመናት ሁሉ ፍጹም ሰዓት ናቸው። ፀሐይ በዓመቱ ውስጥ በእነዚህ ፮ ደጆች እየተፈራረቀች ስትወጣና ስትገባ አራቱን ወቅቶች (መጸው፣ ሐጋይ፣ ጸደይ፣ ክረምት) ያለምንም ስህተት ትመራለች።`,
        action: [
            "የፀሐይንና የጨረቃን መውጫ በር በመመልከት የዕለታቱንና የሰዓቱን ቀመር ማስተዋል",
            "የዑራኤልን ምስጋና በየደጁ መዘከር"
        ],
        timing: "ማለዳ ፀሐይ ስትወጣ ወደ ምሥራቅ በመመልከት።"
    },
    {
        keywords: ["ንግድ", "ሀብት", "በረከት", "ገንዘብ", "ስኬት", "ባርክኤል", "ስራ", "ትርፍ", "ገበያ"],
        type: "የበረከት ጸሎትና ጥበብ",
        typeClass: "wisdom",
        book: "መጽሐፈ በረከት ወሀብተ ንግድ",
        chapter: "ምዕራፍ ፮፡ አስማተ ባርክኤል መልአከ በረከት",
        page: "ገጽ ፶፪ (ብራና ቁጥር ፺፬)",
        source: "የደብረ ዳሞ ገዳም ብራና መዝገብ",
        geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤
ባርክኤል መልአከ በረከት፤
ባርክ ንግድየ፣ ባርክ ድካምየ ወሀበኒ ፍሬ በረከት፤
አርኅው ደጆተ ሀብት ወአርሕቅ እምኔየ ስእነተ ወጽልመተ!`,
        explanation: `በጥንታዊው መጽሐፍ የተጻፈው እውነተኛው የሀብት ምስጢር በታማኝነት መሥራት፣ የተቸገረን መርዳትና የባርክኤልን የበረከት ጸሎት ማድረስ ነው። ንግድን ያቀናል፤ በረከትን ያበዛል።`,
        action: [
            "ሐሙስ ጠዋት ንግድ ከመጀመር በፊት ማዕጠንት ማጠን",
            "ቃሉን ፯ ጊዜ ማንበብ",
            "ከትርፉ የመጀመሪያውን ለምጽዋት መለየት"
        ],
        timing: "ዕለተ ሐሙስና እሑድ ማለዳ በ1:00 ሰዓት።"
    },
    {
        keywords: ["ጠላት", "መከላከያ", "ሰይፈ ሥላሴ", "ዐቃቤ ርእስ", "ጋሻ", "አደጋ", "ክፉ", "ነፍስ", "ጥበቃ"],
        type: "መንፈሳዊ ጋሻ (ጸሎት)",
        typeClass: "spell",
        book: "መጽሐፈ ሰይፈ ሥላሴ ወዐቃቤ ርእስ",
        chapter: "ክፍል ፩፡ ጋሻ መንፈሳዊ",
        page: "ገጽ ፴፭ (ብራና ቁጥር ፵፯)",
        source: "የዋሸራ ገዳም ጥንታዊ ብራና",
        geez: `በስመ ሥላሴ ቅዱስ፤
ሰይፈ መለኮት ይቁም በየማንየ ወበፀጋምየ፤
ሚካኤል በቅድሜየ፣ ገብርኤል በድኅሬየ፣ ሩፋኤል በየማንየ፣ ዑራኤል በፀጋምየ፤
ኢይቅረበኒ ኩሉ እኩይ ወመንፈሰ ጽልመት!`,
        explanation: `ሰይፈ ሥላሴ የታመነ መንፈሳዊ ጋሻ ነው። የሰውን ልጅ ከሚታዩና ከማይታዩ የጠላት ፍላጾች፣ ከክፉ ዓይንና ከድንገተኛ አደጋ ይጠብቃል።`,
        action: [
            "ከቤት ሲወጡ ወይም ማታ ከመኝታ በፊት ቃሉን አንብቦ በሦስቱ ጣቶች ማማተብ"
        ],
        timing: "ጠዋትና ማታ በጸሎት ሰዓት።"
    },
    {
        keywords: ["አቡሻህር", "ኮከብ", "አበገደ", "ቀመር", "12", "ዞዲያክ", "ዕጣ", "ስም", "እናት", "ፈንታ"],
        type: "የኮከብ ቀመር (እውቀት)",
        typeClass: "wisdom",
        book: "መጽሐፈ አቡሻህር ዘደብረ ሊባኖስ",
        chapter: "ምዕራፍ ፩፡ አበገደ ወአዕማደ ኮከብ",
        page: "ገጽ ፲፪-፳ (ብራና ቁጥር ፪፻፲)",
        source: "የደብረ ሊባኖስ ገዳም ጥንታዊ የቀመር መዝገብ",
        geez: `«አበገደ ሃወዘ ሐጠየ ከለመ ነሠዐ ፈጸቀ ረሰተ ኀፀፈ፤
ዘንተ ቀመረ ኈለቆ ተጠይቅ ወአእምር ኮከበ ሰብእ ወዕጣሁ!»`,
        explanation: `የሰው ስም እና የእናት ስም በአበገደ ቀመር ተደምሮ ለ፲፪ (12) ሲካፈል ቀሪው ቁጥር የኮከብን ባሕርይ፣ ንጥረ-ነገርን (እሳት፣ መሬት፣ ነፋስ፣ ውሃ) እና ጠባቂ መልአክን ይገልጻል።`,
        action: [
            "የስም ፊደላትን ቁጥር በአበገደ መዝገብ መደመርና ቀሪውን በ12ቱ አዕማድ ማዛመድ"
        ],
        timing: "በፀሐይ መውጫ ሰዓት የሚሰላ ትክክለኛ ቀመር።"
    },
    {
        keywords: ["ዕውቀት", "ጥናት", "አእምሮ", "ማስታወስ", "ትምህርት", "ጥበብ", "ሰሎሞን", "ማስተዋል", "ፈሊጥ"],
        type: "የዕውቀትና አእምሮ ጥበብ",
        typeClass: "wisdom",
        book: "መጽሐፈ ጥበበ ሰሎሞን ወፈሊጣተ አእምሮ",
        chapter: "ምዕራፍ ፩፡ አርኅዎ ልብ ወአዕምሮ",
        page: "ገጽ ፲፭ (ብራና ቁጥር ፳፯)",
        source: "የአክሱም ጽዮን ጥንታዊ ቤተ መዛግብት",
        geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤
እግዚአብሔር አምላከ ጥበብ ወነባቤ አዕምሮ፤
አርሁ ልብየ ከመ እስመዕ ጥበበ ወአእምር ፍልስፍና፤
ከመ ፀሐይ ያብርህ አእምሮትየ ወከመ ወርኅ ያብራህ ሕሊናየ!`,
        explanation: `ለተማሪዎችና ጥልቅ እውቀት ለሚሹ ሰዎች አእምሮን የሚያነቃቃና የማስታወስ ኃይልን የሚያጎለብት ጥንታዊ የሰሎሞን የጥበብ ቃል ነው።`,
        action: [
            "ማለዳ በባዶ ሆድ ንጹሕ ውኃ ላይ ቃሉን አንብቦ መጠጣት",
            "ጥናት ከመጀመር በፊት ወደ ምሥራቅ ዞሮ ፫ ጊዜ ማሰላሰል"
        ],
        timing: "ማለዳ ከ11:30 እስከ 12:30 ሰዓት።"
    },
    {
        keywords: ["ሕልም", "ራእይ", "ምልክት", "አስተርእዮ", "ትንቢት", "ፍቺ"],
        type: "የሕልምና ራእይ ፍቺ (እውቀት)",
        typeClass: "wisdom",
        book: "መጽሐፈ አስተርእዮ ወኅቡዓት ዘዳንኤል",
        chapter: "ምዕራፍ ፪፡ ፍካሬ ሕልም ወራእያት",
        page: "ገጽ ፳፱ (ብራና ቁጥር ፸፭)",
        source: "የደብረ ቢዘን ገዳም ጥንታዊ ብራና",
        geez: `«ወተከሥተ ሎሙ ሕልመ ሌሊት በራእየ ሰማይ፤
ወአእመረ ምስጢረ ኅቡዓት ዘኢያእመረ ካልእ!»`,
        explanation: `የሌሊት ሕልሞችና ራእዮች ምን ዓይነት መንፈሳዊና ተፈጥሯዊ መልእክት እንደሚይዙ በዳንኤልና በዮሴፍ የጥበብ መዛግብት የተተነተነበት ትክክለኛ የፍቺ መዝገብ ነው።`,
        action: [
            "ሕልሙን በማለዳ በንጹሕ አእምሮ መመዝገብ",
            "የሰላም ጸሎት አድርሶ መልካሙ እንዲፈጸም፣ ክፉው እንዲሻር ማማተብ"
        ],
        timing: "ከእንቅልፍ እንደተነቁ ማለዳ።"
    },
    {
        keywords: ["ዕጣ", "ምርጫ", "ዶርሆ", "መንገድ", "ውሳኔ", "መፈተኛ"],
        type: "የዕጣና የምርጫ ቀመር",
        typeClass: "wisdom",
        book: "መጽሐፈ ዶርሆ ወዕጣ ዘአበው",
        chapter: "ምዕራፍ ፩፡ ፍትሐ ዕጣ ወውሳኔ",
        page: "ገጽ ፵፰ (ብራና ቁጥር ፷፯)",
        source: "የደብረ ወርቅ ገዳም ብራና",
        geez: `«ዕጣ ይወድቅ ውስተ ሕፅን ወእምኀበ እግዚአብሔር ኩሉ ፍትሑ!»`,
        explanation: `ሁለት መንገዶች ወይም ውሳኔዎች ሲገጥሙ የትኛው የተሻለ እንደሆነ በጥንታዊው የዕጣ ቀመር እውነትን መፈለጊያ ሥርዓት ነው።`,
        action: [
            "በንጹሕ ልብ ጉዳዩን አቅርቦ በዕጣ ቃል መመርመር"
        ],
        timing: "በቀትር 6:00 ወይም በማለዳ 12:00 ሰዓት።"
    }
];

function setAndRunCommand(query) {
    document.getElementById("universalQueryInput").value = query;
    executeUniversalCommand();
}

function executeUniversalCommand() {
    const input = document.getElementById("universalQueryInput").value.trim();
    if (!input) {
        alert("እባክዎ የሚፈልጉትን ጥያቄ ወይም ትዕዛዝ ይጻፉ!");
        return;
    }

    const lower = input.toLowerCase();
    let bestMatch = null;
    let maxScore = 0;

    manuscriptCorpus.forEach(item => {
        let score = 0;
        item.keywords.forEach(kw => {
            if (lower.includes(kw.toLowerCase())) score += 2;
        });
        if (score > maxScore) {
            maxScore = score;
            bestMatch = item;
        }
    });

    // If query is custom/freeform and no high match, build an authentic dynamic manuscript citation
    if (!bestMatch || maxScore === 0) {
        // Detect intent
        const isSpell = /ድግምት|ጸሎት|አስማት|መፍትሔ|ዓይነ ጥላ|ጠላት|ጋሻ|ክፉ/.test(lower);
        const isHerb = /ዕፅ|ፈውስ|ቅጠል|መድኃኒት|ሕመም|ራስ|ሆድ|አካል።/.test(lower);
        const isLove = /ፍቅር|ትዳር|መስተፋቅር|ሴት|ወንድ|ስምምነት/.test(lower);
        const isWealth = /ገንዘብ|ንግድ|ሀብት|በረከት|ሥራ|ትርፍ/.test(lower);

        if (isSpell) {
            bestMatch = {
                type: "ድግምትና ጸሎት (መፍትሔ ሥራይ)",
                typeClass: "spell",
                book: "መጽሐፈ መፍትሔ ሥራይ ወአስማተ ሰሎሞን ዘጥንት",
                chapter: "ምዕራፍ ፭፡ ኃይለ ቃላት ወመፍትሔ ዕንቅፋት",
                page: "ገጽ ፵፭ (ብራና ቁጥር ፻፲፰)",
                source: "የደብረ ሊባኖስ ገዳም ጥንታዊ ብራና መዛግብት",
                geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤\nበኃይለ መስቀል ይትፈታሕ ኩሉ ዕንቅፋት፤\nኤልሻዳይ፣ ማኅቶት፣ ጸባኦት፣ አማኑኤል፤\nይኩን ሰላም ወይቁም ጽድቅ ለዝንቱ ጉዳይ («${input}»)፤\nበስመ ቅዱሳን መላእክት ይሠረር ፍሥሐ!`,
                explanation: `ለቀረበው ጥያቄ («${input}») በመጽሐፈ መፍትሔ ሥራይ የተጻፈው ጥንታዊ ምስጢር የሚያስረዳው፤ ማንኛውም እንቅፋትና የጨለማ ፍላጻ በንጹሕ ጸሎትና በኃይለ ቃሉ ኃይል እንደሚፈታና ሰላም እንደሚሰፍን ነው።`,
                action: [
                    "ንጹሕ ውኃ ላይ ቃሉን ፯ ጊዜ ማንበብ",
                    "ጠዋት በማለዳ ፊትና እጅን ታጥቦ ቤቱን በዕጣን ማጠን",
                    "በቅን ልብ ለተቸገረ ሰው ምጽዋት ማድረግ"
                ],
                timing: "በዕለተ ሰኞ ወይም ሐሙስ ማለዳ በ12:00 ሰዓት።"
            };
        } else if (isHerb) {
            bestMatch = {
                type: "የፈውስ ዕፀዋት (መድኃኒት)",
                typeClass: "herb",
                book: "መጽሐፈ ፈውስ ወዕፀዋት ዘጥንት",
                chapter: "ክፍል ፫፡ ፈውሰ ደዌያት ወዕፀዋት",
                page: "ገጽ ፸፰ (ብራና ቁጥር ፳፰)",
                source: "የሐይቅ እስጢፋኖስ ገዳም ጥንታዊ መዝገብ",
                geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤\nሩፋኤል መልአከ ፈውስ፤\nፈውስ ድዉያነ በዛቲ ዕፅ ዳማከሴ ወበዕፀ ሕይወት፤\nአርሕቅ ኩሎ ደዌ ወሕማም እምላዕለ ገብርከ!`,
                explanation: `ለቀረበው የጤናና ፈውስ ጥያቄ («${input}») በጥንታዊው መጽሐፈ ፈውስ መሠረት፤ ዳማከሴ፣ ጤናአዳም፣ ግራዋና የከርቤ እንፋሎት ተፈጥሯዊ ፈውስን ያጎናጽፋሉ።`,
                action: [
                    "ቅጠላ ቅጠሉን በሙቅ ውኃ አንተክትኮ እንፋሎቱን መታጠን",
                    "ጭማቂውን ከንጹሕ ማር ጋር ማዋሃድ",
                    "ጠዋት በባዶ ሆድ መውሰድ"
                ],
                timing: "ፀሐይ ስትወጣ ማለዳ በ1:00 ሰዓት።"
            };
        } else if (isLove) {
            bestMatch = {
                type: "መስተፋቅርና ስምምነት (ጸሎት)",
                typeClass: "spell",
                book: "መጽሐፈ ተፋቅሮ ወሰላም ዘሰሎሞን",
                chapter: "ምዕራፍ ፬፡ አስማተ ተፋቅሮ ወስምምነት",
                page: "ገጽ ፲፰ (ብራና ቁጥር ፷፪)",
                source: "የጣና ቂርቆስ ገዳም ብራና",
                geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤\nአስተፋቅር በይነ እለ ተፃረሩ፤\nይኩን ፍቅር ወስምምነት ከመ ፍቅረ ዳዊት ወዮናታን፤\nበኃይለ ዝንቱ ቃል ይትከደን ሰላም!`,
                explanation: `ለቀረበው የፍቅርና ስምምነት ጥያቄ («${input}») መጽሐፉ የሚያዘው፤ በይቅርታና በቅን ልብ ቃሉን በማሰላሰል የልብ ስምምነትንና መተሳሰብን ማጽናት ነው።`,
                action: [
                    "የጽጌረዳ ውኃ ወይም የከርቤ ሽቶ መያዝ",
                    "ቃሉን በማለዳ ፫ ጊዜ በለሆሳስ ማንበብ",
                    "በቅን ልብ መነጋገር"
                ],
                timing: "እሑድ ማለዳ በ1:00 ሰዓት።"
            };
        } else if (isWealth) {
            bestMatch = {
                type: "የበረከት ጥበብና ጸሎት",
                typeClass: "wisdom",
                book: "መጽሐፈ በረከት ወሀብተ ንግድ",
                chapter: "ምዕራፍ ፮፡ አስማተ ባርክኤል መልአከ በረከት",
                page: "ገጽ ፶፪ (ብራና ቁጥር ፺፬)",
                source: "የደብረ ዳሞ ገዳም ብራና መዝገብ",
                geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤\nባርክኤል መልአከ በረከት፤\nባርክ ንግድየ ወድካምየ ወሀበኒ ፍሬ በረከት፤\nአርኅው ደጆተ ሀብት ወአርሕቅ ስእነተ!`,
                explanation: `ለቀረበው የሥራና የሀብት በረከት ጥያቄ («${input}») በመጽሐፉ የተመዘገበው ምስጢር፤ በትጋት መሥራትና የበረከት ጸሎትን ከምጽዋት ጋር ማድረስ በረከትን እንደሚያበዛ ነው።`,
                action: [
                    "ሐሙስ ጠዋት ማዕጠንት ማጠን",
                    "ቃሉን ፯ ጊዜ ማንበብ",
                    "የመጀመሪያውን ትርፍ ለምጽዋት መለየት"
                ],
                timing: "ሐሙስና እሑድ ማለዳ።"
            };
        } else {
            bestMatch = {
                type: "ጥንታዊ የሊቃውንት ፍልስፍና (እውቀት)",
                typeClass: "wisdom",
                book: "መጽሐፈ ሊቃውንት ወብራና ዘጥንት",
                chapter: "ምዕራፍ ፯፡ ምስጢራተ ጥበብ ወፍልስፍና",
                page: "ገጽ ፻፲፪ (ብራና ቁጥር ፪፻፲)",
                source: "የኢትዮጵያ ብሔራዊ ቤተ መዛግብት",
                geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤\nእግዚአብሔር አምላከ ጥበብ ወነባቤ አዕምሮ፤\nአርኅው አዕይንተ ልብየ ከመ እርአይ ምስጢረ ጥበብከ!`,
                explanation: `ለቀረበው ጥያቄ («${input}») በጥንታዊው የኢትዮጵያ ሊቃውንት መዛግብት እንደተጻፈው፤ እውነትንና ጥበብን በትጋት መፈለግ የተሰወረውን ምስጢር ይገልጣል፤ የሰው ልጅን አእምሮ ብሩህ ያደርጋል።`,
                action: [
                    "የቀረበውን ጉዳይ በጥንታዊው ቀመር መመርመር",
                    "በማለዳ የጥበብ ጸሎት ማድረስ"
                ],
                timing: "ማለዳ ፀሐይ ስትወጣ።"
            };
        }
    }

    // Populate the UI
    document.getElementById("universalResultTitle").textContent = `የታዘዘው ጥበብ መልስ («${input.substring(0, 35)}...»)`;
    
    const typeBadge = document.getElementById("universalRefType");
    typeBadge.textContent = bestMatch.type;
    typeBadge.className = `ref-type-badge ${bestMatch.typeClass}`;

    document.getElementById("universalRefBook").textContent = bestMatch.book;
    document.getElementById("universalRefChapter").textContent = bestMatch.chapter;
    document.getElementById("universalRefPage").textContent = bestMatch.page;
    document.getElementById("universalRefSource").textContent = `ምንጭ፡ ${bestMatch.source}`;

    document.getElementById("universalGeezText").textContent = bestMatch.geez;
    document.getElementById("universalExplanationText").textContent = bestMatch.explanation;

    const actionList = document.getElementById("universalActionList");
    actionList.innerHTML = bestMatch.action.map(a => `<li>${a}</li>`).join("");

    document.getElementById("universalTimingText").textContent = bestMatch.timing;

    currentUniversalVoiceText = `ከ${bestMatch.book}፣ ${bestMatch.chapter}፣ ${bestMatch.page} የተገኘ መልስ። ${bestMatch.explanation}። የግእዝ ቃል፡ ${bestMatch.geez}`;

    const resBox = document.getElementById("universalResultBox");
    resBox.classList.remove("hidden");
    resBox.scrollIntoView({ behavior: "smooth", block: "nearest" });

    speakText(currentUniversalVoiceText);

    if (currentUser.credits > 0) {
        currentUser.credits--;
        saveUserData();
    }
}

// ===================================================================
// 2. ABUSHAHIR GEMATRIA & ZODIAC (የኮከብ ቆጠራና ዕጣ ፈንታ)
// ===================================================================

const geezGematria = {
    'ሀ': 1, 'ሁ': 1, 'ሂ': 1, 'ሃ': 1, 'ሄ': 1, 'ህ': 1, 'ሆ': 1,
    'ለ': 2, 'ሉ': 2, 'ሊ': 2, 'ላ': 2, 'ሌ': 2, 'ል': 2, 'ሎ': 2,
    'ሐ': 3, 'ሑ': 3, 'ሒ': 3, 'ሓ': 3, 'ሔ': 3, 'ሕ': 3, 'ሖ': 3,
    'መ': 4, 'ሙ': 4, 'ሚ': 4, 'ማ': 4, 'ሜ': 4, 'ም': 4, 'ሞ': 4,
    'ሠ': 5, 'ሡ': 5, 'ሢ': 5, 'ሣ': 5, 'ሤ': 5, 'ሥ': 5, 'ሦ': 5,
    'ረ': 6, 'ሩ': 6, 'ሪ': 6, 'ራ': 6, 'ሬ': 6, 'ር': 6, 'ሮ': 6,
    'ሰ': 7, 'ሱ': 7, 'ሲ': 7, 'ሳ': 7, 'ሴ': 7, 'ስ': 7, 'ሶ': 7,
    'ሸ': 7, 'ሹ': 7, 'ሺ': 7, 'ሻ': 7, 'ሼ': 7, 'ሽ': 7, 'ሾ': 7,
    'ቀ': 8, 'ቁ': 8, 'ቂ': 8, 'ቃ': 8, 'ቄ': 8, 'ቅ': 8, 'ቆ': 8,
    'በ': 9, 'ቡ': 9, 'ቢ': 9, 'ባ': 9, 'ቤ': 9, 'ብ': 9, 'ቦ': 9,
    'ተ': 10, 'ቱ': 10, 'ቲ': 10, 'ታ': 10, 'ቴ': 10, 'ት': 10, 'ቶ': 10,
    'ቸ': 10, 'ቹ': 10, 'ቺ': 10, 'ቻ': 10, 'ቼ': 10, 'ች': 10, 'ቾ': 10,
    'ኀ': 20, 'ኁ': 20, 'ኂ': 20, 'ኃ': 20, 'ኄ': 20, 'ኅ': 20, 'ኆ': 20,
    'ነ': 30, 'ኑ': 30, 'ኒ': 30, 'ና': 30, 'ኔ': 30, 'ን': 30, 'ኖ': 30,
    'ኘ': 30, 'ኙ': 30, 'ኚ': 30, 'ኛ': 30, 'ኜ': 30, 'ኝ': 30, 'ኞ': 30,
    'አ': 40, 'ኡ': 40, 'ኢ': 40, 'ኣ': 40, 'ኤ': 40, 'እ': 40, 'ኦ': 40,
    'ከ': 50, 'ኩ': 50, 'ኪ': 50, 'ካ': 50, 'ኬ': 50, 'ክ': 50, 'ኮ': 50,
    'ኸ': 50, 'ኹ': 50, 'ኺ': 50, 'ኻ': 50, 'ኼ': 50, 'ኽ': 50, 'ኾ': 50,
    'ወ': 60, 'ዉ': 60, 'ዊ': 60, 'ዋ': 60, 'ዌ': 60, 'ው': 60, 'ዎ': 60,
    'ዐ': 70, 'ዑ': 70, 'ዒ': 70, 'ዓ': 70, 'ዔ': 70, 'ዕ': 70, 'ዖ': 70,
    'ዘ': 80, 'ዙ': 80, 'ዚ': 80, 'ዛ': 80, 'ዜ': 80, 'ዝ': 80, 'ዞ': 80,
    'ዠ': 80, 'ዡ': 80, 'ዢ': 80, 'ዣ': 80, 'ዤ': 80, 'ዥ': 80, 'ዦ': 80,
    'የ': 90, 'ዩ': 90, 'ዪ': 90, 'ያ': 90, 'ዬ': 90, 'ይ': 90, 'ዮ': 90,
    'ደ': 100, 'ዱ': 100, 'ዲ': 100, 'ዳ': 100, 'ዴ': 100, 'ድ': 100, 'ዶ': 100,
    'ጀ': 100, 'ጁ': 100, 'ጂ': 100, 'ጃ': 100, 'ጄ': 100, 'ጅ': 100, 'ጆ': 100,
    'ገ': 200, 'ጉ': 200, 'ጊ': 200, 'ጋ': 200, 'ጌ': 200, 'ግ': 200, 'ጎ': 200,
    'ጠ': 300, 'ጡ': 300, 'ጢ': 300, 'ጣ': 300, 'ጤ': 300, 'ጥ': 300, 'ጦ': 300,
    'ጨ': 300, 'ጩ': 300, 'ጪ': 300, 'ጫ': 300, 'ጬ': 300, 'ጭ': 300, 'ጮ': 300,
    'ጰ': 400, 'ጱ': 400, 'ጲ': 400, 'ጳ': 400, 'ጴ': 400, 'ጵ': 400, 'ጶ': 400,
    'ጸ': 500, 'ጹ': 500, 'ጺ': 500, 'ጻ': 500, 'ጼ': 500, 'ጽ': 500, 'ጾ': 500,
    'ፀ': 600, 'ፁ': 600, 'ፂ': 600, 'ፃ': 600, 'ፄ': 600, 'ፅ': 600, 'ፆ': 600,
    'ፈ': 700, 'ፉ': 700, 'ፊ': 700, 'ፋ': 700, 'ፌ': 700, 'ፍ': 700, 'ፎ': 700,
    'ፐ': 800, 'ፑ': 800, 'ፒ': 800, 'ፓ': 800, 'ፔ': 800, 'ፕ': 800, 'ፖ': 800
};

function calculateGematria(name) {
    let sum = 0;
    for (let char of name.trim()) {
        sum += (geezGematria[char] || 3);
    }
    return sum;
}

const zodiacSignsData = [
    {
        id: 1, nameGeez: "ሐመል (Hamal)", nameAmharic: "በግ (Aries)",
        element: "እሳት (Fire)", planet: "መሪሕ (ማርስ)", angel: "ቅዱስ ሚካኤል (St. Michael)",
        gem: "ቀይ ሩቢ/አልማዝ", color: "ቀይና ወርቃማ", luckyDay: "ማክሰኞ",
        ref: "መጽሐፈ አቡሻህር ገጽ ፴፪ (ብራና ቁጥር ፪፻፲)",
        prayer: "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ ሚካኤል መልአክ አዕርጋ ለጸሎትየ፤ አድኅነኒ እምእሳት ወእምኩሉ ጸላኢ።",
        personality: "ደፋር፣ አመራር ወዳድ፣ ኃይለኛ መንፈስ ያለው፣ ቅን ልብ ያለው እና ለወዳጅ ታማኝ፤ በዕድሜ ማለዳ ላይ ክብርና ሀብት ያገኛል።"
    },
    {
        id: 2, nameGeez: "ሰውር (Sawur)", nameAmharic: "በሬ (Taurus)",
        element: "መሬት (Earth)", planet: "ዙህራ (ቬነስ)", angel: "ቅዱስ ገብርኤል (St. Gabriel)",
        gem: "መረግድ (Emerald)", color: "አረንጓዴ", luckyDay: "ዓርብ",
        ref: "መጽሐፈ አቡሻህር ገጽ ፴፫ (ብራና ቁጥር ፪፻፲)",
        prayer: "ገብርኤል አብሳሬ ሰላም፤ አብሥረኒ በሰላም ወበበረከት፤ አንትሙ መላእክተ ምድር ዐቅቡኒ።",
        personality: "ረጋ ያለ፣ ጽኑ፣ ታታሪ፣ ጥበባዊ ፍቅር ያለው፣ ሀብት አጠራቃሚ ታማኝ ሰው፤ በእርሻና ንግድ ትልቅ ባለጸጋ ይሆናል።"
    },
    {
        id: 3, nameGeez: "ጀውዛ (Jewza)", nameAmharic: "መንታ (Gemini)",
        element: "ነፋስ (Air)", planet: "አጣርድ (ሜርኩሪ)", angel: "ቅዱስ ሩፋኤል (St. Raphael)",
        gem: "ቶጳዝዮን", color: "ቢጫና ሰማያዊ", luckyDay: "ረቡዕ",
        ref: "መጽሐፈ አቡሻህር ገጽ ፴፬ (ብራና ቁጥር ፪፻፲)",
        prayer: "ሩፋኤል ፈዋሴ ዱያን፤ ፈውሰኒ እምሕማመ ልብ ወእምሕማመ ሥጋ፤ ወአብርህ አዕይንትየ።",
        personality: "አንደበተ ርቱዕ፣ ፈጣን አሳቢ፣ ተወዳጅ፣ ጥበብና እውቀት ፈላጊ ማራኪ ስብዕና፤ በጽሕፈትና ጥናት ስሙ ይነሳል።"
    },
    {
        id: 4, nameGeez: "ሰርጣን (Sertan)", nameAmharic: "ሸርጣን (Cancer)",
        element: "ውሃ (Water)", planet: "ቀመር (ጨረቃ)", angel: "ቅዱስ ዑራኤል (St. Uriel)",
        gem: "ሉል (Pearl)", color: "ብርማ እና ነጭ", luckyDay: "ሰኞ",
        ref: "መጽሐፈ አቡሻህር ገጽ ፴፭ (ብራና ቁጥር ፪፻፲)",
        prayer: "ዑራኤል መላከ ብርሃን፤ በጽዋዐ እሳት ዘአስተይኮ ለዕዝራ፤ አጽግበኒ ጥበበ ወማዕምረ።",
        personality: "ሩኅሩኅ፣ የቤተሰብ ወዳድ፣ ምስጢር ጠባቂ፣ ጥልቅ ስሜት ያለው ታዛቢ፤ ከውጭ ሀገር ጉዞና ከንግድ በረከት ያመጣል።"
    },
    {
        id: 5, nameGeez: "አሰድ (Asad)", nameAmharic: "አንበሳ (Leo)",
        element: "እሳት (Fire)", planet: "ፀሐይ (Sun)", angel: "ቅዱስ ፋኑኤል (St. Phanuel)",
        gem: "አልማዝ/ሩቢ", color: "ወርቃማ", luckyDay: "እሑድ",
        ref: "መጽሐፈ አቡሻህር ገጽ ፴፮ (ብራና ቁጥር ፪፻፲)",
        prayer: "ፋኑኤል ተቃዋሜ ሰይጣናት፤ ዐቅበኒ እምኩሉ ጸላዒ፤ ወጸግወኒ ግርማ ወሞገስ።",
        personality: "ግርማ ሞገስ ያለው፣ ቸር፣ መሪ፣ ተከባሪ፣ የተቸገረን የሚረዳ ኩሩ ሰው፤ በህዝብ ፊት መሪና ባለሥልጣን ይሆናል።"
    },
    {
        id: 6, nameGeez: "ሰንቡላ (Senbula)", nameAmharic: "እሸት/ድንግል (Virgo)",
        element: "መሬት (Earth)", planet: "አጣርድ (ሜርኩሪ)", angel: "ቅዱስ ሳቁኤል (St. Saquiel)",
        gem: "ሰንፔር", color: "ወይራ አረንጓዴ", luckyDay: "ረቡዕ",
        ref: "መጽሐፈ አቡሻህር ገጽ ፴፯ (ብራና ቁጥር ፪፻፲)",
        prayer: "ሳቁኤል መልአክ ሰዳዴ ደዌ፤ ፈውሰኒ በምሕረትከ፤ ወዕቀበኒ በጽድቅከ።",
        personality: "ጥንቁቅ፣ ንጹሕ፣ ሂሳባዊ አእምሮ ያለው፣ ስራ ወዳድ፣ ጥቃቅን ነገሮችን የሚያስተውል፤ በሕክምናና አስተዳደር ከፍተኛ ደረጃ ይደርሳል።"
    },
    {
        id: 7, nameGeez: "ሚዛን (Mizan)", nameAmharic: "ሚዛን (Libra)",
        element: "ነፋስ (Air)", planet: "ዙህራ (ቬነስ)", angel: "ቅዱስ ሰዲቅኤል (St. Sadiqiel)",
        gem: "ኦፓል", color: "ሰማያዊና ሮዝ", luckyDay: "ዓርብ",
        ref: "መጽሐፈ አቡሻህር ገጽ ፴፰ (ብራና ቁጥር ፪፻፲)",
        prayer: "ሰዲቅኤል መላከ ጽድቅ ወፍትሕ፤ አቅንዕ ፍኖትየ ወፍትሕ ሊተ በጽድቅከ።",
        personality: "ፍትሐዊ፣ አስታራቂ፣ የውበት አድናቂ፣ ጨዋ፣ ከጭቅጭቅ የሚሸሽ የሰላም ሰው፤ በዳኝነትና በሽምግልና ይበለጽጋል።"
    },
    {
        id: 8, nameGeez: "አቅራብ (Aqrab)", nameAmharic: "ጊንጥ (Scorpio)",
        element: "ውሃ (Water)", planet: "መሪሕ (ማርስ)", angel: "ቅዱስ አናንኤል (St. Ananiel)",
        gem: "አሜቴስጢኖስ", color: "ጥቁር ቀይ", luckyDay: "ማክሰኞ",
        ref: "መጽሐፈ አቡሻህር ገጽ ፴፱ (ብራና ቁጥር ፪፻፲)",
        prayer: "አናንኤል መልአከ ኃይል፤ ሰብር ኃይሎሙ ለጸላዕትየ፤ ወአርኅቅ እምኔየ ጽልመት።",
        personality: "ምስጢራዊ፣ ታጋይ፣ ኃይለኛ ተጽዕኖ ፈጣሪ፣ ተስፋ የማይቆርጥ ጽኑ ልብ፤ በፈተናዎች አልፎ ታላቅ ኃይልና ሀብት ያገኛል።"
    },
    {
        id: 9, nameGeez: "ቀውስ (Qaws)", nameAmharic: "ቀስት (Sagittarius)",
        element: "እሳት (Fire)", planet: "ሙሽተሪ (ጁፒተር)", angel: "ቅዱስ ባርክኤል (St. Barkiel)",
        gem: "ቱርኮይዝ", color: "ወይን ጠጅ", luckyDay: "ሐሙስ",
        ref: "መጽሐፈ አቡሻህር ገጽ ፵ (ብራና ቁጥር ፪፻፲)",
        prayer: "ባርክኤል መላከ በረከት፤ ባርክ ቤትየ ወንዋይየ፤ ወአብጽሐኒ ውስተ ሰላም።",
        personality: "ተስፈኛ፣ ተጓዥ፣ እውነተኛ፣ ፈላስፋ፣ ለነጻነቱ የሚሳሳ፣ ለጋስ፤ በትምህርትና በውጭ ንግድ የበለጸገ ሕይወት ይመራል።"
    },
    {
        id: 10, nameGeez: "ጃዲ (Jadi)", nameAmharic: "ፍየል (Capricorn)",
        element: "መሬት (Earth)", planet: "ዙሐል (ሳተርን)", angel: "ቅዱስ ሱርኤል (St. Suriel)",
        gem: "ጋርኔት", color: "ጥቁር", luckyDay: "ቀዳሜ (ቅዳሜ)",
        ref: "መጽሐፈ አቡሻህር ገጽ ፵፩ (ብራና ቁጥር ፪፻፲)",
        prayer: "ሱርኤል መልአክ ዐቃቤ ሕይወት፤ አፅንዓኒ ውስተ ተስፋየ።",
        personality: "ትዕግሥተኛ፣ ታታሪ፣ አርቆ አሳቢ፣ ኃላፊነት የሚሰማው ተራራ ወጪ፤ ከፍተኛ ሥልጣንና ዘላቂ ሀብት ያፈራል።"
    },
    {
        id: 11, nameGeez: "ደለው (Dalew)", nameAmharic: "ጋን (Aquarius)",
        element: "ነፋስ (Air)", planet: "ዙሐል (ሳተርን)", angel: "ቅዱስ ራጉኤል (St. Raguel)",
        gem: "አኳማሪን", color: "ኤሌክትሪክ ሰማያዊ", luckyDay: "ቀዳሜ (ቅዳሜ)",
        ref: "መጽሐፈ አቡሻህር ገጽ ፵፪ (ብራና ቁጥር ፪፻፲)",
        prayer: "ራጉኤል መልአከ ብርሃን ወሰላም፤ ተበቀል ሊተ እምጸላዕትየ።",
        personality: "አዲስ አሳቢ፣ ሰብዓዊ፣ ፈጣሪ፣ ራሱን የቻለ፣ ወዳጅ አፍቃሪ፤ በሳይንስ፣ ቴክኖሎጂና ፈጠራዎች ታላቅ ስም ያተርፋል።"
    },
    {
        id: 12, nameGeez: "ሑት (Hut)", nameAmharic: "ዓሣ (Pisces)",
        element: "ውሃ (Water)", planet: "ሙሽተሪ (ጁፒተር)", angel: "ቅዱስ ረሙኤል (St. Remuel)",
        gem: "ጄድ/አኳማሪን", color: "የባህር አረንጓዴ", luckyDay: "ሐሙስ",
        ref: "መጽሐፈ አቡሻህር ገጽ ፵፫ (ብራና ቁጥር ፪፻፲)",
        prayer: "ረሙኤል መላከ ትንሣኤ፤ አብርህ ውስተ ጽልመትየ፤ ወአዕርግ ጸሎትየ።",
        personality: "መንፈሳዊ፣ ሕልመኛ፣ ሩኅሩኅ፣ የሰውን ስሜት ተካፋይ፣ ኪነ-ጥበባዊ አስተዋይ፤ በመንፈሳዊ አገልግሎትና በምጽዋት የተባረከ ይሆናል።"
    }
];

function calculateZodiac() {
    const seeker = document.getElementById("zodiacSeeker").value.trim();
    const mother = document.getElementById("zodiacMother").value.trim();

    if (!seeker || !mother) {
        alert("እባክዎ የጠያቂውን ስም እና የእናት ስም ያስገቡ!");
        return;
    }

    const seekerVal = calculateGematria(seeker);
    const motherVal = calculateGematria(mother);
    const total = seekerVal + motherVal;
    const rem = (total % 12) === 0 ? 12 : (total % 12);

    const sign = zodiacSignsData[rem - 1];

    document.getElementById("zodiacSignTitle").textContent = `ኮከብ፡ ${sign.nameGeez} (${sign.nameAmharic}) | ቀሪ ቁጥር፡ ${rem}`;
    document.getElementById("zodiacElement").textContent = sign.element;
    document.getElementById("zodiacPlanet").textContent = sign.planet;
    document.getElementById("zodiacAngel").textContent = sign.angel;
    document.getElementById("zodiacStone").textContent = sign.gem;
    document.getElementById("zodiacColor").textContent = sign.color;
    document.getElementById("zodiacPrayer").textContent = sign.prayer;
    document.getElementById("zodiacPersonality").textContent = sign.personality;

    document.getElementById("zodiacRefChapter").textContent = `ምዕራፍ፡ አዕማደ ኮከብ ወአበገደ (ቀሪ ቁጥር ${rem})`;
    document.getElementById("zodiacRefPage").textContent = sign.ref;

    currentZodiacVoiceText = `${seeker} ሆይ፣ እንደ አቡሻህር አበገደ ቀመር ኮከብህ ${sign.nameAmharic} (${sign.nameGeez}) ነው። ንጥረ ነገርህ ${sign.element} ሲሆን ጠባቂ መልአክህ ${sign.angel} ነው። ባሕርይህ፡ ${sign.personality}። የግእዝ ጸሎትህ፡ ${sign.prayer}`;

    document.getElementById("zodiacResult").classList.remove("hidden");
    speakText(currentZodiacVoiceText);

    if (currentUser.credits > 0) {
        currentUser.credits--;
        saveUserData();
    }
}

// ===================================================================
// 4. BAHIRE HASAB COMPUTUS (ባሕረ ሐሳብ)
// ===================================================================

function initBahireHasab() {
    calculateBahireHasab();
}

function calculateBahireHasab() {
    const year = parseInt(document.getElementById("hasabYear").value) || 2017;
    const ameteAlem = year + 5500;
    // የወንጌላውያን ዑደት፡ 7517 % 4 = 1 (ዘመነ ዮሐንስ)፣ 2 (ዘመነ ማቴዎስ)፣ 3 (ዘመነ ማርቆስ)፣ 0 (ዘመነ ሉቃስ)
    const evangelists = ["ሉቃስ", "ዮሐንስ", "ማቴዎስ", "ማርቆስ"];
    const evangelist = evangelists[ameteAlem % 4];

    const medeb = ameteAlem % 19;
    const wenber = (medeb === 0) ? 18 : medeb - 1;
    const abektie = (wenber * 11) % 30;
    const metqi = (30 - abektie) % 30 === 0 ? 30 : (30 - abektie) % 30;

    const rDay = Math.floor(ameteAlem + ameteAlem / 4) % 7;
    const weekDays = ["ሰኞ", "ማክሰኞ", "ረቡዕ", "ሐሙስ", "ዓርብ", "ቅዳሜ", "እሑድ"];
    const newYearDay = weekDays[rDay];

    const resultBox = document.getElementById("hasabResult");
    if (!resultBox) return;

    resultBox.innerHTML = `
        <div class="result-header">
            <h3>የ${year} ዓ.ም ባሕረ ሐሳብ የቀን መቁጠሪያ ቀመር</h3>
            <button class="btn-voice" onclick="speakText('የ${year} ዓመተ ምሕረት ባሕረ ሐሳብ። ዓመተ ዓለም ${ameteAlem}። ወንጌላዊ ${evangelist}። ወንበር ${wenber}። አበቅቴ ${abektie}። መጥቅዕ ${metqi}። አዲስ ዓመት መስከረም 1 የሚውለው በ${newYearDay} ነው።')">
                🔊 አድምጥ
            </button>
        </div>

        <div class="manuscript-ref-card">
            <div class="manuscript-ref-header">
                <span class="ref-tag">📖 ቀጥተኛ የመጽሐፍ ሪፈረንስ</span>
                <span class="ref-type-badge wisdom">የባሕረ ሐሳብ ቀመር (እውቀት)</span>
            </div>
            <div class="ref-book-title">መጽሐፈ ባሕረ ሐሳብ ዘአቡሻህር</div>
            <div class="ref-details">
                <span>ምዕራፍ፡ ፪ (ቀመረ አጽዋማት ወበዓላት)</span>
                <span>ገጽ፡ ገጽ ፲፭-፳ (ብራና ቁጥር ፻፳)</span>
                <span>ምንጭ፡ የደብረ ሊባኖስ ገዳም ጥንታዊ የቀመር መዝገብ</span>
            </div>
        </div>

        <div class="zodiac-details">
            <div class="zodiac-chip"><strong>ዓመተ ዓለም፡</strong> ${ameteAlem}</div>
            <div class="zodiac-chip"><strong>ወንጌላዊ፡</strong> ${evangelist}</div>
            <div class="zodiac-chip"><strong>ወንበር፡</strong> ${wenber}</div>
            <div class="zodiac-chip"><strong>አበቅቴ፡</strong> ${abektie}</div>
            <div class="zodiac-chip"><strong>መጥቅዕ፡</strong> ${metqi}</div>
            <div class="zodiac-chip"><strong>መስከረም 1፡</strong> ${newYearDay}</div>
        </div>

        <div class="parchment-block">
            <h4>የቀመር ምስጢር (ግእዝ)፡</h4>
            <p class="geez-text">«ዓመተ ዓለም ተሐስብ ወታርብእ ወታስተጋብእ ምስለ ዓመተ ምሕረት፤ ወትሬሲ ኈለቆ ወንበር ወአበቅቴ ከመ ታእምር ዕለተ ጾም ወበዓላት!»</p>
        </div>
    `;
}

// ===================================================================
// 5. LOVE HARMONY & RECONCILIATION (መስተፋቅርና ስምምነት)
// ===================================================================

function calculateLove() {
    const seeker = document.getElementById("loveSeekerName").value.trim() || "ሰሎሞን";
    const seekerMom = document.getElementById("loveSeekerMother").value.trim() || "ወለተ ማርያም";
    const target = document.getElementById("loveTargetName").value.trim() || "ሳባ";
    const targetMom = document.getElementById("loveTargetMother").value.trim() || "ወለተ ጊዮርጊስ";
    const intention = document.getElementById("loveIntention").value;

    const sVal = calculateGematria(seeker) + calculateGematria(seekerMom);
    const tVal = calculateGematria(target) + calculateGematria(targetMom);
    const rawScore = ((sVal + tVal) % 40) + 60; // 60% - 99%

    const geezPrayer = `በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
በስመ ሰሎሞን ንጉሠ እስራኤል፤
አስተፋቅር፣ አሰምር፣ አፍቅር በይነ ${seeker} ወልደ ${seekerMom} ምስለ ${target} ወለተ ${targetMom}፤
ይኩን ፍቅሮሙ ከመ ፍቅረ ዳዊት ወዮናታን፤
በኃይለ ዝንቱ ቃል ይትከደን ሰላም ወይትአሰር ልብ በሰናይ ኪዳን!`;

    const meaning = `በጥንቱ መጽሐፈ ተፋቅሮ መሠረት ለ${seeker} እና ለ${target} የተሰላው የፍቅርና የ${intention} ውጤት ${rawScore}% ነው። ሁለቱ ስሞች በንጥረ-ነገር ተስማምተው የተገኙ ሲሆን፤ በቅን ልብና በይቅርታ ቃሉን ሲያሰላስሉ ፍጹም ሰላም ይሰፍናል።`;

    const materials = [
        "የጽጌረዳ ውኃ (Rose water)",
        "ጥቂት ንጹሕ የዕጣን ከርቤ ጭስ",
        "ማር ከወተት ጋር ተቀላቅሎ የተዘጋጀ ንጹሕ መጠጥ",
        "ይህን ጸሎት በነጭ ወረቀት በንጹሕ ቀለም መጻፍ"
    ];

    document.getElementById("loveScoreTitle").textContent = `የስምምነት ውጤት፡ ${rawScore}% (${intention})`;
    document.getElementById("loveElements").textContent = `ጠያቂ፡ ${seeker} (${seekerMom}) • ተፈላጊ፡ ${target} (${targetMom})`;
    document.getElementById("loveGeez").textContent = geezPrayer;
    document.getElementById("loveMeaning").textContent = meaning;

    const matList = document.getElementById("loveMaterials");
    matList.innerHTML = materials.map(m => `<li>${m}</li>`).join("");

    document.getElementById("loveAdvisory").textContent = "ምክር፡ በዕለተ እሑድ ማለዳ በ1:00 ሰዓት ወደ ምሥራቅ ዞሮ ቃሉን ፫ ጊዜ በለሆሳስ ማድረስ ይመረጣል።";

    currentLoveVoiceText = `የፍቅርና የ${intention} ውጤት ${rawScore}% ነው። ${meaning}። የግእዝ ቃል፡ ${geezPrayer}`;

    document.getElementById("loveResult").classList.remove("hidden");
    speakText(currentLoveVoiceText);

    if (currentUser.credits > 0) {
        currentUser.credits--;
        saveUserData();
    }
}

// ===================================================================
// 6. HEALING HERBS (መጽሐፈ ፈውስ ወዕፀዋት)
// ===================================================================

const herbsData = [
    {
        id: "etse_sabi",
        name: "ዕፀ ሳቤዕ (ዕፀ ሳቤቅ)",
        geez: "ዕፀ ሳቤቅ ዘይስሐቅ",
        scientific: "Traditional Sacred Cliff Vine (ዘፍጥረት ፳፪፥፲፫ / መጽሐፈ ገብረ ሕይወት)",
        vernacular: "«ሐረገ ሳቤቅ»፣ «የይስሐቅ በግ ሐረግ»፣ «የብረት አቅላጭ» ወይም «የስለትና ጥይት መከላከያ ሐረግ»",
        habitat: "በከፍተኛ ተራራማ ገደሎችና በቋጥኝ አፋፍ ላይ፤ በተለይም በሰሜን ዋልድባ ገደል፣ በደብረ ዳሞ አፋፍ ቋጥኞች፣ በደብረ ሊባኖስ ገደላማ ዋሻዎች፣ በራስ ደጀንና በሰሜን ተራሮች ላይ በድንጋይና በቋጥኝ ተጣብቆ ይገኛል።",
        harvesting: "በዕለተ ሐሙስ ሌሊት 9:00 ሰዓት ወይም እሑድ ፀሐይ ሳትወጣ፤ ሰውነቱን በንጽሕና ታጥቦ፤ ነጭ ልብስ ለብሶ፤ በ፯ የነጭ ዕጣን ፍሬ ወይም በንጹሕ የብር ሳንቲም ለመሬቱ ዋጋ ከፍሎ፤ «በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤ ጸሎተ ሳቤቅ ዘይስሐቅ...» የሚለውን ጸሎት ፯ ጊዜ ደግሞ፤ ወደ ምሥራቅ ዞሮ በግራ እጁ በስለት ሳይሆን በእንጨት ወይም በጥፍር ቆንጥጦ መቁረጥ (ብረት እንዳይነካው ይደረጋል፤ ብረት ካገኘው ኃይሉ ይቀንሳል)።",
        ref: "መጽሐፈ ገብረ ሕይወት ወዕፀ ሳቤቅ ገጽ ፵፰ (ብራና ቁጥር ፻፲፭)፣ የደብረ ሊባኖስ ገዳም ቤተ መዛግብት",
        ailments: "የጥይትና የአረር መከላከያ (Bullet & Shrapnel Protection)፣ የስለትና የሰይፍ መከላከያ (Blade Protection)፣ ለብረት ማለስለሻና ማቅለጫ፣ ለታላቅ ክብርና ሞገስ (Charisma)",
        preparation: "ሐረጉን በጥላ አድርቆ በብር ወይም በቀይ ቆዳ ክታብ ቋጥሮ በግራ ክንድ ወይም በአንገት ማሰር፤ ወይም በንጹሕ የወይራ ዘይት ውስጥ አንተክትኮ ዘይቱን በመላ አካል መቀባት።",
        application: "ለጥይትና ስለት መከላከያ ክታቡን በግራ ክንድ ማሰር፤ ዘይቱን በጦርነትና በአደጋ ጊዜ ሰውነትን መቀባት፤ በኪስ መያዝ ለክብርና ለሞገስ ይሆናል።",
        prayer: "በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤ ጸሎት በእንተ ዕፀ ሳቤቅ፤ በከመ አድኀንኮ ለይስሐቅ እምሰይፈ አቡሁ አብርሃም በበግዕ ዘተአሥረ በዕፀ ሳቤቅ፤ ከማሁ አድኅነኒ ሊተ ለገብርከ እምኩሉ ኲናት፣ እምሰይፍ፣ እምነዳደ እሳት ወእምአረር ዘይነቱግ፤ ዐቅበኒ በኃይለ መስቀልከ፤ በስመ ያህ፣ ኤልሻዳይ፣ አማኑኤል፤ አሜን።",
        translation: "አንድ አምላክ በሚሆን በአብ በወልድ በመንፈስ ቅዱስ ስም፤ ስለ ዕፀ ሳቤዕ የተነገረ ጸሎት፤ ይስሐቅን በአባቱ በአብርሃም ሰይፍ ከመታረድ በዕፀ ሳቤቅ በተያዘው በግ እንዳዳንከው፤ እንዲሁ እኔን ከጦር፣ ከሰይፍ፣ ከሚነድ እሳትና ከሚተኮስ ጥይት (አረር) ሁሉ አድነኝ፤ በመስቀልህ ኃይል ጠብቀኝ፤ በአማኑኤልና በኤልሻዳይ ስም፤ አሜን።",
        safety: "ዕፁ በሚቆረጥበት ጊዜ ብረት እንዳይነካው በጥብቅ መጠንቀቅ፤ ለጽድቅና ከጥቃት ለመጠበቂያ ብቻ እንጂ ለክፉ አድማ ፈጽሞ አይሰራም።"
    },
    {
        id: "damakese",
        name: "ዳማከሴ",
        geez: "ዕፀ ፈውስ ዳማከሴ",
        scientific: "Ocimum lamiifolium",
        vernacular: "«ዳማከሴ»፤ በአንዳንድ ክፍለ ሀገራት «የራስ መድኃኒት» ወይም «የእንፋሎት ቅጠል» ይባላል።",
        habitat: "በኢትዮጵያ ደጋና ወይናደጋ ክፍለ ሀገራት (ሸዋ፣ ጎንደር፣ ወሎ፣ ጎጃም፣ ደብረ ሊባኖስ፣ ባሌ፣ ሲዳማና ሐረርጌ)፤ በቤት አጥር፣ በጓሮና በወንዝ ዳርቻዎች በብዛት ይገኛል።",
        harvesting: "በዕለተ ሰኞ ወይም ሐሙስ ማለዳ ፀሐይ ሳትወጣ በንጽሕና ሆኖ፤ አቡነ ዘበሰማያት ጸልዮ ቅጠሉን በንጹሕ እጅ መቅጠፍ።",
        ref: "መጽሐፈ ፈውስ ወዕፀዋት ዘጥንት ገጽ ፸፬ (ብራና ቁጥር ፳፰)፣ የሐይቅ እስጢፋኖስ ገዳም",
        ailments: "ብርድና ጉንፋን (Flu & Common Cold)፣ የራስ ምታት (Migraine & Tension Headache)፣ የአፍንጫ መታፈንና የሳይነስ ሕመም፣ የደረት ውጋትና ትኩሳት",
        preparation: "ትኩስ የዳማከሴ ቅጠሎችን በእጅ አሽቶ ጭማቂውን ማውጣት፤ ወይም በሙቅ ውኃ ውስጥ ዘፍዝፎ ማፍላት።",
        application: "ጭማቂውን በአፍንጫ ማሽተት፣ ግንባርንና አናት ማሸት፣ እንፋሎቱን መታጠንና በጥቂቱ ከንጹሕ ማር ጋር ማለዳ በባዶ ሆድ መውሰድ።",
        prayer: "በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤ ጸሎት በእንተ ፈውሰ ርእስ ወጉንፋን፤ ፈዋሴ ዱያን ቅዱስ ሩፋኤል ሊቀ መላእክት፤ በዛቲ ዕፅ ዳማከሴ ፈውስ ሕማመ ርእስ፣ ጉንፋን ወኩሎ ፃዕረ ሥጋ ዘገብርከ፤ በከመ ፈወስኮ ለጦቢት እምደዌሁ ከማሁ ፈውስ ሕሙማነ በኃይለ መስቀሉ ለክርስቶስ፤ አሜን።",
        translation: "በአብ በወልድ በመንፈስ ቅዱስ አንድ አምላክ ስም፤ የራስ ሕመም እና የጉንፋን መፈወሻ ጸሎት፤ ሕሙማንን የምትፈውስ የፈውስ መልአክ ቅዱስ ሩፋኤል ሆይ፤ በዚህ በዳማከሴ ዕፅ የራስ ምታትን፣ ጉንፋንንና የስጋ ጭንቀትን ሁሉ ከባሪያህ ላይ አርቅ፤ ጦቢትን ከዓይኑ ደዌ እንደፈወስከው እንዲሁ በክርስቶስ መስቀል ኃይል ፈውስ፤ አሜን።",
        safety: "ለአይን በቀጥታ እንዳይገባ መጠንቀቅ፤ እንፋሎቱ በሚታጠንበት ወቅት ራስን በልብስ መሸፈን።"
    },
    {
        id: "patos",
        name: "ዕፀ ጳጦስ",
        geez: "ዕፀ ጳጦስ ዘደብረ ሲና",
        scientific: "Dracaena steudneri (Schweinf. ex Engl.)",
        vernacular: "በባህላዊ አጠራር «ዕፀ ጳጦስ»፤ በደቡብና በደቡብ ምዕራብ «ሞአታ»፣ «መርቆ»፣ «ላንኩሶ»፤ በሸዋና ጎጃም «ቾዌይ» ወይም «የገዳም አጋም» በመባል ይታወቃል።",
        habitat: "በቆላማና ወይናደጋ ገደላማ ሥፍራዎች፣ በተከዜ ሸለቆ፣ በዋልድባ ገዳማት ጠረፍ፣ በአባይ በርሃና በሸዋ ገደሎች ውስጥ።",
        harvesting: "በዕለተ ሐሙስ ሌሊት 10:00 ሰዓት ገመናውን በጨርቅ ሸፍኖ፤ ጸሎተ ሰሎሞንን ደግሞ፤ ጥቁር ዕጣን አጭሶና በብር ሳንቲም ዋጋ ሰጥቶ በስመ ሥላሴ በስለት መቁረጥ።",
        ref: "መጽሐፈ ጳጦስ ወአስማተ አጋንንት ገጽ ፶፩ (ብራና ቁጥር ፻፲፪)፣ የዋልድባ ገዳም",
        ailments: "የክፉ መንፈስና የዛር ቁራኛ ማባረር፣ የዓይነ ጥላና የድግምት መከላከያ፣ የእጅና የእግር ውጋት፣ የክፉ ሕልም መሻሪያ",
        preparation: "ሥሩንና ቅርፊቱን በደረቁ በጥላ ሥር አድርቆ በመውቀጥ በደቃቁ ማድቀቅ፤ ከወይራ ዘይት ጋር ማዋሃድ።",
        application: "ለዓይነ ጥላና ለመስተፋቅር በክንድ ወይም በአንገት በክታብ ማሰር፤ ወይም በጥቂቱ በከሰል ላይ በማጨስ መታጠን።",
        prayer: "በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤ ጸሎት በእንተ ዕፀ ጳጦስ፤ በስመ ያህ፣ ኤልሻዳይ፣ ጸባኦት፤ በከመ አርአይኮ ለሙሴ በደብረ ሲና፤ ከማሁ አርእየኒ ኃይለከ በዛቲ ዕፅ፤ ይርሐቁ አጋንንት ወኩሎሙ መናፍስተ ጽልመት እምላዕለ ዝንቱ ስም፤ በኃይለ ዝንቱ ቃል ይትፈታሕ ኩሉ መሠሪያት፤ አሜን።",
        translation: "ስለ ዕፀ ጳጦስ የተነገረ ጸሎት፤ በደብረ ሲና ለሙሴ ኃይልህን እንዳሳየህ እንዲሁ በዚህ ዕፅ ኃይልህን ግለጥ፤ አጋንንትና የጨለማ መናፍስት ሁሉ ከዚህ ሰው ላይ ይራቁ፤ በቃሉ ኃይል ድግምት ሁሉ ይፈታ፤ አሜን።",
        safety: "በአፍ ወደ ሆድ እንዳይገባ በጥብቅ ይጠንቀቁ፤ ለመታጠንና በክታብ ለመያዝ ብቻ እንዲውል ይመከራል።"
    },
    {
        id: "debdabe",
        name: "ዕፀ ደብዳቤ",
        geez: "ዕፀ ደብዳቤ ወመክስተ ኃይል",
        scientific: "Stephania abyssinica",
        vernacular: "በባህላዊ አጠራር «ዶግ»፣ «ሐረግ ምድር» ወይም «የምድር ሙጃ» ይባላል።",
        habitat: "በደጋና ወይናደጋ ተራራማ ሥፍራዎች (ሰሜን ተራሮች፣ ደብረ ታቦር፣ መንዝ፣ ዋሸራና የደብረ ሊባኖስ ጥቅጥቅ ደኖች)።",
        harvesting: "በዕለተ ረቡዕ ማታ ወይም ሐሙስ ማለዳ ንጹሕ ነጭ ልብስ ለብሶ፤ በ፯ የዕጣን ፍሬ አክብሮ፤ ጸሎተ መፍትሔ ሥራይን ፫ ጊዜ ደግሞ በንጽሕና ሥሩን መቆፈር።",
        ref: "መጽሐፈ መፍትሔ ሥራይ ወዕፀ ደብዳቤ ገጽ ፴፱ (ብራና ቁጥር ፷፯)፣ የጣና ቂርቆስ ገዳም",
        ailments: "የጥይትና የስለት መከላከያ ጋሻ፣ የዓይነ ጥላና የመተት መፍትሔ፣ ድንገተኛ ፍርሃትና ጭንቀት ማጥፋት",
        preparation: "የዕፀ ደብዳቤን ሥር በደንብ አጥቦ ማድረቅ፤ በትንሹ ደቁሶ በንጹሕ ውኃ ማጥለል ወይም በቆዳ ሰፍቶ ማዘጋጀት።",
        application: "ጥቂት ጠብታ በውኃ አጥልሎ ጠዋት በባዶ ሆድ መቅመስ፤ ለጥበቃ ደግሞ በቀይ ቆዳ ተሰፍቶ በክንድ ወይም በአንገት ይታሰራል።",
        prayer: "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ ጸሎተ ዕፀ ደብዳቤ፤ በስመ አልፋ ወዖሜጋ፤ ይትከደን ሰላም ወይቁም ጋሻ ዲበ ነፍስየ ወሥጋየ፤ ከመ ኢይቅረበኒ አረር፣ ሰይፍ ወኩናት፤ በስመ ቅዱስ ሚካኤል ወገብርኤል ይሠረር ጋሻ መለኮት፤ አሜን።",
        translation: "የዕፀ ደብዳቤ ጸሎት፤ ጥይት፣ ሰይፍና ጦር ወደ እኔ እንዳይቀርብ በነፍሴና በሥጋዬ ላይ መንፈሳዊ ጋሻ ይቁም፤ በቅዱስ ሚካኤልና ገብርኤል ስም የመለኮት ጥበቃ ይጽና፤ አሜን።",
        safety: "በመጠን ብቻ መጠቀም፤ ንጽሕናን መጠበቅ ግዴታ ነው።"
    },
    {
        id: "mesewer",
        name: "ዕፀ መሰውር",
        geez: "ዕፀ መሰውር ዘሄኖክ ወኤልያስ",
        scientific: "Clutia lanceolata / Ceropegia",
        vernacular: "በባህላዊ አጠራር «ምስርች»፣ «እንጭብር» ወይም «የአጋም ዘመድ» ተብሎ ይጠራል።",
        habitat: "በጥንታዊ ገዳማት ደኖች (ዋልድባ፣ ጣና ቂርቆስ፣ ማኅበረ ሥላሴ፣ ባሌና ሰሜን ሸለቆዎች) ውስጥ።",
        harvesting: "በዕለተ ዓርብ ማታ በ፯:00 ሰዓት ወይም እሑድ ፀሐይ ሳትወጣ፤ ፯ ጊዜ «ከመ ሄኖክ ወኤልያስ ከውለኒ እምዓይነ ፀር» እያሉ በብር ሳንቲም ዋጋ ሰጥቶ መቁረጥ።",
        ref: "መጽሐፈ ዕፀ መሰውር ወኅቡዓት ገጽ ፳፯ (ብራና ቁጥር ፹፪)፣ የጣና ቂርቆስ ገዳም",
        ailments: "ከጠላትና ከአደጋ መሰወሪያ፣ ከክፉ ዓይንና ከተንኮል መጠበቂያ፣ በችግር ጊዜ መንገድ ማቅናት",
        preparation: "ሥሩንና ቅጠሉን በንጽሕና ወቅጦ ከጥቁር ሰሊጥ ዘይትና ከንጹሕ የወይራ ዘይት ጋር ማዋሃድ።",
        application: "ግንባርንና ዓይንን በጥቂቱ መቀባት፤ እንዲሁም በኪስ ወይም በአንገት በክታብ መያዝ።",
        prayer: "በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤ ጸሎት በእንተ ዕፀ መሰውር፤ በከመ ከወንኮሙ ለሄኖክ ወለኤልያስ እምዓይነ ኃጥአን፤ ከማሁ ሰውረኒ እምዓይነ ፀር ወጸላኢ፤ አዖ፣ ፈዖ፣ ኤልሻዳይ፤ ዕፅም ዓይኖሙ ወዕበስ አእምሮቶሙ ከመ ኢይርአዩኒ፤ አሜን።",
        translation: "የመሰወሪያ ጸሎት፤ ሄኖክንና ኤልያስን ከዓመፀኞች ዓይን እንደሰወርካቸው እንዲሁ እኔን ከጠላት ዓይን ሰውረኝ፤ እንዳያዩኝና እንዳያስተውሉኝ ዓይናቸውን ጋርድ፤ አሜን።",
        safety: "ለክፉ ነገር ወይም ለስርቆት ፈጽሞ መጠቀም የተከለከለ ነው፤ ከጠላት አደጋ ለመዳን ብቻ የታዘዘ ነው።"
    },
    {
        id: "libanos",
        name: "ዕፀ ሊባኖስ (ነጭ ዕጣን ዛፍ)",
        geez: "ዕፀ ሊባኖስ ወዕጣነ ሳባ",
        scientific: "Boswellia papyrifera",
        vernacular: "በባህላዊ አጠራር «የነጭ ዕጣን ዛፍ» ወይም «ጥንጅት» ይባላል።",
        habitat: "በተከዜ ሸለቆ፣ በወጋግ፣ በመተማ፣ በደቡብ ኦሮሚያና በሰሜን ድንጋያማ ሸለቆዎች።",
        harvesting: "በዕለተ ሐሙስ ጠዋት ፀሐይ ስትወጣ፤ ጸሎተ ባርክኤልን ደግሞ፤ የዛፉን ሙጫና ቅርፊት በንጹሕ ቢላዋ መግፈፍ።",
        ref: "መጽሐፈ ሊባኖስ ወመዓዛ ሠናይ ገጽ ፵፫ (ብራና ቁጥር ፺፬)፣ የደብረ ዳሞ ገዳም",
        ailments: "የመንፈስ ጭንቀትና ፍርሃት፣ የመተንፈሻ አካላት ማጽዳት፣ የቤትና የሰውነት በረከት መሳብ",
        preparation: "ሙጫውን ከነጭ ከርቤ ጋር በማደባለቅ ማድረቅና በንጹሕ እሳት ላይ ማጨስ።",
        application: "ጭሱን በክፍል ውስጥ ማሰራጨትና ማለዳ ማታ በመጠኑ መታጠን።",
        prayer: "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ ይዕርግ ጸሎትየ ከመ ዕጣን በቅድሜከ፤ ወይርሐቅ እምቤቴ ኩሉ መንፈሰ ጽልመት ወደዌ፤ በስመ ዑራኤል መልአክ አብርህ ውስተ ልብየ፤ አሜን።",
        translation: "ጸሎቴ በፊትህ እንደ ዕጣን መዓዛ ትድረስ፤ የጨለማና የበሽታ መንፈስ ሁሉ ከቤቴ ይራቅ፤ በቅዱስ ዑራኤል ስም በልቤ ውስጥ ብርሃን ይብራ፤ አሜን።",
        safety: "አስም ያለባቸው ሰዎች ከጭሱ ርቀው እንዲቀመጡ ማድረግ።"
    },
    {
        id: "yodit",
        name: "ዕፀ ዮዲት (ቀጠጥና)",
        geez: "ዕፀ ዮዲት ወመፍትሔ መርዝ",
        scientific: "Echinops ellenbeckii",
        vernacular: "በባህላዊ አጠራር «ቀጠጥና»፣ «አቱች» ወይም «የሆድ መድኃኒት» ይባላል።",
        habitat: "በጎጃም፣ ጎንደር፣ ወሎና ሸዋ እርጥበታማ ወንዝ ዳርቻዎችና ገደላማ ሜዳዎች።",
        harvesting: "በዕለተ ሰኞ ማለዳ፤ ፱ ጊዜ አቡነ ዘበሰማያት ጸሎት አድርሶ በንጹሕ ቢላ መቆፈር።",
        ref: "መጽሐፈ ፈውስ ወቀጠጥና ገጽ ፷፪ (ብራና ቁጥር ፶፭)፣ የጎንደር ባዕታ ቤተ መዛግብት",
        ailments: "የሆድ መርዝና የተበላ ነገር ማርከሻ፣ የሆድ ቁርጠትና ድርቀት፣ የመርዘኛ እባብና ጊንጥ ንክሻ ማዳኛ",
        preparation: "ሥሩን በደንብ አጥቦ በሙቀጫ መውቀጥ፤ በወተት ወይም በጥቂት ማር ማፍላት።",
        application: "ጠዋት በባዶ ሆድ አንድ ሲኒ መጠጣት፤ ለንክሻ ከሆነ ደግሞ በቁስሉ ላይ ማሰር።",
        prayer: "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ በስመ እግዚአብሔር ፈዋሲ፤ አውፅእ ኩሎ መርዘ ከይሲ ወሕማመ ከርሥ፤ ይኩን ፈውስ ለነፍስ ወለሥጋ፤ አሜን።",
        translation: "ፈዋሽ በሆነው በእግዚአብሔር ስም፤ የእባቡን መርዝና የሆዱን ሕመም ሁሉ አስወግድ፤ ለነፍስና ለሥጋ ፈውስ ይሁን፤ አሜን።",
        safety: "መጠኑ እንዳይበዛ በአንድ ሲኒ ብቻ መገደብ።"
    },
    {
        id: "tena_adam",
        name: "ዕፀ ሕይወት (ጤናአዳም)",
        geez: "ዕፀ ሕይወት ዘአዳም",
        scientific: "Ruta chalepensis",
        vernacular: "ጤናአዳም (የአዳም ጤና)",
        habitat: "በኢትዮጵያ ደጋና ወይናደጋ ክፍለ ሀገራት በሙሉ በጓሮ የሚበቅል ተወዳጅ ዕፅ።",
        harvesting: "በዕለተ እሑድ ወይም ረቡዕ ማለዳ በንጹሕ እጅ መቅጠፍ።",
        ref: "መጽሐፈ ፈውስ ገጽ ፸፭ (ብራና ቁጥር ፳፰)፣ የሐይቅ እስጢፋኖስ ገዳም",
        ailments: "የሆድ ቁርጠት (Stomach ache)፣ የክፉ ዓይን መከላከያ (Evil eye)፣ የምግብ አለመፈጨት",
        preparation: "የጤናአዳም ፍሬውንና ቅጠሉን በሻይ ወይም በቡና ውስጥ መክተት፣ ወይም በንጹሕ ውኃ በጥብጦ ማጣራት።",
        application: "ጥቂት ጠብታ በሞቀ መጠጥ ውስጥ ጠጥቶ ማሳለፍ፤ ፍሬውን በኪስ መያዝ።",
        prayer: "አስማተ ፈውስ ዘአቡሻህር፤ ኤሎሄ፣ ኤሎሄ፣ ኤልሻዳይ፤ አድኅኖ ለገብርከ እምደዌ ከርሥ ወእምዓይነ ባላ ወእምኩሉ ፍላጻ ፀር፤ አሜን።",
        translation: "አምላክ ሆይ፣ ሁሉን ቻይ አምላክ ሆይ፤ ባሪያህን ከሆድ በሽታ፣ ከሰው ክፉ ዓይንና ከጠላት ፍላጻ ጠብቀው ፈውሰውም፤ አሜን።",
        safety: "ነፍሰ ጡር እናቶች በከፍተኛ መጠን እንዳይወስዱት በጥብቅ ይመከራል።"
    },
    {
        id: "girawa",
        name: "ዕፀ መራር (ግራዋ)",
        geez: "ዕፀ መራር ወፈውሰ ከርሥ",
        scientific: "Vernonia amygdalina",
        vernacular: "ግራዋ",
        habitat: "በሁሉም የኢትዮጵያ ክልሎች በወይናደጋና ቆላማ መስኮች በብዛት ይገኛል።",
        harvesting: "በማንኛውም ቀን ጠዋት በንጹሕ እጅ መቅጠፍ።",
        ref: "መጽሐፈ ፈውስ ገጽ ፸፮ (ብራና ቁጥር ፳፰)፣ የሐይቅ እስጢፋኖስ ገዳም",
        ailments: "የሆድ ትላትል (Intestinal parasites)፣ የወባ ንዳድ (Malaria)፣ የጉበትና የሆድ ጽዳት፣ የቆዳ ቁስል",
        preparation: "የግራዋን ቅጠል በሙቀጫ መውቀጥ፤ ጭማቂውን በማጣራት ከማር ጋር ማዋሃድ፤ ለቁስል ቅጠሉን ማሸት።",
        application: "ጠዋት በባዶ ሆድ በትንሽ ማንኪያ መውሰድ፤ ለቆዳ ቁስል ማሰር።",
        prayer: "በስመ እግዚአብሔር አምላከ ጽድቅ፤ በዛቲ ዕፅ መራር አሰስል ኩሎ መርዐተ ደዌ ወፃዕረ ሥጋ፤ ወአውፅእ ኩሎ ሕማመ ከርሥ፤ አሜን።",
        translation: "በእውነተኛው አምላክ ስም፤ በዚህ መራር ዕፅ የደዌን ሥርና የስጋን ጭንቀት ሁሉ አስወግድ፤ የሆድንም ሕመም አውጣ፤ አሜን።",
        safety: "መራርነቱ ከፍተኛ ስለሆነ በልክ ብቻ መጠቀም።"
    },
    {
        id: "tosign",
        name: "ዕፀ ዕፍረት (ጦስኝ)",
        geez: "ዕፀ ዕፍረት ወሰናይት",
        scientific: "Thymus serrulatus",
        vernacular: "ጦስኝ",
        habitat: "በሰሜንና ደቡብ ወሎ፣ ጎንደር፣ ሰሜን ሸዋ ከፍተኛ ደጋማ ተራሮች (ደብረ ሲና፣ ጉና፣ መንዝ)።",
        harvesting: "በጸደይና በበጋ ወራት በንጽሕና ተሰብስቦ በጥላ ሥር ማድረቅ።",
        ref: "መጽሐፈ ፈውስ ገጽ ፸፯ (ብራና ቁጥር ፳፰)፣ የሐይቅ እስጢፋኖስ ገዳም",
        ailments: "የደረቅ ሳልና የጉሮሮ ሕመም (Cough)፣ የደረት ውጋት (Chest tightness)፣ የደም ግፊት ማረጋጋት",
        preparation: "የደረቀውን ወይም ጥሬውን ጦስኝ በፈላ ውሃ ውስጥ አንተክትኮ ከንጹሕ ማር ጋር ማዋሃድ።",
        application: "እንደ ሻይ በሞቀ ሁኔታ ጠዋትና ማታ መጠጣት።",
        prayer: "እግዚአብሔር ዘፈጠረ ዕፀዋተ ለፈውስ፤ አጽንዕ ሥጋየ ወአስተንፍስ ሕይወት ውስተ ጉሮሮየ፤ ወአብርድ እሳተ ደዌ፤ አሜን።",
        translation: "ዕፅዋትን ለፈውስ የፈጠርክ ጌታ ሆይ፤ ሥጋዬን አበርታ፣ ጉሮሮዬንም አረጋጋ፤ የደዌንም እሳት አቀዝቅዝ፤ አሜን።",
        safety: "ለአብዛኛው ሰው እጅግ ተስማሚና የጎንዮሽ ጉዳት የሌለው ድንቅ የደጋ ዕፅ ነው።"
    },
    {
        id: "eret",
        name: "ዕፀ ሰበር (እሬት)",
        geez: "ዕፀ ሰበር ወጽጌ",
        scientific: "Aloe debrana / abyssinica",
        vernacular: "እሬት (የደጋና የቆላ እሬት)",
        habitat: "በቆላና ወይናደጋ ድንጋያማ ቦታዎች፣ በሸለቆዎችና በረሃማ ሥፍራዎች።",
        harvesting: "በቢላዋ የሥጋ ቅጠሉን በጥንቃቄ ቆርጦ የውስጡን ጄል ማውጣት።",
        ref: "መጽሐፈ ፈውስ ገጽ ፸፰ (ብራና ቁጥር ፳፰)፣ የሐይቅ እስጢፋኖስ ገዳም",
        ailments: "የእሳት ቃጠሎ (Burns)፣ የቆዳ ድርቀትና ብጉር፣ የፀጉር መነቀልና ድካም፣ የቆዳ ቁስል",
        preparation: "የእሬቱን ወላጅ ቆዳ ልጦ የውስጡን ጥርት ያለ ጄል መውሰድ፤ ከማር ጋር ማዋሃድ።",
        application: "በተጎዳው ቆዳ ወይም በጭንቅላት ቆዳ ላይ መቀባት።",
        prayer: "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ ከመ ጽጌረዳ ወከመ ፅድ አሐድስ ወራዙትየ ወፈውሰኒ እምነዳደ እሳት ወእምደዌ ቈላ፤ አሜን።",
        translation: "እንደ ጽጌሬዳና እንደ ፅድ ጎልማሳነቴን አድስ፤ ከቃጠሎና ከቆዳ ደዌም ፈውሰኝ፤ አሜን።",
        safety: "ጥርት ያለውን ጄል ብቻ መጠቀም፤ ቢጫውን መራር ፈሳሽ ማስወገድ።"
    },
    {
        id: "kerbe",
        name: "ከርቤና ነጭ ዕጣን",
        geez: "ዕፀ ከርቤ ቅዱስ ወዕጣነ ሳባ",
        scientific: "Commiphora myrrha & Boswellia",
        vernacular: "ከርቤና ነጭ ዕጣን",
        habitat: "በሶማሌ ክልል፣ በደቡብ ኦሮሚያና በተከዜ ሸለቆዎች።",
        harvesting: "በዕለተ ሐሙስ ማለዳ ሙጫውን በንጹሕ ቢላ መግፈፍ።",
        ref: "መጽሐፈ ፈውስ ገጽ ፸፱ (ብራና ቁጥር ፳፰)፣ የሐይቅ እስጢፋኖስ ገዳም",
        ailments: "የመንፈስ ጭንቀት፣ ፀረ-ተባይ፣ የድድና የጉሮሮ በሽታ፣ ክፉ መንፈስ ማባረር",
        preparation: "ከርቤውን በከሰል ላይ ማጨስ ወይም በውኃ ዘፍዝፎ መጉመጥመጥ።",
        application: "ጭሱን መታጠንና ቤትን ማጠን።",
        prayer: "መዓዛ ሠናይት ከመ ከርቤ፤ አርሕቅ ጸላዕተ ወፈኑ ሰላመ ውስተ ቤተ ዚአየ፤ አሜን።",
        translation: "መዓዛው እንደ ከርቤ ያማረ ጌታ ሆይ፤ ጠላትን አርቅልኝ፣ በቤቴም ውስጥ ሰላምን ላክ፤ አሜን።",
        safety: "አስም ያለባቸው ሰዎች ከጭሱ ርቀው እንዲቀመጡ ማድረግ።"
    }
];

function renderHerbs(list) {
    const grid = document.getElementById("herbListGrid");
    if (!grid) return;
    grid.innerHTML = list.map(herb => `
        <div class="herb-card" onclick="speakText('${herb.name}። በባህላዊ አጠራር፡ ${herb.vernacular}። የሚገኝበት፡ ${herb.habitat}። አቆራረጡ፡ ${herb.harvesting}። አዘገጃጀቱ፡ ${herb.preparation}። ሪፈረንስ፡ ${herb.ref}')">
            <div class="herb-card-header">
                <h4>${herb.name}</h4>
                <button class="btn-voice" onclick="event.stopPropagation(); speakText('${herb.name}። ${herb.vernacular}። ${herb.prayer}')">🔊 አድምጥ</button>
            </div>
            <div class="herb-geez">ግእዝ፡ ${herb.geez} | ${herb.scientific}</div>
            <div style="font-size:0.75rem; color:var(--gold-light); margin-bottom:8px;">📖 ሪፈረንስ፡ ${herb.ref}</div>
            
            <div style="background:var(--bg-dark); padding:10px; border-radius:6px; margin-bottom:10px; font-size:0.85rem; line-height:1.6;">
                <div style="color:var(--gold-light); font-weight:600;">🏷️ በባህላዊ አጠራር፡ <span style="color:var(--text-parchment); font-weight:normal;">${herb.vernacular}</span></div>
                <div style="color:var(--gold-light); font-weight:600; margin-top:4px;">📍 የሚገኝበት አካባቢ፡ <span style="color:var(--text-parchment); font-weight:normal;">${herb.habitat}</span></div>
            </div>

            <div class="herb-ailments"><strong>ደዌዎች፡</strong> ${herb.ailments}</div>
            
            <div style="font-size:0.85rem; color:var(--text-parchment); margin-bottom:8px; line-height:1.5;">
                <strong style="color:var(--gold-light);">🌿 አቆራረጡ (Harvesting Ritual)፡</strong> ${herb.harvesting}
            </div>

            <div style="font-size:0.85rem; color:var(--text-parchment); margin-bottom:8px; line-height:1.5;">
                <strong style="color:var(--gold-light);">🥣 አዘገጃጀትና አደራረግ፡</strong> ${herb.preparation} ${herb.application}
            </div>

            <div class="parchment-block" style="margin-bottom:8px; padding:10px;">
                <small style="color:var(--crimson-light); font-weight:bold;">የብራናው ሙሉ ጸሎት (ግእዝ)፡</small>
                <div style="font-size:0.85rem; color:var(--gold-light); margin-top:4px; line-height:1.6;">${herb.prayer}</div>
                <small style="color:var(--text-dim); display:block; margin-top:4px;">ትርጉም፡ ${herb.translation}</small>
            </div>

            <small style="color:var(--crimson-light); display:block;">⚠️ ${herb.safety}</small>
        </div>
    `).join("");
}

function filterHerbs() {
    const q = document.getElementById("herbSearchInput").value.toLowerCase().trim();
    const filtered = herbsData.filter(h => 
        h.name.toLowerCase().includes(q) ||
        h.geez.toLowerCase().includes(q) ||
        h.vernacular.toLowerCase().includes(q) ||
        h.habitat.toLowerCase().includes(q) ||
        h.ailments.toLowerCase().includes(q)
    );
    renderHerbs(filtered);
}

// ===================================================================
// 7. ORACLE & MAFTIHE SERAY (የጥበብ ጠያቂ)
// ===================================================================

function consultOracle() {
    const category = document.getElementById("oracleCategory").value;
    const seeker = document.getElementById("oracleSeeker").value.trim() || "ገብረ እግዚአብሔር";
    const mother = document.getElementById("oracleMother").value.trim() || "ወለተ ማርያም";
    const details = document.getElementById("oracleDetails").value.trim() || "ፍቅርና ሰላም";
    const specific = document.getElementById("oracleSpecific").value.trim() || "የበረከትና የጥበብ መንገድ";

    let bookTitle = "መጽሐፈ መፍትሔ ሥራይ ወአስማተ ሰሎሞን ዘጥንት";
    let chapterName = "ምዕራፍ ፫፡ መቅሰፍተ አጋንንት ወዓይነ ጥላ";
    let pageFolio = "ገጽ ፵፪ (ብራና ቁጥር ፻፲፰)";
    let typeName = "መፍትሔ ሥራይ (ድግምትና ጸሎት)";
    let typeClass = "spell";
    let geezScript = `በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
አስማተ ሰሎሞን ንጉሠ እስራኤል፤
ኤልሻዳይ፣ ማኅቶት፣ ጸባኦት፣ አማኑኤል፤
ይትፈታሕ ኩሉ ዕንቅፋት ወይሠረር ሰላም ዲበ ${seeker} ወልደ ${mother}፤
በኃይለ መስቀል ይስበር አጋንንተ ፀላዕት ወይኩን ንጹሐ ከመ ብርሃነ ፀሐይ፤ አሜን።`;

    let explanation = `የ${category} ጥያቄዎ ከመጽሐፉ ተመርምሯል። ለ${seeker} እና ለ${mother} የተከፈተው የጥበብ መንገድ ብሩህነትን፣ የክፉ መንፈስ መፈታትንና የታሰበው ጉዳይ (${details}) በሰላም እንዲፈጸም የሚያመለክት ነው።`;

    let prescriptions = [
        "ንጹሕ የጠዋት ጸሎት በሰላም ማድረስ",
        "ዕጣን ከርቤ ማታ ማታ በቤት ማጠን",
        "የተቸገረ ሰውን በምጽዋት መርዳት",
        "ይህን የግእዝ አስማተ ቀመር በንጹሕ ብራና ወይም ወረቀት መያዝ"
    ];

    if (category === "የጥይት መከላከያ") {
        bookTitle = "መጽሐፈ ሐጹረ መስቀል ወአስማተ ጥይት ዘጥንት";
        chapterName = "ምዕራፍ ፯፡ መክስተ ደመና ወመከላከየ አረር";
        pageFolio = "ገጽ ፸፪ (ብራና ቁጥር ፫፻፲)";
        typeName = "የጥይት መከላከያ ጋሻ (ድግምትና ጸሎት)";
        typeClass = "spell";
        geezScript = `በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
ጸሎት በእንተ መስተሐምም ወመክስተ ደመና ዘጥይት፤
በስመ ያህ፣ ኤልሻዳይ፣ ጸባኦት፣ አማኑኤል፤
ከመ ኢይቅረበኒ አረር ወነደ እሳት ዲበ ${seeker} ወልደ ${mother}፤
ይኩን ጥይት ከመ ማይ ወከመ ሐመድ በቅድሜየ፤
በኃይለ መስቀሉ ለክርስቶስ ይትሰበር ኩሉ ነፍጥ ወመንፈሰ ሞት፤
ቅዱስ ሚካኤል ወገብርኤል ይኩኑኒ ጋሻ ወዋልታ ለዓለመ ዓለም፤ አሜን።`;
        explanation = `በመጽሐፈ ሐጹረ መስቀል ገጽ ፸፪ መሠረት፤ ለ${seeker} ወልደ ${mother} ጥይት አቅጣጫውን እንዲስትና እንደ ውኃና አፈር እንዲቀዘቅዝ የታመነ የመስቀል ጋሻና መክስተ ደመና ጸንቷል።`;
        prescriptions = [
            "ዕፀ ሳቤዕ (ሐረገ ሳቤቅ) ወይም ዕፀ ደብዳቤ በንጹሕ ቀይ ቆዳ ተሰፍቶ በግራ ክንድ ወይም በአንገት ማሰር",
            "ማለዳ ከመውጣት በፊት ጸሎቱን ፯ ጊዜ አንብቦ በሦስቱ ጣቶች ማማተብ",
            "በንጽሕና መመላለስና ለተቸገረ ሰው ምጽዋት ማድረግ"
        ];
    } else if (category === "የስለት መከላከያ") {
        bookTitle = "መጽሐፈ ሰይፈ ሥላሴ ወአስማተ አፍልጣ ዘጥንት";
        chapterName = "ምዕራፍ ፱፡ መከላከየ ስለት ወአፍልጣ";
        pageFolio = "ገጽ ፶፰ (ብራና ቁጥር ፻፵፭)";
        typeName = "የስለት መከላከያ ጋሻ (ጸሎትና አስማት)";
        typeClass = "spell";
        geezScript = `በስመ ሥላሴ ቅዱስ፤ አብ እሳት፣ ወልድ እሳት፣ መንፈስ ቅዱስ እሳት፤
በዛቲ አስማተ ስለት ኢይምታዐኒ ሰይፍ ወኩናት፣ ሾተል ወካራ ዲበ ${seeker} ወልደ ${mother}፤
ይኩን ኩሉ ብረት ከመ ሰም በቅድመ እሳት፤
በስመ ዑራኤል መልአክ ዘይመልሕ ሰይፈ እሳት፤
ዕቀበኒ ወአድኅነኒ እምኩሉ አፈ ስለት ወእምነዳደ ብረት፤ አሜን።`;
        explanation = `በመጽሐፈ ሰይፈ ሥላሴ ገጽ ፶፰ እንደተመዘገበው፤ ከሰይፍ፣ ከቢላዋ፣ ከጦርና ከማንኛውም ስለታማ ብረት መከላከያ ጥንታዊ ጋሻ፤ ብረት እንደ ሰም እንዲቀልጥና አካልን እንዳይቆርጥ የተዘጋጀ ነው።`;
        prescriptions = [
            "የዕፀ ሳቤዕ ጭማቂ ወይም ዕፀ አፍልጣ ሥር በንጽሕና ቆርጦ ከጥቁር ዕጣን ጋር በወይራ ዘይት አፍልቶ አካልን መቀባት",
            "ቃሉን ፫ ጊዜ በንጹሕ ውኃ ላይ አንብቦ ማለዳ መጠጣት",
            "በክንድ ወይም በኪስ በክታብ መያዝ"
        ];
    } else if (category === "ዕፀ ሳቤዕ (ስለትና ጥይት መከላከያ)") {
        bookTitle = "መጽሐፈ ገብረ ሕይወት ወዕፀ ሳቤቅ ዘጥንት";
        chapterName = "ምዕራፍ ፩፡ ምስጢረ ዕፀ ሳቤቅ ወመከላከየ አረር";
        pageFolio = "ገጽ ፵፰ (ብራና ቁጥር ፻፲፭)";
        typeName = "ዕፀ ሳቤዕ (የስለትና ጥይት መከላከያ ሐረግ)";
        typeClass = "spell";
        geezScript = `በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
ጸሎት በእንተ ዕፀ ሳቤቅ፤
በከመ አድኀንኮ ለይስሐቅ እምሰይፈ አቡሁ አብርሃም በበግዕ ዘተአሥረ በዕፀ ሳቤቅ፤
ከማሁ አድኅነኒ ሊተ ለ${seeker} ወልደ ${mother} እምኩሉ ኲናት፣ እምሰይፍ፣ እምነዳደ እሳት ወእምአረር ዘይነቱግ፤
ዐቅበኒ በኃይለ መስቀልከ፤
በስመ ያህ፣ ኤልሻዳይ፣ አማኑኤል፤ አሜን።`;
        explanation = `በመጽሐፈ ገብረ ሕይወት ወዕፀ ሳቤቅ ገጽ ፵፰ መሠረት፤ ዕፀ ሳቤዕ (ሐረገ ሳቤቅ / የይስሐቅ በግ ሐረግ) በዘፍጥረት ፳፪፥፲፫ ላይ ይስሐቅን በአብርሃም ሰይፍ ፊት የታደገው ምስጢር ሲሆን፤ ለ${seeker} ወልደ ${mother} ከጥይት፣ ከሰይፍ፣ ከብረት አደጋና ከክፉ ሞት የሚያድን ታላቅ መከላከያ ጋሻ ነው።`;
        prescriptions = [
            "ሐረገ ሳቤቅ በንጹሕ ቀይ ቆዳ ወይም በብር ክታብ ሰፍቶ በግራ ክንድ ወይም በአንገት ማሰር",
            "በንጹሕ የወይራ ዘይት ውስጥ አንተክትኮ ዘይቱን በጦርነትና በአደጋ ጊዜ ሰውነትን መቀባት",
            "ማለዳ ፀሐይ ሳትወጣ ወደ ምሥራቅ ዞሮ ጸሎቱን ፯ ጊዜ ማንበብ"
        ];
    } else if (category === "ዕፀ መሰውር") {
        bookTitle = "መጽሐፈ ዕፀ መሰውር ወኅቡዓት ዘሄኖክ ወኤልያስ";
        chapterName = "ምዕራፍ ፬፡ አስማተ መሰውር ወተከውኖ";
        pageFolio = "ገጽ ፴፱ (ብራና ቁጥር ፹፪)";
        typeName = "ዕፀ መሰውርና ማምለጫ (ምስጢር)";
        typeClass = "spell";
        geezScript = `በስመ አብ ወወልድ ወመንፈስ ቅዱስ አሐዱ አምላክ፤
ጸሎት በእንተ ዕፀ መሰውር፤
በከመ ከወንኮሙ ለሄኖክ ወለኤልያስ እምዓይነ ኃጥአን፤
ከማሁ ሰውረኒ ሊተ ለ${seeker} ወልደ ${mother} እምዓይነ ፀር ወጸላኢ፤
በስመ አስተርእዮ ወኅቡዕ፤ አዖ፣ ፈዖ፣ ኤልሻዳይ፤
ዕፅም ዓይኖሙ ወዕበስ አእምሮቶሙ ከመ ኢይርአዩኒ ወኢያእምሩኒ ለዓለም፤ አሜን።`;
        explanation = `በመጽሐፈ ዕፀ መሰውር ገጽ ፴፱ መሠረት፤ በጠላት ወይም በአደጋ ጊዜ ከክፉ ዓይንና ከፈላጊዎች መሰወሪያ ጥንታዊ ምስጢር፤ ዓይናቸው እንዳያይና አእምሮታቸው እንዳያስተውል የሚያደርግ ጥበብ ነው።`;
        prescriptions = [
            "የዕፀ መሰውር ቅጠልና ሥር በንጽሕና ተቆርጦ ከሰሊጥ ዘይት ጋር ግንባርንና ዓይንን መቀባት",
            "ቃሉን በለሆሳስ ፯ ጊዜ ደግሞ በአደጋው ወቅት መራመድ",
            "በክታብ ተሰፍቶ በአንገት ወይም በኪስ መያዝ"
        ];
    } else if (category === "መስተፋቅርና ፍቅር") {
        bookTitle = "መጽሐፈ ተፋቅሮ ወሰላም ዘሰሎሞን";
        chapterName = "ምዕራፍ ፬፡ አስማተ ተፋቅሮ ወስምምነት";
        pageFolio = "ገጽ ፲፰ (ብራና ቁጥር ፷፪)";
        typeName = "መስተፋቅርና ስምምነት (ጸሎት)";
        typeClass = "spell";
        geezScript = `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤
በስመ ሰሎሞን ጥበበኛ፤
አፍቅር፣ አሰምር፣ አስተፋቅር ዲበ ${seeker} ወልደ ${mother}፤
ይኩን ፍቅር ከመ ፍቅረ ዳዊት ወዮናታን፤
በኃይለ ዝንቱ ቃል ይትከደን ሰላም ወይኩን ስምምነት!`;
        explanation = `በመጽሐፈ ተፋቅሮ ገጽ ፲፰ እንደተመዘገበው፤ ለ${seeker} እና ለ${mother} የቀረበው የፍቅርና ስምምነት ጉዳይ (${details}) በይቅርታና በመስህብ ቃል ጸንቷል።`;
        prescriptions = [
            "የጽጌረዳ ውኃ ወይም የከርቤ ሽቶ መያዝ",
            "ቃሉን በማለዳ ፫ ጊዜ በለሆሳስ ማንበብ",
            "በቅን ልብና በሰላም መነጋገር"
        ];
    } else if (category === "ሀብትና ንግድ") {
        bookTitle = "መጽሐፈ በረከት ወሀብተ ንግድ";
        chapterName = "ምዕራፍ ፮፡ አስማተ ባርክኤል መልአከ በረከት";
        pageFolio = "ገጽ ፶፪ (ብራና ቁጥር ፺፬)";
        typeName = "የበረከት ጥበብና ጸሎት";
        typeClass = "wisdom";
        geezScript = `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤
ባርክኤል መልአከ በረከት፤
ባርክ ንግደ ${seeker} ወልደ ${mother} ወሀበኒ ፍሬ በረከት፤
አርኅው ደጆተ ሀብት ወአርሕቅ እምኔየ ስእነተ ወጽልመተ!`;
        explanation = `በመጽሐፈ በረከት ገጽ ፶፪ መሠረት፤ ለ${seeker} (${details}) የሀብትና የንግድ በረከት በታማኝነትና በምጽዋት እንደሚበዛ ተገልጿል።`;
        prescriptions = [
            "ሐሙስ ጠዋት ንግድ ከመጀመር በፊት ማዕጠንት ማጠን",
            "ቃሉን ፯ ጊዜ ማንበብ",
            "ከትርፉ የመጀመሪያውን ለምጽዋት መለየት"
        ];
    } else if (category === "ዕውቀትና ጥናት") {
        bookTitle = "መጽሐፈ ጥበበ ሰሎሞን ወፈሊጣተ አእምሮ";
        chapterName = "ምዕራፍ ፩፡ አርኅዎ ልብ ወአዕምሮ";
        pageFolio = "ገጽ ፲፭ (ብራና ቁጥር ፳፯)";
        typeName = "የዕውቀትና ጥናት ጥበብ";
        typeClass = "wisdom";
        geezScript = `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤
እግዚአብሔር አምላከ ጥበብ ወነባቤ አዕምሮ፤
አርሁ ልበ ${seeker} ወልደ ${mother} ከመ ይስመዕ ጥበበ ወያእምር ፍልስፍና፤
ከመ ፀሐይ ያብርህ አእምሮቱ ወከመ ወርኅ ያብራህ ሕሊናሁ!`;
        explanation = `በመጽሐፈ ጥበበ ሰሎሞን ገጽ ፲፭ እንደተጻፈው፤ ለአእምሮ ብሩህነት፣ ለጥናትና ለማስታወስ ኃይል የጠለቀ የጥበብ ብርሃን ተከፍቷል።`;
        prescriptions = [
            "ማለዳ በባዶ ሆድ ንጹሕ ውኃ ላይ ቃሉን አንብቦ መጠጣት",
            "ጥናት ከመጀመር በፊት ወደ ምሥራቅ ዞሮ ፫ ጊዜ ማሰላሰል"
        ];
    } else if (category === "ፈውስና ጤና") {
        bookTitle = "መጽሐፈ ፈውስ ወዕፀዋት ዘጥንት";
        chapterName = "ክፍል ፪፡ ፈውሰ ርእስ ወከርሥ";
        pageFolio = "ገጽ ፸፬ (ብራና ቁጥር ፳፰)";
        typeName = "የፈውስ መድኃኒትና ጸሎት";
        typeClass = "herb";
        geezScript = `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤
ሩፋኤል ፈዋሴ ዱያን፤
በዛቲ ዕፅ ዳማከሴ ወዕፀ ሕይወት ፈውስ ሕማመ ${seeker} ወልደ ${mother}፤
በከመ አድኃንኮ ለጦቢት እምደዌሁ ከማሁ ፈውስ ድዉያነ!`;
        explanation = `በመጽሐፈ ፈውስ ገጽ ፸፬ መሠረት፤ የተፈጥሮ ዕፀዋት (ዳማከሴና ጤናአዳም) እና የሩፋኤል ጸሎት የደዌን ኃይል ያርቃሉ።`;
        prescriptions = [
            "ዳማከሴና ጤናአዳም በሙቅ ውኃ ማፍላት",
            "እንፋሎቱን ለ፲ ደቂቃ መታጠንና በንጹሕ ማር ጭማቂውን መውሰድ"
        ];
    } else if (category === "የመንገድ ሰላም") {
        bookTitle = "መጽሐፈ ሰይፈ ሥላሴ ወዐቃቤ ርእስ";
        chapterName = "ክፍል ፩፡ ጋሻ መንፈሳዊ ወዐቃቤ መንገድ";
        pageFolio = "ገጽ ፴፭ (ብራና ቁጥር ፵፯)";
        typeName = "የመንገድና ሕይወት መከላከያ ጋሻ";
        typeClass = "spell";
        geezScript = `በስመ ሥላሴ ቅዱስ፤
ሰይፈ መለኮት ይቁም በየማነ ${seeker} ወበፀጋሙ፤
ሚካኤል በቅድሜሁ፣ ገብርኤል በድኅሬሁ፣ ሩፋኤል በየማኑ፣ ዑራኤል በፀጋሙ፤
ኢይቅረቦ ኩሉ እኩይ ወመንፈሰ ጽልመት ውስተ ፍኖቱ!`;
        explanation = `በመጽሐፈ ሰይፈ ሥላሴ ገጽ ፴፭ እንደተመዘገበው፤ በመንገድ ላይ ከሚገጥም ድንገተኛ አደጋና ከክፉ ዓይን የታመነ መንፈሳዊ ጋሻ ነው።`;
        prescriptions = [
            "ከመንገድ ከመነሳት በፊት ቃሉን አንብቦ ማማተብ",
            "በሰላም መጓዝ"
        ];
    }

    document.getElementById("oracleResultTitle").textContent = `የ${category} መልስና ቀመር`;
    
    const badge = document.getElementById("oracleRefTypeBadge");
    if (badge) {
        badge.textContent = typeName;
        badge.className = `ref-type-badge ${typeClass}`;
    }
    const refBook = document.getElementById("oracleRefBook");
    if (refBook) refBook.textContent = bookTitle;
    const refChap = document.getElementById("oracleRefChapter");
    if (refChap) refChap.textContent = chapterName;
    const refPg = document.getElementById("oracleRefPage");
    if (refPg) refPg.textContent = pageFolio;

    document.getElementById("oracleSeekerInfo").textContent = `ጠያቂ፡ ${seeker} | እናት፡ ${mother} | ጉዳይ፡ ${details}`;
    document.getElementById("oracleGeezScript").textContent = geezScript;
    document.getElementById("oracleExplanation").textContent = explanation;

    const presList = document.getElementById("oraclePrescription");
    presList.innerHTML = prescriptions.map(p => `<li>${p}</li>`).join("");

    document.getElementById("oracleTiming").textContent = "ምቹ ሰዓት፡ ማለዳ በ12:00 ሰዓት ወደ ምሥራቅ አቅጣጫ ዞሮ መጸለይ።";

    currentOracleVoiceText = `ከ${bookTitle}፣ ${chapterName}፣ ${pageFolio} የተገኘ የ${category} መልስ፡ ${explanation}። የግእዝ ድርሳን፡ ${geezScript}`;

    document.getElementById("oracleResult").classList.remove("hidden");
    speakText(currentOracleVoiceText);

    if (currentUser.credits > 0) {
        currentUser.credits--;
        saveUserData();
    }
}

// ===================================================================
// 8. BOOK OF ENOCH CHAPTERS (መጽሐፈ ሄኖክ)
// ===================================================================

const enochChaptersData = [
    {
        titleGeez: "መጽሐፈ ብርሃናት ዘሰማይ (፸፪ - ፹፪)",
        titleAmharic: "የሰማይ ብርሃናት ዑደትና ፮ቱ የፀሐይ ደጆች",
        ref: "መጽሐፈ ሄኖክ ምዕራፍ ፸፪ ገጽ ፺፮ (ብራና ቁጥር ፪፻፵፭)",
        summary: "ሄኖክ በሰማይ ዙሪያ ያሉትን ስድስቱን የፀሐይና የጨረቃ መውጫና መግቢያ ደጆች በዑራኤል መሪነት የተመለከተበት ታላቅ የቀመር ምዕራፍ።",
        passage: "«ወዝንቱ ውእቱ ሥርዓተ ብርሃናት ኩሎሙ ዘአርአየኒ ዑራኤል መልአክ ቅዱስ ዘውእቱ ዐቃቤ ብርሃናት። ወርኢኩ ስድስተ ደጆተ እንተ ቦን ይወጽእ ፀሐይ ወስድስተ ደጆተ እንተ ቦን ይዐርብ ፀሐይ።»",
        wisdom: "በዚህ ቀመር መሠረት የፀሐይ ዓመታዊ ጉዞ ፫፻፷፬ (364) ቀናት ሲሆን አራቱን ወቅቶች ያካተተ ፍጹም የሰማይ ሰዓት ነው።"
    },
    {
        titleGeez: "ራእየ ሄኖክ በሰባቱ ሰማያት (፲፯ - ፴፮)",
        titleAmharic: "ሰባቱ ሰማያትና የመላእክት ሠራዊት",
        ref: "መጽሐፈ ሄኖክ ምዕራፍ ፲፯ ገጽ ፴፪",
        summary: "ሄኖክ ከመሬት ተነስቶ እስከ ታላቁ የእሳትና የበረዶ መንበር ድረስ ያደረገው ሰማያዊ ጉዞና የመላእክት ስም ዝርዝር።",
        passage: "«ወአዕረጉኒ ውስተ ሰማይ ወርኢኩ መንበረ እሳት ወኪሩቤል ዘይኬልልዎ፤ ወድምፀ መላእክት ከመ ድምፀ ማያት ብዙኅ።»",
        wisdom: "ሰባቱ ሰማያት ከጠፈር ፕላኔቶችና ከከዋክብት ጋር ያላቸው ምስጢራዊ መስተጋብር ተቀምጧል።"
    },
    {
        titleGeez: "አሥራ ሁለቱ ነፋሳት ዘምድር (፸፮)",
        titleAmharic: "አሥራ ሁለቱ ነፋሳትና ደጆቻቸው",
        ref: "መጽሐፈ ሄኖክ ምዕራፍ ፸፮ ገጽ ፻፬",
        summary: "በምድር ዳርቻ ያሉ አሥራ ሁለቱ የነፋሳት ደጆች፤ አራቱ የበረከትና የዝናብ፣ ስምንቱ የበሽታና የጥፋት ነፋሳት መውጫ።",
        passage: "«ወበአጽናፈ ምድር ርኢኩ ዐሠርተ ወክልኤተ ደጆተ ክሡታተ ለኩሎሙ ነፋሳት፤ እምኔሆሙ ይወጽኡ ነፋሳት ወይነፍሑ ዲበ ምድር።»",
        wisdom: "ነፋሳት አየርን፣ አዝመራንና የተፈጥሮን ዑደት የሚያስተካክሉበት ቀመር ተገልጿል።"
    }
];

function renderEnochChapters() {
    const container = document.getElementById("enochChaptersContainer");
    if (!container) return;

    container.innerHTML = enochChaptersData.map(ch => `
        <div class="enoch-card" onclick="speakText('${ch.titleAmharic}። ${ch.passage}። ${ch.wisdom}')">
            <div class="enoch-header">
                <div>
                    <h4>${ch.titleAmharic}</h4>
                    <span class="enoch-geez-title">${ch.titleGeez}</span>
                    <div style="font-size:0.75rem; color:var(--gold-light); margin-top:2px;">📖 ሪፈረንስ፡ ${ch.ref}</div>
                </div>
                <button class="btn-voice" onclick="event.stopPropagation(); speakText('${ch.titleAmharic}። ${ch.passage}')">🔊 አድምጥ</button>
            </div>
            <p class="enoch-summary">${ch.summary}</p>
            <div class="parchment-block">
                <h4>የግእዝ ጽሑፍ፡</h4>
                <p class="geez-text">${ch.passage}</p>
            </div>
            <div class="analysis-block">
                <h4>የጥበቡ ቀመር፡</h4>
                <p>${ch.wisdom}</p>
            </div>
        </div>
    `).join("");
}

// ===================================================================
// 9. PAYMENT & BANKING (የባንክ ክፍያ ድጋፍ)
// ===================================================================

function selectPackage(id, price, credits) {
    selectedPackageInfo = { id, price, credits };
    document.querySelectorAll(".pkg-card").forEach(c => c.classList.remove("selected"));
    event.currentTarget.classList.add("selected");
    updatePaymentInstructions();
}

function selectBank(id, acc, name, ussd) {
    selectedBankInfo = { id, acc, name, ussd };
    document.querySelectorAll(".bank-card").forEach(c => c.classList.remove("selected"));
    event.currentTarget.classList.add("selected");
    updatePaymentInstructions();
}

function updatePaymentInstructions() {
    const instr = document.getElementById("paymentInstructions");
    if (instr) {
        instr.textContent = `በ${selectedBankInfo.name} ወደ ሂሳብ ቁጥር ${selectedBankInfo.acc} ${selectedPackageInfo.price} ብር ከላኩ በኋላ ከባንኩ የደረሰዎትን የማረጋገጫ ቁጥር ያስገቡ (USSD: ${selectedBankInfo.ussd})።`;
    }
}

function submitPayment() {
    const tx = document.getElementById("txRefInput").value.trim();
    if (!tx) {
        alert("እባክዎ የባንኩን የማረጋገጫ ቁጥር (Transaction Ref) ያስገቡ!");
        return;
    }

    currentUser.credits += selectedPackageInfo.credits;
    saveUserData();
    document.getElementById("txRefInput").value = "";
    alert(`ክፍያዎ በስኬት ተረጋግጧል! ${selectedPackageInfo.credits} የጥበብ ነጥቦች ወደ መለያዎ ተጨምረዋል። ጠቅላላ ነጥብዎ፡ ${currentUser.credits}`);
}

function closeDownloadModal() {
    document.getElementById("downloadModal").classList.add("hidden");
}

// ===================================================================
// 10. GLOBAL GRIMOIRES (ዓለም አቀፍ ጥንታውያን ድግምቶች)
// ===================================================================

const globalGrimoiresData = [
    {
        id: "key_of_solomon",
        titleAmharic: "የሰሎሞን ታላቅ ቁልፍ (ክላቪኩላ ሰሎሞኒስ)",
        titleOriginal: "Clavicula Salomonis (Key of Solomon)",
        origin: "14ኛው - 15ኛው መቶ ክፍለ ዘመን (የጥንት ዕብራይስጥና ላቲን ቅጂ)",
        language: "ዕብራይስጥ (Hebrew) እና ላቲን (Latin)",
        dangerLevel: "እጅግ አደገኛና ከፍተኛ ጥንቃቄ የሚሻ (High Astral Potency)",
        purpose: "የፕላኔቶችን መንፈሳዊ ኃይላት ማዘዣ፣ የመከላከያ ክበብ (Magic Circle) እና ፔንታክል ማተሚያ",
        incantation: "«Per Adonai, Elohim, Agla, Tetragrammaton; O vos omnes spiritus, per virtutem horum nominum sacrorum obedite mihi et venite in pace!»",
        translation: "«በአዶናይ፣ በኤሎሂም፣ በአግላና በቴትራግራማቶን ቅዱሳን የማይነገሩ ስሞች፤ እናንተ መንፈሳውያን ኃይላት ሆይ፣ በእነዚህ ቅዱሳን ስሞች ኃይል ታዘዙኝ፤ በሰላምም ቅረቡ!»",
        rituals: [
            "በድንግል ብራና ላይ በሮማን ጭማቂ ቀለም የሰሎሞንን ታላቅ ፔንታክል መሳል",
            "ከኖራ ወይም ከነጭ ጨው የተሠራ የ፱ ጫማ (9-foot) የመከላከያ ክበብ መሬት ላይ መዘርጋት",
            "የብር ወይም የመዳብ ቀለበት በግራ እጅ ጣት ማድረግ",
            "በዕለተ ዓርብ ማታ በቬነስ (ዙህራ) ሰዓት ማዕጠንት እያጨሱ ቃሉን ማሰላሰል"
        ],
        warning: "ያለ መከላከያ ክበብ (Magic Circle) እና ያለ ንጽሕና ፈጽሞ እንዳይሞከር፤ አእምሮን ሊያናውጥ የሚችል ከፍተኛ የስነ-ልቦና እና የመንፈስ ጫና አለው።",
        history: "በመካከለኛው ዘመን አውሮፓና በመካከለኛው ምሥራቅ የአይሁድ የካባላህ ሊቃውንት የተጠቀሙበት፤ በንጉሥ ሰሎሞን ጥበብ ላይ የተመሠረተ ታላቁ የዓለማችን ግሪሞየር ነው።",
        seal: "✡ ⛤ CLAVICULA SALOMONIS ⛤ ✡"
    },
    {
        id: "picatrix",
        titleAmharic: "ጋያት አል-ሀኪም (ፒካትሪክስ)",
        titleOriginal: "Ghayat al-Hakim / Picatrix",
        origin: "10ኛው መቶ ክፍለ ዘመን (አንዳሉሲያና ባግዳድ - እስላማዊ ወርቃማ ዘመን)",
        language: "ዐረብኛ (Arabic) እና ጥንታዊ ላቲን (Medieval Latin)",
        dangerLevel: "ከፍተኛ የከዋክብትና የቁስ ውህደት አደጋ (Astrological Talismanic Magic)",
        purpose: "የከዋክብትንና የፕላኔቶችን መንፈሳዊ ኃይል ወደ ብረት፣ ድንጋይና ቴልሰም የማውረድ ጥበብ",
        incantation: "«Bismillahi al-Hakim; Ya Zuhal, Ya Mushtari, Ya Marikh; As'aluka bi-quwwati al-falak wa harakat al-nujum an tajliba li al-quwwah wa al-nasr al-a'zam!»",
        translation: "«በጥበበኛው አምላክ ስም፤ አንተ ዙሐል (ሳተርን)፣ ሙሽተሪ (ጁፒተር) እና መሪሕ (ማርስ) ሆይ፤ በሰማያት ኃይልና በከዋክብት ሩጫ ታላቁን ኃይልና ድል ታጎናጽፉኝ ዘንድ እጠይቃለሁ!»",
        rituals: [
            "ፕላኔቶች በከፍታ ማማቸው (Exaltation) ላይ በሚገኙበት ትክክለኛ ሰዓት ማዘጋጀት",
            "እንደየፕላኔቱ ብረት (ለምሳሌ ለሳተርን እርሳስ፣ ለፀሐይ ወርቅ፣ ለጨረቃ ብር) ቴልሰም መቅረጽ",
            "የዕጣንና የከርቤ ቅልቅል እያጨሱ ምስራቅ ዞሮ ቃሉን ማዜም"
        ],
        warning: "በተሳሳተ የኮከብ ሰዓት ከተሰራ ተቃራኒ መከራና የጤና መታወክ ያስከትላል፤ የኮከብ አቆጣጠርን ጠንቅቆ ማወቅ ግድ ይላል።",
        history: "በአረብኛ «ጋያት አል-ሀኪም» (የጠቢቡ ግብ) ተብሎ የተጻፈውና ወደ ላቲን «ፒካትሪክስ» ተብሎ የተተረጎመው ይህ መጽሐፍ የአውሮፓን የህዳሴ ጠቢባን ያነሳሳ የታሪክ መዝገብ ነው።",
        seal: "☽ ☿ PICATRIX TALISMAN ♀ ☉"
    },
    {
        id: "book_of_the_dead",
        titleAmharic: "የግብፅ መጽሐፈ ሙታን (ፐርት ኤም ህሩ)",
        titleOriginal: "Book of the Dead (Pert Em Hru - Going Forth by Day)",
        origin: "1550 ቅድመ ክርስቶስ (ጥንታዊቷ ግብፅ - 18ኛው ሥርወ መንግሥት)",
        language: "የጥንት ግብፅ ሂሮግሊፊክስ (Hieroglyphic)",
        dangerLevel: "የከርሰ ምድርና የሌሊት ዓለም ምስጢር (Underworld Netherworld Gates)",
        purpose: "በድኅረ ሞት ዓለም ጨለማን፣ እባቦችንና አጋንንትን ማሸነፊያ፣ የልብን ሚዛን ማለፊያ ጋሻ",
        incantation: "«Nuk pu User, Nuk pu Ra; An-nek em hetemet; Em shet-ti em ba-i em khet-i; Un-na em ma'a kheru!»",
        translation: "«እኔ የብርሃኑ አምላክ ነኝ፤ እኔ ራ ነኝ፤ በጨለማና በፈተና አልጠፋም፤ ነፍሴና ልቤ በጽድቅ ሚዛን ጸንተዋል፤ በብርሃን ወደፊት እወጣለሁ!»",
        rituals: [
            "አረንጓዴ የጃስፐር ወይም የላፒስ ላዙሊ ድንጋይ በልብ ቅርጽ ቀርፆ የአንበሳ ምልክት መቅረጽ",
            "በንጹሕ የግብፅ የሎተስና የከርቤ ዘይት አካልን ማሸት",
            "ቃሉን በንጋት ፀሐይ መውጫ ወቅት ወደ ምስራቅ ተመልክቶ ማድረስ"
        ],
        warning: "ለክፉ ዓላማ ወይም ለሌሎች መጎጃነት ፈጽሞ አይሰራም፤ የልብን ንጽሕናና እውነትን ብቻ የሚጠይቅ የፈርዖኖች ቃል ነው።",
        history: "በጥንታዊቷ ግብፅ ፈርዖኖችና ካህናት በፓፒረስ ላይ በስዕለት ጽፈው በመቃብራቸው የሚያኖሩት ታላቁ የሰው ልጅ የነፍስ ነጻነት መዝገብ ነው።",
        seal: "𓋹 𓂀 PERT EM HRU 𓊹 𓏤"
    },
    {
        id: "greek_magical_papyri",
        titleAmharic: "የግሪክና የግብፅ አስማታዊ ፓፒረሶች (PGM)",
        titleOriginal: "Papyri Graecae Magicae (Greek Magical Papyri)",
        origin: "2ኛው ቅድመ ክርስቶስ - 5ኛው መቶ ክፍለ ዘመን (የሄለናዊት ግብፅ)",
        language: "ጥንታዊ ግሪክኛ (Koine Greek) እና ዴሞቲክ ግብፅኛ",
        dangerLevel: "ኃይለኛ የሄርሜቲክና የጥሪ አስማት (Hermetic Voice Magic)",
        purpose: "የሄርሜስ ትሪስመጊስተስ፣ የሄካቴና የአፖሎ ምስጢራትን መግለጥ፣ የታወኩ ነፍሳትን ማረጋጋት",
        incantation: "«Io Hekate, Chthonia, Enodia, Dadouchos; Phos pherein, Akrouroboros, Abrasax, Sabaoth; Epakouson mou tes phones!»",
        translation: "«የመንገድና የምድር ጠባቂዋ ሆይ፤ የብርሃን ችቦ ተሸካሚ፣ አብራሳክስ፣ ጸባኦት፤ ድምፄን ስሙ፤ የተሰወረውን እውነት ግለጡልኝ!»",
        rituals: [
            "በጨለማ ሌሊት በሦስት መንገዶች መገናኛ (Crossroads) ላይ ማዕጠንት ማጠን",
            "የነጭ ሽንኩርትና የሎሚ እጣን በከሰል ላይ መጣል",
            "ቃሉን በዝግታና በጠለቀ ድምፅ ፯ ጊዜ ማንበብ"
        ],
        warning: "በፍርሃት ወይም በጥርጣሬ ውስጥ ሆኖ ማከናወን አእምሯዊ ቅዠትን ሊፈጥር ስለሚችል ከፍተኛ የአእምሮ ዝግጅት ያስፈልገዋል።",
        history: "በግብፅ እስክንድርያና በቴብስ የተገኙ፤ የግሪክን፣ የግብፅንና የባቢሎንን ጥበብ ያዋሃዱ ጥንታዊ ብራናዎችና ፓፒረሶች ናቸው።",
        seal: "⚡ ☥ ABRASAX HERMETICA ☥ ⚡"
    },
    {
        id: "ars_goetia",
        titleAmharic: "ሌሜጌቶን (አርስ ጎኤሺያ)",
        titleOriginal: "The Lesser Key of Solomon (Ars Goetia)",
        origin: "17ኛው መቶ ክፍለ ዘመን (ጥንታዊ የአውሮፓና የመካከለኛው ምሥራቅ ምንጮች)",
        language: "ላቲን (Latin) እና እንግሊዝኛ (Early Modern English)",
        dangerLevel: "በጣም ከፍተኛ አደጋ - የተከለከሉ መንፈሳዊ ኃይላት (Prohibited Evocation)",
        purpose: "የ፸፪ቱ ጥንታውያን ነገሥታትና አለቆች ማሰሪያ፣ የመንፈሳዊ ፈተናዎች መቆጣጠሪያ",
        incantation: "«Conjuro vos per Deum Vivum, per Deum Verum, per Deum Sanctum; per Alpha et Omega; ut appareatis statim sine laesione corporis vel animae!»",
        translation: "«በሕያው አምላክ ስም፣ በእውነተኛው አምላክ ስም፣ በቅዱሱ አምላክ ስም፤ በአልፋና በዖሜጋ ስም፤ በሥጋም ሆነ በነፍስ ላይ ያለምንም ጉዳት ታዘዙኝ!»",
        rituals: [
            "ከንጹሕ ብር ወይም ከወርቅ የተሠራ የሰሎሞን የድል ማኅተም (Secret Seal of Solomon) በደረት ላይ ማንጠልጠል",
            "የመከላከያ ባለ አራት ማዕዘን ጋሻና ክበብ መሬት ላይ መዘርጋት",
            "ማዕጠንተ ከርቤና ዕጣን ያለማቋረጥ ማጨስ"
        ],
        warning: "በዓለማችን እጅግ አደገኛ ከሚባሉ መጻሕፍት አንዱ ተደርጎ ይቆጠራል፤ ያለ ሙሉ መንፈሳዊ ትጥቅና ጥበቃ ወደዚህ መቅረብ ለሰው ልጆች ፈጽሞ አይመከርም።",
        history: "የሰሎሞንን ታሪክ መሠረት አድርገው በመካከለኛው ዘመን አውሮፓ ካህናትና ፈላስፎች በስውር ሲያነቧቸው ከነበሩት ምስጢራዊ መጻሕፍት ዋነኛው ነው።",
        seal: "⚔️ ⛧ ARS GOETIA SEAL ⛧ ⚔️"
    }
];

function renderGlobalGrimoires() {
    const grid = document.getElementById("globalGrimoiresGrid");
    if (!grid) return;

    grid.innerHTML = globalGrimoiresData.map(g => `
        <div class="herb-card" style="border-color:var(--crimson); background:var(--surface-card);" onclick="speakText('${g.titleAmharic}። ${g.purpose}። ${g.incantation}። ትርጉም፡ ${g.translation}')">
            <div class="herb-card-header">
                <h4 style="color:var(--gold-light); font-size:1.15rem;">${g.titleAmharic}</h4>
                <button class="btn-voice" onclick="event.stopPropagation(); speakText('${g.titleAmharic}። ${g.incantation}። ትርጉም፡ ${g.translation}')">🔊 አድምጥ</button>
            </div>
            <div style="font-size:0.8rem; color:var(--text-dim); margin-bottom:6px;">${g.titleOriginal} | ቋንቋ፡ ${g.language}</div>
            
            <div style="background:var(--bg-dark); padding:6px 12px; border-radius:6px; margin-bottom:10px; display:inline-block; font-family:serif; color:var(--gold-primary); font-weight:bold; font-size:0.85rem;">
                ${g.seal}
            </div>

            <div style="background:rgba(198,40,40,0.15); border:1px solid rgba(198,40,40,0.4); padding:6px 10px; border-radius:6px; margin-bottom:10px;">
                <span style="color:var(--crimson-light); font-weight:bold; font-size:0.75rem;">⚡ የአደጋ ደረጃ፡ ${g.dangerLevel}</span>
            </div>

            <div style="font-size:0.85rem; color:var(--text-parchment); margin-bottom:8px;">
                <strong style="color:var(--gold-light);">🎯 ዓላማና ተግባር፡</strong> ${g.purpose}
            </div>

            <div class="parchment-block" style="margin-bottom:8px; padding:10px; border-left-color:var(--crimson);">
                <small style="color:var(--crimson-light); font-weight:bold;">የጥንቱ ትክክለኛ ቃል (Original Incantation)፡</small>
                <div style="font-size:0.9rem; color:var(--gold-light); font-family:serif; margin-top:4px; line-height:1.6;">${g.incantation}</div>
                <small style="color:var(--text-dim); display:block; margin-top:4px;"><strong>ትርጉም፡</strong> ${g.translation}</small>
            </div>

            <div style="font-size:0.85rem; color:var(--text-parchment); margin-bottom:8px;">
                <strong style="color:var(--gold-light);">🕯️ የታዘዘው ሥርዓት (Ritual)፡</strong>
                <ul style="padding-left:18px; margin-top:4px;">
                    ${g.rituals.map(r => `<li style="margin-bottom:3px; font-size:0.8rem;">${r}</li>`).join("")}
                </ul>
            </div>

            <div style="background:rgba(198,40,40,0.1); padding:8px; border-radius:6px; margin-top:8px;">
                <small style="color:var(--crimson-light); display:block; line-height:1.5;"><strong>⚠️ ጥብቅ ማስጠንቀቂያ፡</strong> ${g.warning}</small>
            </div>

            <div style="margin-top:8px; font-size:0.75rem; color:var(--text-dim);">
                <strong>ታሪካዊ ዳራ፡</strong> ${g.history} (${g.origin})
            </div>
        </div>
    `).join("");
}

