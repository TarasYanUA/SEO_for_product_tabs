package adminPanel;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$;

public class ProductSettings {
    public ProductSettings(){super();}
    public SelenideElement field_productSearch = $("#simple_search input");
    public SelenideElement button_SearchProduct = $(".advanced-search-field__search");
    public SelenideElement productTemplate = $("#elm_details_layout");


    public Select getProductTemplate(){return new Select(productTemplate);}
    public void selectProductTemplate(String value){
        getProductTemplate().selectByValue(value);
    }
}