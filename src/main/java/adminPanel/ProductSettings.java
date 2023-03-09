package adminPanel;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import storefront.ProductPage;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProductSettings {
    public ProductSettings(){super();}
    public SelenideElement closeNotificationWindowOfCore = $(".cm-notification-close-ajax");
    public SelenideElement field_productSearch = $("#simple_search input");
    public SelenideElement button_SearchProduct = $(".advanced-search-field__search");
    public SelenideElement anyProductInSearchList = $(".products-list__image");
    public SelenideElement productTemplate = $("#elm_details_layout");
    public SelenideElement tab_Addons = $("li#addons");
    public SelenideElement field_ShortName = $("#elm_ab__spt_short_name");
    public SelenideElement tab_Tags = $("#tags");
    public SelenideElement field_TagName = $("#content_tags li .ui-widget-content");
    public SelenideElement tab_RequiredProducts = $("#required_products");
    public SelenideElement button_Picker = $(".object-picker__advanced--products span");
    public SelenideElement pickAProduct = $("#checkbox_id_232");
    public SelenideElement button_AddProductsAndClose = $("input[class='btn cm-process-items cm-dialog-closer btn-primary']");


    public SelenideElement gearWheelOnTop = $(".dropdown-icon--tools");
    public SelenideElement button_Preview = $x("//a[contains(text(), 'Предпросмотр')]");

    public void clickAndType_ShortName (String value){
        field_ShortName.click();
        field_ShortName.clear();
        field_ShortName.setValue(value);
    }
    public void clickAndType_TagName (String value){
        field_TagName.click();
        //Selenide.sleep(1500);
        field_TagName.setValue(value);
        field_TagName.sendKeys(Keys.ENTER);
    }
    public void goToEditingProductPage(String value){
        field_productSearch.click();
        field_productSearch.setValue(value);
        button_SearchProduct.click();
        Selenide.sleep(1500);
        anyProductInSearchList.click();
    }
    public ProductPage navigateToProductPage(int tabNumber){
        gearWheelOnTop.click();
        button_Preview.click();
        getWebDriver().getWindowHandle(); switchTo().window(tabNumber);
        return new ProductPage();
    }
}