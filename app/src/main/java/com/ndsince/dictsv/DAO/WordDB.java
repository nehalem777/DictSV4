package com.ndsince.dictsv.DAO;

import android.content.Context;

import java.util.ArrayList;

/**
 * WordDB
 * Add new data to database
 */
public class WordDB {

    public static final String TAG = "WordDB";
    public static int DATABASE_VERSION = 4;

    private Context mContext;
    private CategoryDAO categoryDAO;
    private WordDAO wordDAO;
    private FavoriteDAO favoriteDAO;
    private ArrayList<String> categoriesArrayList = new ArrayList<>();

    //Category Data
    private String Computer_Category = "Computer";
    private String Math_Category = "Math";

    /**
     * Constructor
     * @param context
     */
    public WordDB(Context context) {
        this.mContext = context;

        //Create Data
        //createCategory();
        //createWord();
        //createFavorite();

    }   // Constructor

    /**
     * //Getting Index of an item in an arraylist
     * @param categoryName category name
     * @return index of category id
     */
    public int getIndexByName(String categoryName) {
        for (String word : categoriesArrayList) {
            if (word.equals(categoryName)) {
                return categoriesArrayList.indexOf(word);
            }
        }
        return -1;
    }   // getIndexByName

    /**
     * Create New Category
     */
    public void createCategory() {
        categoriesArrayList.add(0, "Category");
        categoriesArrayList.add(1, Computer_Category);
        categoriesArrayList.add(2, Math_Category);

        //add new category to database
        categoryDAO = new CategoryDAO(mContext);
        categoryDAO.addCategory(new Category(categoriesArrayList.get
                (getIndexByName(Computer_Category))));
        categoryDAO.addCategory(new Category(categoriesArrayList.get
                (getIndexByName(Math_Category))));
    }   // createCategory

