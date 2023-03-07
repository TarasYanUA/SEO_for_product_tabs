package adminPanel;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import storefront.ProductPage;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CsCartSettings {
    public CsCartSettings(){super();}
    public SelenideElement button_Save = $(".btn.btn-primary.cm-submit");
    public SelenideElement popupWindow = $(".ui-dialog-title");

    public SelenideElement menuAddons = $("#elm_menu_addons");
    public SelenideElement sectionDownloadedAddons = $("#elm_menu_addons_downloaded_add_ons");
    public SelenideElement menuProducts = $x("//li[@class='dropdown nav__header-main-menu-item ']//a[@href='#products']");
    public SelenideElement sectionProducts = $x("//span[text()='Товары']");
    public SelenideElement gearWheelOnTop = $(".dropdown-icon--tools");
    public SelenideElement button_Preview = $x("//a[contains(text(), 'Предпросмотр')]");
    public SelenideElement button_ViewProducts = $("a[href*='products.manage&cid']");
    public SelenideElement menuSettings = $(".dropdown-toggle.settings");
    public SelenideElement sectionAppearance = $("#elm_menu_settings_Appearance");
    public SelenideElement setting_DisplayProductDetailsInTabs = $("#field___product_details_in_tab_288");
    public SelenideElement setting_QuickView = $("#field___enable_quick_view_290");
    public SelenideElement menuDesign = $("#elm_menu_design");
    public SelenideElement sectionProductTabs = $(By.id("elm_menu_design_product_tabs"));
    public SelenideElement menuOfStickerAddon = $("tr#addon_ab__stickers button.btn.dropdown-toggle"); //Для примера!!!!!
    public SelenideElement sectionStickerSettings = $("div.nowrap a[href*='addon=ab__stickers']"); //Для примера!!!!!


    public ProductSettings navigateToEditingProductPage(){
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
/*    public StickerSettings navigateToStickerListPage(){    //Для примера!!!!!
        menuOfStickerAddon.click();
        sectionStickerList.click();
        return new StickerSettings();
    }*/
    public ProductPage navigateToProductPage(int tabNumber){
        button_Save.click();
        Selenide.sleep(2000);
        gearWheelOnTop.click();
        button_Preview.click();
        getWebDriver().getWindowHandle(); switchTo().window(tabNumber);
        return new ProductPage();
    }
    public void cookieNotice(){
        $(".cookie-notice").shouldBe(Condition.interactable);
        $(".cm-btn-success").click();
    }
    public void shiftBrowserTab(int tabNumber){
        getWebDriver().getWindowHandle(); switchTo().window(tabNumber);
    }
}