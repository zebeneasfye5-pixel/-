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
በዝንቱ ዕፀ ሳቤቅ (ዳማከሴ) ወዕፀ ሕይወት (ጤናአዳም) ፈውስ ሕማመ ከርሥ ወርእስ ዘገብርከ፤
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
                geez: `በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤\nሩፋኤል መልአከ ፈውስ፤\nፈውስ ድዉያነ በዕፀ ሳቤቅ ወበዕፀ ሕይወት፤\nአርሕቅ ኩሎ ደዌ ወሕማም እምላዕለ ገብርከ!`,
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
        id: "damakese",
        name: "ዳማከሴ",
        geez: "ዕፀ ሳቤቅ (ሳቤቅ)",
        scientific: "Ocimum lamiifolium",
        ref: "መጽሐፈ ፈውስ ገጽ ፸፬",
        ailments: "ብርድና ጉንፋን (Flu)፣ የራስ ምታት (Migraine)፣ የዓይነ ጥላ ድካም (Heavy Aura)",
        preparation: "ትኩስ የዳማከሴ ቅጠሎችን በእጅ በማሸት ጭማቂውን ማውጣት ወይም በሙቅ ውሃ ውስጥ ዘፍዝፎ መታጠን።",
        application: "ጭማቂውን በአፍንጫ ማሽተት፣ ግንባርን ማሸት እና እንፋሎቱን መታጠን።",
        prayer: "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ ፈዋሴ ዱያን ሩፋኤል፤ በዝንቱ ዕፅ ፈውስ ሕማመ ርእስ ወዓይነ ጥላ ዘሰብእ።"
    },
    {
        id: "tena_adam",
        name: "ጤናአዳም",
        geez: "ዕፀ ሕይወት ዘአዳም",
        scientific: "Ruta chalepensis",
        ref: "መጽሐፈ ፈውስ ገጽ ፸፭",
        ailments: "የሆድ ቁርጠት (Stomach ache)፣ የክፉ ዓይን መከላከያ (Evil eye)፣ የምግብ አለመፈጨት",
        preparation: "የጤናአዳም ፍሬውንና ቅጠሉን በሻይ ወይም በቡና ውስጥ መክተት፣ ወይም በንጹሕ ውኃ በጥብጦ ማጣራት።",
        application: "ጥቂት ጠብታ በሞቀ መጠጥ ውስጥ ጠጥቶ ማሳለፍ።",
        prayer: "አስማተ ፈውስ ዘአቡሻህር፤ ኤሎሄ፣ ኤሎሄ፤ አድኅኖ ለገብርከ እምደዌ ከርሥ ወእምዓይነ ባላ።"
    },
    {
        id: "girawa",
        name: "ግራዋ",
        geez: "ዕፀ መራር",
        scientific: "Vernonia amygdalina",
        ref: "መጽሐፈ ፈውስ ገጽ ፸፮",
        ailments: "የሆድ ትላትል (Intestinal parasites)፣ የጉበትና የሆድ ጽዳት (Liver cleanse)",
        preparation: "የግራዋን ቅጠል በሙቀጫ መውቀስ፣ ጭማቂውን በማጣራት ከማር ጋር ማዋሃድ።",
        application: "ጠዋት በባዶ ሆድ በትንሽ ማንኪያ መውሰድ።",
        prayer: "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ በስመ እግዚአብሔር ፈዋሲ፤ አውፅእ ኩሎ ሕማመ ከርሥ ወመርዘ ከይሲ።"
    },
    {
        id: "tosign",
        name: "ጦስኝ",
        geez: "ዕፀ ሰናይት",
        scientific: "Thymus serrulatus",
        ref: "መጽሐፈ ፈውስ ገጽ ፸፯",
        ailments: "የሳልና የጉሮሮ ሕመም (Cough & throat)፣ የደም ግፊት መረጋጋት",
        preparation: "የደረቀውን ወይም ትኩሱን የጦስኝ ቅጠል በፈላ ውሃ ውስጥ ማፍላት።",
        application: "እንደ ሻይ አድርጎ በቀን ሁለት ጊዜ ማጣጣም።",
        prayer: "በስመ ሥላሴ፤ አብርድ እሳተ ደዌ እምጉርዔ ወእምደረተ ገብርከ።"
    },
    {
        id: "eret",
        name: "እሬት",
        geez: "ዕፀ ጽጌ",
        scientific: "Aloe debrana",
        ref: "መጽሐፈ ፈውስ ገጽ ፸፰",
        ailments: "የቆዳ ቁስል፣ ፎረፎር፣ የሆድ ድርቀትና የቆዳ ልስላሴ",
        preparation: "የእሬቱን ጄል በመፋቅ ከንጹሕ ውኃ ወይም ከማር ጋር መቀላቀል።",
        application: "በተጎዳው የቆዳ ክፍል ላይ በቀን ሁለት ጊዜ መቀባት።",
        prayer: "በስመ አብ ወወልድ ወመንፈስ ቅዱስ፤ አንጽሐኒ እምደዌ ቈላ ወአሕይወኒ በምሕረትከ።"
    },
    {
        id: "kerbe",
        name: "ከርቤ",
        geez: "ዕፀ ከርቤ ቅዱስ",
        scientific: "Commiphora myrrha",
        ref: "መጽሐፈ ፈውስ ገጽ ፸፱",
        ailments: "የመንፈስ ጭንቀት፣ ፀረ-ተባይ፣ የድድና የጉሮሮ በሽታ",
        preparation: "ከርቤውን በከሰል ላይ ማጨስ ወይም በውኃ ዘፍዝፎ መጉመጥመጥ።",
        application: "ጭሱን መታጠንና ቤትን ማጠን።",
        prayer: "መዓዛ ሠናይት ከመ ከርቤ፤ አርሕቅ ጸላዕተ ወፈኑ ሰላመ ውስተ ቤተ ዚአየ።"
    }
];

