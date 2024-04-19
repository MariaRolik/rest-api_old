package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {
    private ElementsCollection listCells = $$("[role=gridcell]");
    private SelenideElement emptyMessage = $(".rt-noData");

    @Step("Открываем страницу профиля")
    public ProfilePage openPage() {
        open("/profile");
        return this;
    }

    @Step("Проверяем, что список книг в профиле пуст")
    public ProfilePage checkEmptyCellsInList() {
        for (SelenideElement cell : listCells) {
            cell.shouldHave(Condition.empty);
        }
        return this;
    }

    @Step("Проверяем текст сообщения о пустом списке книг")
    public ProfilePage checkEmptyListMessage() {
        emptyMessage.shouldHave(text("No rows found"));
        return this;
    }
}