    /**
     * Create New word
     */
    public boolean createWord() {
        wordDAO = new WordDAO(mContext);

        wordDAO.addWord("abend; abnormal end", "การหยุดงานผิดปรกติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("abort", "ยกเลิก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("absolute address; machine address", "เลขที่อยู่สัมบูรณ์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("absolute code", "รหัสสัมบูรณ์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("absolute loader", "โปรแกรมบรรจุสัมบูรณ์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("abstract data type (ADT)", "แบบชนิดข้อมูลนามธรรม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("abstract machine", "เครื่องเชิงนามธรรม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("abstraction", "การกำหนดสาระสำคัญ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("acceptance test", "การทดสอบเพื่อยอมรับ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("access", "๑. เข้าถึง\n" + "๒. การเข้าถึง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("access arm", "ก้านเข้าถึง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("access code", "รหัสการเข้าถึง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("access denied", "ข้อความห้ามเข้าถึง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("access key", "กุญแจการเข้าถึง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("access level", "ระดับการเข้าถึง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("access mechanism", "กลไกการเข้าถึง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("access method", "๑. วิธีเข้าถึง\n" + "๒. โปรแกรมสำหรับเข้าถึง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("access time", "ช่วงเวลาเข้าถึง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("accessor", "ตัวเข้าถึง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("account", "บัญชีผู้ใช้", null, getIndexByName(Computer_Category));
        wordDAO.addWord("accounting machine", "เครื่องทำบัญชี", null, getIndexByName(Computer_Category));
        wordDAO.addWord("accumulator", "ตัวสะสม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("accuracy", "ความแม่น", null, getIndexByName(Computer_Category));
        wordDAO.addWord("acknowledgement", "การตอบรับ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("acoustic coupler", "ตัวคู่ต่อทางเสียง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("acoustic delay line", "สายหน่วงเสียง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("acoustic memory; acoustic store", "หน่วยความจำเชิงเสียง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("acronym", "รัสพจน์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("active device", "อุปกรณ์กัมมันต์, อุปกรณ์แอ็กทิฟ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("activity diagram", "แผนภาพกิจกรรม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("activity ratio", "อัตราส่วนกิจกรรม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("actual code", "รหัสจริง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("actual decimal point", "จุดทศนิยมจริง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("actual instruction", "คำสั่งจริง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("acyclic graph", "กราฟไม่มีวง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Ada", "(ภาษา)เอดา", "เอดา", getIndexByName(Computer_Category)); //TODo have translate
        wordDAO.addWord("adapter", "ตัวปรับต่อ, ตัวปรับ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("addend", "ตัวบวก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("adder", "๑. เครื่องบวก\n" + "๒. วงจรบวก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("adder-subtracter", "วงจรบวกลบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("addition", "การบวก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("additional character", "อักขระเพิ่มเติม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("address", "เลขที่อยู่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("address computation", "การคำนวณเลขที่อยู่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("address format", "รูปแบบเลขที่อยู่ [ในคำสั่ง]", null, getIndexByName(Computer_Category));
        wordDAO.addWord("address mapping", "การส่งเลขที่อยู่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("address modification", "การดัดแปรเลขที่อยู่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("address part", "ส่วนเลขที่อยู่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("address register", "เรจิสเตอร์เลขที่อยู่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("address track", "๑. วงเก็บเลขที่อยู่\n" + "๒. ร่องเก็บเลขที่อยู่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("addressing", "การกำหนดเลขที่อยู่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("advanced program to program communications (APPC)", "การสื่อสารระดับสูงระหว่างโปรแกรม (เอพีพีซี)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Advanced Research Projects Agency Network (ARPANET)", "เครือข่ายสำนักงานโครงการวิจัยขั้นสูง (อาร์พาเน็ต)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("advanced SCSI program interface (ASPI)", "ส่วนต่อประสานโปรแกรมสกัสซีระดับสูง (เอเอสพีไอ)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("aggregation", "ภาพรวมกลุ่ม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("algorithm", "ขั้นตอนวิธี", null, getIndexByName(Computer_Category));//TODO งงว่ามีในทับศัพท์หรือไม่
        wordDAO.addWord("algorithmic complexity", "ความซับซ้อนของขั้นตอนวิธี", null, getIndexByName(Computer_Category));
        wordDAO.addWord("algorithmic language", "ภาษาขั้นตอนวิธี", null, getIndexByName(Computer_Category));
        wordDAO.addWord("alias", "สมนาม [อ่านว่า สะมะนาม]", null, getIndexByName(Computer_Category));
        wordDAO.addWord("allocate", "จัดสรร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("all-purpose computer; general-purpose computer", "คอมพิวเตอร์อเนกประสงค์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("alphabet", "ชุดตัวอักษร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("alphabetic", "ตัวอักษร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("alphabetic code", "รหัสตัวอักษร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("alphabetic string", "สายตัวอักษร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("alphameric; alphanumeric", "ตัวอักษรเลข", null, getIndexByName(Computer_Category));
        wordDAO.addWord("alphanumeric mode", "ภาวะตัวอักษรเลข [= character mode และ text mode]", null, getIndexByName(Computer_Category));
        wordDAO.addWord("alternate key", "กุญแจสำรอง [ใช้กับแฟ้ม]", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Alternate key; Alt key", "แป้นสลับ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("alternate track", "๑. วงสำรอง\n" +"๒. ร่องสำรอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("alternative denial", "การปฏิเสธแบบเลือก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("ambiguity error", "ความผิดพลาดกำกวม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("amendment file", "แฟ้มแก้ไข", null, getIndexByName(Computer_Category));
        wordDAO.addWord("amendment record", "ระเบียนแก้ไข", null, getIndexByName(Computer_Category));
        wordDAO.addWord("amendment tape", "แถบแก้ไข", null, getIndexByName(Computer_Category));
        wordDAO.addWord("American National Standard Institute (ANSI)", "สถาบันมาตรฐานแห่งชาติของสหรัฐอเมริกา (แอนซี)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("American Standard Code for Information Interchange (ASCII)", "รหัสมาตรฐานของสหรัฐอเมริกาเพื่อการสับเปลี่ยนสารสนเทศ (แอสกี)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("analog; analogue", "เชิงอุปมาน,แอนะล็อก", "แอนะล็อก", getIndexByName(Computer_Category));//TODO have
        wordDAO.addWord("analog computer", "คอมพิวเตอร์เชิงอุปมาน", "แอนะล็อกคอมพิวเตอร์", getIndexByName(Computer_Category));//TODO have
        wordDAO.addWord("analog data", "ข้อมูลเชิงอุปมาน, ข้อมูลแอนะล็อก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("analog network", "วงจรข่ายเชิงอุปมาน, วงจรข่ายแอนะล็อก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("analog signal", "สัญญาณเชิงอุปมาน, สัญญาณแอนะล็อก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("analog-to-digital converter (ADC)", "ตัวแปลงแอนะล็อกเป็นดิจิทัล, ตัวเปลี่ยนแอนะล็อกเป็นดิจิทัล (เอดีซี)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("analogue; analog", "เชิงอุปมาน, แอนะล็อก", "แอนะล็อก", getIndexByName(Computer_Category));
        wordDAO.addWord("analyser; analyzer", "๑. ตัววิเคราะห์, เครื่องวิเคราะห์\n" +"๒. โปรแกรมวิเคราะห์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("ancestor node", "บัพบน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("animation", "๑. การทำให้เคลื่อนไหว\n" + "๒. การทำภาพเคลื่อนไหว\n" +"๓. ภาพเคลื่อนไหว", null, getIndexByName(Computer_Category));
        wordDAO.addWord("anonymous FTP", "เอฟทีพีแบบนิรนาม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("append", "ผนวก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("applet", "แอปเพล็ต", "แอปเพล็ต", getIndexByName(Computer_Category));//TODO have
        wordDAO.addWord("application", "๑. การประยุกต์\n" + "๒. โปรแกรมประยุกต์\n" + "๓. ระบบประยุกต์\n" + "๔. งานประยุกต์", null, getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("application package", "โปรแกรมสำเร็จประยุกต์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("application program interface (API)", "ส่วนต่อประสานโปรแกรมประยุกต์ (เอพีไอ)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("application specific integrated circuit (ASIC)", "วงจรรวมเฉพาะงาน (เอสิก)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("applicative language; functional language", "ภาษาเชิงหน้าที่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("architecture; computer architecture", "สถาปัตยกรรมคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("archive", "หน่วยเก็บถาวร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("archived file", "แฟ้มเก็บถาวร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("archiving", "การเก็บถาวร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("argument", "อาร์กิวเมนต์", "อาร์กิวเมนต์", getIndexByName(Computer_Category));//TODO have
        wordDAO.addWord("arithmetic expression", "นิพจน์คำนวณ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("arithmetic instruction", "คำสั่งคำนวณ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("arithmetic operation", "การดำเนินการคำนวณ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("arithmetic operator", "ตัวดำเนินการคำนวณ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("arithmetic register", "เรจิสเตอร์คำนวณ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("arithmetic relation", "ความสัมพันธ์เชิงคำนวณ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("arithmetic shift; arithmetical shift", "การเลื่อนเชิงคำนวณ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("arithmetic statement", "ข้อความสั่งคำนวณ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("arithmetic unit", "หน่วยคำนวณ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("arithmetic-logic unit (ALU)", "หน่วยคำนวณและตรรกะ (เอแอลยู)", null, getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("arithmetical shift; arithmetic shift", "การเลื่อนเชิงคำนวณ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("array", "แถวลำดับ", null, getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("arrow key", "แป้นลูกศร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("artificial intelligence (AI)", "ปัญญาประดิษฐ์ (เอไอ)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("ascending sort", "การเรียงลำดับขึ้น", null, getIndexByName(Computer_Category));
        wordDAO.addWord("assemble", "แปลภาษาแอสเซมบลี", null, getIndexByName(Computer_Category));
        wordDAO.addWord("assembler", "แอสเซมเบลอร์", "แอสเซมเบลอร์", getIndexByName(Computer_Category));//TODO have
        wordDAO.addWord("assembly language", "ภาษาแอสเซมบลี", null, getIndexByName(Computer_Category));
        wordDAO.addWord("assembly program", "โปรแกรมภาษาแอสเซมบลี", null, getIndexByName(Computer_Category));
        wordDAO.addWord("assertion", "ข้อความยืนยัน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("assign", "กำหนด", null, getIndexByName(Computer_Category));
        wordDAO.addWord("assignment operator", "ตัวกำหนดค่า", null, getIndexByName(Computer_Category));
        wordDAO.addWord("assignment statement", "ข้อความสั่งกำหนดค่า", null, getIndexByName(Computer_Category));
        wordDAO.addWord("associative memory", "หน่วยความจำสาระ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("associative storage", "หน่วยเก็บสาระ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("assumed decimal point", "จุดทศนิยมสมมุติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("asymmetric digital subscriber line (ADSL)", "สายผู้เช่าดิจิทัลแบบอสมมาตร (เอดีเอสแอล)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("asynchronous", "ไม่ประสานเวลา, อสมวาร, อะซิงโครนัส", "อะซิงโครนัส", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("asynchronous computer", "คอมพิวเตอร์แบบไม่ประสานเวลา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("asynchronous device", "อุปกรณ์แบบไม่ประสานเวลา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("asynchronous port", "ช่องทางไม่ประสานเวลา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("asynchronous transfer mode (ATM)", "ภาวะถ่ายโอนแบบไม่ประสานเวลา (เอทีเอ็ม)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("asynchronous transmission", "การส่งแบบไม่ประสานเวลา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("ATM adaptation layer (AAL)", "ชั้นการปรับเปลี่ยนข้อมูลเอทีเอ็ม (เอเอแอล)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("atomicity", "ภาวะครบหน่วย", null, getIndexByName(Computer_Category));
        wordDAO.addWord("attribute", "ลักษณะประจำ", null, getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("attribute grammar", "ไวยากรณ์เชิงลักษณะประจำ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("audio response unit", "หน่วยตอบสนองด้วยเสียง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("audit of computer systems", "การตรวจสอบระบบคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("audit trail", "หลักฐานการตรวจสอบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("augend", "ตัวตั้งบวก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("augment", "แต่งเติม, เสริม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("augmented reality", "ความเป็นจริงเสริม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("augmenter", "ตัวแต่งเติม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("authentication", "การพิสูจน์ตัวจริง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("authoring language", "ภาษาสร้างโปรแกรมบทเรียน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("auto-answer", "รับ(โทรศัพท์)อัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("auto-call", "เรียกอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("auto-dial", "ต่อเลขหมายอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("auto-dialing modem", "โมเด็มต่อเลขหมายอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("auto-restart", "เริ่มทำต่ออัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automata theory", "ทฤษฎีออโตมาตา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automate", "ทำให้เป็นอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automated office", "สำนักงานอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automated theorem proving", "การพิสูจน์ทฤษฎีบทด้วยเครื่อง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic abstract", "บทคัดย่ออัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic calling unit (ACU)", "หน่วยเรียกอัตโนมัติ (เอซียู)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic carriage return", "การขึ้นบรรทัดใหม่อัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic check", "การตรวจสอบอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic coding", "การเข้ารหัสอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic data capture", "การยึดเก็บข้อมูลอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic data processing (ADP)", "การประมวลผลข้อมูลแบบอัตโนมัติ (เอดีพี)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic decimal adjustment", "การปรับทศนิยมอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic dictionary", "พจนานุกรมอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic error correction", "การแก้ความผิดพลาดโดยอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic error detection", "การตรวจหาความผิดพลาดโดยอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic exchange", "ชุมสายอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic feed-punch; automatic punch", "เครื่องเจาะบัตรอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic interrupt", "การขัดจังหวะอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic loader", "โปรแกรมบรรจุอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic programming", "การสร้างโปรแกรมอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic punch; automatic feed-punch", "เครื่องเจาะบัตรอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic repeat", "การซ้ำอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic send-receive set (ASR)", "ชุดรับส่งอัตโนมัติ (เอเอสอาร์)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic sequencing", "การจัดลำดับอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic stop", "การหยุดอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic tape punch", "เครื่องเจาะแถบกระดาษอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic teller machine (ATM)", "เครื่องรับจ่ายเงินอัตโนมัติ (เอทีเอ็ม)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic test equipment (ATE)", "เครื่องทดสอบอัตโนมัติ (เอทีอี)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automatic verifier", "เครื่องทวนสอบอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automation", "การทำให้เป็นอัตโนมัติ, การอัตโนมัติ, การวางระบบอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("automonitor", "โปรแกรมเฝ้าสังเกตตัวเอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("autonomy", "ภาวะอิสระ, ภาวะอัตโนวัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("autopolling", "การหยั่งสัญญาณอัตโนมัติ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("auxiliary equipment", "บริภัณฑ์ช่วย", null, getIndexByName(Computer_Category));
        wordDAO.addWord("auxiliary storage", "หน่วยเก็บช่วย", null, getIndexByName(Computer_Category));
        wordDAO.addWord("availability", "สภาพพร้อมใช้งาน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("available machine time; available time", "เวลาเครื่องพร้อม", null, getIndexByName(Computer_Category));
        //TODO B
        wordDAO.addWord("back-end", "-ส่วนหลัง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("backbone", "แกนหลัก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("backdrop", "ฉากหลัง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("background", "๑. -พื้นหลัง\n" +"๒. ภาวะพื้นหลัง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("backhaul", "ช่องสื่อสารภาคพื้นดิน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("backing store", "หน่วยเก็บหนุน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("backspace", "ถอยหลัง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("backtrack", "ย้อนรอย", null, getIndexByName(Computer_Category));
        wordDAO.addWord("backup", "๑. สำรอง\n" +"๒. การสำรอcanง\n" +"๓. สิ่งสำรอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("balance error", "ความผิดพลาดของดุล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("balanced error", "ความผิดพลาดได้ดุล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("balanced height tree", "(รูป)ต้นไม้ความสูงได้ดุล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("balanced merge sort", "การเรียงลำดับผสานได้ดุล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("balanced sort", "การเรียงลำดับได้ดุล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("balanced tree", "(รูป)ต้นไม้ได้ดุล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("band", "๑. แถบ\n" +"๒. แถบความถี่ [มีความหมายเหมือนกับ frequency band]", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bandwidth", "๑. ความกว้างแถบความถี่\n" +"๒. แบนด์วิดท์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bar code", "รหัสแท่ง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("base", "ฐาน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("base address", "เลขที่อยู่ฐาน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("base class", "คลาสพื้นฐาน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("base number", "เลขฐาน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("baseband", "แถบความถี่ฐาน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("BASIC (Beginner’s All-purpose Symbolic Instruction Code)", "(ภาษา)เบสิก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("batch", "กลุ่ม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("batch mode; batch processing mode", "ภาวะประมวลผลแบบกลุ่ม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("batch processing", "การประมวลผลแบบกลุ่ม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("batch system", "ระบบแบบกลุ่ม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("baud", "บอด","บอด", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("BBS (bulletin board system)", "บีบีเอส (ระบบกระดานข่าว)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("BCD (binary coded decimal)", "บีซีดี (เลขฐานสิบเข้ารหัสฐานสอง)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Bean", "บีน", "บีน", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("beat", "บีต", "บีต", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("beginning of information mark", "เครื่องหมายเริ่มต้น", null, getIndexByName(Computer_Category));
        wordDAO.addWord("beginning of file label", "ป้ายเริ่มแฟ้ม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("behavior", "พฤติกรรม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("benchmark", "๑. เกณฑ์เปรียบเทียบสมรรถนะ\n" +"๒. การวัดเปรียบเทียบสมรรถนะ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("benchmark program", "โปรแกรมวัดเปรียบเทียบสมรรถนะ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bias", "๑. ตั้งจุดทำงาน\n" +"๒. การตั้งจุดทำงาน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("biconditional", "มีเงื่อนไขสองทาง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary", "๑. ฐานสอง\n" +"๒. ทวิภาค", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary adder", "วงจรบวกฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary arithmetic operation", "การดำเนินการคำนวณฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary Boolean operation; dyadic Boolean operation", "การดำเนินการแบบบูลชนิดทวิภาค", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary cell", "เซลล์เก็บบิตเดียว", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary code", "รหัสฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary coded character", "อักขระเข้ารหัสฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary coded decimal (BCD)", "เลขฐานสิบเข้ารหัสฐานสอง (บีซีดี)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary coded decimal representation", "การแทนเลขฐานสิบเข้ารหัสฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary coded digit", "เลขโดดเข้ารหัสฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary counter", "วงจรนับฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary digit", "เลขโดดฐานสอง [มีความหมายเหมือนกับ bit]", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary dump", "ข้อมูลฐานสองเทออก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary half adder", "วงจรบวกครึ่งอัตราฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary image", "ภาพลักษณ์ฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary large object (BLOB)", "อ็อบเจกต์ฐานสองขนาดใหญ่ (บล็อบ)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary notation", "สัญกรณ์ฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary number", "๑. เลขฐานสอง\n" +"๒. จำนวนฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary numeral", "ตัวเลขฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary search; bisection search", "การค้นหาแบบทวิภาค", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary search tree", "(รูป)ต้นไม้ค้นหาแบบทวิภาค", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary tree", "(รูป)ต้นไม้แบบทวิภาค", null, getIndexByName(Computer_Category));
        wordDAO.addWord("binary variable", "ตัวแปรฐานสอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bind", "ยึดเหนี่ยว", null, getIndexByName(Computer_Category));
        wordDAO.addWord("BinHex", "บินเฮกซ์", "บินเฮกซ์", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("bionics", "ชีวประดิษฐศาสตร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("biosensor", "เครื่องรับรู้ชีวภาพ, ตัวรับรู้ชีวภาพ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bipolar", "สองขั้ว", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bipolar technology", "เทคโนโลยีสองขั้ว", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bipolar transistor", "ทรานซิสเตอร์ชนิดสองขั้ว", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bistable", "ทวิเสถียร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("BISYNC", "ไบซิงก์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bisynchronous", "ประสานเวลาคู่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bit", "บิต", "บิต", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("bit density", "ความหนาแน่นบิต", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bit location", "ตำแหน่งเก็บบิต", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bit map", "แผนที่บิต", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bit pattern", "แบบรูปของบิต", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bit rate", "อัตราบิต", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bit slicing", "การแบ่งบิต", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bit string", "สายบิต", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bit stuffing", "การสอดแทรกบิต", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bit track", "๑. วงบิต\n" +"๒. ร่องบิต", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bit-map", "ทำแผนที่บิต", null, getIndexByName(Computer_Category));
        wordDAO.addWord("blank", "๑. หน้าว่าง\n" +"๒. ตัวว่าง\n" +"๓. พื้นที่ว่าง\n" +"๔. ว่าง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("blank disk", "จานบันทึกเปล่า", null, getIndexByName(Computer_Category));
        wordDAO.addWord("blank tape", "แถบเปล่า", null, getIndexByName(Computer_Category));
        wordDAO.addWord("block", "๑. กลุ่มระเบียน\n" +"๒. บล็อก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("block diagram", "แผนภาพบล็อก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("block header", "๑. ส่วนหัวกลุ่มระเบียน\n" +"๒. ส่วนหัวบล็อก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("block length", "ความยาวกลุ่มระเบียน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("blocking", "การจัดเป็นกลุ่มระเบียน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("blocking factor", "จำนวนระเบียนต่อกลุ่ม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bookmark", "คั่นหน้า", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Boolean complementation", "การเติมเต็มแบบบูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Boolean connective", "ตัวเชื่อมแบบบูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Boolean logic", "ตรรกะแบบบูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Boolean negation", "นิเสธแบบบูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Boolean operation", "การดำเนินการแบบบูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("boot", "๑. การปลุกเครื่อง [มีความหมายเหมือนกับ bootstrap]\n" +"๒. ปลุกเครื่อง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bootstrap", "การปลุกเครื่อง [มีความหมายเหมือนกับ boot ๑]", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bottom-up desig", "การออกแบบจากล่างขึ้นบน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bounce", "การเด้ง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bound, CPU", "-เน้นซีพียู", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bound, I/O", "-เน้นไอ/โอ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bounds registers", "เรจิสเตอร์แบ่งเขต", null, getIndexByName(Computer_Category));
        wordDAO.addWord("branch", "๑. กิ่ง\n" +"๒. แตกกิ่ง, แยกสาขา\n" +"๓. แยก(ทาง)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("branch node", "บัพกิ่ง [มีความหมายเหมือนกับ interior node และ nonleaf node; nonterminal node]", null, getIndexByName(Computer_Category));
        wordDAO.addWord("branchpoint", "จุดแยก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("breadboard", "แผงวงจรทดลอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("breadth-first search", "การค้นหาในแนวกว้าง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("breakdown", "การเสีย", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Break key", "แป้นหยุดงาน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("breakpoint", "จุดพัก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("breakpoint instruction", "คำสั่งพัก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("breakpoint symbol", "สัญลักษณ์จุดพัก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bridgeware", "บริดจ์แวร์", "บริดจ์แวร์", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("broadband; wideband", "แถบความถี่กว้าง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("broadband ISDN (B-ISDN)", "ไอเอสดีเอ็นแถบกว้าง (บีไอเอสดีเอ็น)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("brouter", "เบราเตอร์", "เบราเตอร์", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("browse", "ค้นดู", null, getIndexByName(Computer_Category));
        wordDAO.addWord("browser", "โปรแกรมค้นดู, เบราว์เซอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bubble memory", "หน่วยความจำแบบฟอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bubble sort", "การเรียงลำดับแบบฟอง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bucket", "ที่ฝากข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("buddy system", "ระบบคู่เคียง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("buffer", "๑. ที่พัก(ข้อมูล)\n" +"๒. บัฟเฟอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("buffer amplifier", "เครื่องขยายชนิดบัฟเฟอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("buffer storage", "หน่วยเก็บที่พัก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("buffering", "การปรับอัตรา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bug", "จุดบกพร่อง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("built-in check", "การตรวจสอบในตัว", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bulk storage", "หน่วยเก็บขนาดใหญ่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bullet", "จุดนำ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bulletin board", "กระดานข่าว", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bundle", "รวมชุด", null, getIndexByName(Computer_Category));
        wordDAO.addWord("burst", "๑. แยกแผ่น [กระดาษต่อเนื่อง]\n" +"๒. ส่งเป็นชุดอย่างเร็ว", null, getIndexByName(Computer_Category));
        wordDAO.addWord("burst mode", "วิธีส่งเป็นชุดอย่างเร็ว", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bursty data", "ข้อมูลส่งเป็นชุดอย่างเร็ว", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bus", "บัส", "บัส", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("bus driver", "ตัวขับบัส", null, getIndexByName(Computer_Category));
        wordDAO.addWord("bus network", "ข่ายงานแบบบัส", null, getIndexByName(Computer_Category));
        wordDAO.addWord("button", "ปุ่ม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("byte", "ไบต์", "ไบต์", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("byte code", "รหัสไบต์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("byte mode", "วิธีส่งเป็นไบต์", null, getIndexByName(Computer_Category));
        //TODO C
        wordDAO.addWord("cache", "แคช", "แคช", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("cache memory", "หน่วยความจำแคช", null, getIndexByName(Computer_Category));
        wordDAO.addWord("CAD (computer-aided design)", "แคด (การออกแบบใช้คอมพิวเตอร์ช่วย)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("CAE (computer-aided engineering)", "ซีเออี (งานวิศวกรรมใช้คอมพิวเตอร์ช่วย)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("CAI (computer-aided instruction; computer-assisted instruction)", "ซีเอไอ (การสอนใช้คอมพิวเตอร์ช่วย)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("calculator", "เครื่องคิดเลข", null, getIndexByName(Computer_Category));
        wordDAO.addWord("call", "๑. เรียก\n" +"๒. การเรียก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("call by name", "เรียกด้วยชื่อ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("call by reference", "เรียกโดยอ้างอิง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("call by value", "เรียกด้วยมูลค่า", null, getIndexByName(Computer_Category));
        wordDAO.addWord("call direction code", "รหัสทิศทางเรียก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("call instruction", "คำสั่งเรียก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("calling sequence", "ลำดับการเรียก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("campus area network", "ข่ายงานบริเวณวิทยาเขต", null, getIndexByName(Computer_Category));
        wordDAO.addWord("cancel", "ยกเลิก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("candidate key", "กุญแจให้เลือก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("capacitor store", "หน่วยเก็บใช้ประจุ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("capacity", "ความจุ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Caps Lock key", "แป้นตรึงอักษรตัวใหญ่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("card", "๑. บัตร\n" +"๒. แผ่นวงจร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("card feed", "ส่วนป้อนบัตร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("card stacker", "ช่องรับบัตร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("carriage return", "๑. ปัดแคร่\n" +"๒. แป้นปัดแคร่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("carrier", "๑. คลื่นพาห์\n" +"๒. ผู้ให้บริการโทรคมนาคม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("carrier system", "ระบบพาหะ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("carry", "๑. ตัวทด\n" +"๒. เลขทด\n" +"๓. การทด\n" +"๔. -ทด", null, getIndexByName(Computer_Category));
        wordDAO.addWord("cascade control", "การควบคุมแบบต่อเรียง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("case sensitive", "ไวต่ออักษรใหญ่เล็ก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("catalog; catalogue", "๑. สารบัญแฟ้ม\n" +"๒. เข้าสารบัญแฟ้ม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("CCD (charge coupled device)", "ซีซีดี (อุปกรณ์ถ่ายเทประจุ)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("CCD memory", "หน่วยความจำแบบซีซีดี", null, getIndexByName(Computer_Category));
        wordDAO.addWord("CD (compact disc)", "แผ่นซีดี", null, getIndexByName(Computer_Category));
        wordDAO.addWord("CD-ROM", "ซีดีรอม", "ซีดีรอม", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("central processing unit (CPU)", "หน่วยประมวลผลกลาง (ซีพียู)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("central processor", "ตัวประมวลผลกลาง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("CEO (chief executive officer)", "ซีอีโอ (ประธานบริหาร)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("clear", "ว่าง; ทำให้ว่าง, ลบล้าง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("chain", "๑. โยง\n" +"๒. ลูกโซ่, สายโซ่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("chaining search", "การค้นหาแบบลูกโซ่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("channel", "๑. ช่อง, ช่องสัญญาณ\n" +"๒. แชนเนล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("character", "อักขระ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("character code", "รหัสอักขระ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("character recognition", "การรู้จำอักขระ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("character set", "ชุดอักขระ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("chat room", "ห้องคุย", null, getIndexByName(Computer_Category));
        wordDAO.addWord("check", "การตรวจสอบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("check bit", "บิตตรวจสอบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("check character", "อักขระตรวจสอบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("check digit", "เลขโดดตรวจสอบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("check indicator", "เครื่องชี้บอกการตรวจสอบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("check number", "จำนวนตรวจสอบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("check register", "เรจิสเตอร์ตรวจสอบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("check sum", "ผลรวมตรวจสอบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("checkbox", "กล่องเลือก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("checkout", "การหาจุดบกพร่อง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("checkpoint", "จุดตรวจสอบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("chip", "ชิป", "ชิป", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("circular list", "รายการแบบวงกลม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("class", "คลาส [ใช้ในภาษาเชิงวัตถุ]", null, getIndexByName(Computer_Category));
        wordDAO.addWord("click", "(กด)คลิก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("client-server system", "ระบบรับ-ให้บริการ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("clip", "๑. เล็ม, ขริบ\n" +"๒. สิ่งบันทึก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("clip art", "กฤตศิลป์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("clipboard", "คลิปบอร์ด", null, getIndexByName(Computer_Category));
        wordDAO.addWord("clock", "นาฬิกา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("clock rate", "อัตราสัญญาณนาฬิกา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("clock signal", "สัญญาณนาฬิกา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("closed loop", "๑. วงวนปิด\n" +"๒. วงวนไม่รู้จบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("cluster", "กลุ่ม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("COBOL (Common Business Oriented Language)", "(ภาษา)โคบอล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("code", "๑. รหัส, รหัสคำสั่ง [สัญลักษณ์ที่ใช้แทนข้อมูลหรือคำสั่ง]\n" +"๒. คำสั่ง, โปรแกรม\n" +"๓. ลงรหัส\n" +"๔. เขียนโปรแกรม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("coder", "๑. ตัวลงรหัส\n" +"๒. ผู้ลงรหัส", null, getIndexByName(Computer_Category));
        wordDAO.addWord("column", "สดมภ์, แนวตั้ง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("COM (component object model)", "คอม (แบบจำลองอ็อบเจกต์ย่อย)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("COM (computer output on microfilm)", "ซีโอเอ็ม, คอม (ไมโครฟิล์มจากคอมพิวเตอร์)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("combinational circuit", "๑. วงจรเชิงผสม\n" +"๒. วงจรไร้ความจำ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("command", "คำสั่งงาน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("comment", "หมายเหตุ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("commercial language", "ภาษาเชิงพาณิชย์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("common gateway interface (CGI)", "โปรแกรมต่อประสานร่วมสำหรับเกตเวย์ (ซีจีไอ)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("common language", "ภาษาร่วม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("communication channel", "ช่องสื่อสาร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("communication device", "อุปกรณ์สื่อสาร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("compact disc (CD)", "แผ่นซีดี", null, getIndexByName(Computer_Category));
        wordDAO.addWord("comparator", "อุปกรณ์เปรียบเทียบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("compatible", "๑. เข้ากันได้, ใช้แทนกันได้\n" +"๒. แบบเดียวกับ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("compile", "แปลโปรแกรม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("compiler", "๑. ตัวแปลโปรแกรม\n" +"๒. โปรแกรมแปลโปรแกรม, คอมไพเลอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("complete carry", "เลขทดบริบูรณ์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("component", "ส่วนโปรแกรม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Computer", "คอมพิวเตอร์, คณิตกรณ์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("computer algebra", "พีชคณิตคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("computer audit", "การตรวจสอบบัญชีด้วยคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("computer code", "รหัสคอมพิวเตอร์ [", null, getIndexByName(Computer_Category));
        wordDAO.addWord("computer engineering", "วิศวกรรมคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("computer graphics", "วิชาเรขภาพคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("computer hacker; hacker", "นักเลงคอมพิวเตอร์, เซียนคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("computer language", "ภาษาเครื่องคอมพิวเตอร์ [มีความหมายเหมือนกับ machine language]", null, getIndexByName(Computer_Category));
        wordDAO.addWord("computer network", "ข่ายงานคอมพิวเตอร์, เครือข่ายคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("computer operation", "การดำเนินการคอมพิวเตอร์, การปฏิบัติการคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("computer science", "วิทยาการคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("computer system", "ระบบคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("computing", "๑. การคำนวณ, การคณนา [มีความหมายเหมือนกับ computation]\n" +"๒. -คอมพิวเตอร์\n" +"๓. การคอมพิวเตอร์\n" +"๔. วิชาการคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("conditional", "มีเงื่อนไข", null, getIndexByName(Computer_Category));
        wordDAO.addWord("conditional breakpoint", "จุดพักมีเงื่อนไข", null, getIndexByName(Computer_Category));
        wordDAO.addWord("configuration", "โครงแบบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("conjunction", "การเชื่อม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("connective", "ตัวเชื่อม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("connectivity", "ภาวะเชื่อมต่อ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("connector", "๑. สัญลักษณ์เชื่อมต่อ [ใช้ในการเขียนผังงาน]\n" +"๒. ตัวเชื่อมต่อ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("console", "๑. ส่วนเฝ้าคุม\n" +"๒. จอเฝ้าคุม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("constructor", "ตัวสร้าง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("context", "บริบท", null, getIndexByName(Computer_Category));
        wordDAO.addWord("control bus", "บัสควบคุม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("control language", "ภาษาควบคุม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("Control key; Ctrl key", "แป้นควบคุม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("control loop", "วงควบคุม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("control register", "เรจิสเตอร์ควบคุม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("control stack", "กองซ้อนควบคุม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("control statement", "ข้อความสั่งควบคุม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("controller", "๑. อุปกรณ์ควบคุม, เครื่องควบคุม, ตัวควบคุม\n" +"๒. โปรแกรมควบคุม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("copy", "สำเนา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("counter", "๑. ตัวนับ, เครื่องนับ, อุปกรณ์นับ\n" +"๒. โปรแกรมนับ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("cursor", "ตัวชี้ตำแหน่ง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("cyber", "ไซเบอร์", "ไซเบอร์", getIndexByName(Computer_Category));//TODO
        wordDAO.addWord("cycle", "วัฏจักร, รอบ, วง", null, getIndexByName(Computer_Category));
        //TODO D
        wordDAO.addWord("DAC (digital-to-analog converter)", "ดีเอซี (ตัวแปลงดิจิทัลเป็นแอนะล็อก, ตัวเปลี่ยนดิจิทัลเป็นแอนะล็อก)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("daemon", "ดีมอน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data", "ข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data abstraction", "การกำหนดสาระสำคัญของข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data administrator", "ผู้บริหารข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data area", "พื้นที่ข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data bus", "บัสข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data center", "ศูนย์ข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data channel", "ช่องสัญญาณข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data communications", "การสื่อสารข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data definition", "การนิยามข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data dictionary", "พจนานุกรมข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data logger", "ตัวลงบันทึกข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data network", "ข่ายงานข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data source", "อุปกรณ์ส่งข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data structure", "โครงสร้างข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data transfer", "การถ่ายโอนข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data transfer rate", "อัตราถ่ายโอนข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("data transmission", "การส่งข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("database", "ฐานข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("dataflow computer", "คอมพิวเตอร์แบบกระแสข้อมูล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("datagram", "เดทาแกรม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("DBMS (database management system)", "ดีบีเอ็มเอส (ระบบจัดการฐานข้อมูล)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("deadlock", "ติดตาย", null, getIndexByName(Computer_Category));
        wordDAO.addWord("debug", "แก้จุดบกพร่อง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("debugger", "โปรแกรมตรวจแก้จุดบกพร่อง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("decay time", "ช่วงเวลาลดระดับ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("decimal", "๑. ฐานสิบ\n" +"๒. ทศนิยม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("decision tree", "(รูป)ต้นไม้การตัดสินใจ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("declaration", "การประกาศ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("decode", "ถอดรหัส", null, getIndexByName(Computer_Category));
        wordDAO.addWord("decoder", "๑. ตัวถอดรหัส\n" +"๒. ผู้ถอดรหัส", null, getIndexByName(Computer_Category));
        wordDAO.addWord("decompiler", "โปรแกรมแปลกลับ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("decrement", "ส่วนลด", null, getIndexByName(Computer_Category));
        wordDAO.addWord("decryption", "การถอดรหัสลับ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("default", "๑. โดยปริยาย\n" +"๒. ค่าโดยปริยาย [มีความหมายเหมือนกับ default value]", null, getIndexByName(Computer_Category));
        wordDAO.addWord("define", "นิยาม, กำหนด", null, getIndexByName(Computer_Category));
        wordDAO.addWord("definition", "๑. การนิยาม, การกำหนด\n" +"๒. บทนิยาม\n" +"๓. ความชัด", null, getIndexByName(Computer_Category));
        wordDAO.addWord("degree", "ดีกรี", null, getIndexByName(Computer_Category));
        wordDAO.addWord("depth-first search", "การค้นหาในแนวลึก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("desktop", "๑. -ตั้งโต๊ะ\n" +"๒. เดสก์ท็อป", null, getIndexByName(Computer_Category));
        wordDAO.addWord("desktop computer", "คอมพิวเตอร์แบบตั้งโต๊ะ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("detail flowchart", "ผังงานละเอียด", null, getIndexByName(Computer_Category));
        wordDAO.addWord("detection", "การตรวจหา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("development system", "ระบบช่วยการพัฒนา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("development tool", "เครื่องมือช่วยการพัฒนา", null, getIndexByName(Computer_Category));
        wordDAO.addWord("diagnosis", "การวินิจฉัย", null, getIndexByName(Computer_Category));
        wordDAO.addWord("dialog; dialogue", "คำโต้ตอบ", null, getIndexByName(Computer_Category));
        wordDAO.addWord("difference", "ผลต่าง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("digit", "เลขโดด", null, getIndexByName(Computer_Category));
        wordDAO.addWord("digital", "เชิงเลข, ดิจิทัล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("digital adder", "วงจรบวกเชิงเลข", null, getIndexByName(Computer_Category));
        wordDAO.addWord("digital clock", "นาฬิกาแบบดิจิทัล", null, getIndexByName(Computer_Category));
        wordDAO.addWord("digital computer", "คอมพิวเตอร์เชิงเลข, ดิจิทัลคอมพิวเตอร์", null, getIndexByName(Computer_Category));
        wordDAO.addWord("direct access", "๑. ตัวแปลงเป็นดิจิทัล\n" +"๒. เครื่องอ่านพิกัด, ตัวแปลงเป็นเลข [มีความหมายเหมือนกับ data tablet; tablet]", null, getIndexByName(Computer_Category));
        wordDAO.addWord("direct access method (DAM)", "วิธีเข้าถึงโดยตรง (แดม)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("direct file", "แฟ้มตรง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("direct memory access (DMA)", "การเข้าถึงหน่วยความจำโดยตรง (ดีเอ็มเอ)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("disable", "ปิดทาง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("disk; disc", "จาน, จานบันทึก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("disk drive", "หน่วยขับจาน, หน่วยขับจานบันทึก, หน่วยขับแผ่นบันทึก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("diskette; floppy disk", "แผ่นบันทึก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("display", "๑. แสดงผล\n" +"๒. หน่วยแสดงผล\n" +"๓. ผลแสดง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("divider", "๑. วงจรหาร\n" +"๒. ตัวหาร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("DNS (domain name server)", "ดีเอ็นเอส (เครื่องบริการชื่อโดเมน)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("document", "เอกสาร", null, getIndexByName(Computer_Category));
        wordDAO.addWord("domain", "เขต, โดเมน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("domain name address", "ที่อยู่โดเมน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("domain name server (DNS)", "เครื่องบริการชื่อโดเมน (ดีเอ็นเอส)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("DOS (disk operating system)", "ดอส (ระบบปฏิบัติการแบบใช้จาน)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("double-click", "(กด)คลิกคลิก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("downlink", "๑. เชื่อมโยงลง\n" +"๒. การเชื่อมโยงลง\n" +"๓. ข่ายการเชื่อมโยงลง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("download", "บรรจุลง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("downtime", "ช่วงเวลาเครื่องไม่ทำงาน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("drag", "ลาก", null, getIndexByName(Computer_Category));
        wordDAO.addWord("drag-and-drop", "ลากแล้วปล่อย", null, getIndexByName(Computer_Category));
        wordDAO.addWord("DRAM (dynamic RAM)", "ดีแรม (แรมพลวัต)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("drum printer", "เครื่องพิมพ์แบบดรัม", null, getIndexByName(Computer_Category));
        wordDAO.addWord("duplex", "สื่อสารสองทาง", null, getIndexByName(Computer_Category));
        wordDAO.addWord("duplex computer system", "ระบบคอมพิวเตอร์ควบคู่", null, getIndexByName(Computer_Category));
        wordDAO.addWord("duplex printing", "การพิมพ์สองด้าน", null, getIndexByName(Computer_Category));
        wordDAO.addWord("DVD (digital versatile disc)", "ดีวีดี (แผ่นดิจิทัลอเนกประสงค์)", null, getIndexByName(Computer_Category));
        wordDAO.addWord("dynamic RAM (DRAM)", "แรมพลวัต (ดีแรม)", null, getIndexByName(Computer_Category));


        wordDAO.addWord("abend; abnormal end", "การหยุดงานผิดปรกติ", null, getIndexByName(Math_Category));
        wordDAO.addWord("abort", "ยกเลิก", null, getIndexByName(Math_Category));
        wordDAO.addWord("absolute address; machine address", "เลขที่อยู่สัมบูรณ์", null, getIndexByName(Math_Category));
        wordDAO.addWord("absolute code", "รหัสสัมบูรณ์", null, getIndexByName(Math_Category));
        wordDAO.addWord("absolute loader", "โปรแกรมบรรจุสัมบูรณ์", null, getIndexByName(Math_Category));
        wordDAO.addWord("abstract data type (ADT)", "แบบชนิดข้อมูลนามธรรม", null, getIndexByName(Math_Category));
        wordDAO.addWord("abstract machine", "เครื่องเชิงนามธรรม", null, getIndexByName(Math_Category));
        wordDAO.addWord("abstraction", "การกำหนดสาระสำคัญ", null, getIndexByName(Math_Category));


        return true;
    }   // createWord


    public void createFavorite() {
        favoriteDAO = new FavoriteDAO(mContext);

        favoriteDAO.addFavorite(new Word(1, null, null, null, null));
        favoriteDAO.addFavorite(new Word(5, null, null, null, null));
        favoriteDAO.addFavorite(new Word(7, null, null, null, null));
        favoriteDAO.addFavorite(new Word(8, null, null, null, null));

    }
}   // class
