/*
 * Copyright (c) 2018 Arcesium LLC. All rights reserved.
 *
 * This software is the confidential and proprietary information of Arcesium LLC. ("Confidential Information"). You
 * shall not disclose such Confidential Information and shall use it only in accordance with the terms of the license
 * agreement you entered into with Arcesium LLC.
 */
package ratefinder.service;

import java.util.List;

import ratefinder.bean.SearchEntry;

/**
 * @author somanip
 */
public interface RateScraper {

    List<SearchEntry> scrape(String keyword);
}
