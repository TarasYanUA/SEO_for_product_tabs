package adminPanel;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import storefront.ProductPage;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CsCartSettings {
    public CsCartSettings(){super();}
    public SelenideElement button_Save = $(".btn.btn-primary.cm-submit");
    public SelenideElement popupWindow = $(".ui-dialog-title");

    //Страница "Скачанные модули"
    public SelenideElement menuAddons = $("#elm_menu_addons");
    public SelenideElement sectionDownloadedAddons = $("#elm_menu_addons_downloaded_add_ons");
    public SelenideElement menu_SeoTabsAddon = $("tr#addon_ab__seo_product_tabs button.btn.dropdown-toggle");
    public SelenideElement section_SeoTabsSettings = $("div.nowrap a[href*='addon=ab__seo_product_tabs']");
    public SelenideElement menu_UniTheme = $("tr#addon_abt__unitheme2 button.btn.dropdown-toggle");
    public SelenideElement section_ThemeSettings = $("div.nowrap a[href$='abt__ut2.settings']");

    public SelenideElement menuProducts = $x("//li[@class='dropdown nav__header-main-menu-item ']//a[@href='#products']");
    public SelenideElement sectionProducts = $x("//span[text()='Товары']");
    public SelenideElement gearWheelOnTop = $(".dropdown-icon--tools");
    public SelenideElement button_Preview = $x("//a[contains(text(), 'Предпросмотр')]");
    public SelenideElement menuSettings = $(".dropdown-toggle.settings");
    public SelenideElement sectionAppearance = $("#elm_menu_settings_Appearance");
    public SelenideElement setting_DisplayProductDetailsInTabs = $("#field___product_details_in_tab_288");

    //Меню -- Дизайн -- Вкладки товара
    public SelenideElement menuDesign = $("#elm_menu_design");
    public SelenideElement sectionProductTabs = $(By.id("elm_menu_design_product_tabs"));
    public SelenideElement tabName_Description = $(".cm-sortable-id-1 a");
    public SelenideElement tabName_Features = $(".cm-sortable-id-2 a");
    public SelenideElement tabName_Tags = $(".cm-sortable-id-4 a");
    public SelenideElement tabName_Reviews = $(".cm-sortable-id-9 a");
    public SelenideElement tabName_RequiredProducts = $(".cm-sortable-id-10 a");
    public SelenideElement field_Name = $("input[id*='elm_description_tab']");
    public SelenideElement tab_SeoForProductTabs = $("li[id*='ab__spt_tab']");
    public SelenideElement setting_ShowTabOnFloatingPanel = $("input[id*='elm_ab__spt_add_tab_to_floating_panel_tab']");
    public SelenideElement setting_ActivateSettings = $("input[id*='elm_ab__spt_activate_settings_tab']");
    public SelenideElement field_TabHeader = $("input[id*='elm_ab__spt_tab_header_tab']");
    public SelenideElement button_SaveTab = $(".buttons-container-picker input.btn.btn-primary");


    public ProductSettings navigateToProductListPage(){
        menuProducts.hover();
        sectionProducts.click();
        return new ProductSettings();
    }
    public void navigateToAppearanceSettings(){
        menuSettings.hover();
        sectionAppearance.click();
    }
    public void navigateToProductTabs(){
        menuDesign.hover();
        sectionProductTabs.click();
    }
    public void navigateToAddonsPage(){
        menuAddons.hover();
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
    public ProductPage navigateToProductPage(int tabNumber){
        button_Save.click();
        Selenide.sleep(2000);
        gearWheelOnTop.click();
        button_Preview.click();
        getWebDriver().getWindowHandle(); switchTo().window(tabNumber);
        return new ProductPage();
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
        getWebDriver().getWindowHandle(); switchTo().window(tabNumber);
    }
}