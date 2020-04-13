package cn.jxj4869.message.service;

import cn.jxj4869.message.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jxj4869
 * @since 2020-04-12
 */
public interface IUserService extends IService<User> {

    public boolean findByEmail(String email);

    public String generatorCheckCode(String email);

    public boolean findByUserName(String userName);

    public boolean register(User user);

    public User login(String userName,String password);
}
