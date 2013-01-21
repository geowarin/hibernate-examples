package com.geowarin;

public class GenerateDdlApp {
	
	public static void main(String[] args) {

//		HibernateExporter exporter = new HibernateExporter("org.hibernate.dialect.HSQLDialect", "com.geowarin.model");
		HibernateExporter exporter = new HibernateExporter("org.hibernate.dialect.MySQL5Dialect", "com.geowarin.model");
		exporter.exportToConsole();
	}
}
