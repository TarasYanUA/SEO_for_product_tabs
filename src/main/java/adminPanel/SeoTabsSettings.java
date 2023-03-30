package adminPanel;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;

public class SeoTabsSettings {
    public SeoTabsSettings(){super();}
    public SelenideElement button_SaveSettings = $(".cm-addons-save-settings");
    public SelenideElement tab_Settings = $("#settings");
    public SelenideElement setting_AddNavigationPanel = $(By.cssSelector("input[id*='addon_option_ab__seo_product_tabs_add_tabs_fixed_panel']"));
    public SelenideElement setting_PositionOfNavigationPanel = $(By.cssSelector("select[id*='addon_option_ab__seo_product_tabs_tabs_fixed_panel_position']"));
}