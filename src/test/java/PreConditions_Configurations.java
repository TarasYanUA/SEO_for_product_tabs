import adminPanel.CsCartSettings;
import adminPanel.ProductSettings;
import adminPanel.SeoTabsSettings;
import org.testng.annotations.Test;
import utils.Utils;

import static com.codeborne.selenide.Selenide.$;

/*
Устанавливаем следующие настройки:
- Настройки -> Внешний вид -> Показывать информацию о товаре во вкладках -- Нет
- Настройки модуля:
    * Добавить навигационную панель вкладок на странице товара -- Да
    * Позиция навигационной панели вкладок -- Перед вкладками товара
- Настраиваем вкладки товара
- Настраиваем страницу товара с опциями "Wii U DELUXE":
    * шаблон "Большая картинка, плоский"
    * вписываем значение в поле модуля "AB: Краткое название товара"
    * добавляем тег
    * добавляем обязательный товар
*/

public class PreConditions_Configurations extends TestRunner {
    @Test
    public void setConfigurations() {
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateTo_AppearanceSettings();
        csCartSettings.disableSetting_DisplayProductDetailsInTabs();

        //Настраиваем настройки модуля
        SeoTabsSettings seoTabsSettings = csCartSettings.navigateTo_SeoTabsSettings();
        seoTabsSettings.tab_Settings.click();
        Utils.setCheckboxState(seoTabsSettings.setting_AddNavigationPanel, true);
        seoTabsSettings.setting_PositionOfNavigationPanel.selectOptionByValue("before_tabs");
        seoTabsSettings.button_SaveSettings.click();

        //Настраиваем вкладки товара
        csCartSettings.navigateTo_ProductTabs();
        csCartSettings.tabName_Description.click();
        setProductTab("Описание (Показывать содержимое вкладки)", "Описание [product]");
        csCartSettings.tabName_Features.click();
        setProductTab("Особенности (Показывать содержимое вкладки)", "Особенности товара [product]");
        csCartSettings.tabName_Tags.click();
        setProductTab("Теги (Показывать содержимое вкладки)", "Теги для [product]");
        csCartSettings.tabName_Reviews.click();
        setProductTab("Отзывы (Показывать содержимое вкладки)", "Отзывы [<]о [product][>] от реальных покупателей");
        csCartSettings.tabName_RequiredProducts.click();
        setProductTab("Обязательные товары", "[tab_name]");

        //Настраиваем товар
        ProductSettings productSettings = csCartSettings.navigateTo_ProductListPage();
        if (productSettings.closeNotificationWindowOfCore.exists())
            productSettings.closeNotificationWindowOfCore.click();
        productSettings.goToEditingProductPage("X-Box");
        productSettings.productTemplate.selectOptionByValue("abt__ut2_bigpicture_flat_template");
        productSettings.tab_Addons.scrollIntoCenter().click();
        productSettings.field_ShortName.setValue("ShortName");
        productSettings.tab_Tags.scrollIntoCenter().click();
        productSettings.clickAndType_TagName("Sport");
        productSettings.tab_RequiredProducts.scrollIntoCenter().click();
        if (!$(".cm-object-picker-object.object-picker__selection-extended").exists()) {
            productSettings.button_Picker.click();
            Utils.waitForDialogWindowToAppear();
            productSettings.pickAProduct.click();
            productSettings.button_AddProductsAndClose.click();
        }
        CsCartSettings.button_Save.click();
    }

    public void setProductTab(String name, String header) {
        CsCartSettings csCartSettings = new CsCartSettings();
        Utils.waitForDialogWindowToAppear();
        csCartSettings.field_Name.setValue(name);
        csCartSettings.tab_SeoForProductTabs.click();
        Utils.setCheckboxState(csCartSettings.setting_ShowTabOnFloatingPanel, true);
        Utils.setCheckboxState(csCartSettings.setting_ActivateSettings, true);
        csCartSettings.field_TabHeader.setValue(header);
        csCartSettings.button_SaveTab.click();
    }
}