package adminPanel;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class CsCartSettings implements CheckMenuToBeActive {
    public CsCartSettings(){super();}
    public SelenideElement button_Save = $(".btn.btn-primary.cm-submit");

    public void shiftBrowserTab(int tabNumber){
        switchTo().window(tabNumber);
    }

    //Меню "Товары"
    private final SelenideElement menu_Products = $("a[href='#primary_main_menu_1_3_body']");
    private final SelenideElement section_Products = $(By.id("products_products"));


    public ProductSettings navigateTo_ProductListPage(){
        checkMenuToBeActive("dispatch=products.manage", menu_Products);
        section_Products.click();
        return new ProductSettings();
    }


    //Меню "Веб-сайт -- Темы"
    private final SelenideElement menu_Website = $("a[href='#primary_main_menu_1_6_body']");
    private final SelenideElement section_Themes = $(By.id("website_themes"));
    public SelenideElement button_ActivateTheme = $("a[href*='style=Bright_theme']");

    //Меню "Веб-сайт -- Темы -- Вкладки товара"
    SelenideElement section_ProductTabs = $("a[href$='dispatch=tabs.manage']");
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


    public void navigateTo_WebsiteThemes(){
        checkMenuToBeActive("dispatch=themes.manage", menu_Website);
        section_Themes.click();
    }

    public void navigateTo_ProductTabs(){
        navigateTo_WebsiteThemes();
        section_ProductTabs.click();
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


    //Меню "Модули -- Скачанные модули"
    private final SelenideElement menu_Addons = $("a[href=\"#primary_main_menu_1_7_body\"]");
    private final SelenideElement section_DownloadedAddons = $(By.id("addons_downloaded_add_ons"));
    private final SelenideElement gearwheelOfAddon_SeoTabsAddon = $("tr#addon_ab__seo_product_tabs button.btn.dropdown-toggle");
    private final SelenideElement section_SeoTabsSettings = $("div.nowrap a[href*='addon=ab__seo_product_tabs']");
    public SelenideElement gearwheelOfAddon_UniTheme = $("tr#addon_abt__unitheme2 button.btn.dropdown-toggle");
    public SelenideElement section_ThemeSettings = $("div.nowrap a[href$='abt__ut2.settings']");


    private void navigateTo_DownloadedAddonsPage(){
        checkMenuToBeActive("dispatch=addons.manage", menu_Addons);
        section_DownloadedAddons.click();
    }

    public SeoTabsSettings navigateTo_SeoTabsSettings(){
        navigateTo_DownloadedAddonsPage();
        gearwheelOfAddon_SeoTabsAddon.click();
        section_SeoTabsSettings.click();
        return new SeoTabsSettings();
    }

    public UniThemeSettings navigateToThemeSettings(){
        navigateTo_DownloadedAddonsPage();
        gearwheelOfAddon_UniTheme.click();
        section_ThemeSettings.click();
        return new UniThemeSettings();
    }


    //Меню "Настройки -- Общие настройки -- Внешний вид"
    private final SelenideElement menu_Settings = $(By.id("administration"));
    private final SelenideElement menu_General = $("a[href$='section_id=General']");
    private final SelenideElement section_Appearance = $("a[href$='section_id=Appearance']");
    public SelenideElement setting_DisplayProductDetailsInTabs = $("#field___product_details_in_tab_288");


    public void navigateTo_AppearanceSettings(){
        menu_Settings.click();
        menu_General.click();
        section_Appearance.click();
    }
}