package com.itclj.common;

import com.alibaba.druid.pool.DruidDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * hive数据源配置
 * Create by lujun.chen on 2018/07/04
 */
@Configuration
public class HiveDataSource {
  @Autowired
  private Environment env;

  @Bean(name = "hiveJdbcDataSource")
  @Qualifier("hiveJdbcDataSource")
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setUrl(env.getProperty("hive.url"));
    dataSource.setDriverClassName(env.getProperty("hive.driver-class-name"));
    dataSource.setUsername(env.getProperty("hive.username"));
    dataSource.setPassword(env.getProperty("hive.password"));
    return dataSource;
  }

  @Bean(name = "hiveJdbcTemplate")
  public JdbcTemplate hiveJdbcTemplate(@Qualifier("hiveJdbcDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

}
