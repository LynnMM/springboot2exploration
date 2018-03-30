package org.lynn.springboot2.exploration.config;

import java.util.Collection;
import org.lynn.springboot2.exploration.domain.User;
import org.lynn.springboot2.exploration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 路由器函数 配置
 */
@Configuration
public class RouterFunctionConfiguration {

  /**
   * Servlet
   * 请求接口: ServletRequest 或者 HttpServletRequest
   * 响应接口: ServletResponse 或者 HttpServletResponse
   * Spring 5.0 重新定义了服务请求和响应接口:
   * 请求接口: ServletRequest
   * 响应接口: ServletResponse
   * 即可支持Servlet规范，也可以支持自定义，比如Netty(Web Server)
   * 以本例：
   * 定义GET其你去，并且返回所有的用户对象 URi: /person/find/all
   * Flux 是0-N个对象集合
   * Mono 是0-1个对象集合
   * Reative中的Flux或者Mono 异步处理(非阻塞)
   * 其他集合对象基本上是同步处理(阻塞)
   * Flux或者Mono都是一个Publisher
   */

  @Bean
  @Autowired
  public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository){

    return RouterFunctions.route(RequestPredicates.GET("/person/find/all"), serverRequest -> {
      Collection<User> users = userRepository.findAll();
      Flux<User> userFlux = Flux.fromIterable(users);
      Mono<ServerResponse> responseMono = ServerResponse.ok().body(userFlux, User.class);
      return responseMono;
    });
  }
}
