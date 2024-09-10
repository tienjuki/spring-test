package com.comit.notjpa.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		   LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	        em.setDataSource(dataSource);
	        em.setPackagesToScan("com.comit.notjpa.entities");
	        
	        // Cấu hình thêm cho Hibernate
	        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	        em.getJpaPropertyMap().put("hibernate.hbm2ddl.auto", "none");
	        em.getJpaPropertyMap().put("hibernate.dialect", "org.hibernate.dialect.DB2Dialect"); // Thay đổi dialect phù hợp
	        return em;
	}

    @Bean
    public DataSource dataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.ibm.db2.jcc.DB2Driver");
        dataSource.setUrl("jdbc:db2://192.168.7.2:50000/test01");
        dataSource.setUsername("db2inst1");
        dataSource.setPassword("db2inst1");

        return dataSource;
    }

    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setBaselineOnMigrate(true); // Tự động tạo bảng metadata nếu không có
        flyway.setBaselineVersion("0");
        flyway.migrate(); // Thực hiện migration
        return flyway;
    }

}
