package org.lacitysan.landfill.server.config;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.lacitysan.landfill.server.config.constant.ApplicationConstant;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.ImeRepairData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedImeData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.user.User;
import org.lacitysan.landfill.server.persistence.entity.user.UserGroup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author Alvin Quach
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

	@Value("${spring.datasource.driver-class-name}")
	private String className;

	@Value("${spring.datasource.url}")
	private String serverName;

	//	Database name is now defined in ApplicationProperty
	//	@Value("${spring.datasource.name}")
	//	private String databaseName;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.hikari.initialization-fail-fast}")
	private Boolean initializationFailFast;

	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());
	}

	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(getDataSource())
				.addAnnotatedClasses(

						// Instantaneous
						ImeData.class,
						ImeNumber.class,
						ImeRepairData.class,
						InstantaneousData.class,
						WarmspotData.class,

						// Instrument
						Instrument.class,
						InstrumentType.class,

						// Unverified
						UnverifiedDataSet.class,
						UnverifiedImeData.class,
						UnverifiedInstantaneousData.class,

						// User
						User.class,
						UserGroup.class

						)
				.buildSessionFactory();
	}

	@Bean
	public DataSource getDataSource() {
		HikariConfig dataSource = new HikariConfig();
		dataSource.setDataSourceClassName(className);
		dataSource.addDataSourceProperty("serverName", serverName);
		dataSource.addDataSourceProperty("databaseName", ApplicationConstant.DATABASE_NAME);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setInitializationFailFast(initializationFailFast);
		return new HikariDataSource(dataSource);
	}

	@Bean
	public HibernateTransactionManager hibernateTransactionManager(){
		return new HibernateTransactionManager(sessionFactory());
	}

}
