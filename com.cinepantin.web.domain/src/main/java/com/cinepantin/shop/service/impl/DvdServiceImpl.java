package com.cinepantin.shop.service.impl;

import com.cinepantin.shop.domain.Dvd;
import com.cinepantin.shop.service.DvdService;

public class DvdServiceImpl
	extends ArticleServiceImpl<Dvd>
	implements DvdService
{

	public DvdServiceImpl(Dvd type) {
		super(type);
	}

}
