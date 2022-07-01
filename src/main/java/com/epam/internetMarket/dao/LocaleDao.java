package com.epam.internetMarket.dao;

import com.epam.internetMarket.entity.Locale;

import java.util.List;

public interface LocaleDao {
    List<Locale> getAllLocales();
    String getLocaleShortNameById(Long id);
}
