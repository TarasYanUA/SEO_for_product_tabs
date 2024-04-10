package adminPanel;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class CsCartSettings {
    public CsCartSettings(){super();}
    public SelenideElement button_Save = $(".btn.btn-primary.cm-submit");

    //Страница "Скачанные модули"
    private final SelenideElement menuAddons = $("a[href=\"#primary_main_menu_1_8_body\"]");
    private final SelenideElement sectionDownloadedAddons = $(By.id("addons_downloaded_add_ons"));
    private final SelenideElement menu_SeoTabsAddon = $("tr#addon_ab__seo_product_tabs button.btn.dropdown-toggle");
    private final SelenideElement section_SeoTabsSettings = $("div.nowrap a[href*='addon=ab__seo_product_tabs']");
    public SelenideElement menu_UniTheme = $("tr#addon_abt__unitheme2 button.btn.dropdown-toggle");
    public SelenideElement section_ThemeSettings = $("div.nowrap a[href$='abt__ut2.settings']");

    private final SelenideElement menuProducts = $("a[href='#primary_main_menu_1_4_body']");
    private final SelenideElement sectionProducts = $(By.id("products_products"));
    private final SelenideElement menuSettings = $(By.id("administration"));
    private final SelenideElement menu_General = $("a[href$='section_id=General']");
    private final SelenideElement sectionAppearance = $("a[href$='section_id=Appearance']");
    public SelenideElement setting_DisplayProductDetailsInTabs = $("#field___product_details_in_tab_288");

    //Веб-сайт -- Темы
    private final SelenideElement menu_Website = $("a[href=\"#primary_main_menu_1_7_body\"]");
    private final SelenideElement section_Themes = $(By.id("website_themes"));
    public SelenideElement button_ActivateTheme = $("a[href*='style=Bright_theme']");

    //Веб-сайт -- Вкладки товара
    public SelenideElement tabName_Description = $(".cm-sortable-id-1 a");
    public SelenideElement tabName_Features = $(".cm-sortable-id-2 a");
    public SelenideElement tabName_Tags = $(".cm-sortable-id-4 a");
    public SelenideElement tabName_Reviews = $(".cm-sortable-id-9 a");
    public SelenideElement tabName_RequiredProducts = $(".cm-sortable-id-10 a");
    private final SelenideElement field_Name = $("input[id*='elm_description_tab']");
    public SelenideElement tab_SeoForProductTabs = $("li[id*='ab__spt_tab']");
    public SelenideElement setting_ShowTabOnFloatingPanel = $("input[id*='elm_ab__spt_add_tab_to_floating_panel_tab']");
    public SelenideElement setting_ActivateSettings = $("input[id*='elm_ab__spt_activate_settings_tab']");
    private final SelenideElement field_TabHeader = $("input[id*='elm_ab__spt_tab_header_tab']");
    public SelenideElement button_SaveTab = $(".buttons-container-picker input.btn.btn-primary");


    public ProductSettings navigateToProductListPage(){
        menuProducts.scrollIntoView(true).click();
        sectionProducts.click();
        return new ProductSettings();
    }
    public void navigateToAppearanceSettings(){
        menuSettings.click();
        menu_General.click();
        sectionAppearance.click();
    }
    public void navigateToProductTabs(){
        String url = WebDriverRunner.getWebDriver().getCurrentUrl();
        String[] split = url.split("\\?");
        String mainUrl = split[0]; //получили ссылку
        String sectionProductTabs = mainUrl + "?dispatch=tabs.manage";
        open(sectionProductTabs);
    }
    public void navigateToDesignThemes(){
        menu_Website.click();
        section_Themes.click();
    }
    public void navigateToAddonsPage(){
        menuAddons.scrollIntoView(true).click();
        sectionDownloadedAddons.click();
    }
    public SeoTabsSettings navigateToSeoTabsSettings(){
        menu_SeoTabsAddon.click();
        section_SeoTabsSettings.click();
        return new SeoTabsSettings();
    }
    public UniThemeSettings navigateToThemeSettings(){
        menu_UniTheme.click();
        section_ThemeSettings.click();
        return new UniThemeSettings();
    }
    public void clickAndType_TabName(String value){
        field_Name.click();
        field_Name.clear();
        field_Name.setValue(value);
    }
    public void clickAndType_TabHeader(String value){
        field_TabHeader.click();
        field_TabHeader.clear();
        field_TabHeader.setValue(value);
    }
    public void shiftBrowserTab(int tabNumber){
        switchTo().window(tabNumber);
    }
}