import adminPanel.CsCartSettings;
import adminPanel.ProductSettings;
import adminPanel.SeoTabsSettings;
import adminPanel.UniThemeSettings;
import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.$;

/*
Устанавливаем следующие настройки:
- Настройки -> Внешний вид -> Показывать информацию о товаре во вкладках -- Нет
- Настройки модуля:
    * Добавить навигационную панель вкладок на странице товара -- Да
    * Позиция навигационной панели вкладок -- Перед вкладками товара
- Настройки темы Юни2:
    * Верхняя липкая панель -- Да
- Настраиваем вкладки товара
- Настраиваем страницу товара с опциями "Wii U DELUXE":
    * шаблон "Большая картинка, плоский"
    * вписываем значение в поле модуля "AB: Краткое название товара"
    * добавляем тег
    * добавляем обязательный товар
*/

public class PreConditions_Configurations extends TestRunner{
    @Test
    public void setConfigurations() {
        //Настраиваем CS-Cart настройки
        CsCartSettings csCartSettings = new CsCartSettings();
        csCartSettings.navigateToAppearanceSettings();
        if(csCartSettings.setting_DisplayProductDetailsInTabs.isSelected()) {
            csCartSettings.setting_DisplayProductDetailsInTabs.click();
            csCartSettings.button_Save.click();
        }

        //Настраиваем настройки модуля
        csCartSettings.navigateToAddonsPage();
        SeoTabsSettings seoTabsSettings = csCartSettings.navigateToSeoTabsSettings();
        seoTabsSettings.tab_Settings.click();
        if(!seoTabsSettings.setting_AddNavigationPanel.isSelected()){
            seoTabsSettings.setting_AddNavigationPanel.click();
        }
        seoTabsSettings.setting_PositionOfNavigationPanel.selectOptionByValue("before_tabs");
        seoTabsSettings.button_SaveSettings.click();

        //Настраиваем вкладки товара
        csCartSettings.navigateToProductTabs();
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
        ProductSettings productSettings = csCartSettings.navigateToProductListPage();
        if(productSettings.closeNotificationWindowOfCore.exists()){
            productSettings.closeNotificationWindowOfCore.click(); }
        productSettings.goToEditingProductPage("Wii U DELUXE");
        productSettings.productTemplate.selectOptionByValue("abt__ut2_bigpicture_flat_template");
        productSettings.tab_Addons.scrollIntoView("{behavior: \"instant\", block: \"center\", inline: \"center\"}").click();
        productSettings.clickAndType_ShortName("ShortName");
        productSettings.tab_Tags.click();
        productSettings.clickAndType_TagName("Sport");
        productSettings.tab_RequiredProducts.click();
        if(!$(".cm-object-picker-object.object-picker__selection-extended").exists()){
            productSettings.button_Picker.click();
            $(".ui-dialog-title").shouldBe(Condition.enabled);
            productSettings.pickAProduct.click();
            productSettings.button_AddProductsAndClose.click();
        }
        csCartSettings.button_Save.click();
    }

    public void setProductTab (String name, String header){
        CsCartSettings csCartSettings = new CsCartSettings();
        $(".ui-dialog-title").shouldBe(Condition.enabled);
        csCartSettings.clickAndType_TabName(name);
        csCartSettings.tab_SeoForProductTabs.click();
        if(!csCartSettings.setting_ShowTabOnFloatingPanel.isSelected()){
        csCartSettings.setting_ShowTabOnFloatingPanel.click(); }
        if(!csCartSettings.setting_ActivateSettings.isSelected()){
        csCartSettings.setting_ActivateSettings.click(); }
        csCartSettings.clickAndType_TabHeader(header);
        csCartSettings.button_SaveTab.click();
    }
}