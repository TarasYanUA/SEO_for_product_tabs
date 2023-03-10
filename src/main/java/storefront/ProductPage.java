package storefront;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {
    public ProductPage(){super();}
    public SelenideElement tabPanel = $(".ab-spt-anchors__wrap");
    public SelenideElement tab_Tags = $(".tab-list-title#tags");
}