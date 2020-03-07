package pe.project;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan
@EnableWebMvc
@EnableTransactionManagement
public class WebConfig extends WebMvcConfigurerAdapter
{

    @Bean //resuelve la ruta de los JSPs.
    public ViewResolver viewResolver() 
    {
        InternalResourceViewResolver resolver=new InternalResourceViewResolver();
        
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        
        return resolver;
    }
    
    @Bean //pool de conexiones c3p0 para aplicaciones de producción.
    public ComboPooledDataSource dataSource() throws PropertyVetoException 
    {
        ComboPooledDataSource ds=new ComboPooledDataSource();
        
        ds.setDriverClass("com.mysql.jdbc.Driver"); //Driver de la BD
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/db_projectAP?createDatabaseIfNotExist=true"); //conexión a la BD
        ds.setUser("root"); //nombre del usuario
        ds.setPassword("12345678"); //password del usuario
        
        ds.setMinPoolSize(10);
        ds.setMaxPoolSize(50);
        ds.setMaxStatements(10);
        
        ds.setTestConnectionOnCheckout(true);
        
        return ds;
    }
    
    @Bean //adaptador que transforma las cosas de JPA a Hibernate.
    public JpaVendorAdapter jpaVendorAdapter() 
    {
        HibernateJpaVendorAdapter va=new HibernateJpaVendorAdapter();
        
        va.setDatabase(Database.MYSQL); //BD elegida
        va.setShowSql(true);
        va.setGenerateDdl(true);
        va.setDatabasePlatform("org.hibernate.dialect.MySQLDialect"); //dialecto de la BD
                
        return va;
    }

    @Bean //permite crear objetos de tipo EntityManager.
    public LocalContainerEntityManagerFactoryBean entityManagerFactory
        (DataSource dataSource,JpaVendorAdapter jpaVendorAdapter) 
    {        
        LocalContainerEntityManagerFactoryBean emf=new LocalContainerEntityManagerFactoryBean();
        
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("pe.project.model.entity");
        
        return emf;
    }

    @Bean //permite administrar las transacciones de manera automática.
    public PlatformTransactionManager transactionManager
        (DataSource dataSource,EntityManagerFactory entityManagerFactory) 
    {
        JpaTransactionManager tm=new JpaTransactionManager();
        
        tm.setDataSource(dataSource);
        tm.setEntityManagerFactory(entityManagerFactory);
        
        return tm;
    }

    @Bean //permite usar la anotación @PersistenceContext de JPA.
    public PersistenceAnnotationBeanPostProcessor paPostProcessor() 
    {
        return new PersistenceAnnotationBeanPostProcessor();
    }

    @Bean //permite ver excepciones más limpias y entendibles.
    public BeanPostProcessor persistenceTranslation() 
    {
        return new PersistenceExceptionTranslationPostProcessor();
    }        

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) 
    {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }    
    
    //bean que especifica el tipo de cifrado
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder pe=new BCryptPasswordEncoder();
        return pe;
    }
}
