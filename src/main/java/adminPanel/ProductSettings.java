package adminPanel;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import storefront.ProductPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ProductSettings {
    public ProductSettings() {
        super();
    }

    private final SelenideElement field_productSearch = $(".context-search__input");
    private final SelenideElement anyProductInSearchList = $(".products-list__image");
    public SelenideElement productTemplate = $("#elm_details_layout");
    public SelenideElement tab_Addons = $("li#addons");
    public SelenideElement field_ShortName = $("#elm_ab__spt_short_name");
    public SelenideElement tab_Tags = $("#tags");
    private final SelenideElement field_TagName = $("#content_tags li .ui-widget-content");
    public SelenideElement tab_RequiredProducts = $("#required_products");
    public SelenideElement button_Picker = $(".object-picker__advanced--products span");
    public SelenideElement pickAProduct = $("#checkbox_id_232");
    public SelenideElement button_AddProductsAndClose = $("input[class='btn cm-process-items cm-dialog-closer btn-primary']");
    private final SelenideElement gearWheelOnTop = $(".dropdown-icon--tools");
    private final SelenideElement button_Preview = $x("//a[contains(text(), 'Предпросмотр')]");


    public void clickAndType_TagName(String value) {
        field_TagName.setValue(value);
        field_TagName.sendKeys(Keys.ENTER);
    }

    public void goToEditingProductPage(String value) {
        field_productSearch.setValue(value);
        Selenide.sleep(3000);
        anyProductInSearchList.click();
    }

    public ProductPage navigateTo_ProductPage(int tabNumber) {
        gearWheelOnTop.click();
        button_Preview.click();
        getWebDriver().getWindowHandle();
        switchTo().window(tabNumber);
        return new ProductPage();
    }
}