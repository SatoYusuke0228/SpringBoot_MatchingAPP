package net.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	AppConfig appConfig;

	/**
	 * 「css」や「image」配下のファイルにアクセスする際に
	 * セキュリティ認証する必要がないようにする設定
	 *
	 * ignoring()を使用することでアクセス制御を無視できる
	 */
	@Override
	public void configure(WebSecurity web) {

		web.ignoring()
				.antMatchers("/image/**", "/css/**", "/js/**");
	}

	/**
	 * トップページや商品詳細ページはログインしなくても見せたいページなので、
	 *  これらのページは引き続き誰でも自由にアクセスすることを可能にする
	 *
	 * @param  <code>.antMatchers("/hogehoge/**").authenticated()</code>
	 *         「"/hogehoge"のパスのページは全て認証が必要」と設定
	 *
	 * @param  <code>.formLogin().permitAll();</code>
	 *          ログインページをだれでも閲覧できるように設定
	 *
	 * @param  .loginProcessingUrl()
	 *         認証処理に移るためのURLを指定
	 *
	 * @param  .defaultSuccessUrl()
	 *         ログインに成功した場合に移動するページのURL
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				//誰でもアクセス可能なページ
				.antMatchers("/", "/index").permitAll()
				.antMatchers("/registration/**").permitAll()
				.antMatchers("/login-success").authenticated()
				.antMatchers("/admin/**").authenticated()
				.antMatchers("/user/**").authenticated();

		//ログインページを指定。
		//ログインページへのアクセスは全員許可する。
		http.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/authenticate")
				.usernameParameter("mail")     //HTMLファイルの[name="mail"]タグと同一
				.passwordParameter("password") //HTMLファイルの[name="password"]タグと同一
				.failureUrl("/login?error")
				.defaultSuccessUrl("/login-success")
				.permitAll()
				.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.permitAll();
	}

	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(appConfig.bcryptPasswordEncoder());
	}

	/**
	 * ログインユーザとパスワードを静的に登録しているmethod
	 **/
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("admin")
				.roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
