package adminPanel;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ProductSettings {
    public ProductSettings(){super();}
    public SelenideElement field_productSearch = $("#simple_search input");
    public SelenideElement button_SearchProduct = $(".advanced-search-field__search");
    public SelenideElement anyProductInSearchList = $(".products-list__image");
    public SelenideElement productTemplate = $("#elm_details_layout");


    public void goToEditingProductPage(String value){
        field_productSearch.click();
        field_productSearch.setValue(value);
        button_SearchProduct.click();
        Selenide.sleep(1500);
        anyProductInSearchList.click();
    }

/*    public Select getProductTemplate(){return new Select(productTemplate);}
    public void selectProductTemplate(String value){
        getProductTemplate().selectByValue(value);
    }
    */

}