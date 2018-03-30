package org.lynn.springboot2.exploration.repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.lynn.springboot2.exploration.domain.User;
import org.springframework.stereotype.Repository;

/**
 * {@link User} {@link Repository}
 */
@Repository
public class UserRepository {
  /**
   * 采用内存型的存储方式-> Map
   */
  private final ConcurrentMap<Integer, User> repository = new ConcurrentHashMap<>();

  private final static AtomicInteger idGenerator = new AtomicInteger();

  /**
   * 保存用户对象
   * @param user {@link User} 对象
   * @return 如果保存成功，返回<code>true</code>，
   *          否则，返回<code>false</code>
   */
  public boolean save(User user)
  {
    Integer id = idGenerator.incrementAndGet();
    user.setId(id);
    return repository.put(id, user) == null;
  }

  /**
   * 返回所有用户
   * @return
   */
  public Collection<User> findAll()
  {
    return repository.values();
  }
}