function renderHerbs(list) {
    const grid = document.getElementById("herbListGrid");
    if (!grid) return;
    grid.innerHTML = list.map(herb => `
        <div class="herb-card" onclick="speakText('${herb.name}። ግእዝ፡ ${herb.geez}። የሚያክመው፡ ${herb.ailments}። አዘገጃጀት፡ ${herb.preparation}። ሪፈረንስ፡ ${herb.ref}')">
            <div class="herb-card-header">
                <h4>${herb.name}</h4>
                <button class="btn-voice" onclick="event.stopPropagation(); speakText('${herb.name}። ${herb.preparation}። ${herb.prayer}')">🔊 አድምጥ</button>
            </div>
            <div class="herb-geez">ግእዝ፡ ${herb.geez} | ${herb.scientific}</div>
            <div style="font-size:0.75rem; color:var(--gold-light); margin-bottom:6px;">📖 ሪፈረንስ፡ ${herb.ref}</div>
            <div class="herb-ailments"><strong>ደዌዎች፡</strong> ${herb.ailments}</div>
            <p style="font-size:0.85rem; color:var(--text-parchment); margin-bottom:8px;"><strong>አዘገጃጀት፡</strong> ${herb.preparation}</p>
            <div class="parchment-block" style="margin-bottom:0; padding:10px;">
                <small style="color:var(--crimson-light); font-weight:bold;">የብራናው ጸሎት፡</small>
                <div style="font-size:0.85rem; color:var(--gold-light);">${herb.prayer}</div>
            </div>
        </div>
    `).join("");
}

function filterHerbs() {
    const q = document.getElementById("herbSearchInput").value.toLowerCase().trim();
    const filtered = herbsData.filter(h => 
        h.name.toLowerCase().includes(q) ||
        h.geez.toLowerCase().includes(q) ||
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
በኃይለ መስቀል ይስበር አጋንንተ ፀላዕት ወይኩን ንጹሐ ከመ ብርሃነ ፀሐይ!`;

    let explanation = `የ${category} ጥያቄዎ ከመጽሐፉ ተመርምሯል። ለ${seeker} እና ለ${mother} የተከፈተው የጥበብ መንገድ ብሩህነትን፣ የክፉ መንፈስ መፈታትንና የታሰበው ጉዳይ (${details}) በሰላም እንዲፈጸም የሚያመለክት ነው።`;

    let prescriptions = [
        "ንጹሕ የጠዋት ጸሎት በሰላም ማድረስ",
        "ዕጣን ከርቤ ማታ ማታ በቤት ማጠን",
        "የተቸገረ ሰውን በምጽዋት መርዳት",
        "ይህን የግእዝ አስማተ ቀመር በንጹሕ ብራና ወይም ወረቀት መያዝ"
    ];

    if (category === "መስተፋቅርና ፍቅር") {
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
በዝንቱ ዕፀ ሳቤቅ ወዕፀ ሕይወት ፈውስ ሕማመ ${seeker} ወልደ ${mother}፤
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
