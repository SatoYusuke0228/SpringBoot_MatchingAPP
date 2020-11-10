package net.admin;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

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
	 * @param  <code>.antMatchers("/admin/**").authenticated()</code>
	 *         「"/admin"のパスのページは全て認証が必要」と設定
	 *
	 * @param  .formLogin().permitAll();
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
				//.anyRequest().authenticated()
				//誰でもアクセス可能なページ
				.antMatchers("/", "/index").permitAll()
				.antMatchers("/registration/**").permitAll()
				.anyRequest().authenticated();
		//アクセスに権限が必要なぺージ
		//				.antMatchers("/login-success").authenticated()
		//				.antMatchers("/admin/**").authenticated()
		//				.antMatchers("/user/**").authenticated();

		//ログインページを指定。
		//ログインページへのアクセスは全員許可する。
		http.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/authenticate")
				.usernameParameter("userName") //HTML[name="userName"]と同一
				.passwordParameter("password") //HTML[name="password"]と同一
				.failureUrl("/login?error")
				.defaultSuccessUrl("/login-success")
				.permitAll()
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.permitAll();
	}

	/**
	 * ログインユーザとパスワードを静的に登録しているmethod
	 *
	 * @return
	 *「ユーザー名 = admin/パスワード = admin」でログインできるようユーザ情報をまとめた処理
	 */
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

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder());
	}
}
