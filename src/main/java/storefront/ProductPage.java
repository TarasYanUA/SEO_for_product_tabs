package storefront;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class ProductPage {
    public ProductPage() {
        super();
    }

    public SelenideElement tab_Panel = $(".ab-spt-anchors__wrap");
    public SelenideElement tab_Tags = $(".tab-list-title#tags");

    public void scrollToTab(SelenideElement element) {
        element.scrollIntoCenter();
        sleep(1500);
    }
}