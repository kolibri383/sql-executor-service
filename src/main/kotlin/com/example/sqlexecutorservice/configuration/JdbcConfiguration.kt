package com.example.sqlexecutorservice.configuration

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer
import org.springframework.core.env.Environment
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.init.DataSourceInitializer
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.lang.Boolean
import javax.sql.DataSource
import kotlin.String


@Configuration
@ComponentScan
@EnableTransactionManagement
@PropertySource(value = ["classpath:application.yaml"])
class AppConfig {
    @Autowired
    private val env: Environment? = null

    @Value("\${init-db:false}")
    private val initDatabase: String? = null


    @Bean
    fun jdbcTemplate(dataSource: DataSource?): JdbcTemplate {
        return JdbcTemplate(dataSource!!)
    }

    @Bean
    fun transactionManager(dataSource: DataSource?): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource!!)
    }

    @Bean
    fun dataSource(): DataSource {
        val dataSource = HikariDataSource().apply {
            driverClassName = env?.getProperty("spring.datasource.driverClassName")
            jdbcUrl = env?.getProperty("spring.datasource.url")
            username = env?.getProperty("spring.datasource.username")
            password = env?.getProperty("spring.datasource.password")
        }
        return dataSource
    }

    @Bean
    fun dataSourceInitializer(dataSource: DataSource?): DataSourceInitializer {

        val databasePopulator = ResourceDatabasePopulator().apply { addScript(ClassPathResource("data.sql")) }
        val dataSourceInitializer = DataSourceInitializer().apply {
            setDataSource(dataSource!!)
            setDatabasePopulator(databasePopulator)
            setEnabled(Boolean.parseBoolean(initDatabase))
        }

        return dataSourceInitializer
    }

    companion object {
        @Bean
        fun placeHolderConfigurer(): PropertySourcesPlaceholderConfigurer {
            return PropertySourcesPlaceholderConfigurer()
        }
    }
}
