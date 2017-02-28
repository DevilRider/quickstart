package org.liang.db.conf;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "org.liang.**.domain.mapper")
public class MyBatisConfig implements TransactionManagementConfigurer {

    private static final String TYPE_ALIASES_PACKAGE = "org.liang.**.domain.entity";
    private static final String MAPPER_LOCATIONS = "classpath:/META-INF/db/mapper/*.xml";
    private static final String CONFIG_LOCATION = "classpath:/META-INF/db/mybatis-config.xml";

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage(TYPE_ALIASES_PACKAGE);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources(MAPPER_LOCATIONS));
        sessionFactory.setConfigLocation(new DefaultResourceLoader().getResource(CONFIG_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
