package org.lacitysan.landfill.server.config;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.lacitysan.landfill.server.persistence.entity.email.EmailRecipient;
import org.lacitysan.landfill.server.persistence.entity.instrument.Instrument;
import org.lacitysan.landfill.server.persistence.entity.instrument.InstrumentType;
import org.lacitysan.landfill.server.persistence.entity.probe.ProbeData;
import org.lacitysan.landfill.server.persistence.entity.scheduled.Schedule;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledEmail;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledNotification;
import org.lacitysan.landfill.server.persistence.entity.scheduled.ScheduledReport;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeNumber;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.ImeRepairData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.InstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.instantaneous.WarmspotData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IntegratedData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IseData;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IseNumber;
import org.lacitysan.landfill.server.persistence.entity.serviceemission.integrated.IseRepairData;
import org.lacitysan.landfill.server.persistence.entity.system.ApplicationSetting;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedDataSet;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedInstantaneousData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedIntegratedData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedProbeData;
import org.lacitysan.landfill.server.persistence.entity.unverified.UnverifiedWarmspotData;
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

	@Value("${spring.datasource.name}")
	private String databaseName;

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

						// System
						ApplicationSetting.class,
						
						// Email
						EmailRecipient.class,

						// Instantaneous
						ImeData.class,
						ImeNumber.class,
						ImeRepairData.class,
						InstantaneousData.class,
						WarmspotData.class,

						// Instrument
						Instrument.class,
						InstrumentType.class,

						// Integrated
						IntegratedData.class,
						IseData.class,
						IseNumber.class,
						IseRepairData.class,
						
						// Probe
						ProbeData.class,
						
						// Scheduled
						Schedule.class,
						ScheduledEmail.class,
						ScheduledNotification.class,
						ScheduledReport.class,
						
						// Unverified
						UnverifiedDataSet.class,
						UnverifiedInstantaneousData.class,
						UnverifiedIntegratedData.class,
						UnverifiedProbeData.class,
						UnverifiedWarmspotData.class,

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
		dataSource.addDataSourceProperty("databaseName", databaseName);
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
