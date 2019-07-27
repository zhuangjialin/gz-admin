package com.lam.gz.admin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.lam.gz.base.EnableBaseOper;
import com.lam.gz.common.EnableAccessFilter;
import com.lam.gz.impexp.EnableImpexp;
import com.lam.gz.log.EnableOperLog;
import com.lam.gz.mybatis.EnableMybatis;

/**
 * @author bo.liu01
 *
 */
@SpringBootApplication
@EnableMybatis
@EnableOperLog
@EnableAccessFilter
@EnableBaseOper
@EnableImpexp
public class AdminBootstrap {
	public static void main(String[] args) {

		new SpringApplicationBuilder(AdminBootstrap.class).web(true).run(args);
	}
}
