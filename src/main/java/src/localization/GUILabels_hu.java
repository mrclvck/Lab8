package src.localization;

import java.util.ListResourceBundle;

public class GUILabels_hu extends ListResourceBundle {

    private static final Object[][] contents =
    {
            {"log_in", "BELÉPÉS"},
            {"login", "Bejelentkezés"},
            {"password", "Jelszó"},
            {"sign_up", "REGISZTRÁCIÓ"},
            {"sign_in", "Van már fiókod? Jelentkezz be"},
            {"auth", "Hitelesítés"},
            {"reg", "Regisztráció"},
            {"become", "Nincs fiókod? Regisztrálj"},
            {"already", "Már létezik"},
            {"invalid", "Érvénytelen bemenet"},
            {"add", "HOZZÁADÁS"},
            {"exit", "KILÉPÉS"},
            {"clear", "TÖRLÉS"},
            {"b_help", "SEGÍTSÉG"},
            {"info", "INFORMÁCIÓ"},
            {"map", "TÉRKÉP"},
            {"count", "SZÁMLÁLÁS TÍPUS SZERINT"},
            {"script", "SZKRIPT VÉGREHAJTÁSA"},
            {"remove_type", "TÖRLÉS TÍPUS ALAPJÁN"},
            {"remove_id", "TÖRLÉS AZONOSÍTÓ ALAPJÁN"},
            {"upd", "AZONOSÍTÓ ALAPJÁN FRISSÍTÉS"},
            {"username", "FELHASZNÁLÓNÉV"},
            {"vehicle", "Jármű"},
            {"name", "Cím"},
            {"creator", "Létrehozó"},
            {"coords", "Koordináták"},
            {"creation_date", "Létrehozás dátuma"},
            {"type", "Típus"},
            {"capacity", "Kapacitás"},
            {"ep", "Teljesítmény"},
            {"fc", "Kiadás"},
            {"all", "ÖSSZES"},
            {"script_help", """
                    add {elem} : új elem hozzáadása a gyűjteményhez
                    execute_script fájlnév : olvassa be és hajtsa végre a parancsot a megadott fájlból. A szkript parancsokat tartalmaz ugyanabban a formában, ahogy be vannak írva
                    clear : törli a gyűjteményt
                    update azonosító : frissítse a gyűjtemény elemének értékét, amelynek azonosítója megegyezik a megadottal
                    remove_by_id azonosító : eltávolít egy elemet a gyűjteményből az azonosítója alapján
                    remove_all_by_type típus : eltávolítja a gyűjteményből az összes elemet, amelynek a típus mezőjének értéke megegyezik a megadottal
                    """},
            {"help", """
                    add {elem} : új elem hozzáadása a gyűjteményhez
                    execute_script fájlnév : olvassa be és hajtsa végre a parancsot a megadott fájlból. A szkript parancsokat tartalmaz ugyanabban a formában, ahogy be vannak írva
                    clear : törli a gyűjteményt
                    update azonosító : frissítse a gyűjtemény elemének értékét, amelynek azonosítója megegyezik a megadottal
                    remove_by_id azonosító : eltávolít egy elemet a gyűjteményből az azonosítója alapján
                    count_greater_than_type típus : kiírja a típus mező értékének nagyobb, mint a megadott értékű elemek számát
                    help : rendelkezésre álló parancsokhoz tartozó súgó kiírása
                    exit : program leállítása (mentés nélkül)
                    remove_all_by_type típus : eltávolítja a gyűjteményből az összes elemet, amelynek a típus mezőjének értéke megegyezik a megadottal
                    info : információk kiírása a gyűjteményről (típus, inicializáció dátuma, elemek száma stb.)
                    """},
            {"separator", ","},
            {"date_format", "yyyy.MM.dd HH:mm:ss Z"},
            {"col_type", "Gyűjtemény típusa"},
            {"init_date", "Inicializáció dátuma"},
            {"amount", "Elemek száma"},
            {"delete_ps", "Csak azokat a járműveket törölheti, amelyeknek Ön a létrehozója"},
            {"doesnt_exist", "Nem létezik"},
            {"not_ur", "Nem a tied"},
            {"deleted", "Törölt járművek száma"},
            {"count_res", "A típusú járművek száma nagyobb, mint a megadott"},
            {"table", "ASZTAL"}
    };

    public Object[][] getContents() {
        return contents;
    }

}
