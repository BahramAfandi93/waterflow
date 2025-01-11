import com.calcpro.flowmaster.service.CustomOAuth2AppUserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val customOAuth2AppUserService: CustomOAuth2AppUserService
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { requests ->
                requests
                    .antMatchers("/", "/login**", "/oauth2/**").permitAll()
                    .anyRequest().authenticated()
            }
            .oauth2Login {
                it.userInfoEndpoint { userInfo ->
                    userInfo.userService(customOAuth2AppUserService)
                }
                it.defaultSuccessUrl("/dashboard", true) // Redirect after successful login
            }
            .logout { logout ->
                logout.logoutSuccessUrl("/").permitAll()
            }
        return http.build()
    }
}