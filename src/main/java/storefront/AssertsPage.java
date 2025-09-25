package storefront;

import org.testng.asserts.SoftAssert;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;

public class AssertsPage {
    public AssertsPage() {
        super();
    }

    SoftAssert softAssert = CollectAssertMessages.getSoftAssertions();

    public String productTabsPanel = ".ab-spt-floating-panel";
    public String productTabsPosition_BeforeProductTabs = ".ab-spt-floating-position-before_tabs";
    public String productTabsPosition_AfterH1 = ".ab-spt-floating-position-after_h1";

    public void assertElementExists(String selector) {
        Map<String, String> messages = Map.of(
                productTabsPanel, "There is no product tabs panel!",
                productTabsPosition_BeforeProductTabs,"Position of the product tabs panel is not before tabs!",
                productTabsPosition_AfterH1, "Position of the product tabs panel is not after H1 header!"
        );

        String message = messages.get(selector);
        if (message == null)
            throw new IllegalArgumentException("No assert found for selector: " + selector);

        softAssert.assertTrue($(selector).exists(), message);
    }

    public void assertShortNameExists() {
        String result = null;
        String expectedWord = "ShortName";
        String myString = $(".tab-list-title").getText();
        String[] couple = myString.split(" ");
        for (int i = 0; i < couple.length; i++) {
            if (couple[i].equals(expectedWord))
                result = couple[i];
        }
        softAssert.assertEquals(result, expectedWord, "There is no product short name!");
    }
}
