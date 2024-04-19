package tests;

import extensions.WithLogin;
import pages.ProfilePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Профиль")
public class ProfileTest extends TestBase {
    ProfilePage profilePage = new ProfilePage();

    @Test
    @WithLogin
    @DisplayName("Пустой список книг")
    void emptyBookListTest() {
        profilePage
                .openPage()
                .checkEmptyCellsInList()
                .checkEmptyListMessage();
    }
}
