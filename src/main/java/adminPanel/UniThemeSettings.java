package adminPanel;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class UniThemeSettings {
    public UniThemeSettings(){super();}
    public SelenideElement setting_TopStickyPanel = $(By.id("settings.abt__ut2.general.top_sticky_panel.enable.desktop"));
}
