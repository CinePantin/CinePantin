package com.cinepantin.shop.service;

import com.cinepantin.shop.domain.Dvd;

/**
 * This marker interface solves Spring injection problems with type erasure.
 * 
 * @see http://stackoverflow.com/a/503011/326294
 *
 */
public interface DvdService extends ArticleService<Dvd> {

}
