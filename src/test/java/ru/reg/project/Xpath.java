package ru.reg.project;

public enum Xpath {

    MARKET_ALL_CTGS_BTN("/html/body/div[1]/div[2]/div[1]/div/div[1]/div/button"),
    CHOOSE_COUNTRY_POPUP("body > div.popup.popup_animate_no.popup_theme_ffffff.popup_autoclosable_yes.popup_adaptive_yes.input__popup.input__popup_type_geo.input__popup_fade_yes.i-bem.popup_js_inited.popup_to_bottom");




    private String domString;

    Xpath(String s) {
        this.domString = s;
    }

    public String getDomString() {
        return domString;
    }
}
