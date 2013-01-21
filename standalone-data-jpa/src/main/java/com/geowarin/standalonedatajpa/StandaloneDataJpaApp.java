package com.geowarin.standalonedatajpa;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Geoffroy Warin (https://github.com/geowarin)
 *
 */
public class StandaloneDataJpaApp {

	private static final String CONFIG_PACKAGE = "com.geowarin.standalonedatajpa.config";

	public static void main(String[] args) {
		
		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext()) {
			
			ctx.scan(CONFIG_PACKAGE);
			ctx.refresh();
			
			MainBean bean = ctx.getBean(MainBean.class);
			bean.start();
		}
	}
}
