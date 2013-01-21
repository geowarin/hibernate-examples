package com.geowarin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class will create an hibernate {@link Configuration} with the given dialect and will scan provided
 * package for {@link MappedSuperclass} and {@link Entity}.
 * You can then use the export methods to generate your schema DDL. 
 * 
 * @author Geoffroy Warin (https://github.com/geowarin)
 *
 */
public class HibernateExporter {

	private static Logger log = LoggerFactory.getLogger(HibernateExporter.class);
	
	private String dialect;
	private String entityPackage;
	
	private boolean generateCreateQueries = true;
	private boolean generateDropQueries = false;

	private Configuration hibernateConfiguration;
	
	public HibernateExporter(String dialect, String entityPackage) {
		this.dialect = dialect;
		this.entityPackage = entityPackage;
		
		hibernateConfiguration = createHibernateConfig();
	}
	
	public void export(OutputStream out, boolean generateCreateQueries, boolean generateDropQueries) {
		
		Dialect hibDialect = Dialect.getDialect(hibernateConfiguration.getProperties());
		if (generateCreateQueries) {
			String[] createSQL = hibernateConfiguration.generateSchemaCreationScript(hibDialect);
			write(out, createSQL, FormatStyle.DDL.getFormatter());
		}
		if (generateDropQueries) {
			String[] dropSQL = hibernateConfiguration.generateDropSchemaScript(hibDialect);
			write(out, dropSQL, FormatStyle.DDL.getFormatter());
		}
	}

	public void export(File exportFile) throws IOException {
		
		try (FileOutputStream out = new FileOutputStream(exportFile)) {
			export(out, generateCreateQueries, generateDropQueries);
		}
	}
	
	public void exportToConsole() {
		
		export(System.out, generateCreateQueries, generateDropQueries);
	}
	
	private void write(OutputStream out, String[] lines, Formatter formatter) {
		
		try (PrintWriter writer = new PrintWriter(out)) {
			
			for (String string : lines)
				writer.println(formatter.format(string) + ";");
		}
	}

	private Configuration createHibernateConfig() {
		
		hibernateConfiguration = new Configuration();

		final Reflections reflections = new Reflections(entityPackage);
		for (Class<?> cl : reflections.getTypesAnnotatedWith(MappedSuperclass.class)) {
			hibernateConfiguration.addAnnotatedClass(cl);
			log.info("Mapped = " + cl.getName());
		}
		for (Class<?> cl : reflections.getTypesAnnotatedWith(Entity.class)) {
			hibernateConfiguration.addAnnotatedClass(cl);
			log.info("Mapped = " + cl.getName());
		}
		hibernateConfiguration.setProperty(AvailableSettings.DIALECT, dialect);
		return hibernateConfiguration;
	}
}
