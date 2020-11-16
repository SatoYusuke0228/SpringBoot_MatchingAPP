package net.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.common.Constant.RoleName;

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
	 * @param  <code>.loginProcessingUrl()</code>
	 *         認証処理に移るためのURLを指定
	 *
	 * @param  <code>.defaultSuccessUrl()</code>
	 *         ログインに成功した場合に移動するページのURL
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				//誰でもアクセス可能なページ
				.antMatchers("/", "/index").permitAll()
				.antMatchers("/registration/**").permitAll()
				//権限が必要なページ
				.antMatchers("/admin/**").hasRole(RoleName.ADMIN)
				.antMatchers("/user/employee/**").hasAnyRole(RoleName.ADMIN, RoleName.EMPLOYEE)
				.antMatchers("/user/employer/**").hasAnyRole(RoleName.ADMIN, RoleName.EMPLOYER)
				//他は全部ログインが必要なページに設定
				.anyRequest().authenticated();

		//ログインとログアウト処理の実装
		http.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/authenticate")
				.usernameParameter("mail") //HTMLファイルの[name="mail"]タグと同一
				.passwordParameter("password") //HTMLファイルの[name="password"]タグと同一
				//.failureHandler()
				.failureUrl("/login?error")
				.defaultSuccessUrl("/login-success")
				.permitAll();

		http.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login")
				.permitAll();
	}

	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(appConfig.bcryptPasswordEncoder());
	}
}
