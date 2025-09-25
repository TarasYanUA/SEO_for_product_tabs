package utils;

import adminPanel.CsCartSettings;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class Utils {

    public static void setCheckboxState(SelenideElement checkbox, boolean shouldBeChecked) {
        if (checkbox.isSelected() != shouldBeChecked) {
            checkbox.scrollIntoCenter().click();
        }
    }

    public static void setCheckboxAndSave(SelenideElement checkbox, boolean shouldBeChecked) {
        if (checkbox.isSelected() != shouldBeChecked) {
            checkbox.scrollIntoCenter().click();
            CsCartSettings.button_Save.click();
        }
    }

    public static void waitForDialogWindowToAppear() {
        $(".ui-dialog-title").shouldBe(Condition.enabled, Duration.ofSeconds(8));
    }

    public static void closeCookieNoticeIfExists() {
        if ($(".cookie-notice").exists())
            $(".cm-btn-success").shouldBe(Condition.clickable).click();
    }

    public static void selectLanguage_RU() {
        $("a[id*='_wrap_language_']").scrollIntoCenter().click();
        $(".ty-select-block__list-item a[data-ca-name='ru']").click();
    }

    public static void refreshPage(){
        Selenide.refresh();
        Selenide.sleep(2000);
    }
}