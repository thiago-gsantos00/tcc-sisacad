/**
 * Classe responsável pelas configurações da JPA, Pool de conexões e Hibernate 
 */

package br.pucminas.sisacad.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class JPAConfig {

	@Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPersistenceUnitName("sisacad");
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[]{"br.pucminas.sisacad"});
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaProperties(hibernateProperties());
		return em;
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
		properties.setProperty("hibernate.show_sql", "false");
		properties.setProperty("hibernate.format_sql", "true");
		return properties;
	}
	
	@Bean(name = "dataSource", destroyMethod = "close")
	public DataSource dataSource(){
		ResourceBundle resource = ResourceBundle.getBundle("jdbc");
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(resource.getString("jdbc.driverClassName"));
			dataSource.setJdbcUrl(resource.getString("jdbc.url"));
			dataSource.setUser(resource.getString("jdbc.username"));
			dataSource.setPassword(resource.getString("jdbc.password"));
			dataSource.setAcquireIncrement(Integer.parseInt(resource.getString("c3p0.acquireIncrement")));
			dataSource.setInitialPoolSize(Integer.parseInt(resource.getString("c3p0.initialPoolSize")));
			dataSource.setMinPoolSize(Integer.parseInt(resource.getString("c3p0.minPoolSize")));
			dataSource.setMaxPoolSize(Integer.parseInt(resource.getString("c3p0.maxPoolSize")));
			dataSource.setMaxIdleTime(Integer.parseInt(resource.getString("c3p0.maxIdleTime")));
			dataSource.setMaxStatements(Integer.parseInt(resource.getString("c3p0.maxStatements")));
			dataSource.setMaxConnectionAge(Integer.parseInt(resource.getString("c3p0.maxConnectionAge")));
			dataSource.setIdleConnectionTestPeriod(Integer.parseInt(resource.getString("c3p0.idleConnectionTestPeriod")));
			dataSource.setPreferredTestQuery(resource.getString("c3p0.preferredTestQuery"));
			return dataSource;
		} catch (PropertyVetoException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
}
