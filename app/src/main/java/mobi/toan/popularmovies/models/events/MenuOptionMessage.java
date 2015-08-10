package mobi.toan.popularmovies.models.events;

/**
 * Created by toan on 8/3/15.
 */
public class MenuOptionMessage {
    public enum MENU_OPTIONS {
        BY_POPULARITY,
        BY_RATING,
        FAVOURITE
    }

    public MenuOptionMessage(MENU_OPTIONS option) {
        setMenuOption(option);
    }

    private MENU_OPTIONS mMenuOption;


    public MENU_OPTIONS getMenuOption() {
        return mMenuOption;
    }

    public void setMenuOption(MENU_OPTIONS menuOption) {
        mMenuOption = menuOption;
    }
}
